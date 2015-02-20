package magicbees.bees;

import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalExpansionHelper;
import net.minecraft.potion.Potion;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IAlleleRegistry;

public class Allele implements IAllele
{
	public static AlleleFloat speedBlinding;
	
	public static IAlleleBeeEffect forestryBaseEffect;
	
	public static IAlleleFlowers flowerBookshelf;
	public static IAlleleFlowers flowerThaumcraft;
	public static IAlleleFlowers flowerArsMagica;
	public static IAlleleFlowers flowerAuraNode;
	
	public static IAlleleEffect effectCleansing;
	public static IAlleleEffect effectDigSpeed;
	public static IAlleleEffect effectMoveSpeed;
	public static IAlleleEffect effectSlowSpeed;
	public static IAlleleEffect effectWithering;
	public static IAlleleEffect effectTransmuting;
	public static IAlleleEffect effectCrumbling;
	public static IAlleleEffect effectInvisibility;
	
	public static IAlleleEffect spawnBrainyZombie;
	public static IAlleleEffect spawnWisp;
	public static IAlleleEffect spawnBats;
	public static IAlleleEffect spawnGhast;
	public static IAlleleEffect spawnSpider;
	public static IAlleleEffect spawnBlaze;
	public static IAlleleEffect spawnBlizz;
	public static IAlleleEffect spawnManaDrainer;
	public static IAlleleEffect spawnWispOrHecate;
	
	/*public static IAlleleEffect effectNodeAttract;
	public static IAlleleEffect effectNodePurify;
	public static IAlleleEffect effectNodeFlux;
	public static IAlleleEffect effectNodeCharge;*/
	
	public static void setupAdditionalAlleles()
	{
		forestryBaseEffect = (IAlleleBeeEffect)getBaseAllele("effectNone");
		
		Allele.speedBlinding = new AlleleFloat("speedBlinding", 2f, false);
		
		Allele.flowerBookshelf = new AlleleFlower("Bookshelf", new FlowerProviderBookshelf(), true);
		
		if (ThaumcraftHelper.isActive())
		{
			Allele.flowerThaumcraft = new AlleleFlower("ThaumcraftPlant", new FlowerProviderThaumcraftFlower(), false);
			Allele.flowerAuraNode = new AlleleFlower("AuraNode", new FlowerProviderAuraNode(), true);

			Allele.spawnBrainyZombie = new AlleleEffectSpawnMob("Brainy", false, ThaumcraftHelper.Entity.BRAINY_ZOMBIE.entityID)
					.setAggrosPlayerOnSpawn().setThrottle(800).setSpawnsOnPlayerNear(null).setMaxMobsInSpawnZone(2);

			Allele.spawnBats = new AlleleEffectSpawnMob("Batty", false, ThaumcraftHelper.Entity.FIREBAT.entityID)
					.setThrottle(300).setSpawnsOnPlayerNear("Bat");

			Allele.spawnWisp = new AlleleEffectSpawnWisp("Wispy", false, ThaumcraftHelper.Entity.WISP.entityID, "thaumcraft.wisplive")
					.setThrottle(1800).setChanceToSpawn(79);
		}
		else
		{
			Allele.flowerThaumcraft = Allele.flowerAuraNode = (IAlleleFlowers)Allele.getBaseAllele("flowersVanilla");
		}
		
		if (ArsMagicaHelper.isActive())
		{
			Allele.flowerArsMagica = new AlleleFlower("flowerArsMagicaPlant", new FlowerProviderArsMagicaFlower(), false);
			//String id, boolean isDominant, int throttle, String[] mobs, int[] chance
			Allele.spawnManaDrainer = new AlleleEffectSpawnMobWeighted("ManaDrain", true, 20,
					new String[] { ArsMagicaHelper.Name + ".MobManaCreeper", ArsMagicaHelper.Name + ".ManaVortex" },
					new int[] { 60, 2 });
			
			Allele.spawnWispOrHecate = new AlleleEffectSpawnMobWeighted("AMWisp", true, 20,
					new String[] { ArsMagicaHelper.Name + ".MobHecate" },
					new int[] { 10 });
		}
		else
		{
			Allele.flowerArsMagica = (IAlleleFlowers)Allele.getBaseAllele("flowersVanilla");
			Allele.spawnManaDrainer = Allele.spawnWispOrHecate = (IAlleleEffect)Allele.getBaseAllele("effectNone");
		}

		/*Allele.effectNodeAttract = new AlleleEffectAuraNodeAttract("NodeAttract", false, 400);
		Allele.effectNodePurify = new AlleleEffectAuraNodePurify("NodePurify", false, 600, 150);
		Allele.effectNodeFlux = new AlleleEffectAuraNodeFlux("NodeFlux", true, 300, 300);
		Allele.effectNodeCharge = new AlleleEffectAuraNodeCharge("NodeCharge", true, 1200);*/
		
		if (ThermalExpansionHelper.isActive())
		{
			Allele.spawnBlizz = new AlleleEffectSpawnMob("TEBlizzy", true, ThermalExpansionHelper.Entity.BLIZZ.entityID)
					.setThrottle(100).setChanceToSpawn(80);
		}
		else
		{
			Allele.spawnBlizz = (IAlleleEffect)Allele.getBaseAllele("effectNone");
		}

		Allele.effectCleansing = new AlleleEffectCure("Curative", false);
		Allele.effectDigSpeed = new AlleleEffectPotion("DigSpeed", Potion.digSpeed, 15, false);
		Allele.effectMoveSpeed = new AlleleEffectPotion("MoveSpeed", Potion.moveSpeed, 10, false);
		Allele.effectSlowSpeed = new AlleleEffectPotion("SlowSpeed", Potion.moveSlowdown, 3, false).setMalicious();
		Allele.effectWithering = new AlleleEffectPotion("Withering", Potion.wither, 10, false).setMalicious();
		
		Allele.effectTransmuting = new AlleleEffectTransmuting("Transmuting", true);
		Allele.effectCrumbling = new AlleleEffectCrumbling("Crumbling", true);
		
		Allele.effectInvisibility = new AlleleEffectPotion("Invisibility", Potion.invisibility, 10, false);

		Allele.spawnGhast = new AlleleEffectSpawnMob("Ghastly", false, "Ghast", "mob.ghast.moan")
				.setThrottle(2060).setChanceToSpawn(10).setMaxMobsInSpawnZone(1);
		
		Allele.spawnSpider = new AlleleEffectSpawnMob("Spidery", false, "Spider", "mob.spider.step")
				.setThrottle(400).setChanceToSpawn(70).setMaxMobsInSpawnZone(4);
		
		Allele.spawnBlaze = new AlleleEffectSpawnMob("Ablaze", false, "Blaze", "mob.blaze.breathe")
				.setThrottle(800).setChanceToSpawn(60).setMaxMobsInSpawnZone(2);
	}
	
	public static void registerDeprecatedAlleleReplacements()
	{
		IAlleleRegistry registry = AlleleManager.alleleRegistry;

		registry.registerDeprecatedAlleleReplacement("thaumicbees.fertilityHighDominant",	Allele.getBaseAllele("fertilityHigh"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.flowerflowerBookshelf",	flowerBookshelf);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speedBlinding",			speedBlinding);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeAttract",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodePurify",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeFlux",			Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectNodeCharge",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeAttract", 		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodePurify",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeFlux",			Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("magicbees.effectNodeCharge",		Allele.getBaseAllele("effectBeatific"));
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effectCurative",			effectCleansing);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectDigSpeed",	effectDigSpeed);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectMoveSpeed",	effectMoveSpeed);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.effecteffectSlowSpeed",	effectSlowSpeed);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.Ghast",					spawnGhast);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.Spider",					spawnSpider);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEsoteric",			BeeSpecies.ESOTERIC);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesMysterious",		BeeSpecies.MYSTERIOUS);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesArcane",			BeeSpecies.ARCANE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesCharmed",			BeeSpecies.CHARMED);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEnchanted",		BeeSpecies.ENCHANTED);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSupernatural",		BeeSpecies.SUPERNATURAL);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesPupil",			BeeSpecies.PUPIL);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesScholarly",		BeeSpecies.SCHOLARLY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSavant",			BeeSpecies.SAVANT);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesAware",			BeeSpecies.AWARE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSpirit",			BeeSpecies.SPIRIT);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSoul",				BeeSpecies.SOUL);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSkulking",			BeeSpecies.SKULKING);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesGhastly",			BeeSpecies.GHASTLY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSpidery",			BeeSpecies.SPIDERY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesTimely",			BeeSpecies.TIMELY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesLordly",			BeeSpecies.LORDLY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesDoctoral",			BeeSpecies.DOCTORAL);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesIron",				BeeSpecies.IRON);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesGold",				BeeSpecies.GOLD);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesCopper",			BeeSpecies.COPPER);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesTin",				BeeSpecies.TIN);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSilver",			BeeSpecies.SILVER);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesLead",				BeeSpecies.LEAD);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesDiamond",			BeeSpecies.DIAMOND);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEmerald",			BeeSpecies.EMERALD);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesApatite",			BeeSpecies.APATITE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesStark",			BeeSpecies.TC_CHAOS);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesAura",				BeeSpecies.TC_AIR);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesIgnis",			BeeSpecies.TC_FIRE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesAqua",				BeeSpecies.TC_WATER);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesSolum",			BeeSpecies.TC_EARTH);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesPraecantatio",		BeeSpecies.TC_ORDER);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesVis",				BeeSpecies.TC_VIS);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesPure",				BeeSpecies.TC_PURE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesFlux",				BeeSpecies.TC_TAINT);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesNode",				BeeSpecies.TC_ATTRACT);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesRejuvenating",		BeeSpecies.TC_REJUVENATING);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesBrainy",			BeeSpecies.TC_BRAINY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesGossamer",			BeeSpecies.TC_WISPY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesWispy",			BeeSpecies.TC_WISPY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesBatty",			BeeSpecies.TC_BATTY);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesChicken",			BeeSpecies.TC_CHICKEN);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesBeef",				BeeSpecies.TC_BEEF);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesPork",				BeeSpecies.TC_PORK);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesMinium",			BeeSpecies.EE_MINIUM);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEssence",			BeeSpecies.AM_ESSENCE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesQuintessence",		BeeSpecies.AM_QUINTESSENCE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesErde",				BeeSpecies.AM_EARTH);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesLuft",				BeeSpecies.AM_AIR);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesFeuer",			BeeSpecies.AM_FIRE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesWasser",			BeeSpecies.AM_WATER);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesBlitz",			BeeSpecies.AM_LIGHTNING);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesStaude",			BeeSpecies.AM_PLANT);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesEis",				BeeSpecies.AM_ICE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesMagma",			BeeSpecies.AM_FIRE);
		registry.registerDeprecatedAlleleReplacement("magicbees.speciesMagma",				BeeSpecies.AM_FIRE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesArkanen",			BeeSpecies.AM_ARCANE);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesVortex",			BeeSpecies.AM_VORTEX);
		registry.registerDeprecatedAlleleReplacement("thaumicbees.speciesWight",			BeeSpecies.AM_WIGHT);
	}

	public static IAlleleBeeSpecies getBaseSpecies(String name)
	{
		return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("forestry.species").append(name).toString());
	}
	
	public static IAlleleBeeSpecies getExtraSpecies(String name)
	{
		return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("extrabees.species.").append(name).toString());
	}
	
	public static IAllele getBaseAllele(String name)
	{
		return AlleleManager.alleleRegistry.getAllele("forestry." + name);
	}
	
	public static IAllele getAllele(String name)
	{
		return AlleleManager.alleleRegistry.getAllele(name);
	}
	
	private String uid;
	private boolean dominant;

	public Allele(String id, boolean isDominant)
	{
		this.uid = "magicbees." + id;
		this.dominant = isDominant;
		AlleleManager.alleleRegistry.registerAllele(this);
	}

	@Override
	public String getUID()
	{
		return this.uid;
	}

	@Override
	public boolean isDominant()
	{
		return this.dominant;
	}

	@Override
	public String getName()
	{
		return LocalizationManager.getLocalizedString(getUID());
	}

	@Override
	public String getUnlocalizedName()
	{
		return this.uid;
	}

}
