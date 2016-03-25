package magicbees.bees;

import magicbees.main.utils.compat.AppliedEnergisticsHelper;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.EquivalentExchangeHelper;
import magicbees.main.utils.compat.RedstoneArsenalHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalModsHelper;
import net.minecraft.item.ItemStack;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IAlleleBeeSpeciesCustom;
import forestry.api.apiculture.IBeeFactory;
import forestry.api.apiculture.IBeeIconColourProvider;
import forestry.api.apiculture.IBeeIconProvider;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.EnumTolerance;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleTolerance;
import forestry.api.genetics.IClassification;

public enum BeeSpecies
{
	MYSTICAL("Mystical", "mysticum", BeeClassification.VEILED,
			0xAFFFB7, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	SORCEROUS("Sorcerous", "fascinatio", BeeClassification.VEILED,
			0xEA9A9A, EnumTemperature.HOT, EnumHumidity.ARID, false, false, true),
	UNUSUAL("Unusual", "inusitatus", BeeClassification.VEILED,
			0x72D361, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	ATTUNED("Attuned", "similis", BeeClassification.VEILED,
			0x0086A8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	
	ELDRITCH("Eldritch", "prodigiosus", BeeClassification.VEILED,
			0x8D75A0, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	
	ESOTERIC("Esoteric", "secretiore", BeeClassification.ARCANE,
			0x001099, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	MYSTERIOUS("Mysterious", "mysticus", BeeClassification.ARCANE,
			0x762bc2, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ARCANE("Arcane", "arcanus", BeeClassification.ARCANE,
			0xd242df, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	CHARMED("Charmed", "larvatus", BeeClassification.SUPERNATURAL,
			0x48EEEC, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ENCHANTED("Enchanted", "cantatus", BeeClassification.SUPERNATURAL,
			0x18e726, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SUPERNATURAL("Supernatural", "coeleste", BeeClassification.SUPERNATURAL,
			0x005614, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	ETHEREAL("Ethereal", "diaphanum",BeeClassification.MAGICAL,
			0xBA3B3B, 0xEFF8FF, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	WATERY("Watery", "aquatilis", BeeClassification.MAGICAL,
			0x313C5E, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	EARTHY("Earthen", "fictili", BeeClassification.MAGICAL,
			0x78822D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	FIREY("Firey", "ardens", BeeClassification.MAGICAL,
			0xD35119, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	WINDY("Windy", "ventosum", BeeClassification.MAGICAL,
			0xFFFDBA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	PUPIL("Pupil", "disciplina", BeeClassification.SCHOLARLY,
			0xFFFF00, EnumTemperature.NORMAL, EnumHumidity.ARID, false, true),
	SCHOLARLY("Scholarly", "studiosis", BeeClassification.SCHOLARLY,
			0x6E0000, EnumTemperature.NORMAL, EnumHumidity.ARID, false, false),
	SAVANT("Savant", "philologus", BeeClassification.SCHOLARLY,
			0xFFA042, EnumTemperature.NORMAL, EnumHumidity.ARID, true, false),

	AWARE("Aware", "sensibilis", BeeClassification.MAGICAL,
			0x5E95B5, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	SPIRIT("Spirit", "larva", BeeClassification.SOUL,
			0xb2964b, EnumTemperature.WARM, EnumHumidity.NORMAL, false, true),
	SOUL("Soul", "anima", BeeClassification.SOUL,
			0x7d591b, EnumTemperature.HELLISH, EnumHumidity.NORMAL, true, false),

	SKULKING("Skulking", "malevolens", BeeClassification.SKULKING,
			0x524827, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	GHASTLY("Ghastly", "pallens", BeeClassification.SKULKING,
			0xccccee, 0xbf877c, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	SPIDERY("Spidery", "araneolus", BeeClassification.SKULKING,
			0x0888888, 0x222222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SMOULDERING("Smouldering", "flagrantia", BeeClassification.SKULKING,
			0xFFC747, 0xEA8344, EnumTemperature.HELLISH, EnumHumidity.NORMAL, false, false),
	BRAINY("TCBrainy", "cerebrum", BeeClassification.SKULKING,
			0x83FF70, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BIGBAD("BigBad", "magnumalum", BeeClassification.SKULKING,
			0xA9344B, 0x453536, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true, true, true),
	CHICKEN("TCChicken", "pullus", BeeClassification.FLESHY,
			0xFF0000, 0xD3D3D3, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BEEF("TCBeef", "bubulae", BeeClassification.FLESHY,
			0xB7B7B7, 0x3F3024, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	PORK("TCPork", "porcina", BeeClassification.FLESHY,
			0xF1AEAC, 0xDF847B, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BATTY("TCBatty", "chiroptera", BeeClassification.FLESHY,
			0x5B482B, 0x271B0F, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SHEEPISH("Sheepish", "balans", BeeClassification.FLESHY,
			0xF7F7F7, 0xCACACA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	HORSE("Horse", "equus", BeeClassification.FLESHY,
			0x906330, 0x7B4E1B, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	CATTY("Catty", "feline", BeeClassification.FLESHY,
			0xECE684, 0x563C24, EnumTemperature.HOT, EnumHumidity.NORMAL, false, true),

	TIMELY("Timely", "gallifreis", BeeClassification.TIME,
			0xC6AF86, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	LORDLY("Lordly", "rassilonis", BeeClassification.TIME,
			0xC6AF86, 0x8E0213, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	DOCTORAL("Doctoral", "medicus qui", BeeClassification.TIME,
			0xDDE5FC, 0x4B6E8C, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	INFERNAL("Infernal", "infernales", BeeClassification.ABOMINABLE,
			0xFF1C1C, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, false, true),
	HATEFUL("Hateful", "odibilis", BeeClassification.ABOMINABLE,
			0xDB00DB, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, false, false),
	SPITEFUL("Spiteful", "maligna", BeeClassification.ABOMINABLE,
			0x5FCC00, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, true, false),
	WITHERING("Withering", "vietus", BeeClassification.ABOMINABLE,
			0x5B5B5B, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, true, false),

	OBLIVION("Oblivion", "oblivioni", BeeClassification.EXTRINSIC,
			0xD5C3E5, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	NAMELESS("Nameless", "sine nomine", BeeClassification.EXTRINSIC,
			0x8ca7cb, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, true),
	ABANDONED("Abandoned", "reliquit", BeeClassification.EXTRINSIC,
			0xc5cb8c, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, true),
	FORLORN("Forlorn", "perditus", BeeClassification.EXTRINSIC,
			0xcba88c, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, true, false),
	DRACONIC("Draconic", "draconic", BeeClassification.EXTRINSIC,
			0x9f56ad, 0x5a3b62, EnumTemperature.COLD, EnumHumidity.NORMAL, true, false),

	IRON("Iron", "ferrus", BeeClassification.METALLIC,
			0x686868, 0xE9E9E9, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	GOLD("Gold", "aurum", BeeClassification.METALLIC,
			0x684B01, 0xFFFF0B, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	COPPER("Copper", "aercus", BeeClassification.METALLIC,
			0x684B01, 0xFFC81A, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TIN("Tin", "stannum", BeeClassification.METALLIC,
			0x3E596D, 0xA6BACB, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SILVER("Silver", "argenteus", BeeClassification.METALLIC,
			0x747C81, 0x96BFC4, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	LEAD("Lead", "plumbeus", BeeClassification.METALLIC,
			0x96BFC4, 0x91A9F3, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ALUMINUM("Aluminum", "aluminium", BeeClassification.METALLIC,
			0xEDEDED, 0x767676, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ARDITE("Ardite", "aurantiaco", BeeClassification.METALLIC,
			0x720000, 0xFF9E00, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	COBALT("Cobalt", "caeruleo", BeeClassification.METALLIC,
			0x03265F, 0x59AAEF, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	MANYULLYN("Manyullyn", "manahmanah", BeeClassification.METALLIC,
			0x481D6D, 0xBD92F1, EnumTemperature.HOT, EnumHumidity.ARID, true, false),
	OSMIUM("Osmium", "hyacintho", BeeClassification.METALLIC,
			0x374B5B, 0x6C7B89, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	
	DIAMOND("Diamond", "diamond", BeeClassification.GEM,
			0x209581, 0x8DF5E3, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	EMERALD("Emerald", "prasinus", BeeClassification.GEM,
			0x005300, 0x17DD62, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	APATITE("Apatite", "apatite", BeeClassification.GEM,
			0x2EA7EC, 0x001D51, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	SILICON("Silicon", "siliconisque", BeeClassification.GEM,
			0xADA2A7, 0x736675, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	CERTUS("Certus", "alia cristallum", BeeClassification.GEM,
			0x93C7FF, 0xA6B8C7, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	FLUIX("Fluix", "alien cristallum", BeeClassification.GEM,
			0xFC639E, 0x534797, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	MUTABLE("Mutable", "mutable", BeeClassification.TRANSMUTING,
			0xDBB24C, 0xE0D5A6, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TRANSMUTING("Transmuting", "transmuting", BeeClassification.TRANSMUTING,
			0xDBB24C, 0xA2D2D8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	CRUMBLING("Crumbling", "crumbling", BeeClassification.TRANSMUTING,
			0xDBB24C, 0xDBA4A4, EnumTemperature.HOT, EnumHumidity.ARID, false, false),

	INVISIBLE("Invisible", "invisible", BeeClassification.VEILED,
			0xffccff, 0xffffff, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// --------- Thaumcraft Bees ---------------------------------------------------------------------------------------
	TC_AIR("TCAir", "aether", BeeClassification.THAUMIC,
			0xD9D636, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_FIRE("TCFire", "praefervidus", BeeClassification.THAUMIC,
			0xE50B0B, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.HOT, EnumHumidity.ARID, true, true),
	TC_WATER("TCWater", "umidus", BeeClassification.THAUMIC,
			0x36CFD9, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.NORMAL, EnumHumidity.DAMP, true, true),
	TC_EARTH("TCEarth", "sordida", BeeClassification.THAUMIC,
			0x005100, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_ORDER("TCOrder", "ordinatus", BeeClassification.THAUMIC,
			0xaa32fc, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_CHAOS("TCChaos", "tenebrarum", BeeClassification.THAUMIC,
			0xCCCCCC, BodyColours.THAUMCRAFT_SHARD, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),

	TC_VIS("TCVis", "arcanus saecula", BeeClassification.THAUMIC,
			0x004c99, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_REJUVENATING("TCRejuvenating", "arcanus vitae", BeeClassification.THAUMIC,
			0x91D0D9, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_EMPOWERING("TCEmpowering", "tractus", BeeClassification.THAUMIC,
			0x96FFBC, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TC_NEXUS("TCNexus", "nexi", BeeClassification.THAUMIC,
			0x15AFAF, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	TC_TAINT("TCFlux", "arcanus labe", BeeClassification.THAUMIC,
			0x91376A, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_PURE("TCPure", "arcanus puritatem", BeeClassification.THAUMIC,
			0xE23F65, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_HUNGRY("TCHungry", "omnique", BeeClassification.THAUMIC,
			0xDCA5E2, BodyColours.THAUMCRAFT_NODE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	TC_WISPY("TCWispy", "umbrabilis", BeeClassification.THAUMIC,
			0x9cb8d5, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_VOID("TCVoid", "obscurus", BeeClassification.METALLIC,
			0x180A29, 0x4B2A74, EnumTemperature.ICY, EnumHumidity.NORMAL, false, false),

	// --------- Equivalent Exchange Bees -----------------------------------------------------------------------------	
	EE_MINIUM("EEMinium", "mutabilis", BeeClassification.ALCHEMICAL,
			0xac0921, 0x3a030b, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	// --------- Ars Magica Bees --------------------------------------------------------------------------------------	
	AM_ESSENCE("AMEssence", "essentia", BeeClassification.ESSENTIAL,
			0x86BBC5, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	AM_QUINTESSENCE("AMQuintessence", "cor essentia", BeeClassification.ESSENTIAL,
			0xE3A45B, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	AM_EARTH("AMEarth", "magica terra", BeeClassification.ESSENTIAL,
			0xAA875E, BodyColours.ARSMAGICA, EnumTemperature.WARM, EnumHumidity.ARID, false, false),
	AM_AIR("AMAir", "magica aer", BeeClassification.ESSENTIAL,
			0xD5EB9D, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_FIRE("AMFire", "magica ignis", BeeClassification.ESSENTIAL,
			0x93451E, BodyColours.ARSMAGICA, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	AM_WATER("AMWater", "magica aqua", BeeClassification.ESSENTIAL,
			0x3B7D8C, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.DAMP, false, false),
	AM_LIGHTNING("AMLightning", "magica fulgur", BeeClassification.ESSENTIAL,
			0xEBEFA1, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_PLANT("AMPlant", "magica herba", BeeClassification.ESSENTIAL,
			0x49B549, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_ICE("AMIce", "magica glacium", BeeClassification.ESSENTIAL,
			0x86BAC6, BodyColours.ARSMAGICA, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	AM_ARCANE("AMArcane", "magica arcanum", BeeClassification.ESSENTIAL,
			0x76184D, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),

	AM_VORTEX("AMVortex", "gurges", BeeClassification.ESSENTIAL,
			0x71BBE2, 0x0B35A8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	AM_WIGHT("AMWight", "vectem", BeeClassification.ESSENTIAL,
			0xB50000, 0x4C4837, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// ---------------------Thermal Expansion Bees---------------------------
	TE_BLIZZY("TEBlizzy", "blizzard", BeeClassification.ABOMINABLE,
			0x0073C4, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	TE_GELID("TEGelid", "cyro", BeeClassification.ABOMINABLE,
			0x4AAFF7, EnumTemperature.COLD, EnumHumidity.NORMAL, true, true),
	TE_DANTE("TEDante", "inferno", BeeClassification.ABOMINABLE,
			0xF7AC4A, EnumTemperature.HELLISH, EnumHumidity.ARID, false, false),
	TE_PYRO("TEPyro", "pyromania", BeeClassification.ABOMINABLE,
			0xFA930C, EnumTemperature.HELLISH, EnumHumidity.ARID, true, true),
	TE_SHOCKING("TEShocking", "horrendum", BeeClassification.ABOMINABLE,
			0xC5FF26, 0xF8EE00, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_AMPED("TEAmped", "concitatus", BeeClassification.ABOMINABLE,
			0x8AFFFF, 0xECE670, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	TE_GROUNDED("TEGrounded", "tellus", BeeClassification.ABOMINABLE,
			0xCEC1C1, 0x826767, EnumTemperature.HOT, EnumHumidity.ARID, false, true),
	TE_ROCKING("TERocking", "saxsous", BeeClassification.ABOMINABLE,
			0x980000, 0xAB9D9B, EnumTemperature.HOT, EnumHumidity.ARID, true, true),

	TE_ELECTRUM("TEElectrum", "electrum", BeeClassification.THERMAL,
			0xEAF79E, EnumTemperature.HOT,EnumHumidity.ARID, false, false),
	TE_PLATINUM("TEPlatinum", "platina", BeeClassification.THERMAL,
			0x9EE7F7, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_NICKEL("TENickel", "nickel",BeeClassification.THERMAL,
			0xB4C989, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_INVAR("TEInvar", "invar", BeeClassification.THERMAL,
			0xCDE3A1, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	TE_BRONZE("TEBronze", "pyropus", BeeClassification.THERMAL,
			0xB56D07, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_COAL("TECoal", "carbonis", BeeClassification.THERMAL,
			0x2E2D2D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_DESTABILIZED("TEDestabilized", "electric", BeeClassification.THERMAL,
			0x5E0203, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_LUX("TELux", "lux", BeeClassification.THERMAL,
			0xF1FA89, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_WINSOME("TEWinsome", "cuniculus", BeeClassification.ADORABLE,
			0x096B67, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	TE_ENDEARING("TEEndearing", "cognito", BeeClassification.ADORABLE,
			0x069E97, EnumTemperature.COLD, EnumHumidity.NORMAL, true, true),

	// --------------------Redstone Arsenal Bees-----------------------------
	RSA_FLUXED("RSAFluxed", "Thermametallic electroflux", BeeClassification.THERMAL,
			0x9E060D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// ----------------------Botania Bees---------------------------------------
	BOT_ROOTED("BotRooted", "truncus", BeeClassification.BOTANICAL,
			0x00A800, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	BOT_BOTANIC("BotBotanic", "botanica", BeeClassification.BOTANICAL,
			0x94C661, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BOT_BLOSSOM("BotBlossom", "viridis", BeeClassification.BOTANICAL,
			0xA4C193, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	BOT_FLORAL("BotFloral", "florens", BeeClassification.BOTANICAL,
			0x29D81A, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	BOT_VAZBEE("BotVazbee", "vazbii", BeeClassification.BOTANICAL,
			0xff6b9c, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	BOT_SOMNOLENT("BotSomnolent", "soporatus", BeeClassification.BOTANICAL,
			0x2978C6, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true, true, true),
	BOT_DREAMING("BotDreaming", "somnior", BeeClassification.BOTANICAL,
			0x123456, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true, true, true),

	BOT_ALFHEIM("BotAlfheim", "alfheimis", BeeClassification.BOTANICAL,
			-1, -1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// -------------------Applied Energistics 2 Bees---------------------------
	AE_SKYSTONE("AESkystone", "terra astris", BeeClassification.TRANSMUTING,
			0x4B8381, 0x252929, EnumTemperature.HOT, EnumHumidity.ARID, false, true),
	
	// ------------------------- EnderIO Bees ---------------------------------
	STEEL("Steely", "ferrocarbon", BeeClassification.METALLIC,
			0x5B5B5B, 0xD5D5DB, EnumTemperature.NORMAL, EnumHumidity.ARID, false, true),
	OBSIDIAN("Obsidian", "vulcovitrum", BeeClassification.MAGICAL,
			0x2D2D2E, 0x1D0018, EnumTemperature.HELLISH, EnumHumidity.NORMAL, false, false),
	SOULARIUM("Soularium", "psychi", BeeClassification.SOUL,
			0xBF877C, BodyColours.SKULKING, EnumTemperature.HELLISH, EnumHumidity.NORMAL, false, true),
	
	EIO_ELECTRICAL("ElectricalSteel", "ferrolectrapis", BeeClassification.ALLOYED,
			0x576857, 0xDBE9E9, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	EIO_DARK_STEEL("DarkSteel", "tenebruschalbys", BeeClassification.ALLOYED,
			0x2D2D3E, BodyColours.ABOMINABLE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	EIO_PULSATING("PulsatingIron", "pulpitoferrus", BeeClassification.ALLOYED,
			0x68FF68, 0xE9E9E9, EnumTemperature.COLD, EnumHumidity.NORMAL, true, true),
	EIO_ENERGETIC("EnergeticAlloy", "potentiauro", BeeClassification.ALLOYED,
			0xFFAE9A, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	EIO_VIBRANT("VibrantAlloy", "vivivi", BeeClassification.ALLOYED,
			0x95FF95, 0xFFFF0B, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	
	;

	// For body colours used by more than one bee.
	private class BodyColours {
		static final int DEFAULT = 0xFF7C26;
		static final int ARCANE = 0xFF9D60;
		static final int ABOMINABLE = 0x960F00;
		static final int EXTRINSIC = 0xF696FF;
		static final int SKULKING = 0xe15236;
		static final int THAUMCRAFT_SHARD = 0x999999;
		static final int THAUMCRAFT_NODE = 0x675ED1;
		static final int ARSMAGICA = 0xE3A55B;
		static final int BOTANIA = 0xFFB2BB;
	}

	public static void setupBeeSpecies() {
		MYSTICAL.registerGenomeTemplate(BeeGenomeManager.getTemplateMystical());
		SORCEROUS.registerGenomeTemplate(BeeGenomeManager.getTemplateSorcerous());
		UNUSUAL.registerGenomeTemplate(BeeGenomeManager.getTemplateUnusual());
		ATTUNED.registerGenomeTemplate(BeeGenomeManager.getTemplateAttuned());
		ELDRITCH.registerGenomeTemplate(BeeGenomeManager.getTemplateEldritch());
		ESOTERIC.registerGenomeTemplate(BeeGenomeManager.getTemplateEsoteric());
		MYSTERIOUS.registerGenomeTemplate(BeeGenomeManager.getTemplateMysterious());
		ARCANE.registerGenomeTemplate(BeeGenomeManager.getTemplateArcane());
		CHARMED.registerGenomeTemplate(BeeGenomeManager.getTemplateCharmed());
		ENCHANTED.registerGenomeTemplate(BeeGenomeManager.getTemplateEnchanted());
		SUPERNATURAL.registerGenomeTemplate(BeeGenomeManager.getTemplateSupernatural());	
		ETHEREAL.registerGenomeTemplate(BeeGenomeManager.getTemplateEthereal());	
		WATERY.registerGenomeTemplate(BeeGenomeManager.getTemplateWatery());
		EARTHY.registerGenomeTemplate(BeeGenomeManager.getTemplateEarthy());
		FIREY.registerGenomeTemplate(BeeGenomeManager.getTemplateFirey());
		WINDY.registerGenomeTemplate(BeeGenomeManager.getTemplateWindy());	
		PUPIL.registerGenomeTemplate(BeeGenomeManager.getTemplatePupil());
		SCHOLARLY.registerGenomeTemplate(BeeGenomeManager.getTemplateScholarly());
		SAVANT.registerGenomeTemplate(BeeGenomeManager.getTemplateSavant());	
		AWARE.registerGenomeTemplate(BeeGenomeManager.getTemplateAware());
		SPIRIT.registerGenomeTemplate(BeeGenomeManager.getTemplateSpirit());
		SOUL.registerGenomeTemplate(BeeGenomeManager.getTemplateSoul());	
		SKULKING.registerGenomeTemplate(BeeGenomeManager.getTemplateSkulking());
		GHASTLY.registerGenomeTemplate(BeeGenomeManager.getTemplateGhastly());
		SPIDERY.registerGenomeTemplate(BeeGenomeManager.getTemplateSpidery());
		SMOULDERING.registerGenomeTemplate(BeeGenomeManager.getTemplateSmouldering());
		BIGBAD.registerGenomeTemplate(BeeGenomeManager.getTemplateBigbad());
		CHICKEN.registerGenomeTemplate(BeeGenomeManager.getTemplateChicken());
		BEEF.registerGenomeTemplate(BeeGenomeManager.getTemplateBeef());
		PORK.registerGenomeTemplate(BeeGenomeManager.getTemplatePork());
		SHEEPISH.registerGenomeTemplate(BeeGenomeManager.getTemplateSheepish());
		HORSE.registerGenomeTemplate(BeeGenomeManager.getTemplateHorse());
		CATTY.registerGenomeTemplate(BeeGenomeManager.getTemplateCatty());
		TIMELY.registerGenomeTemplate(BeeGenomeManager.getTemplateTimely());
		LORDLY.registerGenomeTemplate(BeeGenomeManager.getTemplateLordly());
		DOCTORAL.registerGenomeTemplate(BeeGenomeManager.getTemplateDoctoral());	
		INFERNAL.registerGenomeTemplate(BeeGenomeManager.getTemplateInfernal());
		HATEFUL.registerGenomeTemplate(BeeGenomeManager.getTemplateHateful());
		SPITEFUL.registerGenomeTemplate(BeeGenomeManager.getTemplateSpiteful());
		WITHERING.registerGenomeTemplate(BeeGenomeManager.getTemplateWithering());	
		OBLIVION.registerGenomeTemplate(BeeGenomeManager.getTemplateOblivion());
		NAMELESS.registerGenomeTemplate(BeeGenomeManager.getTemplateNameless());
		ABANDONED.registerGenomeTemplate(BeeGenomeManager.getTemplateAbandoned());
		FORLORN.registerGenomeTemplate(BeeGenomeManager.getTemplateForlorn());
		DRACONIC.registerGenomeTemplate(BeeGenomeManager.getTemplateDraconic());	
		IRON.registerGenomeTemplate(BeeGenomeManager.getTemplateIron());
		GOLD.registerGenomeTemplate(BeeGenomeManager.getTemplateGold());
		COPPER.registerGenomeTemplate(BeeGenomeManager.getTemplateCopper());
		TIN.registerGenomeTemplate(BeeGenomeManager.getTemplateTin());
		SILVER.registerGenomeTemplate(BeeGenomeManager.getTemplateSilver());
		LEAD.registerGenomeTemplate(BeeGenomeManager.getTemplateLead());
		ALUMINUM.registerGenomeTemplate(BeeGenomeManager.getTemplateAluminum());
		ARDITE.registerGenomeTemplate(BeeGenomeManager.getTemplateArdite());
		COBALT.registerGenomeTemplate(BeeGenomeManager.getTemplateCobalt());
		MANYULLYN.registerGenomeTemplate(BeeGenomeManager.getTemplateManyullyn());
		OSMIUM.registerGenomeTemplate(BeeGenomeManager.getTemplateOsmium());
		DIAMOND.registerGenomeTemplate(BeeGenomeManager.getTemplateDiamond());
		EMERALD.registerGenomeTemplate(BeeGenomeManager.getTemplateEmerald());
		APATITE.registerGenomeTemplate(BeeGenomeManager.getTemplateApatite());
		SILICON.registerGenomeTemplate(BeeGenomeManager.getTemplateSilicon());
		FLUIX.registerGenomeTemplate(BeeGenomeManager.getTemplateFluix());
		CERTUS.registerGenomeTemplate(BeeGenomeManager.getTemplateCertus());
		MUTABLE.registerGenomeTemplate(BeeGenomeManager.getTemplateMutable());
		TRANSMUTING.registerGenomeTemplate(BeeGenomeManager.getTemplateTransmuting());
		CRUMBLING.registerGenomeTemplate(BeeGenomeManager.getTemplateCrumbling());	
		INVISIBLE.registerGenomeTemplate(BeeGenomeManager.getTemplateInvisible());
		TC_AIR.registerGenomeTemplate(BeeGenomeManager.getTemplateTCAir());
		TC_FIRE.registerGenomeTemplate(BeeGenomeManager.getTemplateTCFire());
		TC_WATER.registerGenomeTemplate(BeeGenomeManager.getTemplateTCWater());
		TC_EARTH.registerGenomeTemplate(BeeGenomeManager.getTemplateTCEarth());
		TC_ORDER.registerGenomeTemplate(BeeGenomeManager.getTemplateTCOrder());
		TC_CHAOS.registerGenomeTemplate(BeeGenomeManager.getTemplateTCChaos());
		TC_VIS.registerGenomeTemplate(BeeGenomeManager.getTemplateTCVis());
		TC_REJUVENATING.registerGenomeTemplate(BeeGenomeManager.getTemplateTCRejuvinating());
		TC_EMPOWERING.registerGenomeTemplate(BeeGenomeManager.getTemplateTCEmpowering());
		TC_NEXUS.registerGenomeTemplate(BeeGenomeManager.getTemplateTCNexus());
		TC_TAINT.registerGenomeTemplate(BeeGenomeManager.getTemplateTCTaint());
		TC_PURE.registerGenomeTemplate(BeeGenomeManager.getTemplateTCPure());
		TC_HUNGRY.registerGenomeTemplate(BeeGenomeManager.getTemplateTCHungry());
		BRAINY.registerGenomeTemplate(BeeGenomeManager.getTemplateBrainy());
		TC_WISPY.registerGenomeTemplate(BeeGenomeManager.getTemplateTCWispy());
		BATTY.registerGenomeTemplate(BeeGenomeManager.getTemplateBatty());
		TC_VOID.registerGenomeTemplate(BeeGenomeManager.getTemplaceTCVoid());
		EE_MINIUM.registerGenomeTemplate(BeeGenomeManager.getTemplateEEMinium());
		AM_ESSENCE.registerGenomeTemplate(BeeGenomeManager.getTemplateAMEssence());
		AM_QUINTESSENCE.registerGenomeTemplate(BeeGenomeManager.getTemplateAMQuintessence());
		AM_EARTH.registerGenomeTemplate(BeeGenomeManager.getTemplateAMEarth());
		AM_AIR.registerGenomeTemplate(BeeGenomeManager.getTemplateAMAir());
		AM_FIRE.registerGenomeTemplate(BeeGenomeManager.getTemplateAMFire());
		AM_WATER.registerGenomeTemplate(BeeGenomeManager.getTemplateAMWater());
		AM_LIGHTNING.registerGenomeTemplate(BeeGenomeManager.getTemplateAMLightning());
		AM_PLANT.registerGenomeTemplate(BeeGenomeManager.getTemplateAMPlant());
		AM_ICE.registerGenomeTemplate(BeeGenomeManager.getTemplateAMIce());
		AM_ARCANE.registerGenomeTemplate(BeeGenomeManager.getTemplateAMArcane());
		AM_VORTEX.registerGenomeTemplate(BeeGenomeManager.getTemplateAMVortex());
		AM_WIGHT.registerGenomeTemplate(BeeGenomeManager.getTemplateAMWight());
		TE_BLIZZY.registerGenomeTemplate(BeeGenomeManager.getTemplateTEBlizzy());
		TE_GELID.registerGenomeTemplate(BeeGenomeManager.getTemplateTEGelid());
		TE_DANTE.registerGenomeTemplate(BeeGenomeManager.getTemplateTEDante());
		TE_PYRO.registerGenomeTemplate(BeeGenomeManager.getTemplateTEPyro());
		TE_SHOCKING.registerGenomeTemplate(BeeGenomeManager.getTemplateTEShocking());
		TE_AMPED.registerGenomeTemplate(BeeGenomeManager.getTemplateTEAmped());
		TE_GROUNDED.registerGenomeTemplate(BeeGenomeManager.getTemplateTEGrounded());
		TE_ROCKING.registerGenomeTemplate(BeeGenomeManager.getTemplateTERocking());
		TE_ELECTRUM.registerGenomeTemplate(BeeGenomeManager.getTemplateTEElectrum());
		TE_PLATINUM.registerGenomeTemplate(BeeGenomeManager.getTemplateTEPlatinum());
		TE_NICKEL.registerGenomeTemplate(BeeGenomeManager.getTemplateTENickel());
		TE_INVAR.registerGenomeTemplate(BeeGenomeManager.getTemplateTEInvar());
		TE_BRONZE.registerGenomeTemplate(BeeGenomeManager.getTemplateTEBronze());
		TE_COAL.registerGenomeTemplate(BeeGenomeManager.getTemplateTECoal());
		TE_DESTABILIZED.registerGenomeTemplate(BeeGenomeManager.getTemplateTEDestabilized());
		TE_LUX.registerGenomeTemplate(BeeGenomeManager.getTemplateTELux());
		TE_WINSOME.registerGenomeTemplate(BeeGenomeManager.getTemplateTEWinsome());
		TE_ENDEARING.registerGenomeTemplate(BeeGenomeManager.getTemplateTEEndearing());
		RSA_FLUXED.registerGenomeTemplate(BeeGenomeManager.getTemplateRSAFluxed());
		BOT_ROOTED.registerGenomeTemplate(BeeGenomeManager.getTemplateBotRooted());
		BOT_BOTANIC.registerGenomeTemplate(BeeGenomeManager.getTemplateBotBotanic());
		BOT_BLOSSOM.registerGenomeTemplate(BeeGenomeManager.getTemplateBotBlossom());
		BOT_FLORAL.registerGenomeTemplate(BeeGenomeManager.getTemplateBotFloral());
		BOT_VAZBEE.registerGenomeTemplate(BeeGenomeManager.getTemplateBotVazbee());
		BOT_SOMNOLENT.registerGenomeTemplate(BeeGenomeManager.getTemplateBotSomnolent());
		BOT_DREAMING.registerGenomeTemplate(BeeGenomeManager.getTemplateBotDreaming());
		BOT_ALFHEIM.registerGenomeTemplate(BeeGenomeManager.getTemplateBotAelfheim());
		AE_SKYSTONE.registerGenomeTemplate(BeeGenomeManager.getTemplateAESkystone());
		STEEL.registerGenomeTemplate(BeeGenomeManager.getTemplateSteel());
		OBSIDIAN.registerGenomeTemplate(BeeGenomeManager.getTemplateObsidian());
		SOULARIUM.registerGenomeTemplate(BeeGenomeManager.getTemplateSoularium());
		EIO_ELECTRICAL.registerGenomeTemplate(BeeGenomeManager.getTemplateElectricalSteel());
		EIO_DARK_STEEL.registerGenomeTemplate(BeeGenomeManager.getTemplateDarkSteel());
		EIO_PULSATING.registerGenomeTemplate(BeeGenomeManager.getTemplatePulsatingIron());
		EIO_ENERGETIC.registerGenomeTemplate(BeeGenomeManager.getTemplateEnergeticAlloy());
		EIO_VIBRANT.registerGenomeTemplate(BeeGenomeManager.getTemplateVibrantAlloy());
		
		BeeProductHelper.initThaumcraftProducts();
		if (!ThaumcraftHelper.isActive()) {
			TC_CHAOS.setInactive();
			TC_AIR.setInactive();
			TC_FIRE.setInactive();
			TC_WATER.setInactive();
			TC_EARTH.setInactive();
			TC_ORDER.setInactive();

			TC_VIS.setInactive();
			TC_REJUVENATING.setInactive();
			TC_EMPOWERING.setInactive();
			TC_NEXUS.setInactive();
			TC_TAINT.setInactive();
			TC_PURE.setInactive();
			TC_HUNGRY.setInactive();

			BRAINY.setInactive();
			
			TC_WISPY.setInactive();
			TC_VOID.setInactive();
		}

		BeeProductHelper.initEquivalentExchange3Species();
		if (!EquivalentExchangeHelper.isActive()) {
			EE_MINIUM.setInactive();
		}

		BeeProductHelper.initArsMagicaSpecies();
		if (!ArsMagicaHelper.isActive()) {
			AM_ESSENCE.setInactive();
			AM_QUINTESSENCE.setInactive();
			AM_EARTH.setInactive();
			AM_AIR.setInactive();
			AM_FIRE.setInactive();
			AM_WATER.setInactive();
			AM_LIGHTNING.setInactive();
			AM_PLANT.setInactive();
			AM_ICE.setInactive();
			AM_ARCANE.setInactive();
			AM_VORTEX.setInactive();
			AM_WIGHT.setInactive();
		}

		BeeProductHelper.initThermalExpansionProducts();
		if (!ThermalModsHelper.isActive()) {
			TE_BLIZZY.setInactive();
			TE_GELID.setInactive();
			TE_DANTE.setInactive();
			TE_PYRO.setInactive();
			TE_SHOCKING.setInactive();
			TE_AMPED.setInactive();
			TE_GROUNDED.setInactive();
			TE_ROCKING.setInactive();
			TE_DESTABILIZED.setInactive();
			TE_COAL.setInactive();
			TE_LUX.setInactive();
			TE_WINSOME.setInactive();
			TE_ENDEARING.setInactive();
		}

		BeeProductHelper.initRedstoneArsenelProducts();
		if (!RedstoneArsenalHelper.isActive()) {
			RSA_FLUXED.setInactive();
		}

		BeeProductHelper.initBotaniaProducts();
		if (!BotaniaHelper.isActive()) {
			BOT_ROOTED.setInactive();
			BOT_BLOSSOM.setInactive();
			BOT_BOTANIC.setInactive();
			BOT_FLORAL.setInactive();
			BOT_VAZBEE.setInactive();
			BOT_SOMNOLENT.setInactive();
			BOT_DREAMING.setInactive();
			BOT_ALFHEIM.setInactive();
		}
		
		BeeProductHelper.initAppEngProducts();
		if (!AppliedEnergisticsHelper.isActive()) {
			AE_SKYSTONE.setInactive();
		}

		for (BeeSpecies beeSpecies : BeeSpecies.values()) {
			if (beeSpecies.isActive()) {
				beeSpecies.setupCustomIconProvider();
			}
		}
	}

	private static final String authority = "MysteriousAges";

	private final IAlleleBeeSpeciesCustom species;
	private final EnumTemperature temperature;
	private final EnumHumidity humidity;

	private boolean active = true;

	private IAllele genomeTemplate[];

	BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, classification, firstColour, BodyColours.DEFAULT,
				preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant, false);
	}

	BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, classification, firstColour, secondColour,
				preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant, false);
	}

	BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, classification, firstColour, secondColour,
				preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant, false);
	}

	BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, classification, firstColour, BodyColours.DEFAULT,
				preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant, false);
	}

	BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesSecret, boolean isSpeciesCounted,
			boolean isSpeciesDominant, boolean isSpeciesNocturnal) {

		String uid = "magicbees.species" + speciesName;

		this.temperature = preferredTemp;
		this.humidity = preferredHumidity;

		IBeeFactory beeFactory = forestry.api.apiculture.BeeManager.beeFactory;
		this.species = beeFactory.createSpecies(uid, isSpeciesDominant, authority, uid, uid + ".description", classification, genusName, firstColour, secondColour);

		this.species.setTemperature(preferredTemp);
		this.species.setHumidity(preferredHumidity);

		if (isSpeciesNocturnal) {
			this.species.setNocturnal();
		}
		if (hasGlowEffect) {
			this.species.setHasEffect();
		}
		if (isSpeciesSecret) {
			this.species.setIsSecret();
		}
		if (!isSpeciesCounted) {
			this.species.setIsNotCounted();
		}

		classification.addMemberSpecies(species);
	}

	public IAllele[] getGenome() {
		return genomeTemplate;
	}

	public IAlleleBeeSpecies getSpecies() {
		return species;
	}
	
	public BeeSpecies addProduct(ItemStack produce, float chance) {
		species.addProduct(produce, chance);
		return this;
	}
	
	public BeeSpecies addSpecialty(ItemStack produce, float chance) {
		species.addSpecialty(produce, chance);
		return this;
	}

	public ItemStack getBeeItem(EnumBeeType beeType) {
		return BeeManager.beeRoot.getMemberStack(BeeManager.beeRoot.getBee(null, BeeManager.beeRoot.templateAsGenome(genomeTemplate)), beeType.ordinal());
	}

	public BeeSpecies setInactive() {
		this.active = false;
		AlleleManager.alleleRegistry.blacklistAllele(species.getUID());
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public void registerGenomeTemplate(IAllele[] genome) {
		genomeTemplate = genome;
		BeeManager.beeRoot.registerTemplate(species.getUID(), genome);
	}

	private void setupCustomIconProvider() {
		IBeeIconProvider iconProvider = getCustomIconProvider();
		if (iconProvider != null) {
			this.species.setCustomBeeIconProvider(iconProvider);
		}

		IBeeIconColourProvider iconColourProvider = getCustomIconColourProvider();
		if (iconColourProvider != null) {
			this.species.setCustomBeeIconColourProvider(iconColourProvider);
		}
	}

	private IBeeIconProvider getCustomIconProvider() {
		switch (this) {
			case SKULKING:
			case GHASTLY:
			case SPIDERY:
			case SMOULDERING:
			case BIGBAD:
			case BATTY:
			case SHEEPISH:
			case HORSE:
			case CATTY:
			case BRAINY:
			case TC_WISPY:
			case AM_VORTEX:
			case AM_WIGHT:
			case TE_BLIZZY:
			case TE_GELID:
			case TE_DANTE:
			case TE_PYRO:
			case TE_SHOCKING:
			case TE_AMPED:
			case TE_GROUNDED:
			case TE_ROCKING:
				return BeeIconProvider.SKULKING;

			case DOCTORAL:
				return BeeIconProvider.DOCTORAL;

			default:
				return null;
		}
	}

	private IBeeIconColourProvider getCustomIconColourProvider() {
		switch (this) {
			case BOT_ALFHEIM:
				return BeeIconColourProvider.RAINBOW;
		}
		return null;
	}

	public boolean canWorkInTemperature(EnumTemperature temp) {
		IAlleleTolerance tolerance = (IAlleleTolerance) genomeTemplate[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()];
		return AlleleManager.climateHelper
				.isWithinLimits(temp, EnumHumidity.NORMAL, temperature, tolerance.getValue(), EnumHumidity.NORMAL, EnumTolerance.NONE);
	}

	public boolean canWorkInHumidity(EnumHumidity humid) {
		IAlleleTolerance tolerance = (IAlleleTolerance) genomeTemplate[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()];
		return AlleleManager.climateHelper.isWithinLimits(EnumTemperature.NORMAL, humid, EnumTemperature.NORMAL, EnumTolerance.NONE, humidity,
				tolerance.getValue());
	}
}
