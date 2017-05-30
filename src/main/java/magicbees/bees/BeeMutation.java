package magicbees.bees;

import magicbees.main.utils.LogHelper;
import magicbees.main.utils.MoonPhase;
import magicbees.main.utils.compat.AppliedEnergisticsHelper;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.EquivalentExchangeHelper;
import magicbees.main.utils.compat.ExtraBeesHelper;
import magicbees.main.utils.compat.ForestryHelper;
import magicbees.main.utils.compat.RedstoneArsenalHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalModsHelper;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeMutationCustom;
import forestry.api.apiculture.IBeeMutationFactory;

public class BeeMutation {
	private static IBeeMutationFactory beeMutationFactory = forestry.api.apiculture.BeeManager.beeMutationFactory;

	public static void setupMutations() {
		IAlleleBeeSpecies baseA, baseB;
		IBeeMutationCustom mutation;
		
		// Forestry + These -> Common
		
		BeeSpecies[] magicMundane = new BeeSpecies[] { BeeSpecies.MYSTICAL, BeeSpecies.SORCEROUS, BeeSpecies.UNUSUAL, BeeSpecies.ATTUNED };
		String[] forestryMundane = new String[] { "Forest", "Meadows", "Modest", "Wintry", "Tropical", "Marshy" };
		String[] binnieMundane = new String[] { "marble", "rock", "water", "basalt" };
		
		for (BeeSpecies species : magicMundane) {
			for (String str : forestryMundane) {
				beeMutationFactory.createMutation(species.getSpecies(), Allele.getBaseSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
			}
			if (ExtraBeesHelper.isActive()) {
				for (String str : binnieMundane) {
					LogHelper.info("Registering " + str);
					try {
						beeMutationFactory.createMutation(species.getSpecies(), Allele.getExtraSpecies(str), ForestryHelper.getTemplateForestryForSpecies("Common"), 15);
					}
					catch (Exception e) {
						LogHelper.info("Unable to register! This mutation will not be available.");
					}
				}
			}
			beeMutationFactory.createMutation(species.getSpecies(), Allele.getBaseSpecies("Common"), ForestryHelper.getTemplateForestryForSpecies("Cultivated"), 12);
			beeMutationFactory.createMutation(species.getSpecies(), Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH.getGenome(), 12);
		}
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.ESOTERIC.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.ESOTERIC.getSpecies(), BeeSpecies.MYSTERIOUS.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.ESOTERIC.getSpecies(), BeeSpecies.MYSTERIOUS.getSpecies(), BeeSpecies.ARCANE.getGenome(), 8)
				.addMutationCondition(new MoonPhaseMutationBonus(MoonPhase.WAXING_CRESCENT, MoonPhase.WAXING_GIBBOUS, 1.2f));
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Cultivated"), BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.CHARMED.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.CHARMED.getSpecies(), BeeSpecies.ENCHANTED.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.CHARMED.getSpecies(), BeeSpecies.ENCHANTED.getSpecies(), BeeSpecies.SUPERNATURAL.getGenome(), 8)
				.addMutationCondition(new MoonPhaseMutationBonus(MoonPhase.WANING_GIBBOUS, MoonPhase.WANING_CRESCENT, 1.2f));
		
		beeMutationFactory.createMutation(BeeSpecies.ARCANE.getSpecies(), BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.ETHEREAL.getGenome(), 7);
		
		beeMutationFactory.createMutation(BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.WINDY.getGenome(), 14)
				.requireResource("treeLeaves");
		beeMutationFactory.createMutation(BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.WATERY.getGenome(), 14)
				.requireResource(Blocks.water, 0);
		beeMutationFactory.createMutation(BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.EARTHY.getGenome(), 14)
				.requireResource(Blocks.brick_block, 0);
		beeMutationFactory.createMutation(BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.FIREY.getGenome(), 14)
				.requireResource(Blocks.lava, 0);
		
		beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.ATTUNED.getSpecies(), BeeSpecies.AWARE.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.AWARE.getSpecies(), BeeSpecies.SPIRIT.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.ATTUNED.getSpecies(), BeeSpecies.AWARE.getSpecies(), BeeSpecies.SPIRIT.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.AWARE.getSpecies(), BeeSpecies.SPIRIT.getSpecies(), BeeSpecies.SOUL.getGenome(), 7);
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Monastic"), BeeSpecies.ARCANE.getSpecies(), BeeSpecies.PUPIL.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.ARCANE.getSpecies(), BeeSpecies.PUPIL.getSpecies(), BeeSpecies.SCHOLARLY.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.PUPIL.getSpecies(), BeeSpecies.SCHOLARLY.getSpecies(), BeeSpecies.SAVANT.getGenome(), 6);
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.TIMELY.getGenome(), 8);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.TIMELY.getSpecies(), BeeSpecies.LORDLY.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.TIMELY.getSpecies(), BeeSpecies.LORDLY.getSpecies(), BeeSpecies.DOCTORAL.getGenome(), 7);
		
		beeMutationFactory.createMutation(BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.HATEFUL.getGenome(), 9)
				.restrictBiomeType(BiomeDictionary.Type.NETHER);
		beeMutationFactory.createMutation(BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.HATEFUL.getSpecies(), BeeSpecies.SPITEFUL.getGenome(), 7)
				.restrictBiomeType(BiomeDictionary.Type.NETHER);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Demonic"), BeeSpecies.SPITEFUL.getSpecies(), BeeSpecies.WITHERING.getGenome(), 6)
				.restrictBiomeType(BiomeDictionary.Type.NETHER);

		beeMutationFactory.createMutation(Allele.getBaseSpecies("Modest"), BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.SKULKING.getGenome(), 12);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Tropical"), BeeSpecies.SKULKING.getSpecies(), BeeSpecies.SPIDERY.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.BATTY.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.GHASTLY.getGenome(), 9);
		beeMutationFactory.createMutation(BeeSpecies.GHASTLY.getSpecies(), BeeSpecies.HATEFUL.getSpecies(), BeeSpecies.SMOULDERING.getGenome(), 7)
				.restrictBiomeType(BiomeDictionary.Type.NETHER);
		beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.MYSTERIOUS.getSpecies(), BeeSpecies.BIGBAD.getGenome(), 7)
				.addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.FULL));
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Common"), BeeSpecies.SKULKING.getSpecies(), BeeSpecies.CHICKEN.getGenome(), 12)
				.restrictBiomeType(Type.FOREST);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Common"), BeeSpecies.SKULKING.getSpecies(), BeeSpecies.BEEF.getGenome(), 12)
				.restrictBiomeType(Type.PLAINS);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Common"), BeeSpecies.SKULKING.getSpecies(), BeeSpecies.PORK.getGenome(), 12)
				.restrictBiomeType(Type.MOUNTAIN);
		beeMutationFactory.createMutation(BeeSpecies.PORK.getSpecies(), BeeSpecies.SKULKING.getSpecies(), BeeSpecies.SHEEPISH.getGenome(), 13)
				.restrictBiomeType(Type.PLAINS);
		beeMutationFactory.createMutation(BeeSpecies.BEEF.getSpecies(), BeeSpecies.SHEEPISH.getSpecies(), BeeSpecies.HORSE.getGenome(), 12)
				.restrictBiomeType(Type.PLAINS);
		beeMutationFactory.createMutation(BeeSpecies.CHICKEN.getSpecies(), BeeSpecies.SPIDERY.getSpecies(), BeeSpecies.CATTY.getGenome(), 15)
				.restrictBiomeType(Type.JUNGLE);
		beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.BATTY.getGenome(), 9);
		if (ThaumcraftHelper.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.PUPIL.getSpecies(), BeeSpecies.BRAINY.getGenome(), 9);
		}
		else {
			beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.MUTABLE.getSpecies(), BeeSpecies.BRAINY.getGenome(), 14);
		}
		
		beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.OBLIVION.getSpecies(), BeeSpecies.NAMELESS.getGenome(), 10);
		beeMutationFactory.createMutation(BeeSpecies.OBLIVION.getSpecies(), BeeSpecies.NAMELESS.getSpecies(), BeeSpecies.ABANDONED.getGenome(), 8);
		beeMutationFactory.createMutation(BeeSpecies.NAMELESS.getSpecies(), BeeSpecies.ABANDONED.getSpecies(), BeeSpecies.FORLORN.getGenome(), 6);
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.ABANDONED.getSpecies(), BeeSpecies.DRACONIC.getGenome(), 6)
				.restrictBiomeType(BiomeDictionary.Type.END);
		
		beeMutationFactory.createMutation(BeeSpecies.UNUSUAL.getSpecies(), BeeSpecies.ELDRITCH.getSpecies(), BeeSpecies.MUTABLE.getGenome(), 12);
		beeMutationFactory.createMutation(BeeSpecies.UNUSUAL.getSpecies(), BeeSpecies.MUTABLE.getSpecies(), BeeSpecies.TRANSMUTING.getGenome(), 9);
		beeMutationFactory.createMutation(BeeSpecies.UNUSUAL.getSpecies(), BeeSpecies.MUTABLE.getSpecies(), BeeSpecies.CRUMBLING.getGenome(), 9);
		
		beeMutationFactory.createMutation(BeeSpecies.MYSTICAL.getSpecies(), BeeSpecies.MUTABLE.getSpecies(), BeeSpecies.INVISIBLE.getGenome(), 15);
		
		if (BeeSpecies.COPPER.isActive()) {
			beeMutationFactory.createMutation(Allele.getBaseSpecies("Industrious"), Allele.getBaseSpecies("Meadows"), BeeSpecies.COPPER.getGenome(), 12)
					.requireResource("blockCopper");
		}
		
		if (BeeSpecies.TIN.isActive()) {
			beeMutationFactory.createMutation(Allele.getBaseSpecies("Industrious"), Allele.getBaseSpecies("Forest"), BeeSpecies.TIN.getGenome(), 12)
					.requireResource("blockTin");
		}
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Common"), Allele.getBaseSpecies("Industrious"), BeeSpecies.IRON.getGenome(), 12)
				.requireResource(Blocks.iron_block, 0);
		
		if (BeeSpecies.LEAD.isActive()) {
			baseA = (BeeSpecies.TIN.isActive()) ? BeeSpecies.TIN.getSpecies() : (BeeSpecies.COPPER.isActive()) ? BeeSpecies.COPPER.getSpecies() : BeeSpecies.IRON.getSpecies();
			mutation = beeMutationFactory.createMutation(baseA, Allele.getBaseSpecies("Common"), BeeSpecies.LEAD.getGenome(), 10);
			if (OreDictionary.getOres("blockLead").size() > 0) {
				mutation.requireResource("blockLead");
			}
		}

		if (BeeSpecies.SILVER.isActive()) {
			mutation = beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), Allele.getBaseSpecies("Modest"), BeeSpecies.SILVER.getGenome(), 8);
			if (OreDictionary.getOres("blockSilver").size() > 0) {
				mutation.requireResource("blockSilver");
			}
		}
		
		baseA = (BeeSpecies.EE_MINIUM.isActive()) ? BeeSpecies.EE_MINIUM.getSpecies() : Allele.getBaseSpecies("Imperial");
		baseB = (BeeSpecies.LEAD.isActive()) ? BeeSpecies.LEAD.getSpecies() : BeeSpecies.IRON.getSpecies();
		beeMutationFactory.createMutation(baseA, baseB, BeeSpecies.GOLD.getGenome(), 8)
				.requireResource(Blocks.gold_block, 0);
		
		if (BeeSpecies.ALUMINUM.isActive()) {
			beeMutationFactory.createMutation(Allele.getBaseSpecies("Industrious"), Allele.getBaseSpecies("Cultivated"), BeeSpecies.ALUMINUM.getGenome(), 10)
					.requireResource("blockAluminum");
		}
		
		if (BeeSpecies.ARDITE.isActive()) {
			beeMutationFactory.createMutation(Allele.getBaseSpecies("Industrious"), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.ARDITE.getGenome(), 9)
					.requireResource("blockArdite");
		}
		
		if (BeeSpecies.COBALT.isActive()) {
			boolean hasTConCobalt = OreDictionary.getOres("nuggetCobalt").size() > 0 && OreDictionary.getOres("blockCobalt").size() > 0;
			boolean hasNaturalCobalt = OreDictionary.getOres("nuggetNaturalCobalt").size() > 0 && OreDictionary.getOres("blockNaturalCobalt").size() > 0;
			if (hasTConCobalt) {
				beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.COBALT.getGenome(), 11)
					.requireResource("blockCobalt");
			}
			if (hasNaturalCobalt) {
				beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.COBALT.getGenome(), 11)
					.requireResource("blockNaturalCobalt");
			}
			if (!(hasTConCobalt || hasNaturalCobalt)) {
				beeMutationFactory.createMutation(Allele.getBaseSpecies("Imperial"), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.COBALT.getGenome(), 7);
			}
		}
		
		if (BeeSpecies.MANYULLYN.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.ARDITE.getSpecies(), BeeSpecies.COBALT.getSpecies(), BeeSpecies.MANYULLYN.getGenome(), 9)
					.requireResource("blockManyullyn");
		}
		
		if (BeeSpecies.OSMIUM.isActive()) {
			baseA = (BeeSpecies.SILVER.isActive()) ? BeeSpecies.SILVER.getSpecies() : Allele.getBaseSpecies("Imperial");
			baseB = (BeeSpecies.COBALT.isActive()) ? BeeSpecies.COBALT.getSpecies() : BeeSpecies.INFERNAL.getSpecies();
			mutation = beeMutationFactory.createMutation(baseA, baseB, BeeSpecies.OSMIUM.getGenome(), 11);
			if (OreDictionary.getOres("blockOsmium").size() > 0) {
				mutation.requireResource("blockOsmium");
			}
		}
		
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Austere"), BeeSpecies.GOLD.getSpecies(), BeeSpecies.DIAMOND.getGenome(), 7)
				.requireResource("blockDiamond");
		baseA = (BeeSpecies.SILVER.isActive()) ? BeeSpecies.SILVER.getSpecies() : Allele.getBaseSpecies("Imperial");
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Austere"), baseA, BeeSpecies.EMERALD.getGenome(), 6)
				.requireResource("blockEmerald");
		beeMutationFactory.createMutation(Allele.getBaseSpecies("Rural"), BeeSpecies.COPPER.getSpecies(), BeeSpecies.APATITE.getGenome(), 12)
				.requireResource("blockApatite");
		
		if (BeeSpecies.SILICON.isActive()) {
			baseA = (BeeSpecies.AE_SKYSTONE.isActive()) ? BeeSpecies.AE_SKYSTONE.getSpecies() : BeeSpecies.EARTHY.getSpecies();
			beeMutationFactory.createMutation(baseA, BeeSpecies.IRON.getSpecies(), BeeSpecies.SILICON.getGenome(), 17);
		}
		
		if (BeeSpecies.CERTUS.isActive()) {
			baseA = (BeeSpecies.SILICON.isActive()) ? BeeSpecies.SILICON.getSpecies() : BeeSpecies.IRON.getSpecies();
			baseB = (BeeSpecies.AE_SKYSTONE.isActive()) ? BeeSpecies.AE_SKYSTONE.getSpecies() : BeeSpecies.EARTHY.getSpecies();
			beeMutationFactory.createMutation(baseA, baseB, BeeSpecies.CERTUS.getGenome(), 13);
		}
		
		if (BeeSpecies.FLUIX.isActive()) {
			if (BeeSpecies.CERTUS.isActive()) {
				baseA = BeeSpecies.CERTUS.getSpecies();
			}
			else if (BeeSpecies.SILICON.isActive()) {
				baseA = BeeSpecies.SILICON.getSpecies();
			}
			else {
				baseA = BeeSpecies.IRON.getSpecies();
			}
			baseB = (BeeSpecies.AE_SKYSTONE.isActive()) ? BeeSpecies.AE_SKYSTONE.getSpecies() : BeeSpecies.EARTHY.getSpecies();
			beeMutationFactory.createMutation(baseA, baseB, BeeSpecies.FLUIX.getGenome(), 17);
		}
		
		if (ThaumcraftHelper.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.WINDY.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.TC_AIR.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.AIR.ordinal());
			beeMutationFactory.createMutation(BeeSpecies.FIREY.getSpecies(), BeeSpecies.FIREY.getSpecies(), BeeSpecies.TC_FIRE.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.FIRE.ordinal());
			beeMutationFactory.createMutation(BeeSpecies.WATERY.getSpecies(), BeeSpecies.WATERY.getSpecies(), BeeSpecies.TC_WATER.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.WATER.ordinal());
			beeMutationFactory.createMutation(BeeSpecies.EARTHY.getSpecies(), BeeSpecies.EARTHY.getSpecies(), BeeSpecies.TC_EARTH.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.EARTH.ordinal());
			beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.ARCANE.getSpecies(), BeeSpecies.TC_ORDER.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.ORDER.ordinal());
			beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.SUPERNATURAL.getSpecies(), BeeSpecies.TC_CHAOS.getGenome(), 8)
					.requireResource(ThaumcraftHelper.crystal, ThaumcraftHelper.ShardType.CHAOS.ordinal());
			
			beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.TC_VIS.getGenome(), 9)
					.addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.WAXING_HALF, MoonPhase.WANING_HALF));
			beeMutationFactory.createMutation(BeeSpecies.ATTUNED.getSpecies(), BeeSpecies.TC_VIS.getSpecies(), BeeSpecies.TC_REJUVENATING.getGenome(), 8);
			beeMutationFactory.createMutation(BeeSpecies.TC_VIS.getSpecies(), BeeSpecies.TC_REJUVENATING.getSpecies(), BeeSpecies.TC_EMPOWERING.getGenome(), 6)
					.addMutationCondition(new MoonPhaseMutationBonus(MoonPhase.FULL, 1.322f));
			beeMutationFactory.createMutation(BeeSpecies.TC_REJUVENATING.getSpecies(), BeeSpecies.TC_EMPOWERING.getSpecies(), BeeSpecies.TC_NEXUS.getGenome(), 10)
					.restrictBiomeType(Type.MAGICAL)
					.requireResource(ThaumcraftHelper.airy, ThaumcraftHelper.AiryBlockType.NODE.ordinal());
			
			beeMutationFactory.createMutation(BeeSpecies.TRANSMUTING.getSpecies(), BeeSpecies.TC_EMPOWERING.getSpecies(), BeeSpecies.TC_TAINT.getGenome(), 11)
					.addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.NEW));
			beeMutationFactory.createMutation(BeeSpecies.TRANSMUTING.getSpecies(), BeeSpecies.TC_REJUVENATING.getSpecies(), BeeSpecies.TC_PURE.getGenome(), 8)
					.restrictBiomeType(Type.MAGICAL)
					.addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.NEW));
			beeMutationFactory.createMutation(BeeSpecies.BIGBAD.getSpecies(), BeeSpecies.TC_VIS.getSpecies(), BeeSpecies.TC_HUNGRY.getGenome(), 20);
			
			beeMutationFactory.createMutation(BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.GHASTLY.getSpecies(), BeeSpecies.TC_WISPY.getGenome(), 9)
					.addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.WANING_CRESCENT, MoonPhase.WAXING_CRESCENT));
			
			beeMutationFactory.createMutation(BeeSpecies.IRON.getSpecies(), BeeSpecies.TC_TAINT.getSpecies(), BeeSpecies.TC_VOID.getGenome(), 5)
					.restrictBiomeType(Type.MAGICAL)
					.requireNight();
		}
		
		if (ArsMagicaHelper.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.ARCANE.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.AM_ESSENCE.getGenome(), 10);
			beeMutationFactory.createMutation(BeeSpecies.ARCANE.getSpecies(), BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.AM_QUINTESSENCE.getGenome(), 7);
			
			beeMutationFactory.createMutation(BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.AM_AIR.getGenome(), 10);
			beeMutationFactory.createMutation(BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.EARTHY.getSpecies(), BeeSpecies.AM_EARTH.getGenome(), 10);
			beeMutationFactory.createMutation(BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.FIREY.getSpecies(), BeeSpecies.AM_FIRE.getGenome(), 10);
			beeMutationFactory.createMutation(BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.WATERY.getSpecies(), BeeSpecies.AM_WATER.getGenome(), 10);
			beeMutationFactory.createMutation(BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.ETHEREAL.getSpecies(), BeeSpecies.AM_ARCANE.getGenome(), 10);
			
			beeMutationFactory.createMutation(BeeSpecies.WINDY.getSpecies(), BeeSpecies.AM_AIR.getSpecies(), BeeSpecies.AM_LIGHTNING.getGenome(), 8);
			beeMutationFactory.createMutation(BeeSpecies.EARTHY.getSpecies(), BeeSpecies.AM_EARTH.getSpecies(), BeeSpecies.AM_PLANT.getGenome(), 8);
			beeMutationFactory.createMutation(BeeSpecies.WATERY.getSpecies(), BeeSpecies.AM_WATER.getSpecies(), BeeSpecies.AM_ICE.getGenome(), 8);
			
			beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.AM_ESSENCE.getSpecies(), BeeSpecies.AM_VORTEX.getGenome(), 8);
			beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), BeeSpecies.GHASTLY.getSpecies(), BeeSpecies.AM_WIGHT.getGenome(), 8);
		}
		
		if (EquivalentExchangeHelper.isActive()) {
			beeMutationFactory.createMutation(Allele.getBaseSpecies("Frugal"), BeeSpecies.MUTABLE.getSpecies(), BeeSpecies.EE_MINIUM.getGenome(), 8);
		}
		
		if (ThermalModsHelper.isActive()) {
			baseB = BeeSpecies.TE_DESTABILIZED.getSpecies();
		} else if (ThaumcraftHelper.isActive()){
			baseB = BeeSpecies.TC_ORDER.getSpecies();
		} else {
			BeeSpecies.RSA_FLUXED.setInactive();
		}
		if (BeeSpecies.RSA_FLUXED.isActive()) {
			baseA = (BeeSpecies.TE_ELECTRUM.isActive()) ? BeeSpecies.TE_ELECTRUM.getSpecies() : BeeSpecies.GOLD.getSpecies();
			mutation = beeMutationFactory.createMutation(baseA, baseB , BeeSpecies.RSA_FLUXED.getGenome(), 10);
			if(RedstoneArsenalHelper.isActive()){
				mutation.requireResource("blockElectrumFlux");
			}
		}
		
		if (ThermalModsHelper.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.TIN.getSpecies(), BeeSpecies.COPPER.getSpecies(), BeeSpecies.TE_BRONZE.getGenome(), 12)
					.requireResource("blockBronze");
			
			beeMutationFactory.createMutation(BeeSpecies.GOLD.getSpecies(), BeeSpecies.SILVER.getSpecies(), BeeSpecies.TE_ELECTRUM.getGenome(), 10)
					.requireResource("blockElectrum");
			
			beeMutationFactory.createMutation(BeeSpecies.IRON.getSpecies(), BeeSpecies.ESOTERIC.getSpecies(), BeeSpecies.TE_NICKEL.getGenome(), 14)
					.requireResource("blockNickel");
			
			beeMutationFactory.createMutation(BeeSpecies.IRON.getSpecies(), BeeSpecies.TE_NICKEL.getSpecies(), BeeSpecies.TE_INVAR.getGenome(), 14)
					.requireResource("blockInvar");
			
			beeMutationFactory.createMutation(BeeSpecies.TE_NICKEL.getSpecies(), BeeSpecies.TE_INVAR.getSpecies(), BeeSpecies.TE_PLATINUM.getGenome(), 10)
					.requireResource("blockPlatinum");
			
			beeMutationFactory.createMutation(BeeSpecies.TIN.getSpecies(), BeeSpecies.COPPER.getSpecies(), BeeSpecies.TE_BRONZE.getGenome(), 12)
					.requireResource("blockBronze");
			
			beeMutationFactory.createMutation(BeeSpecies.SPITEFUL.getSpecies(), BeeSpecies.TIN.getSpecies(), BeeSpecies.TE_COAL.getGenome(), 12)
					.requireResource(Blocks.coal_ore, 0);
			
			beeMutationFactory.createMutation(BeeSpecies.SPITEFUL.getSpecies(), Allele.getBaseSpecies("Industrious"), BeeSpecies.TE_DESTABILIZED.getGenome(), 12)
					.requireResource(Blocks.redstone_ore, 0);
			
			beeMutationFactory.createMutation(BeeSpecies.SMOULDERING.getSpecies(), BeeSpecies.INFERNAL.getSpecies(), BeeSpecies.TE_LUX.getGenome(), 12)
					.requireResource(Blocks.glowstone, 0);
			
			beeMutationFactory.createMutation(BeeSpecies.SMOULDERING.getSpecies(), Allele.getBaseSpecies("Austere"), BeeSpecies.TE_DANTE.getGenome(), 12)
					.restrictBiomeType(BiomeDictionary.Type.NETHER);
			
			beeMutationFactory.createMutation(BeeSpecies.TE_DANTE.getSpecies(), BeeSpecies.TE_COAL.getSpecies(), BeeSpecies.TE_PYRO.getGenome(), 8)
					.restrictBiomeType(BiomeDictionary.Type.NETHER);
			
			beeMutationFactory.createMutation(BeeSpecies.SKULKING.getSpecies(), Allele.getBaseSpecies("Wintry"), BeeSpecies.TE_BLIZZY.getGenome(), 12);
			beeMutationFactory.createMutation(BeeSpecies.TE_BLIZZY.getSpecies(), Allele.getBaseSpecies("Icy"), BeeSpecies.TE_GELID.getGenome(), 8);
			
			beeMutationFactory.createMutation(BeeSpecies.SMOULDERING.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.TE_SHOCKING.getGenome(), 13);
			beeMutationFactory.createMutation(BeeSpecies.TE_SHOCKING.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.TE_AMPED.getGenome(), 8);
			
			beeMutationFactory.createMutation(BeeSpecies.SMOULDERING.getSpecies(), BeeSpecies.EARTHY.getSpecies(), BeeSpecies.TE_GROUNDED.getGenome(), 12);
			beeMutationFactory.createMutation(BeeSpecies.TE_GROUNDED.getSpecies(), BeeSpecies.EARTHY.getSpecies(), BeeSpecies.TE_ROCKING.getGenome(), 9);

			beeMutationFactory.createMutation(BeeSpecies.TE_PLATINUM.getSpecies(), BeeSpecies.OBLIVION.getSpecies(), BeeSpecies.TE_WINSOME.getGenome(), 12);

			beeMutationFactory.createMutation(BeeSpecies.TE_WINSOME.getSpecies(), BeeSpecies.TE_COAL.getSpecies(), BeeSpecies.TE_ENDEARING.getGenome(), 8)
					.requireResource("blockEnderium");
		}
		
		if (BotaniaHelper.isActive()) {
			beeMutationFactory.createMutation(BeeSpecies.ELDRITCH.getSpecies(), Allele.getBaseSpecies("Forest"), BeeSpecies.BOT_ROOTED.getGenome(), 15)
				.requireResource(BotaniaHelper.blockLivingWood, 0);
			
			beeMutationFactory.createMutation(BeeSpecies.BOT_ROOTED.getSpecies(), BeeSpecies.WATERY.getSpecies(), BeeSpecies.BOT_SOMNOLENT.getGenome(), 16)
				.requireNight();
			beeMutationFactory.createMutation(BeeSpecies.WINDY.getSpecies(), BeeSpecies.BOT_SOMNOLENT.getSpecies(), BeeSpecies.BOT_DREAMING.getGenome(), 8)
				.requireNight();
			
			beeMutationFactory.createMutation(BeeSpecies.BOT_BOTANIC.getSpecies(), BeeSpecies.EARTHY.getSpecies(), BeeSpecies.BOT_BLOSSOM.getGenome(), 12);
			beeMutationFactory.createMutation(BeeSpecies.BOT_BOTANIC.getSpecies(), BeeSpecies.BOT_BLOSSOM.getSpecies(), BeeSpecies.BOT_FLORAL.getGenome(), 8);
		}
		
		if (AppliedEnergisticsHelper.isActive()) {
			if (BeeSpecies.AE_SKYSTONE.isActive()) {
				beeMutationFactory.createMutation(BeeSpecies.EARTHY.getSpecies(), BeeSpecies.WINDY.getSpecies(), BeeSpecies.AE_SKYSTONE.getGenome(), 20)
					.requireResource(AppliedEnergisticsHelper.skystone, 0);
			}
		}
	}

	
}
