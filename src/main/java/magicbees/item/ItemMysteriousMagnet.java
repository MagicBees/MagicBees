package magicbees.item;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import magicbees.main.CommonProxy;
import magicbees.main.Config;
import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.TabMagicBees;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid="Baubles", iface="baubles.api.IBauble")
public class ItemMysteriousMagnet extends Item implements IBauble {
	
	@SideOnly(Side.CLIENT)
	private IIcon activeIcon;
	
	private final float FUDGE_FACTOR = 0.2f;
	
	public ItemMysteriousMagnet() {
		super();
		this.setNoRepair();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(CommonProxy.DOMAIN + ":mysteriousMagnet");
		this.setCreativeTab(TabMagicBees.tabMagicBees);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		String s = LocalizationManager.getLocalizedString("misc.level", getMagnetLevel(itemStack));
		if (isMagnetActive(itemStack)) {
			list.add(LocalizationManager.getLocalizedString("misc.magnetActive", s));
		}
		else {
			list.add(LocalizationManager.getLocalizedString("misc.magnetInactive", s));
		}
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
		if (entity instanceof EntityLivingBase) {
			onWornTick(itemStack, (EntityLivingBase) entity);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (int i = 0; i <= getMaximumLevel(); i++) {
			list.add(new ItemStack(this, 1, i * 2));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(CommonProxy.DOMAIN + ":magnetInactive");
		this.activeIcon = iconRegister.registerIcon(CommonProxy.DOMAIN + ":magnetActive");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return isMagnetActive(meta) ? this.activeIcon : this.itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack, int pass) {
		return isMagnetActive(itemStack);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (player.isSneaking()) {
			toggleActive(itemStack);
		}
		return itemStack;
	}

	public void toggleActive(ItemStack itemStack) {
		itemStack.setItemDamage(itemStack.getItemDamage() ^ 1);
	}
	
	public boolean isMagnetActive(ItemStack itemStack) {
		return isMagnetActive(itemStack.getItemDamage());
	}
	
	private boolean isMagnetActive(int damage) {
		return (damage & 0x01) == 1;
	}
	
	public int getMaximumLevel() {
		return Config.magnetMaxLevel;
	}
	
	public int getMagnetLevel(ItemStack itemStack) {
		return itemStack.getItemDamage() >> 1;
	}

	private float getRadius(ItemStack itemStack) {
		return Config.magnetBaseRange + (Config.magnetLevelMultiplier * getMagnetLevel(itemStack));
	}

	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	@Optional.Method(modid="Baubles")
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.AMULET;
	}

	@Override
	public void onEquipped(ItemStack itemStack, EntityLivingBase entity) {
		if (!isMagnetActive(itemStack)) {
			toggleActive(itemStack);
		}
	}

	@Override
	public void onUnequipped(ItemStack itemStack, EntityLivingBase entity) {
		if (isMagnetActive(itemStack)) {
			toggleActive(itemStack);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void onWornTick(ItemStack itemStack, EntityLivingBase entity) {
		if (isMagnetActive(itemStack) && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			World world = entity.worldObj;
			float radius = getRadius(itemStack) - FUDGE_FACTOR;
			AxisAlignedBB bounds = player.boundingBox.expand(radius, radius, radius);
			
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				bounds.expand(FUDGE_FACTOR, FUDGE_FACTOR, FUDGE_FACTOR);
				
				if (7 <= getMagnetLevel(itemStack)) {
					List<EntityArrow> arrows = world.getEntitiesWithinAABB(EntityArrow.class, bounds);
					
					for (EntityArrow arrow : arrows) {
						if ((arrow.canBePickedUp == 1 || world.rand.nextFloat() < 0.3f)
								&& arrow.shootingEntity != entity) {
							EntityItem replacement = new EntityItem(world, arrow.posX, arrow.posY, arrow.posZ,
									new ItemStack(Items.arrow));
							world.spawnEntityInWorld(replacement);
						}
						world.removeEntity(arrow);
					}
				}
			}
			
			List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, bounds);
			
			for (EntityItem e : list) {
				if (e.age >= 10) {
					double x = player.posX - e.posX;
					double y = player.posY - e.posY;
					double z = player.posZ - e.posZ;
					
					double length = Math.sqrt(x * x + y * y + z * z) * 2;
					
					x = x / length + player.motionX / 2;
					y = y / length + player.motionY / 2;
					z = z / length + player.motionZ / 2;
					
					e.motionX = x;
					e.motionY = y;
					e.motionZ = z;
					e.isAirBorne = true;
					
					if (e.isCollidedHorizontally) {
						e.motionY += 1;
					}
					
					if (world.rand.nextFloat() < 0.2f) {
						float pitch = 0.85f - world.rand.nextFloat() * 3f / 10f;
						if(!Config.disableMagnetSound)
							world.playSoundEffect(e.posX, e.posY, e.posZ, "mob.endermen.portal", 0.6f, pitch);
					}
				}
			}
		}
	}
}
