package magicbees.main.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

/**
 * Using some of the VersionInfo code from cofh. (Thanks, Lemming!)
 *
 * @author MysteriousAges
 */
public class VersionInfo
{
	public static final String ModName = "MagicBees";
	public static final String Version = "@VERSION@";
	public static final String MCVersion = "@MCVERSION@";
	public static final String VersionURL = "https://raw.githubusercontent.com/MagicBees/MagicBees/master/etc/version-1.7.txt";
	public static final String DownloadURL = "http://minecraft.curseforge.com/mc-mods/65764-magic-bees/files";
	public static final String GUI_FACTORY_CLASS = "magicbees.client.gui.GuiFactory";

	public static final String Logo = "/gfx/magicbees/logo.png";

	public static final String Depends = "required-after:Forestry@[4.2.5,);after:Thaumcraft;after:ExtraBees;after:EE3;after:ArsMagica;after:TConstruct;after:Railcraft;after:ThermalFoundation;after:ThermalExpansion;after:RedstoneArsenal;after:AWWayofTime;after:Botania;after:appliedenergistics2;after:EnderIO";

	boolean criticalUpdate;
	boolean newVersion;
	boolean versionCheckComplete;

	String latestModVersion;
	String description = "";

	String modName;
	String modVersion;
	String releaseURL;

	public static int[] parseVersion(String rawVersion)
	{
		ArrayList<Integer> versionTokens = new ArrayList<Integer>();
		String[] tokens = rawVersion.trim().split("[\\. ]");

		for (int i = 0; i < tokens.length; ++i)
		{
			tokens[i] = tokens[i].trim();
			if (tokens[i].matches("[0-9]+"))
			{
				versionTokens.add(Integer.valueOf(tokens[i]));
			}
			else if (tokens[i].matches("[0-9]+[a-z]"))
			{
				String numberString = tokens[i].substring(0, tokens[i].length() - 1);
				versionTokens.add(Integer.valueOf(numberString));
				versionTokens.add(Character.getNumericValue(tokens[i].charAt(tokens[i].length() - 1)));
			}
		}

		// Can't use versionTokens.toArray 'cause that returns an Integer[], not int[]
		int[] value = new int[versionTokens.size()];
		for (int i = 0; i < value.length; ++i)
		{
			value[i] = versionTokens.get(i).intValue();
		}
		return value;
	}

	public static boolean beforeTargetVersion(String version, String target)
	{
		boolean result = false;
		int[] versionTokens = parseVersion(version);
		int[] targetTokens = parseVersion(target);

		for (int i = 0; i < versionTokens.length && i < targetTokens.length; ++i)
		{
			if (versionTokens[i] < targetTokens[i])
			{
				result = true;
				break;
			}
			else if (versionTokens[i] > targetTokens[i])
			{
				result = false;
				break;
			}

			if (i == versionTokens.length - 1 && versionTokens.length < targetTokens.length)
			{
				// If the versions compared are the same, but target has an extra token, it's probably a "letter" build
				//  and is ahead of this one.
				result = true;
			}
		}

		return result;
	}

	public static boolean afterTargetVersion(String version, String target)
	{
		boolean result = false;
		int[] versionTokens = parseVersion(version);
		int[] targetTokens = parseVersion(target);

		for (int i = 0; i < versionTokens.length && i < targetTokens.length; ++i)
		{
			if (versionTokens[i] > targetTokens[i])
			{
				result = true;
				break;
			}
		}

		return result;
	}

	public VersionInfo(String name, String version, String url)
	{
		modName = name;
		modVersion = latestModVersion = version;
		releaseURL = url;
	}

	public VersionInfo(String name, String version, String url, Logger logger)
	{
		modName = name;
		modVersion = latestModVersion = version;
		releaseURL = url;
	}

	public void checkForNewVersion()
	{
		Thread versionCheckThread = new VersionCheckThread();
		versionCheckThread.start();
	}

	public String getCurrentVersion()
	{
		return modVersion;
	}

	public String getLatestVersion()
	{
		return latestModVersion;
	}

	public String getVersionDescription()
	{
		return description;
	}

	public boolean isCriticalUpdate()
	{
		return criticalUpdate;
	}

	public boolean isNewVersionAvailable()
	{
		return newVersion;
	}

	public boolean isVersionCheckComplete()
	{
		return versionCheckComplete;
	}

	private class VersionCheckThread extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				String location = VersionURL;

				HttpURLConnection connection = null;

				// Used to "dereference" any location headers we may get.
				while (location != null && !location.isEmpty())
				{
					URL url = new URL(location);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.60 Safari/537.17");
					connection.connect();
					location = connection.getHeaderField("Location");
				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				latestModVersion = reader.readLine();
				criticalUpdate = Boolean.parseBoolean(reader.readLine());
				while (reader.ready()) {
					description = description + reader.readLine();
				}
				reader.close();

				if (beforeTargetVersion(modVersion, latestModVersion))
				{
					FMLLog.info("An updated version of " + modName + " is available: " + latestModVersion + ". You are currently using " + Version);
					newVersion = true;
					if (criticalUpdate)
					{
						LogHelper.info("This update has been marked as CRITICAL and will ignore notification suppression.");
					}
				}

			}
			catch (Exception e)
			{
				LogHelper.warn("Version check Failed: " + e.getMessage());
			}
			versionCheckComplete = true;
		}
	}

	public static void doVersionCheck()
	{
		VersionInfo versionInfo = new VersionInfo(ModName, Version, VersionURL);
		TickHandlerVersion.go(versionInfo);

		VersionCheckThread thread = versionInfo.new VersionCheckThread();
		thread.start();
	}
}
