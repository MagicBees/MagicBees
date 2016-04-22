package magicbees.bees;


import magicbees.main.utils.ItemInterface;
import magicbees.main.utils.compat.ExtraBeesHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.genetics.IAllele;

/**
 * Simply a class to hold all the functions to manage species' default genomes.
 *
 * @author MysteriousAges
 */
public class BeeGenomeManager {
	// Basic genome for All thaumic bees.
	private static IAllele[] getTemplateModBase() {
		IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MYSTICAL.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");

		return genome;
	}

	public static IAllele[] addRainResist(IAllele[] genome) {
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateMystical() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MYSTICAL.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateSorcerous() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SORCEROUS.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		
		return genome;
	}
	
	public static IAllele[] getTemplateUnusual() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.UNUSUAL.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		
		return genome;
	}
	
	public static IAllele[] getTemplateAttuned() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ATTUNED.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		
		return genome;
	}
	
	public static IAllele[] getTemplateEldritch() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ELDRITCH.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlower");
		
		return genome;
	}
	
	// Basic genome for Arcane branch bees.
	private static IAllele[] getTemplateBaseArcane() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlow");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		
		return genome;
	}

	// Specialization genome for Esoteric bees.
	public static IAllele[] getTemplateEsoteric() {
		IAllele[] genome = getTemplateBaseArcane();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ESOTERIC.getSpecies();
		
		return genome;
	}

	public static IAllele[] getTemplateMysterious() {
		IAllele[] genome = getTemplateBaseArcane();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MYSTERIOUS.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		
		return genome;
	}

	public static IAllele[] getTemplateArcane() {
		IAllele[] genome = getTemplateBaseArcane();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ARCANE.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringAverage");
		
		return genome;
	}

	private static IAllele[] getTemplateBaseSupernatural() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
		
		return genome;
	}

	public static IAllele[] getTemplateCharmed() {
		IAllele[] genome = getTemplateBaseSupernatural();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CHARMED.getSpecies();
		
		return genome;
	}

	public static IAllele[] getTemplateEnchanted() {
		IAllele[] genome = getTemplateBaseSupernatural();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ENCHANTED.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		
		return genome;
	}

	public static IAllele[] getTemplateSupernatural() {
		IAllele[] genome = getTemplateBaseSupernatural();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SUPERNATURAL.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		
		return genome;
	}
	
	public static IAllele[] getTemplateEthereal() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ETHEREAL.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShortened");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringAverage");
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseElemental() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanNormal");
		
		return genome;
	}
	
	public static IAllele[] getTemplateWatery() {
		IAllele[] genome = getTemplateBaseElemental();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.WATERY.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersSnow");
		
		return genome;
	}
	
	public static IAllele[] getTemplateFirey() {
		IAllele[] genome = getTemplateBaseElemental();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.FIREY.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown1");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersCacti");
		
		return genome;
	}
	
	public static IAllele[] getTemplateEarthy() {
		IAllele[] genome = getTemplateBaseElemental();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EARTHY.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLong");
		
		return genome;
	}
	
	public static IAllele[] getTemplateWindy() {
		IAllele[] genome = getTemplateBaseElemental();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.WINDY.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFaster");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFaster");
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseScholarly() {
		IAllele[] genome = getTemplateModBase();

		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerBookshelf;
		
		return genome;
	}

	public static IAllele[] getTemplatePupil() {
		IAllele[] genome = getTemplateBaseScholarly();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.PUPIL.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlower");
		
		return genome;
	}

	public static IAllele[] getTemplateScholarly() {
		IAllele[] genome = getTemplateBaseScholarly();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SCHOLARLY.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		
		return genome;
	}

	public static IAllele[] getTemplateSavant() {
		IAllele[] genome = getTemplateBaseScholarly();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SAVANT.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseSoul() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateAware() {
		IAllele[] genome = getTemplateBaseSoul();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AWARE.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateSpirit() {
		IAllele[] genome = getTemplateBaseSoul();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SPIRIT.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShortened");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSoul() {
		IAllele[] genome = getTemplateBaseSoul();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SOUL.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanNormal");
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseMalevolent() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFaster");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSkulking() {
		IAllele[] genome = getTemplateBaseMalevolent();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SKULKING.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateBatty() {
		IAllele[] genome = getTemplateBaseMalevolent();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BATTY.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBats;
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarge");
		
		return genome;
	}
	
	public static IAllele[] getTemplateChicken() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CHICKEN.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnChicken;
		
		return genome;
	}
	
	public static IAllele[] getTemplateBeef() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BEEF.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnCow;
		
		return genome;
	}
	
	public static IAllele[] getTemplatePork() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.PORK.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnPig;
		
		return genome;
	}


	public static IAllele[] getTemplateBigbad() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BIGBAD.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnWolf;
		
		return genome;
	}

	public static IAllele[] getTemplateSheepish() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SHEEPISH.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnSheep;
		
		return genome;
	}

	public static IAllele[] getTemplateHorse() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.HORSE.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFastest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnHorse;
		
		return genome;
	}

	public static IAllele[] getTemplateCatty() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CATTY.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth3");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnCat;
		
		return genome;
	}
	
	public static IAllele[] getTemplateGhastly() {
		IAllele[] genome = getTemplateBaseMalevolent();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.GHASTLY.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnGhast;
		
		return genome;
	}
	
	public static IAllele[] getTemplateSpidery() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SPIDERY.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnSpider;
		
		return genome;
	}
	
	public static IAllele[] getTemplateBrainy() {
		IAllele[] genome = getTemplateBaseMalevolent();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BRAINY.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBrainyZombie;
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSmouldering() {
		IAllele[] genome = getTemplateBaseMalevolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SMOULDERING.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBlaze;
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseTemporal() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTimely() {
		IAllele[] genome = getTemplateBaseTemporal();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TIMELY.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectSlowSpeed;
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		
		return genome;
	}

	public static IAllele[] getTemplateLordly() {
		IAllele[] genome = getTemplateBaseTemporal();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.LORDLY.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectSlowSpeed;
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLong");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectDrunkard");
		
		return genome;
	}

	public static IAllele[] getTemplateDoctoral() {
		IAllele[] genome = getTemplateBaseTemporal();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.DOCTORAL.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectSlowSpeed;
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth3");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarge");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectHeroic");
		
		return genome;
	}
	
	private static IAllele[] getTemplateAbominableBase() {
		IAllele[] genome = getTemplateModBase();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.INFERNAL.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersNether");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectAggressive");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShort");
		
		return genome;
	}
	
	public static IAllele[] getTemplateInfernal() {
		IAllele[] genome = getTemplateAbominableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.INFERNAL.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		
		return genome;
	}
	
	public static IAllele[] getTemplateHateful() {
		IAllele[] genome = getTemplateAbominableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.HATEFUL.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectMisanthrope");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSpiteful() {
		IAllele[] genome = getTemplateAbominableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SPITEFUL.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLong");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectMisanthrope");
		
		return genome;
	}
	
	public static IAllele[] getTemplateWithering() {
		IAllele[] genome = getTemplateAbominableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.WITHERING.getSpecies();
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLargest");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectWithering;
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseExtrinsic() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectAggressive");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersEnd");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateOblivion() {
		IAllele[] genome = getTemplateBaseExtrinsic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.OBLIVION.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityMaximum");
		
		return genome;
	}
	
	public static IAllele[] getTemplateNameless() {
		IAllele[] genome = getTemplateBaseExtrinsic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.NAMELESS.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		
		return genome;
	}
	
	public static IAllele[] getTemplateAbandoned() {
		IAllele[] genome = getTemplateBaseExtrinsic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ABANDONED.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectRepulsion");
		
		return genome;
	}
	
	public static IAllele[] getTemplateForlorn() {
		IAllele[] genome = getTemplateBaseExtrinsic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.FORLORN.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectRepulsion");
		
		return genome;
	}
	
	public static IAllele[] getTemplateDraconic() {
		IAllele[] genome = getTemplateBaseExtrinsic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.DRACONIC.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectMisanthrope");
		
		return genome;
	}
	
	private static IAllele[] getTemplateBaseMetallic() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateIron() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.IRON.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateGold() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.GOLD.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateCopper() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.COPPER.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTin() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TIN.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateSilver() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SILVER.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateLead() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.LEAD.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateAluminum() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ALUMINUM.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateArdite() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.ARDITE.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateCobalt() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.COBALT.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateManyullyn() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MANYULLYN.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateOsmium() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.OSMIUM.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLonger");
		
		return genome;
	}
	
	public static IAllele[] getTemplateDiamond() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.DIAMOND.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateEmerald() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EMERALD.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateApatite() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.APATITE.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanNormal");
		
		return genome;
	}
	

	public static IAllele[] getTemplateSilicon() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SILICON.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}

	public static IAllele[] getTemplateCertus() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CERTUS.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlower");
		
		return genome;
	}
	

	public static IAllele[] getTemplateFluix() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.FLUIX.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		
		return genome;
	}
	
	public static IAllele[] getTemplateMutableBase() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShortest");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		
		return genome;
	}
	
	public static IAllele[] getTemplateMutable() {
		IAllele[] genome = getTemplateMutableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MUTABLE.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTransmuting() {
		IAllele[] genome = getTemplateMutableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TRANSMUTING.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectTransmuting;
		
		return genome;
	}
	
	public static IAllele[] getTemplateCrumbling() {
		IAllele[] genome = getTemplateMutableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.CRUMBLING.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectCrumbling;
		
		return genome;
	}
	
	public static IAllele[] getTemplateInvisible() {
		IAllele[] genome = getTemplateMutableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.INVISIBLE.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectInvisibility;
		
		return genome;
	}
	
	private static IAllele[] getTemplateTCBase() {
		IAllele[] genome = getTemplateModBase();

		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerThaumcraft;
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		
		return genome;
	}

	public static IAllele[] getTemplateTCChaos() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_CHAOS.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		
		return genome;
	}

	public static IAllele[] getTemplateTCAir() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_AIR.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFastest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShortened");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLargest");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringMaximum");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectMoveSpeed;
		
		return genome;
	}

	public static IAllele[] getTemplateTCFire() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_FIRE.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFaster");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanNormal");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown3");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		
		return genome;
	}

	public static IAllele[] getTemplateTCWater() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_WATER.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLong");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarge");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectCleansing;
		
		return genome;
	}

	public static IAllele[] getTemplateTCEarth() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_EARTH.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLonger");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown1");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFastest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectDigSpeed;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCOrder() {
		IAllele[] genome = getTemplateTCBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_ORDER.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanNormal");
		
		return genome;
	}
	
	private static IAllele[] getTemplateTCBaseVis() {
		IAllele[] genome = getTemplateTCBase();

		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerAuraNode;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCVis() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_VIS.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlower");

		return genome;
	}
	
	public static IAllele[] getTemplateTCRejuvinating() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_REJUVENATING.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectVisRecharge;
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCEmpowering() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_EMPOWERING.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectNodeEmpower;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCNexus() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_NEXUS.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectNodeRepair;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCTaint() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_TAINT.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectNodeConversionTaint;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCPure() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_PURE.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringAverage");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectNodeConversionPure;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTCHungry() {
		IAllele[] genome = getTemplateTCBaseVis();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_HUNGRY.getSpecies();
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLargest");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectNodeConversionHungry;
		
		return genome;
	}
		
	public static IAllele[] getTemplateTCWispy() {
		IAllele[] genome = getTemplateBaseMalevolent();

		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_WISPY.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnWisp;
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerThaumcraft;
		
		return genome;
	}
	
	public static IAllele[] getTemplaceTCVoid() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TC_VOID.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp1");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectGlacial");
		
		return genome;	}
	
	private static IAllele[] getTemplateEEBase() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarge");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		
		return genome;
	}
	
	public static IAllele[] getTemplateEEMinium() {
		IAllele[] genome = getTemplateEEBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EE_MINIUM.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectTransmuting;
		
		return genome;
	}
	
	private static IAllele[] getTemplateAMBase() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerArsMagica;
		
		return genome;
	}

	public static IAllele[] getTemplateAMEssence() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_ESSENCE.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlow");
		
		return genome;
	}

	public static IAllele[] getTemplateAMQuintessence() {
		IAllele[] genome = getTemplateAMEssence();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_QUINTESSENCE.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringAverage");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectMisanthrope");
		
		return genome;
	}

	public static IAllele[] getTemplateAMEarth() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_EARTH.getSpecies();
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFaster");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		
		return genome;
	}

	public static IAllele[] getTemplateAMAir() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_AIR.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLargest");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		
		return genome;
	}

	public static IAllele[] getTemplateAMFire() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_FIRE.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFast");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		
		return genome;
	}

	public static IAllele[] getTemplateAMWater() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_WATER.getSpecies();
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown1");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanElongated");
		
		return genome;
	}

	public static IAllele[] getTemplateAMLightning() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_LIGHTNING.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShortest");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLargest");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.speedBlinding;
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		if (ExtraBeesHelper.isActive())
		{
			genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getAllele("extrabees.effect.lightning");
		}
		
		return genome;
	}

	public static IAllele[] getTemplateAMPlant() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_PLANT.getSpecies();
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFastest");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		
		return genome;
	}

	public static IAllele[] getTemplateAMIce() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_ICE.getSpecies();
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectGlacial");
		
		return genome;
	}

	public static IAllele[] getTemplateAMArcane() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_ARCANE.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateAMVortex() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_VORTEX.getSpecies();
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLonger");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnManaDrainer;
		
		return genome;
	}
	
	public static IAllele[] getTemplateAMWight() {
		IAllele[] genome = getTemplateAMBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AM_WIGHT.getSpecies();
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnWispOrHecate;
		
		return genome;
	}
	
	private static IAllele[] getTemplateTEBase() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLonger");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
		
		
		return genome;
	}
	
	private static IAllele[] getTemplateTEBaseNether() {
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlow");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersNether");
		
		
		return genome;
	}
	
	private static IAllele[] getTemplateTEEnd() {
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFastest");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEElectrum() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_ELECTRUM.getSpecies();
		
		return genome;
	}

	public static IAllele[] getTemplateTEPlatinum() {
		IAllele[] genome = getTemplateTEBaseNether();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_PLATINUM.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTENickel() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_NICKEL.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEInvar() {
		IAllele[] genome = getTemplateTEBaseNether();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_INVAR.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEBronze() {
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_BRONZE.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateRSAFluxed() {
		IAllele[] genome = getTemplateTEBaseNether();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.RSA_FLUXED.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEDestabilized() {
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_DESTABILIZED.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTELux() {
		IAllele[] genome = getTemplateTEBaseNether();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_LUX.getSpecies();
		
		return genome;
	}

	public static IAllele[] getTemplateTEWinsome() {
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_WINSOME.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEEndearing() {
		IAllele[] genome = getTemplateTEEnd();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_ENDEARING.getSpecies();
		
		return genome;
	}

	public static IAllele[] getTemplateTEDante() {
		IAllele[] genome = getTemplateTEBaseNether();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_DANTE.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEPyro() {
		IAllele[] genome = getTemplateTEEnd();
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_PYRO.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");

		return genome;
	}	
	
	public static IAllele[] getTemplateTEBlizzy() {
		IAllele[] genome = getTemplateTEEnd();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_BLIZZY.getSpecies();
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersSnow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectGlacial");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEGelid() {
		IAllele[] genome = getTemplateTEBlizzy();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_GELID.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBlizz;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEShocking() {
		IAllele[] genome = getTemplateTEEnd();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_SHOCKING.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFast");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEAmped() {
		IAllele[] genome = getTemplateTEShocking();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_AMPED.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBlitz;
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.speedBlinding;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTEGrounded() {
		IAllele[] genome = getTemplateTEEnd();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_GROUNDED.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		
		return genome;
	}
	
	public static IAllele[] getTemplateTERocking() {
		IAllele[] genome = getTemplateTEGrounded();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_ROCKING.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFaster");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.spawnBasalz;
		
		return genome;
	}
	
	public static IAllele[] getTemplateTECoal() {
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.TE_COAL.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotaniaBase() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlower");
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotRooted() {
		IAllele[] genome = getTemplateBotaniaBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_ROOTED.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotBotanic() {
		IAllele[] genome = getTemplateBotRooted();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_BOTANIC.getSpecies();
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerBotania;
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotBlossom() {
		IAllele[] genome = getTemplateBotBotanic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_BLOSSOM.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityMaximum");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringFaster");
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotFloral() {
		IAllele[] genome = getTemplateBotBlossom();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_FLORAL.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityHigh");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringMaximum");
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotVazbee() {
		IAllele[] genome = getTemplateBotFloral();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_VAZBEE.getSpecies();
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotSomnolent() {
		IAllele[] genome = getTemplateBotaniaBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_SOMNOLENT.getSpecies(); 
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowerBotania;
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceUp2");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectSlowSpeed;
		
		return genome;
	}
	
	public static IAllele[] getTemplateBotDreaming() {
		IAllele[] genome = getTemplateBotSomnolent();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_DREAMING.getSpecies();
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedNorm");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryLarger");
		
		return genome;
	}

	public static IAllele[] getTemplateBotAelfheim() {
		IAllele[] genome = getTemplateBotDreaming();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.BOT_ALFHEIM.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectDreaming;
		
		return genome;
	}
	
	public static IAllele[] getTemplateAESkystone() {
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.AE_SKYSTONE.getSpecies();
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSteel(){
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.STEEL.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateObsidian(){
		IAllele[] genome = getTemplateModBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.OBSIDIAN.getSpecies();
		genome[EnumBeeChromosome.TEMPERAUTE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceDown2");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth1");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAlle("boolTrue");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersNether");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectAggressive");
		
		return genome;
	}
	
	public static IAllele[] getTemplateSoularium(){
		IAllele[] genome = getTemplateSoul();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.SOULARIUM.getSpecies();
		
		return genome;
	}
	
	public static IAllele[] getTemplateElectricalSteel(){
		IAllele[] genome = getTemplateSilicon();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EIO_ELECTRICAL.getSpecies();
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		
		return genome;
	}
	
	public static IAllele[] getTemplateDarkSteel(){
		IAllele[] genome = getTemplateMutableBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EIO_DARK_STEEL.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectWithering;
		
		return genome;
	}
	
	public static IAllele[] getTemplatePulsatingIron(){
		IAllele[] genome = getTemplateTEBase();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EIO_PULSATING.getSpecies();
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShort");
		
		return genome;
	}
	
	public static IAllele[] getTemplateEnergeticAlloy(){
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EIO_ENERGETIC.getSpecies();
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedFaster");
		
		return genome;
	}
	
	public static IAllele[] getTemplateVibrantAlloy(){
		IAllele[] genome = getTemplateBaseMetallic();
		
		genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.EIO_VIBRANT.getSpecies();
		
		if (ExtraBeesHelper.isActive()){
			genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getAllele("extrabees.effect.lightning");
		} else {
			genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectIgnition");
		}
		
		return genome;
	}
	
	/*--------------------- Other Stuff ---------------------------------- */
	
	public static ItemStack getBeeNBTForSpecies(BeeSpecies species, EnumBeeType beeType) {
		ItemStack taggedBee;
		switch (beeType) {
			case PRINCESS:
				taggedBee = ItemInterface.getItemStack("beePrincessGE");
				break;
			case QUEEN:
				taggedBee = ItemInterface.getItemStack("beeQueenGE");
				break;
			case DRONE:
			default:
				taggedBee = ItemInterface.getItemStack("beeDroneGE");
				break;
		}
		
		NBTTagCompound tags = new NBTTagCompound();
		
		addGeneToCompound(EnumBeeChromosome.SPECIES, species.getSpecies(), tags);
		
		taggedBee.setTagCompound(tags);
		
		return taggedBee;
	}
	
	private static void addGeneToCompound(EnumBeeChromosome gene, IAllele allele, NBTTagCompound compound) {
		NBTTagCompound geneRoot = new NBTTagCompound();
		compound.setTag("Genome", geneRoot);
		NBTTagList chromosomes = new NBTTagList();
		geneRoot.setTag("Chromosomes", chromosomes);
		
		NBTTagCompound selectedGene = new NBTTagCompound();
		chromosomes.appendTag(selectedGene);
		
		selectedGene.setByte("Slot", (byte)gene.ordinal());
		selectedGene.setString("UID0", allele.getUID());
		selectedGene.setString("UID1", allele.getUID());
	}
}
