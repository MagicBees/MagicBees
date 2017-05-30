package magicbees.main.utils.compat.thaumcraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;

public class NodeHelper {
	
	private static Map<NodeType, Float> conversionChances;
	
	static {
		conversionChances = new HashMap<NodeType,Float>();
		conversionChances.put(NodeType.NORMAL, 0.075f);
		conversionChances.put(NodeType.TAINTED, 0.057f);
		conversionChances.put(NodeType.PURE, 0.035f);
		conversionChances.put(NodeType.HUNGRY, 0.0094f);
	}

	@SuppressWarnings("unchecked")
	public static INode findNode(Chunk chunk, int x, int y, int z, int range) {
		Vec3 apiaryPos = Vec3.createVectorHelper(x, y, z);
		List<TileEntity> tileEntities = new ArrayList<TileEntity>(((Map<ChunkPosition, TileEntity>)chunk.chunkTileEntityMap).values());
		Collections.shuffle(tileEntities);
		for (TileEntity entity : tileEntities) {
			if (entity instanceof INode) {
				Vec3 tePos = Vec3.createVectorHelper(entity.xCoord, entity.yCoord, entity.zCoord);
				Vec3 result = apiaryPos.subtract(tePos);
				if (result.lengthVector() <= range) {
					return (INode)entity;
				}
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<INode> findNodesInChunkWithinRange(Chunk chunk, int x, int y, int z, int range) {
		List<INode> nodes = new ArrayList<INode>();
		Vec3 apiaryPos = Vec3.createVectorHelper(x, y, z);
		List<TileEntity> tileEntities = new ArrayList<TileEntity>(((Map<ChunkPosition, TileEntity>)chunk.chunkTileEntityMap).values());
		for (TileEntity entity : tileEntities) {
			if (entity instanceof INode) {
				Vec3 tePos = Vec3.createVectorHelper(entity.xCoord, entity.yCoord, entity.zCoord);
				Vec3 result = apiaryPos.subtract(tePos);
				if (result.lengthVector() <= range) {
					nodes.add((INode)entity);
				}
			}
		}
		
		return nodes;
	}

	public static boolean rechargeNodeInRange(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range) {
		Collections.shuffle(chunks);
		for (Chunk chunk : chunks) {
	    	INode nearestNode = findNode(chunk, xCoord, yCoord, zCoord, range);
	    	boolean nodeChanged = false;
	        if (nearestNode != null) {
	            AspectList baseAspects = nearestNode.getAspectsBase().copy();
	            AspectList currentAspects = nearestNode.getAspects().copy();
	            for (Aspect aspect : nearestNode.getAspects().getAspects()) {
	            	int diff = baseAspects.getAmount(aspect) - currentAspects.getAmount(aspect);
	            	if (1 < diff) {
	            		nearestNode.addToContainer(aspect, world.rand.nextInt(diff - 1) + 1);
	            		nodeChanged = true;
	            	}
	            	else if (diff == 1) {
	            		nearestNode.addToContainer(aspect, 1);
	            		nodeChanged = true;
	            	}
	            }
	            if (nodeChanged) {
	            	updateNode(nearestNode, world);
	            }
	        	return true;
	        }
	    }
		return false;
	}
	
	public static boolean growNodeInRange(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range) {
		Collections.shuffle(chunks);
		int attemptedNodes = 0;
		for (Chunk chunk : chunks) {
			for (INode node : findNodesInChunkWithinRange(chunk, xCoord, yCoord, zCoord, range)) {
				if (node != null) {
					++attemptedNodes;
					AspectList aspectsBase = node.getAspectsBase();
					int randBase = Math.max(aspectsBase.visSize(), 1);
		            if (world.rand.nextInt(randBase) < 120) {
						Aspect aspectToAdd;
						int rollAttempts = 0;
						do {
							aspectToAdd = getWeightedRandomAspect(world.rand);
							++rollAttempts;
						}
						while (aspectsBase.getAmount(aspectToAdd) < 255 && rollAttempts < 20);
						
						if (20 <= rollAttempts) {
							return false;
						}
						
						short amount = (short)(1 + world.rand.nextInt(2));
						aspectsBase.add(aspectToAdd, amount);
						node.getAspects().add(aspectToAdd, amount);
						updateNode(node, world);
						return true;
		            }
				}
			}
		}
		return attemptedNodes < 10;
	}

	public static Aspect getWeightedRandomAspect(Random rand) {
		Aspect aspectToAdd;
		if (0.685f <= rand.nextFloat()) {
			List<Aspect> aspects = new ArrayList<Aspect>(Aspect.aspects.values());
			aspectToAdd = aspects.get(rand.nextInt(aspects.size()));
		}
		else {
			switch (rand.nextInt(6)) {
			default:
			case 0:
				aspectToAdd = Aspect.EARTH;
				break;
			case 1:
				aspectToAdd = Aspect.WATER;
				break;
			case 2:
				aspectToAdd = Aspect.FIRE;
				break;
			case 3:
				aspectToAdd = Aspect.AIR;
				break;
			case 4:
				aspectToAdd = Aspect.ORDER;
				break;
			case 5:
				aspectToAdd = Aspect.ENTROPY;
				break;
			}
		}
		return aspectToAdd;
	}
	
	public static boolean repairNodeInRange(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range) {
		Collections.shuffle(chunks);
		for (Chunk chunk : chunks) {
			INode node = findNode(chunk, xCoord, yCoord, zCoord, range);
			if (node != null) {
				boolean nodeChanged = false;
				if (node.getNodeType() == NodeType.UNSTABLE) {
					node.setNodeType(NodeType.NORMAL);
					nodeChanged = true;
				}
				else {
					NodeModifier modifier = node.getNodeModifier();
					if (modifier == NodeModifier.FADING) {
						node.setNodeModifier(NodeModifier.PALE);
						nodeChanged = true;
					}
					else if (modifier == NodeModifier.PALE) {
						if (world.rand.nextFloat() < 0.65f) {
							node.setNodeModifier(null);
							nodeChanged = true;
						}
					}
					else if (modifier == null) {
						if (world.rand.nextFloat() < 0.15f) {
							node.setNodeModifier(NodeModifier.BRIGHT);
							nodeChanged = true;
						}
					}
				}
				
				if (nodeChanged) {
					updateNode(node, world);
					return true;
				}
			}
		}
		return true;
	}
	
	private static void updateNode(INode nearestNode, World world) {
        if (nearestNode instanceof TileEntity) {
        	TileEntity tile = (TileEntity)nearestNode;
        	tile.markDirty();
        	world.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        }
	}

	public static boolean convertNodeInRangeToType(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range, NodeType targetType) {
		Collections.shuffle(chunks);
		int attemptedNodes = 0;
		boolean nodeChanged = false;
		for (Chunk chunk : chunks) {
			for (INode node : findNodesInChunkWithinRange(chunk, xCoord, yCoord, zCoord, range)) {
				++attemptedNodes;
				NodeType nodeType = node.getNodeType();
				if (nodeType != targetType && nodeType != NodeType.NORMAL) {
					convertNodeToType(node, world, NodeType.NORMAL);
				}
				else if (nodeType == NodeType.NORMAL) {
					convertNodeToType(node, world, targetType);
				}
				
				if (nodeChanged) {
					updateNode(node, world);
					return true;
				}
			}
		}
		return attemptedNodes < 10;
	}
	
	public static boolean convertNodeToType(INode node, World world, NodeType type) {
		if (world.rand.nextFloat() < conversionChances.get(type)) {
			node.setNodeType(type);
			return true;
		}
		else {
			return false;
		}
	}
}
