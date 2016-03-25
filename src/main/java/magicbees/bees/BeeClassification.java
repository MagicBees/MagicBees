package magicbees.bees;

import java.util.ArrayList;

import magicbees.main.utils.LocalizationManager;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;

public enum BeeClassification implements IClassification
{
	VEILED("Veiled", "Velatapis"),
	ARCANE("Arcane", "Arcanapis"),
	SUPERNATURAL("Supernatural", "Occultapis"),
	SCHOLARLY("Scholarly", "Doctapis"),
	SKULKING("Skulking", "Malevolenapis"),
	MAGICAL("Magic", "Magicapis"),
	TIME("Time", "Tempestivapis"),
	SOUL("Soul", "Animapis"),
	
	ABOMINABLE("Abominable", "Detestabilapis"),
	EXTRINSIC("Extrinsic", "Extrarapis"),
	
	METALLIC("Metallic", "Metalliapis"),
	GEM("Gem", "Lapidapis"),
	TRANSMUTING("Transmuting", "Transmutapis"),
	
	THAUMIC("Thaumic", "Thaumiapis"),
	FLESHY("Fleshy", "Carnosapis"),
	
	ALCHEMICAL("Alchemical", "Alchimiapis"),
	
	ESSENTIAL("Essential", "Essentiapis"),
	
	THERMAL("Thermal", "Thermametallic"),
	ADORABLE("Adorable", "Amabilis"),

	BLOODY("Bloody", "Sanguis"),
	
	BOTANICAL("Botanical", "Botanica"),
	
	ALLOYED("Alloyed", "Heterometallus"),
	
	;
	
	private String uID;
	private String latin;
	private ArrayList<IAlleleSpecies> species;
	private IClassification parent;
	private EnumClassLevel level;
	
	private BeeClassification(String name, String scientific)
	{
		this.uID = "classification." + name.toLowerCase();
		this.latin = scientific;
		this.level = EnumClassLevel.GENUS;
		this.species = new ArrayList<IAlleleSpecies>();
		this.parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
		AlleleManager.alleleRegistry.registerClassification(this);
	}
	
	@Override
	public EnumClassLevel getLevel()
	{
		return this.level;
	}

	@Override
	public String getUID()
	{
		return this.uID;
	}

	@Override
	public String getName()
	{
		return LocalizationManager.getLocalizedString(getUID());
	}

	@Override
	public String getScientific()
	{
		return this.latin;
	}

	@Override
	public String getDescription()
	{
		return LocalizationManager.getLocalizedString(getUID() + ".description");
	}

	@Override
	public IClassification[] getMemberGroups()
	{
		return null;
	}

	@Override
	public void addMemberGroup(IClassification group)
	{
		
	}

	@Override
	public IAlleleSpecies[] getMemberSpecies()
	{
		return this.species.toArray(new IAlleleSpecies[this.species.size()]);
	}

	@Override
	public void addMemberSpecies(IAlleleSpecies species)
	{
		if (!this.species.contains(species))
		{
			this.species.add(species);
		}
	}

	@Override
	public IClassification getParent()
	{
		return this.parent;
	}

	@Override
	public void setParent(IClassification parent)
	{
		this.parent = parent;
	}

}
