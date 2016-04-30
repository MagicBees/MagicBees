package magicbees.main;

import java.io.File;
import java.lang.reflect.Field;

import magicbees.block.BlockEffectJar;
import magicbees.block.BlockEnchantedEarth;
import magicbees.block.BlockHive;
import magicbees.block.BlockMagicApiary;
import magicbees.block.BlockManaAuraProvider;
import magicbees.block.BlockVisAuraProvider;
import magicbees.block.types.HiveType;
import magicbees.item.ItemCapsule;
import magicbees.item.ItemComb;
import magicbees.item.ItemDrop;
import magicbees.item.ItemMagicHive;
import magicbees.item.ItemMagicHiveFrame;
import magicbees.item.ItemManasteelGrafter;
import magicbees.item.ItemManasteelScoop;
import magicbees.item.ItemMiscResources;
import magicbees.item.ItemMoonDial;
import magicbees.item.ItemMysteriousMagnet;
import magicbees.item.ItemNugget;
import magicbees.item.ItemPollen;
import magicbees.item.ItemPropolis;
import magicbees.item.ItemThaumiumGrafter;
import magicbees.item.ItemThaumiumScoop;
import magicbees.item.ItemWax;
import magicbees.item.types.CapsuleType;
import magicbees.item.types.HiveFrameType;
import magicbees.item.types.NuggetType;
import magicbees.item.types.ResourceType;
import magicbees.item.types.WaxType;
import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.VersionInfo;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.storage.BackpackDefinition;
import magicbees.tileentity.TileEntityEffectJar;
import magicbees.tileentity.TileEntityMagicApiary;
import magicbees.tileentity.TileEntityManaAuraProvider;
import magicbees.tileentity.TileEntityVisAuraProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.BeeManager;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author MysteriousAges
 */
public class Config
{
	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_DEBUG = "debug";
	public static final String CATEGORY_MODULES = "modules";
	public static final String CATEGORY_BOTANIA = "botaniaPlugin";
	
	public static boolean drawParticleEffects;
	public static boolean beeInfusionsAdded;
	public static boolean thaumaturgeBackpackActive;
	public static boolean addThaumcraftItemsToBackpacks;
	public static boolean disableUpdateNotification;
	public static boolean areMagicPlanksFlammable;
	public static boolean useImpregnatedStickInTools;
	public static boolean moonDialShowsPhaseInText;
	public static boolean doSpecialHiveGen;
	public static String thaumaturgeExtraItems;
	public static int capsuleStackSizeMax;
	public static boolean logHiveSpawns;
	public static double thaumcraftSaplingDroprate;
	public static int aromaticLumpSwarmerRate;

	public static boolean arsMagicaActive;
	public static boolean bloodMagicActive;
	public static boolean equivalentExchangeActive;
	public static boolean extraBeesActive;
	public static boolean redstoneArsenalActive;
	public static boolean thaumcraftActive;
	public static boolean thermalFoundationActive;
	public static boolean botaniaActive;
	public static boolean ae2Active;

	public static float magnetBaseRange;
	public static float magnetLevelMultiplier;
	public static int magnetMaxLevel;

	public static boolean forestryDebugEnabled;

	public static BlockEnchantedEarth enchantedEarth;
	public static BlockEffectJar effectJar;
	public static BlockHive hive;
    public static BlockMagicApiary magicApiary;
    public static BlockManaAuraProvider manaAuraProvider;
    public static BlockVisAuraProvider visAuraProvider;
	
	public static ItemComb combs;
	public static ItemWax wax;
	public static ItemPropolis propolis;
	public static ItemDrop drops;
	public static ItemPollen pollen;
	public static ItemMiscResources miscResources;
	public static ItemFood jellyBaby;
	public static Item manasteelScoop;
	public static Item thaumiumScoop;
	public static Item manasteelGrafter;
	public static Item thaumiumGrafter;
	public static ItemNugget nuggets;
	public static ItemMoonDial moonDial;
	public static ItemMysteriousMagnet magnet;
	
	//----- Liquid Capsules --------------------
	public static ItemCapsule magicCapsule;
	public static ItemCapsule voidCapsule;
	
	//----- Apiary Frames ----------------------
	public static ItemMagicHiveFrame hiveFrameMagic;
	public static ItemMagicHiveFrame hiveFrameResilient;
	public static ItemMagicHiveFrame hiveFrameGentle;
	public static ItemMagicHiveFrame hiveFrameMetabolic;
	public static ItemMagicHiveFrame hiveFrameNecrotic;
	public static ItemMagicHiveFrame hiveFrameTemporal;
	public static ItemMagicHiveFrame hiveFrameOblivion;
	
	//----- Backpacks ------------------------------------------
	public static Item thaumaturgeBackpackT1;
	public static Item thaumaturgeBackpackT2;
	public static BackpackDefinition thaumaturgeBackpackDef;

	//----- Config State info ----------------------------------
	public static Configuration configuration;
	
	public Config(File configFile)
	{
		configuration = new Configuration(configFile);
		configuration.load();
		processConfigFile();
		
		forestryDebugEnabled = (new File("./config/forestry/DEBUG.ON")).exists();
		configuration.save();
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(VersionInfo.ModName))
		{
			if (configuration.hasChanged())
			{
				configuration.save();
			}
			syncConfigs();
		}
	}

	public void saveConfigs()
	{
		configuration.save();
	}

	public void setupBlocks()
	{
		enchantedEarth = new BlockEnchantedEarth();
		GameRegistry.registerBlock(enchantedEarth, "magicbees.enchantedEarth");
		
		setupHives();
		
		setupEffectJar();
		setupApiary();
		
		setupBotaniaBlocks();
		setupThaumcraftBlocks();
	}
	
	public void setupItems()
	{
		combs = new ItemComb();
		wax = new ItemWax();
		propolis = new ItemPropolis();
		drops = new ItemDrop();
		miscResources = new ItemMiscResources();
		pollen = new ItemPollen();
		moonDial = new ItemMoonDial();
		setupJellyBaby();
		setupNuggets();
		setupMysteriousMagnet();
		setupFrames();
		magicCapsule = new ItemCapsule(CapsuleType.MAGIC, capsuleStackSizeMax);
		voidCapsule = new ItemCapsule(CapsuleType.VOID, capsuleStackSizeMax);
		
		setupThaumcraftItems();
		setupBotaniaItems();
		
		setupOreDictionaryEntries();
		setupMiscForestryItemHooks();
	}

	private void processConfigFile()
	{
		// Pull config from Forestry via reflection
		Field f;
		try
		{
			f = Class.forName("forestry.core.config.Config").getField("enableParticleFX");
			drawParticleEffects = f.getBoolean(null);
		}
		catch (Exception e)
		{
			LogHelper.warn("Failed to retrieve Forestry's bee particle FX setting. This is not critical, but should be reported so it can be fixed.");
			LogHelper.debug(e);
		}

		syncConfigs();
	}
	
	private void syncConfigs() {
		doGeneralConfigs();
		doModuleConfigs();
		doDebugConfigs();
	}

	private void doDebugConfigs() {
		Property p;
		p = configuration.get(CATEGORY_DEBUG, "logHiveSpawns", false);
		p.comment = "Enable to see exact locations of MagicBees hive spawns.";
		logHiveSpawns = p.getBoolean();
	}

	private void doModuleConfigs() {
		Property p;
		//Modules
		p = configuration.get(CATEGORY_MODULES, "ArsMagica", true);
		arsMagicaActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "BloodMagic", true);
		bloodMagicActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "EquivalentExchange", true);
		equivalentExchangeActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "ExtraBees", true);
		extraBeesActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "RedstoneArsenal", true);
		redstoneArsenalActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "Thaumcraft", true);
		thaumcraftActive = p.getBoolean();

		p = configuration.get(CATEGORY_MODULES, "ThermalExpansion", true);
		thermalFoundationActive = p.getBoolean();
		
		p = configuration.get(CATEGORY_MODULES, "Botania", true);
		botaniaActive = p.getBoolean();
		BotaniaHelper.doBotaniaModuleConfigs(configuration);
		
		p = configuration.get(CATEGORY_MODULES, "AppliedEnergistics2", true);
		ae2Active = p.getBoolean();
	}

	private void doGeneralConfigs() {
		Property p;
		p = configuration.get(CATEGORY_GENERAL, "backpack.thaumaturge.active", true);
		p.comment = "Set to false to disable the Thaumaturge backpack";
		thaumaturgeBackpackActive = p.getBoolean(true);

		p = configuration.get(CATEGORY_GENERAL, "backpack.thaumaturge.additionalItems", "");
		p.comment = "Add additional items to the Thaumaturge's Backpack." +
				"\n Format is the same as Forestry's: id:meta;id;id:meta (etc)";
		thaumaturgeExtraItems = p.getString();
		
		p = configuration.get(CATEGORY_GENERAL, "backpack.forestry.addThaumcraftItems", true);
		p.comment = "Set to true if you want MagicBees to add several Thaumcraft blocks & items to Forestry backpacks." +
				"\n Set to false to disable.";
		addThaumcraftItemsToBackpacks = p.getBoolean(true);
		
		p = configuration.get(CATEGORY_GENERAL, "capsuleStackSize", 64);
		p.comment = "Allows you to edit the stack size of the capsules in MagicBees if using GregTech, \n" +
				"or the reduced capsule size in Forestry & Railcraft. Default: 64";
		capsuleStackSizeMax = p.getInt();
		
		p = configuration.get(CATEGORY_GENERAL, "disableVersionNotification", false);
		p.comment = "Set to true to stop Magic Bees from notifying you when new updates are available. (Does not supress critical updates)";
		disableUpdateNotification = p.getBoolean(false);
		
		p = configuration.get(CATEGORY_GENERAL, "areMagicPlanksFlammable", false);
		p.comment = "Set to true to allow Greatwood & Silverwood planks to burn in a fire.";
		areMagicPlanksFlammable = p.getBoolean(false);
		
		p = configuration.get(CATEGORY_GENERAL, "useImpregnatedStickInTools", false);
		p.comment = "Set to true to make Thaumium Grafter & Scoop require impregnated sticks in the recipe.";
		useImpregnatedStickInTools = p.getBoolean(false);

		p = configuration.get(CATEGORY_GENERAL, "thaumCraftSaplingDroprate", 0.1, "The chance for thaumcraft saplings using the thaumium grafter", 0.0, 1.0);
		thaumcraftSaplingDroprate = p.getDouble(0.1);

		p = configuration.get(CATEGORY_GENERAL, "moonDialShowText", false);
		p.comment = "set to true to show the current moon phase in mouse-over text.";
		moonDialShowsPhaseInText = p.getBoolean(false);
		
		p = configuration.get(CATEGORY_GENERAL, "doSpecialHiveGen", true);
		p.comment = "Set to false if you hate fun and do not want special hives generating in Magic biomes.";
		doSpecialHiveGen = p.getBoolean(true);

		p = configuration.get(CATEGORY_GENERAL, "magnetRangeBase", 3.0);
		p.comment = "Base range (in blocks) of the Mysterious Magnet";
		magnetBaseRange = (float)p.getDouble(3.0);
		
		p = configuration.get(CATEGORY_GENERAL, "magnetRangeMultiplier", 0.75);
		p.comment = "Range multiplier per level of the Mysterious Magnet. Total range = base range + level * multiplier";
		magnetLevelMultiplier = (float)p.getDouble(0.75);
		
		p = configuration.get(CATEGORY_GENERAL, "magnetMaximumLevel", 8);
		p.comment = "Maximum level of the magnets.";
		magnetMaxLevel = p.getInt();
		
		p = configuration.get(CATEGORY_GENERAL, "aromaticLumpSwarmerRate", 95);
		p.comment = "Aromatic lump swarmer rate. Final value is X/1000. 0 will disable, values outside of [0,1000] will be clamped to range. Default: 95";
		aromaticLumpSwarmerRate = p.getInt();
	}

	private void setupEffectJar() {
		effectJar = new BlockEffectJar();
		GameRegistry.registerBlock(effectJar, "effectJar");
		GameRegistry.registerTileEntity(TileEntityEffectJar.class, TileEntityEffectJar.tileEntityName);
	}
	
	private void setupApiary() {
        magicApiary = new BlockMagicApiary();
        GameRegistry.registerBlock(magicApiary, "magicApiary");
        GameRegistry.registerTileEntity(TileEntityMagicApiary.class, TileEntityMagicApiary.tileEntityName);
	}

	private void setupFrames() {
		hiveFrameMagic = new ItemMagicHiveFrame(HiveFrameType.MAGIC);
		hiveFrameResilient = new ItemMagicHiveFrame(HiveFrameType.RESILIENT);
		hiveFrameGentle = new ItemMagicHiveFrame(HiveFrameType.GENTLE);
		hiveFrameMetabolic = new ItemMagicHiveFrame(HiveFrameType.METABOLIC);
		hiveFrameNecrotic = new ItemMagicHiveFrame(HiveFrameType.NECROTIC);
		hiveFrameTemporal = new ItemMagicHiveFrame(HiveFrameType.TEMPORAL);
		hiveFrameOblivion = new ItemMagicHiveFrame(HiveFrameType.OBLIVION);
		
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(hiveFrameOblivion), 1, 1, 18));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(hiveFrameOblivion), 1, 3, 23));
	}

	private void setupHives() {
		hive = new BlockHive();
		GameRegistry.registerBlock(hive, ItemMagicHive.class, "hive");

		for (HiveType t : HiveType.values())
		{
			hive.setHarvestLevel("scoop", 0, t.ordinal());
		}
	}

	private void setupMysteriousMagnet() {
		magnet = new ItemMysteriousMagnet();
		GameRegistry.registerItem(magnet, "magnet", CommonProxy.DOMAIN);
	}

	private void setupJellyBaby() {
		jellyBaby = new ItemFood(1, false).setAlwaysEdible().setPotionEffect(Potion.moveSpeed.id, 5, 1, 1f);
		jellyBaby.setUnlocalizedName(CommonProxy.DOMAIN + ":jellyBabies").setTextureName(CommonProxy.DOMAIN + ":jellyBabies");
		GameRegistry.registerItem(jellyBaby, "jellyBabies");
	}

	private void setupNuggets() {
		nuggets = new ItemNugget();
		String item;
		for (NuggetType type : NuggetType.values()) {
			LogHelper.info("Found nugget of type " + type.toString());
			item = type.toString().toLowerCase();
			item = Character.toString(item.charAt(0)).toUpperCase() + item.substring(1);
			if (OreDictionary.getOres("ingot" + item).size() <= 0) {
				if (OreDictionary.getOres("nugget" + item).size() <= 0) {
					LogHelper.info("Disabled nugget " + type.toString());
					type.setInactive();
				}
			}
		}

		GameRegistry.registerItem(nuggets, "beeNugget");
	}

	private void setupOreDictionaryEntries() {
		for (int level = 0; level <= 8; level++) {
			OreDictionary.registerOre("mb.magnet.level" + level, new ItemStack(magnet, 1, level * 2));
			OreDictionary.registerOre("mb.magnet.level" + level, new ItemStack(magnet, 1, level * 2 + 1));
		}
		
		OreDictionary.registerOre("beeComb", new ItemStack(combs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("waxMagical", wax.getStackForType(WaxType.MAGIC));
		OreDictionary.registerOre("waxMagical", wax.getStackForType(WaxType.AMNESIC));
		OreDictionary.registerOre("nuggetIron", nuggets.getStackForType(NuggetType.IRON));
		OreDictionary.registerOre("nuggetCopper", nuggets.getStackForType(NuggetType.COPPER));
		OreDictionary.registerOre("nuggetTin", nuggets.getStackForType(NuggetType.TIN));
		OreDictionary.registerOre("nuggetSilver", nuggets.getStackForType(NuggetType.SILVER));
		OreDictionary.registerOre("nuggetLead", nuggets.getStackForType(NuggetType.LEAD));
		OreDictionary.registerOre("nuggetDiamond", nuggets.getStackForType(NuggetType.DIAMOND));
		OreDictionary.registerOre("nuggetEmerald", nuggets.getStackForType(NuggetType.EMERALD));
		OreDictionary.registerOre("nuggetApatite", nuggets.getStackForType(NuggetType.APATITE));
	}
	
	private void setupBotaniaItems() {
		if (BotaniaHelper.isActive()) {
			manasteelScoop = new ItemManasteelScoop();
			GameRegistry.registerItem(manasteelScoop, manasteelScoop.getUnlocalizedName(), CommonProxy.DOMAIN);
			
			manasteelGrafter = new ItemManasteelGrafter();
			GameRegistry.registerItem(manasteelGrafter, manasteelGrafter.getUnlocalizedName(), CommonProxy.DOMAIN);
		}
	}
	
	private void setupBotaniaBlocks() {
		if (BotaniaHelper.isActive()) {
			manaAuraProvider = new BlockManaAuraProvider();
			GameRegistry.registerBlock(manaAuraProvider, "manaAuraProvider");
			GameRegistry.registerTileEntity(TileEntityManaAuraProvider.class, "manaAuraProvider");
		}
	}
	
	private void setupThaumcraftBlocks() {
		if (ThaumcraftHelper.isActive()) {
			visAuraProvider = new BlockVisAuraProvider();
			GameRegistry.registerBlock(visAuraProvider, "visAuraProvider");
			GameRegistry.registerTileEntity(TileEntityVisAuraProvider.class, "visAuraProvider");
		}
	}

	private void setupThaumcraftItems() {
		if (ThaumcraftHelper.isActive())
		{
			setupThaumcraftBackpacks();
			
            // Items
			thaumiumScoop = new ItemThaumiumScoop();
			GameRegistry.registerItem(thaumiumScoop, thaumiumScoop.getUnlocalizedName(), CommonProxy.DOMAIN);
			
			thaumiumGrafter = new ItemThaumiumGrafter();
			GameRegistry.registerItem(thaumiumGrafter, thaumiumGrafter.getUnlocalizedName(), CommonProxy.DOMAIN);
		}
	}
	
	private void setupThaumcraftBackpacks() {
		try {
			// 0x8700C6 is a purple-y colour, which is associated with Thaumcraft.
			String backpackName = LocalizationManager.getLocalizedString("backpack.thaumaturge");
			BackpackDefinition def = new BackpackDefinition("thaumaturge", backpackName, 0x8700C6);
			thaumaturgeBackpackT1 = BackpackManager.backpackInterface.addBackpack(def, EnumBackpackType.T1);
			thaumaturgeBackpackT1.setUnlocalizedName("backpack.thaumaturgeT1");
			GameRegistry.registerItem(thaumaturgeBackpackT1, "backpack.thaumaturgeT1");
			thaumaturgeBackpackT2 = BackpackManager.backpackInterface.addBackpack(def, EnumBackpackType.T2);
			thaumaturgeBackpackT2.setUnlocalizedName("backpack.thaumaturgeT2");
			GameRegistry.registerItem(thaumaturgeBackpackT2, "backpack.thaumaturgeT2");
			// Add additional items from configs to backpack.
			if (thaumaturgeExtraItems.length() > 0) {
				LogHelper.info("Attempting to add extra items to Thaumaturge's backpack. If you get an error, check your MagicBees.conf.");
				FMLInterModComms.sendMessage("Forestry", "add-backpack-items", "thaumaturge@" + thaumaturgeExtraItems);
			}
		}
		catch (Exception e) {
			LogHelper.error("MagicBees encountered a problem during loading!");
			LogHelper.error("Could not register backpacks via Forestry. Check your FML Client log and see if Forestry crashed silently.");
		}
	}

	private void setupMiscForestryItemHooks() {
		// Make Aromatic Lumps a swarmer inducer. Chance is /1000.
		if (aromaticLumpSwarmerRate <= 0) {
			aromaticLumpSwarmerRate = (aromaticLumpSwarmerRate >= 1000) ? 1000: aromaticLumpSwarmerRate;
			BeeManager.inducers.put(miscResources.getStackForType(ResourceType.AROMATIC_LUMP), aromaticLumpSwarmerRate);
		}
	}
}
