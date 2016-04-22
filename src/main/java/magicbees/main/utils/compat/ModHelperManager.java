package magicbees.main.utils.compat;

import java.util.ArrayList;
import java.util.List;

public class ModHelperManager {
	
	private static List<IModHelper> helpers;
	
	public static void preInit() {
		setupHelpers();
		
		for (IModHelper helper : helpers) {
			helper.preInit();
		}
	}

	public static void init() {
		for (IModHelper helper : helpers) {
			helper.init();
		}
	}

	public static void postInit() {
		for (IModHelper helper : helpers) {
			helper.postInit();
		}
	}
	
	private static void setupHelpers() {
		helpers = new ArrayList<IModHelper>();
		
		helpers.add(new ForestryHelper());
		helpers.add(new ExtraBeesHelper());
		helpers.add(new ThaumcraftHelper());
		helpers.add(new EquivalentExchangeHelper());
		helpers.add(new ArsMagicaHelper());
		helpers.add(new ThermalModsHelper());
		helpers.add(new RedstoneArsenalHelper());
		helpers.add(new BotaniaHelper());
		helpers.add(new AppliedEnergisticsHelper());
		helpers.add(new EnderIOHelper());
	}
}
