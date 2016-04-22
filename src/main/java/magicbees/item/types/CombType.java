package magicbees.item.types;

import magicbees.main.utils.LocalizationManager;

public enum CombType {
	// Reminder: Order matters!
	// Meta value of comb == ordinal of enum value.
	
	MUNDANE("mundane", true),
	MOLTEN("molten", true),
	OCCULT("occult", true),
	OTHERWORLDLY("otherworldly", true),
	TRANSMUTED("transmuted", true),
	
	PAPERY("paper", true),
	SOUL("soul", true),
	FURTIVE("furtive", false),
	INTELLECT("aware", false),
	TEMPORAL("time", false),
	FORGOTTEN("forgotten", false),
	
	AIRY("air", false),
	FIREY("fire", false),
	WATERY("water", false),
	EARTHY("earth", false),
	
	TC_AIR("TCair", false),
	TC_FIRE("TCfire", false),
	TC_WATER("TCwater", false),
	TC_EARTH("TCearth", false),
	TC_ORDER("TCorder", false),
	TC_CHAOS("TCchaos", false),
	
	AM_ESSENCE("essence", false),
	AM_POTENT("potent", false),	
	
	TE_DESTABILIZED("TEelectric", false),
	TE_CARBON("TEcarbon", false),
	TE_LUX("TElux", false),
	TE_ENDEARING("TEendearing", false),
	
	OBSIDIAN("obsidian", false),
	;
	
	private static int[][] colours = new int[][] {
			{ 0xFF9859, 0xFFC58E },
			{ 0xcc3333, 0x1E160E },
			{ 0x9872FF, 0x2D2D2D },
			{ 0x3EE0D8, 0x3A3820 },
			{ 0xE5425D, 0x323291 },

			{ 0xBCA664, 0x35332E },
			{ 0x7F7171, 0x876D53 },
			{ 0xB7ACB7, 0x636363 },
			{ 0x0092e9, 0x618fff },
			{ 0x2F9381, 0x773C31 },
			{ 0xB191D8, 0x35443B },

			{ 0xffff7e, 0x606308 },
			{ 0xff3C01, 0x5B0D10 },
			{ 0x0090ff, 0x102F6B },
			{ 0x00a000, 0x043004 },

			{ 0xFFDF14, 0x707019 },
			{ 0xE21802, 0x3F0E12 },
			{ 0x00B6FF, 0x0F373D },
			{ 0x28D328, 0x333004 },
			{ 0xDDDDFF, 0x9D9DB5 },
			{ 0x555577, 0x2D2D56 },

			{ 0xCC8604, 0x41046F },
			{ 0xCCCC04, 0x41046F },

			{ 0xCC002C, 0x6B0118 },
			{ 0x454545, 0x0F0F0F },
			{ 0xF5F3A4, 0xC9C87D },
			{ 0x12E3D9, 0x069E97 },
			
			{ 0x630063, 0x0F0018 },
	};
	
	private CombType(String pName, boolean show) {
		this.name = pName;
		
		this.showInList = show;
	}
	
	public void setHidden() {
		this.showInList = false;
	}
	
	private String name;
	public boolean showInList;
	
	public String getName() {
		return LocalizationManager.getLocalizedString("comb." + this.name);
	}
	
	public int[] getColours() {
		return colours[this.ordinal()];
	}
}
