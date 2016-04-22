package magicbees.item.types;

import magicbees.main.utils.LocalizationManager;
import net.minecraft.item.Item;

public enum NuggetType
{
	IRON,
	COPPER,
	TIN,
	SILVER,
	LEAD,
	DIAMOND,
	EMERALD,
	APATITE,
	ELECTRICAL_STEEL,
	ENERGETIC_ALLOY,
	DARK_STEEL,
	SOULARIUM,
	;
	
	private Item targetIngot;
	private boolean active; 
	
	private NuggetType()
	{
		this.active = true;
	}
	
	public void setIngotItem(Item target)
	{
		this.targetIngot = target;
	}
	
	public Item getIngotItem()
	{
		return this.targetIngot;
	}
	
	public void setInactive()
	{
		this.active = false;
	}
	
	public boolean isActive()
	{
		return this.active;
	}

	public String getName()
	{
		return LocalizationManager.getLocalizedString("nugget." + this.toString().toLowerCase());
	}

}
