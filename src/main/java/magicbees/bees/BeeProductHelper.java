package magicbees.bees;

import magicbees.item.types.CombType;
import magicbees.item.types.DropType;
import magicbees.item.types.NuggetType;
import magicbees.item.types.PollenType;
import magicbees.item.types.PropolisType;
import magicbees.item.types.ResourceType;
import magicbees.main.Config;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.compat.AppliedEnergisticsHelper;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.EquivalentExchangeHelper;
import magicbees.main.utils.compat.ForestryHelper;
import magicbees.main.utils.compat.RedstoneArsenalHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalModsHelper;
import magicbees.main.utils.compat.BotaniaHelper.PastureSeed;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import static magicbees.bees.BeeSpecies.*;

public class BeeProductHelper {
	
	public static void initBaseProducts() {
		MYSTICAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.15f);
		SORCEROUS.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.15f);
		UNUSUAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.15f);
		ATTUNED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.15f);
		ELDRITCH.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.15f);

		ESOTERIC.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.18f);
		MYSTERIOUS.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.20f);
		ARCANE.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.25f)
			.addSpecialty(Config.drops.getStackForType(DropType.ENCHANTED, 1), 0.9f);

		CHARMED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 0.18f);
		ENCHANTED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 0.20f);
		SUPERNATURAL.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 0.25f)
			.addSpecialty(Config.pollen.getStackForType(PollenType.UNUSUAL), 0.8f);

		ETHEREAL.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 0.10f);

		WINDY.addProduct(Config.combs.getStackForType(CombType.AIRY), 0.25f);
		FIREY.addProduct(Config.combs.getStackForType(CombType.FIREY), 0.25f);
		EARTHY.addProduct(Config.combs.getStackForType(CombType.EARTHY), 0.25f);
		WATERY.addProduct(Config.combs.getStackForType(CombType.WATERY), 0.25f)
			.addSpecialty(new ItemStack(Blocks.ice), 0.025f);

		PUPIL.addProduct(Config.combs.getStackForType(CombType.PAPERY), 0.20f);
		SCHOLARLY.addProduct(Config.combs.getStackForType(CombType.PAPERY), 0.25f);
		SAVANT.addProduct(Config.combs.getStackForType(CombType.PAPERY), 0.40f);

		AWARE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.18f);
		SPIRIT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.22f)
			.addSpecialty(Config.combs.getStackForType(CombType.SOUL), 0.16f);
		SOUL.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.28f)
			.addSpecialty(Config.combs.getStackForType(CombType.SOUL), 0.20f);

		SKULKING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f);
		GHASTLY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.8f)
			.addSpecialty(new ItemStack(Items.ghast_tear), 0.099f);
		SPIDERY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.13f)
			.addProduct(new ItemStack(Items.string), 0.08f)
			.addSpecialty(new ItemStack(Items.spider_eye), 0.08f);
		SMOULDERING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.10f)
			.addSpecialty(new ItemStack(Items.blaze_rod), 0.05f);
		BRAINY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addProduct(new ItemStack(Items.rotten_flesh), 0.6f);
		BIGBAD.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.18f)
			.addProduct(new ItemStack(Items.beef), 0.12f)
			.addProduct(new ItemStack(Items.chicken), 0.12f)
			.addSpecialty(new ItemStack(Items.melon), 0.20f);
		BATTY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addSpecialty(new ItemStack(Items.string), 0.00001f);
		CHICKEN.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.23f)
			.addSpecialty(new ItemStack(Items.feather), 0.08f)
			.addSpecialty(new ItemStack(Items.egg), 0.08f);
		BEEF.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.25f)
			.addSpecialty(new ItemStack(Items.leather), 0.165f);
		PORK.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addSpecialty(new ItemStack(Items.carrot), 0.165f);
		SHEEPISH.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.25f)
			.addSpecialty(new ItemStack(Blocks.wool), 0.16f)
			.addSpecialty(new ItemStack(Items.wheat), 0.24f);
		HORSE.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.25f)
			.addSpecialty(new ItemStack(Items.leather), 0.24f)
			.addSpecialty(new ItemStack(Items.apple), 0.38f);
		CATTY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.25f)
			.addSpecialty(new ItemStack(Items.fish), 0.24f);

		TIMELY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 0.16f);
		LORDLY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 0.19f);
		DOCTORAL.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 0.24f)
			.addSpecialty(new ItemStack(Config.jellyBaby), 0.078f);

		INFERNAL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.12f);
		HATEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.18f);
		SPITEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.24f);
		WITHERING.addSpecialty(Config.miscResources.getStackForType(ResourceType.SKULL_CHIP), 0.15f);

		OBLIVION.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 0.14f);
		NAMELESS.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 0.19f);
		ABANDONED.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 0.24f);
		FORLORN.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 0.30f);
		DRACONIC.addSpecialty(Config.miscResources.getStackForType(ResourceType.DRAGON_DUST), 0.15f);

		IRON.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.IRON), 0.18f);
		GOLD.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(new ItemStack(Items.gold_nugget, 1), 0.16f);
		DIAMOND.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.DIAMOND), 0.6f);
		EMERALD.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.EMERALD), 0.4f);
		COPPER.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.COPPER), 0.20f);
		TIN.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.TIN), 0.20f);
		APATITE.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.APATITE), 0.10f);
		
		MUTABLE.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 0.30f)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.10f);
		TRANSMUTING.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 0.10f)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.30f)
				.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 0.5f)
				.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.SIMMERING.ordinal()), 0.5f);
		CRUMBLING.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 0.10f)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.30f)
				.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.POWDERY.ordinal()), 0.10f)
				.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.COCOA.ordinal()), 0.15f);
		
		INVISIBLE.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.35f);

	}

	public static void initOreDictSProducts() {
		SILVER.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("ingotSilver").size() > 0) {
			SILVER.addSpecialty(Config.nuggets.getStackForType(NuggetType.SILVER), 0.16f);
		}
		else {
			SILVER.setInactive();
		}
		
		LEAD.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("ingotLead").size() > 0) {
			LEAD.addSpecialty(Config.nuggets.getStackForType(NuggetType.LEAD), 0.17f);
		}
		else {
			LEAD.setInactive();
		}
		
		ALUMINUM.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetAluminum").size() > 0) {
			ALUMINUM.addSpecialty(OreDictionary.getOres("nuggetAluminum").get(0), 0.20f);
		} else {
			ALUMINUM.setInactive();
		}

		TE_PLATINUM.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetPlatinum").size() > 0&& OreDictionary.getOres("ingotPlatinum").size() > 0) {
			TE_PLATINUM.addSpecialty(OreDictionary.getOres("nuggetPlatinum").get(0), 0.18f);
		} else {
			TE_PLATINUM.setInactive();
		}

		TE_NICKEL.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetNickel").size() > 0 && OreDictionary.getOres("ingotNickel").size() > 0) {
			TE_NICKEL.addSpecialty(OreDictionary.getOres("nuggetNickel").get(0), 0.18f);
		} else {
			TE_NICKEL.setInactive();
		}

		ARDITE.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetArdite").size() > 0) {
			ARDITE.addSpecialty(OreDictionary.getOres("nuggetArdite").get(0), 0.18f);
		} else {
			ARDITE.setInactive();
		}

		COBALT.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetCobalt").size() > 0) {
			COBALT.addSpecialty(OreDictionary.getOres("nuggetCobalt").get(0), 0.18f);
		}
		if (OreDictionary.getOres("nuggetNaturalCobalt").size() > 0) {
			COBALT.addSpecialty(OreDictionary.getOres("nuggetNaturalCobalt").get(0), 0.24f);
		}
		if (COBALT.getSpecies().getSpecialtyChances().size() <= 0) {
			COBALT.setInactive();
		}

		TE_BRONZE.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetBronze").size() > 0 && OreDictionary.getOres("ingotBronze").size() > 0) {
			TE_BRONZE.addSpecialty(OreDictionary.getOres("nuggetBronze").get(0), 0.18f);
		} else {
			TE_BRONZE.setInactive();
		}

		TE_INVAR.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetInvar").size() > 0 && OreDictionary.getOres("ingotInvar").size() > 0) {
			TE_INVAR.addSpecialty(OreDictionary.getOres("nuggetInvar").get(0), 0.18f);
		} else {
			TE_INVAR.setInactive();
		}

		TE_ELECTRUM.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetElectrum").size() > 0 && OreDictionary.getOres("ingotElectrum").size() > 0) {
			TE_ELECTRUM.addSpecialty(OreDictionary.getOres("nuggetElectrum").get(0), 0.18f);
		} else {
			TE_ELECTRUM.setInactive();
		}

		MANYULLYN.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("nuggetManyullyn").size() > 0) {
			MANYULLYN.addSpecialty(OreDictionary.getOres("nuggetManyullyn").get(0), 0.16f);
		} else {
			MANYULLYN.setInactive();
		}
		
		OSMIUM.addProduct(ForestryHelper.itemHoneycomb, 0.15f);
		if (OreDictionary.getOres("nuggetOsmium").size() > 0) {
			OSMIUM.addSpecialty(OreDictionary.getOres("nuggetOsmium").get(0), 0.16f);
		}
		else {
			OSMIUM.setInactive();
		}
		
		CERTUS.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("crystalCertusQuartz").size() > 0) {
			CERTUS.addSpecialty(OreDictionary.getOres("crystalCertusQuartz").get(0), 0.8f);
		}
		else {
			CERTUS.setInactive();
		}
		
		SILICON.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("itemSilicon").size() > 0) {
			SILICON.addSpecialty(OreDictionary.getOres("itemSilicon").get(0), 0.16f);
		}
		else {
			SILICON.setInactive();
		}
		
		FLUIX.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		if (OreDictionary.getOres("crystalFluix").size() > 0) {
			FLUIX.addSpecialty(OreDictionary.getOres("crystalFluix").get(0), 0.6f);
		}
		else {
			FLUIX.setInactive();
		}
	}
	
	public static void initThaumcraftProducts() {
		TC_AIR.addProduct(Config.combs.getStackForType(CombType.TC_AIR), 0.20f);
		TC_FIRE.addProduct(Config.combs.getStackForType(CombType.TC_FIRE), 0.20f);
		TC_WATER.addProduct(Config.combs.getStackForType(CombType.TC_WATER), 0.20f);
		TC_EARTH.addProduct(Config.combs.getStackForType(CombType.TC_EARTH), 0.20f);
		TC_ORDER.addProduct(Config.combs.getStackForType(CombType.TC_ORDER), 0.20f);
		TC_CHAOS.addProduct(Config.combs.getStackForType(CombType.TC_CHAOS), 0.20f);

		TC_VIS.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.10f);
		TC_REJUVENATING.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.18f);
		TC_EMPOWERING.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.14f);
		TC_EMPOWERING.addSpecialty(new ItemStack(ForestryHelper.pollen, 1, ForestryHelper.Pollen.CRYSTALLINE.ordinal()), 0.2f);
		TC_NEXUS.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.25f);
		TC_NEXUS.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 0.12f);
		TC_NEXUS.addSpecialty(new ItemStack(ForestryHelper.pollen, 1, ForestryHelper.Pollen.CRYSTALLINE.ordinal()), 0.2f);
		TC_TAINT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.18f);
		TC_TAINT.addProduct(new ItemStack(ForestryHelper.craftingResource, 1 , ForestryHelper.Propolis.STICKY.ordinal()), 0.213f);
		TC_PURE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.16f);
		TC_PURE.addSpecialty(Config.combs.getStackForType(CombType.SOUL), 0.19f);
		TC_HUNGRY.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 0.28f);
		TC_HUNGRY.addSpecialty(Config.combs.getStackForType(CombType.TEMPORAL), 0.195f);
		TC_WISPY.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 0.22f)
				.addSpecialty(new ItemStack(ForestryHelper.craftingResource, 1, ForestryHelper.CraftingMaterial.SILK_WISP.ordinal()), 0.4f);
		TC_VOID.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		
		if (ThaumcraftHelper.isActive()) {
			SCHOLARLY.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 0.2f);
			SAVANT.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 0.5f);

			TC_AIR.addSpecialty(Config.propolis.getStackForType(PropolisType.AIR), 0.18f);
			TC_FIRE.addSpecialty(Config.propolis.getStackForType(PropolisType.FIRE), 0.18f);
			TC_WATER.addSpecialty(Config.propolis.getStackForType(PropolisType.WATER), 0.18f);
			TC_EARTH.addSpecialty(Config.propolis.getStackForType(PropolisType.EARTH), 0.18f);
			TC_ORDER.addSpecialty(Config.propolis.getStackForType(PropolisType.ORDER), 0.18f);
			TC_CHAOS.addSpecialty(Config.propolis.getStackForType(PropolisType.CHAOS), 0.18f);

			BATTY.addSpecialty(new ItemStack(Items.gunpowder), 0.4f);
			BRAINY.addSpecialty(new ItemStack(ThaumcraftHelper.miscResource, 1, ThaumcraftHelper.MiscResource.ZOMBIE_BRAIN.ordinal()), 0.2f);
			CHICKEN.addSpecialty(new ItemStack(ThaumcraftHelper.nuggetChicken, 1), 0.9f);
			BEEF.addSpecialty(new ItemStack(ThaumcraftHelper.nuggetBeef, 1), 0.9f);
			PORK.addSpecialty(new ItemStack(ThaumcraftHelper.nuggetPork, 1), 0.9f);
			TC_VOID.addSpecialty(new ItemStack(ThaumcraftHelper.nuggetMetal, 1, ThaumcraftHelper.NuggetType.VOID_METAL.ordinal()), 0.155f);
		}
	}
	
	public static void initEquivalentExchange3Species() {
		EE_MINIUM.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.16f);
		
		if (EquivalentExchangeHelper.isActive()) {
			EE_MINIUM.addSpecialty(new ItemStack(EquivalentExchangeHelper.minuimShard), 0.6f);
		}
	}
	
	public static void initArsMagicaSpecies() {
		AM_ESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 0.12f);
		AM_QUINTESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 0.23f);
		AM_EARTH.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_AIR.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_FIRE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_WATER.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_LIGHTNING.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_PLANT.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_ICE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.12f);
		AM_ARCANE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 0.19f);
		AM_VORTEX.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 0.10f);
		AM_WIGHT.addProduct(Config.combs.getStackForType(CombType.SOUL), 0.30f)
			.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f);
		
		if (ArsMagicaHelper.isActive()) {
			AM_QUINTESSENCE.addSpecialty(new ItemStack(ArsMagicaHelper.itemResource, 1, ArsMagicaHelper.ResourceType.ARCANE_COMPOUND.ordinal()), 0.5f);
			AM_EARTH.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.EARTH.ordinal()), 0.7f);
			AM_AIR.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.AIR.ordinal()), 0.7f);
			AM_FIRE.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.FIRE.ordinal()), 0.7f);
			AM_WATER.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.WATER.ordinal()), 0.7f);
			AM_LIGHTNING.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.LIGHTNING.ordinal()), 0.7f);
			AM_PLANT.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.PLANT.ordinal()), 0.7f);
			AM_ICE.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.ICE.ordinal()), 0.7f);
			AM_ARCANE.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.ARCANE.ordinal()), 0.11f);
			AM_VORTEX.addSpecialty(new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.EARTH.ordinal()), 0.15f);
			AM_WIGHT.addSpecialty(new ItemStack(ArsMagicaHelper.itemResource, 1, ArsMagicaHelper.ResourceType.ARCANE_COMPOUND.ordinal()), 0.11f);
		}
	}
	
	public static void initThermalExpansionProducts() {
		TE_DANTE.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.10f)
			.addSpecialty(new ItemStack(Items.blaze_powder), 0.5f);
		TE_PYRO.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 0.10f)
			.addSpecialty(new ItemStack(Items.blaze_powder), 0.5f);
		TE_DESTABILIZED.addProduct(Config.combs.getStackForType(CombType.TE_DESTABILIZED), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.10f)
			.addSpecialty(new ItemStack(Items.redstone), 0.5f);
		TE_LUX.addProduct(Config.combs.getStackForType(CombType.TE_LUX), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.OCCULT), 0.10f)
			.addSpecialty(new ItemStack(Items.glowstone_dust), 0.5f);
		TE_WINSOME.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 0.5f)
			.addSpecialty(new ItemStack(Items.ender_pearl), 0.5f);
		TE_ENDEARING.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.MYSTERIOUS.ordinal()), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 0.5f);
		TE_COAL.addProduct(ForestryHelper.itemHoneycomb, 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.TE_CARBON), 0.5f)
			.addSpecialty(new ItemStack(Items.coal), 0.5f);
		TE_BLIZZY.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 0.10f);
		TE_GELID.addProduct(new ItemStack(ForestryHelper.beeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 0.10f);
		TE_SHOCKING.addProduct(Config.combs.getStackForType(CombType.AIRY), 0.16f);
		TE_AMPED.addProduct(Config.combs.getStackForType(CombType.AIRY), 0.29f);
		TE_GROUNDED.addProduct(Config.combs.getStackForType(CombType.EARTHY), 0.16f);
		TE_ROCKING.addProduct(Config.combs.getStackForType(CombType.EARTHY), 0.29f);
		
		if (ThermalModsHelper.isActive()) {
			LogHelper.info("Thermal Expansion bees active");
			TE_BLIZZY.addSpecialty(ThermalModsHelper.dustBlizz, 0.9f);
			TE_GELID.addSpecialty(ThermalModsHelper.dustCryotheum, 0.9f);
			TE_DANTE.addSpecialty(ThermalModsHelper.dustSulfur, 0.9f);
			TE_PYRO.addSpecialty(ThermalModsHelper.dustPyrotheum, 0.9f);
			TE_SHOCKING.addSpecialty(ThermalModsHelper.dustBlitz, 0.9f);
			TE_AMPED.addSpecialty(ThermalModsHelper.dustAerotheum, 0.9f);
			TE_GROUNDED.addSpecialty(ThermalModsHelper.dustBasalz, 0.9f);
			TE_ROCKING.addSpecialty(ThermalModsHelper.dustPetrotheum, 0.9f);
			TE_WINSOME.addSpecialty(ThermalModsHelper.dustPlatinum, 0.9f);
			TE_ENDEARING.addSpecialty(ThermalModsHelper.enderiumNugget, 0.9f);
		}
	}
	
	public static void initRedstoneArsenelProducts() {
		RSA_FLUXED.addProduct(ForestryHelper.itemHoneycomb, 0.10f);
		
		if (RedstoneArsenalHelper.isActive()) {
			RSA_FLUXED.addSpecialty(RedstoneArsenalHelper.fluxNugget, 0.9f);
		} else if (OreDictionary.getOres("nuggetElectrumFlux").size() > 0){
			RSA_FLUXED.addSpecialty(OreDictionary.getOres("nuggetElectrumFlux").get(0), 0.9f);
		} else {
			RSA_FLUXED.setInactive();
		}
	}
	
	public static void initBotaniaProducts() {
		BOT_ROOTED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.10f);
		BOT_BOTANIC.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.10f)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.5f);
		BOT_BLOSSOM.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.20f)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.5f);
		BOT_FLORAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 0.25f)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.5f);
		BOT_VAZBEE.addProduct(Config.combs.getStackForType(CombType.SOUL), 0.5f)
			.addProduct(new ItemStack(Items.dye, 1, 9), 0.20f)
			.addProduct(new ItemStack(Blocks.wool, 1, 9), 0.2f)
			.addProduct(new ItemStack(Blocks.red_flower, 1, 7), 0.6f)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 0.15f);
		BOT_SOMNOLENT.addProduct(Config.combs.getStackForType(CombType.WATERY), 0.8f)
			.addProduct(Config.combs.getStackForType(CombType.SOUL), 0.15f);
		BOT_DREAMING.addProduct(Config.combs.getStackForType(CombType.WATERY), 0.16f)
			.addProduct(Config.combs.getStackForType(CombType.SOUL), 0.33f);
		BOT_ALFHEIM.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 0.28f);
		
		if (BotaniaHelper.isActive()) {
			for (int i = 0; i < 16; ++i) {
				ItemStack petal = new ItemStack(BotaniaHelper.itemPetal, 1, i);
				BOT_BOTANIC.addSpecialty(petal, 0.1f);
				BOT_BLOSSOM.addSpecialty(petal, 0.4f);
				BOT_FLORAL.addSpecialty(petal, 0.16f);
			}

			for (PastureSeed type : PastureSeed.values()) {
				BOT_VAZBEE.addSpecialty(new ItemStack(BotaniaHelper.itemPastureSeed, 1, type.ordinal()), 0.4f);
			}
		}
	}
	
	public static void initAppEngProducts() {
		AE_SKYSTONE.addProduct(Config.combs.getStackForType(CombType.EARTHY), 0.19f);
		if (AppliedEnergisticsHelper.skystone != null) {
			AE_SKYSTONE.addSpecialty(new ItemStack(AppliedEnergisticsHelper.skystone), 0.2f);
		}
		else {
			AE_SKYSTONE.setInactive();
		}
	}
}
