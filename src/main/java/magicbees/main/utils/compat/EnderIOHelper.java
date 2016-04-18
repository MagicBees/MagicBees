package magicbees.main.utils.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import forestry.api.recipes.RecipeManagers;
import magicbees.item.types.DropType;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class EnderIOHelper implements IModHelper {

	public static Block alloyBlock;

	public static Item alloyIngot;
	public static Item itemMaterial;
	public static Item dust;
	
	public static FluidStack fluidXP;
	
	//both ingots and blocks
	public enum AlloyType {
		ELECTRICAL_STEEL,
		ENERGETIC_ALLOY,
		VIBRANT_ALLOY,
		REDSTONE_ALLOY,
		CONDUCTIVE_IRON,
		PULSATING_IRON,
		DARK_STEEL,
		SOULARIUM,
	}

	public enum materialType {
		SILICON,
		CONDUIT_BINDER,
		BINDER_COMPOSITE,
		NUGGET_PULSATING_IRON,
		NUGGET_VIBRANT_ALLOY,
		PULSATING_CRYSTAL,
		VIBRANT_CRYSTAL,
		DARK_STEEL_BALL,
		ENDER_CRYSTAL,
		ATTRACTOR_CRYSTAL,
		WEATHER_CRYSTAL,
	}
	
	public enum dustType {
		COAL,
		IRON,
		GOLD,
		COPPER,
		TIN,
		ENDER_PEARL,
		ENDERIUM_BASE_INGOT,
		OBSIDIAN,
		FLOUR,
	}

	private static boolean isEnderIOPresent = false;
	public static final String Name = "EnderIO";

	public static boolean isActive() {
		return isEnderIOPresent;
	}

	public void preInit() {
		if (Loader.isModLoaded(Name) && Config.enderIOActive) {
			isEnderIOPresent = true;
		}
	}

	public void init() {
		if (isActive()) {
			getBlocks();
			getItems();
			getFluids();
			setupCrafting();
		}
	}

	public void postInit() {
	}

	private static void getBlocks() {
		EnderIOHelper.alloyBlock = BlockInterface.getBlock(Name, "blockIngotStorage");
	}

	private static void getItems() {
		EnderIOHelper.alloyIngot = ItemInterface.getItem(Name, "itemAlloy");
		EnderIOHelper.itemMaterial = ItemInterface.getItem(Name, "itemMaterial");
		EnderIOHelper.dust = ItemInterface.getItem(Name, "itemPowderIngot");
	}
	
	private static void getFluids() {	//getting 1760 mB from 8 BoE, /64 drops = 27.5
		EnderIOHelper.fluidXP = FluidRegistry.getFluidStack("xpjuice", 25);
	}
	
	private static void setupCrafting() {
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{Config.drops.getStackForType(DropType.INTELLECT)}, EnderIOHelper.fluidXP);

		if(ThermalModsHelper.isActive()){
			NBTTagCompound toSend = new NBTTagCompound();
			toSend.setInteger("energy", 4000);
			toSend.setTag("input", new NBTTagCompound());
			toSend.setTag("output", new NBTTagCompound());
			ItemStack intellectDrop = Config.drops.getStackForType(DropType.INTELLECT);
			intellectDrop.writeToNBT(toSend.getCompoundTag("input"));
			fluidXP.writeToNBT(toSend.getCompoundTag("output"));
			FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
		}
	}
}
