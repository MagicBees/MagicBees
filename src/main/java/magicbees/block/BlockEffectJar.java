package magicbees.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import magicbees.client.gui.UIScreens;
import magicbees.main.CommonProxy;
import magicbees.main.MagicBees;
import magicbees.main.utils.TabMagicBees;
import magicbees.tileentity.TileEntityEffectJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEffectJar extends BlockContainer
{
	public BlockEffectJar()
	{
		super(Material.glass);
		this.setCreativeTab(TabMagicBees.tabMagicBees);
		this.setBlockName("effectJar");
		this.setBlockBounds(0.25f, 0f, 0.25f, 0.75f, 0.81f, 0.74f);
		this.setHardness(0.1f);
		this.setResistance(1.5f);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		boolean activate = false;
		
		if (!player.isSneaking())
		{
			player.openGui(MagicBees.object, UIScreens.EFFECT_JAR.ordinal(), world, x, y, z);
			activate = true;
		}
		
		return activate;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int metadata)
	{
		return new TileEntityEffectJar();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return CommonProxy.RenderIdEffectJar;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		this.dropInventory(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void dropInventory(World world, int x, int y, int z)
	{
		TileEntity e = world.getTileEntity(x, y, z);
		
		if (e != null && e instanceof TileEntityEffectJar)
		{
			TileEntityEffectJar jar = (TileEntityEffectJar)e;
			
			// 0 is the only droppable stack.
			ItemStack stack = jar.getStackInSlot(0);
			
			if (stack != null && stack.stackSize > 0)
			{
				float pX = world.rand.nextFloat() * 0.8f + 0.1f;
				float pY = world.rand.nextFloat() * 0.8f + 0.1f;
				float pZ = world.rand.nextFloat() * 0.8f + 0.1f;
				
				EntityItem entityItem = new EntityItem(world, x + pX, y + pY, z + pZ, stack.copy());
				
				entityItem.motionX = world.rand.nextGaussian() * 0.05f;
				entityItem.motionY = world.rand.nextGaussian() * 0.05f + 0.2f;
				entityItem.motionZ = world.rand.nextGaussian() * 0.05f;
				
				world.spawnEntityInWorld(entityItem);
				stack.stackSize = 0;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = Blocks.glass.getIcon(0, 0);
	}
}
