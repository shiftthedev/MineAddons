package com.iamshift;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config
{
	// CONTROL
	private static final int VERSION = 3;
	private static int ver;

	// V1.0
	public static boolean buttbooster = true;

	public static boolean concrete = true;
	public static boolean terracottablocks = true;
	public static boolean lavasponge = true;
	public static boolean sponge = true;

	public static boolean sacredwater = true;
	public static boolean cursedwater = true;

	// V1.1
	public static boolean ironnuggets = true;
	public static boolean supernametag = true;

	public static boolean observer = true;

	public static boolean truecreeper = true;
	public static boolean peacecreeper = true;

	// V1.2
	public static boolean colorabletorch = true;

	public static boolean endexpansion = true;
	public static boolean endercarp = true;
	public static boolean ancientcarp = true;
	public static boolean noaishulker = true;
	public static boolean voidcreeper = true;

	public static boolean weathercyclecmd = true;


	//BOOSTER
	private static float boostT1 = 0.5f;
	private static float boostT2 = 1.0f;
	private static float boostT3 = 1.5f;
	private static float boostT4 = 1.5f;


	//ANCIENT CARP
	public static int maxcarps = 20;


	private static Configuration conf;

	public static void preInit(File configFile)
	{
		if (conf == null) 
			conf = new Configuration(configFile);

		if(!conf.hasKey("version", "version") && configFile.exists())
		{
			convertConfig(configFile);
		}
		else
		{
			ver = conf.get("version", "version", VERSION).getInt();

			// VANILLA
			buttbooster = conf.get("Vanilla", "ButtBooster", true).getBoolean();
			concrete = conf.get("Vanilla", "Concrete", true).getBoolean();
			terracottablocks = conf.get("Vanilla", "Terracotta Blocks", true).getBoolean();
			ironnuggets = conf.get("Vanilla", "Iron Nuggets", true).getBoolean();
			observer = conf.get("Vanilla", "Observer", true).getBoolean();
			weathercyclecmd = conf.get("Vanilla", "Weather Cycle Rule", true).getBoolean();

			// CUSTOM
			lavasponge = conf.get("Custom", "Lava Sponge", true).getBoolean();
			sponge = conf.get("Custom", "Sponge", true).getBoolean();
			sacredwater = conf.get("Custom", "Sacred Water", true).getBoolean();
			cursedwater = conf.get("Custom", "Cursed Water", true).getBoolean();
			truecreeper = conf.get("Custom", "True Creeper Mob", true).getBoolean();
			peacecreeper = conf.get("Custom", "Peacefull Creeper Mob", true).getBoolean();
			supernametag = conf.get("Custom", "Super Name Tag", true).getBoolean();
			colorabletorch = conf.get("Custom", "Colorable Torch", true).getBoolean();
			endexpansion = conf.get("Custom", "End Expansion", true).getBoolean();

			// END EXPANSION
			endercarp = endexpansion ? conf.get("End Expansion", "Ender Carp", true).getBoolean() : false;
			ancientcarp = endexpansion ? conf.get("End Expansion", "Ancient Carp", true).getBoolean() : false;
			noaishulker = endexpansion ? conf.get("End Expansion", "Brainless Shulker", true).getBoolean() : false;
			voidcreeper = endexpansion ? conf.get("End Expansion", "Void Creeper", true).getBoolean() : false;
			maxcarps = conf.get("End Expansion", "Max Ancient Carps", 20).getInt();

			if(conf.hasChanged())
				conf.save();

			if(ver != VERSION)
				updateFile(configFile);
		}

	}

	// OLD 1.0 && 1.1 CONFIG CONVERTER
	private static void convertConfig(File file)
	{
		buttbooster = conf.getBoolean("ButtBooster", "modules", true, "");
		concrete = conf.getBoolean("Concrete", "modules", true, "");
		terracottablocks = conf.getBoolean("Terracotta Blocks", "modules", true, "");
		ironnuggets = conf.getBoolean("Iron Nuggets", "modules", true, "");
		observer = conf.getBoolean("Observer", "modules", true, "");
		lavasponge = conf.getBoolean("Lava Sponge", "modules", true, "");
		sponge = conf.getBoolean("Sponge", "modules", true, "");
		sacredwater = conf.getBoolean("Sacred Water", "modules", true, "");
		cursedwater = conf.getBoolean("Cursed Water", "modules", true, "");
		truecreeper = conf.getBoolean("True Creeper Mob", "modules", true, "");
		peacecreeper = conf.getBoolean("Peacefull Creeper Mob", "modules", true, "");
		supernametag = conf.getBoolean("Super Name Tag", "modules", true, "");

		updateFile(file);
	}

	// UPDATE IF CONVERSION TRIGGERED
	private static void updateFile(File file)
	{
		file.delete();
		conf = new Configuration(file);

		ver = conf.get("version", "version", VERSION).getInt();

		// VANILLA
		buttbooster = conf.get("Vanilla", "ButtBooster", buttbooster).getBoolean();
		concrete = conf.get("Vanilla", "Concrete", concrete).getBoolean();
		terracottablocks = conf.get("Vanilla", "Terracotta Blocks", terracottablocks).getBoolean();
		ironnuggets = conf.get("Vanilla", "Iron Nuggets", ironnuggets).getBoolean();
		observer = conf.get("Vanilla", "Observer", observer).getBoolean();
		weathercyclecmd = conf.get("Vanilla", "Weather Cycle Rule", weathercyclecmd).getBoolean();


		// CUSTOM
		lavasponge = conf.get("Custom", "Lava Sponge", lavasponge).getBoolean();
		sponge = conf.get("Custom", "Sponge", sponge).getBoolean();
		sacredwater = conf.get("Custom", "Sacred Water", sacredwater).getBoolean();
		cursedwater = conf.get("Custom", "Cursed Water", cursedwater).getBoolean();
		truecreeper = conf.get("Custom", "True Creeper Mob", truecreeper).getBoolean();
		peacecreeper = conf.get("Custom", "Peacefull Creeper Mob", peacecreeper).getBoolean();
		supernametag = conf.get("Custom", "Super Name Tag", supernametag).getBoolean();
		colorabletorch = conf.get("Custom", "Colorable Torch", colorabletorch).getBoolean();
		endexpansion = conf.get("Custom", "End Expansion", endexpansion).getBoolean();

		// END EXPANSION
		endercarp = endexpansion ? conf.get("End Expansion", "Ender Carp", endercarp).getBoolean() : false;
		ancientcarp = endexpansion ? conf.get("End Expansion", "Ancient Carp", ancientcarp).getBoolean() : false;
		noaishulker = endexpansion ? conf.get("End Expansion", "Brainless Shulker", noaishulker).getBoolean() : false;
		voidcreeper = endexpansion ? conf.get("End Expansion", "Void Creeper", voidcreeper).getBoolean() : false;
		maxcarps = conf.get("End Expansion", "Max Ancient Carps", maxcarps).getInt();

		conf.save();
	}

	public static float GetBoosterTier(int i)
	{
		switch (i) 
		{
		case 1:
			return boostT1;
		case 2:
			return boostT2;
		case 3:
			return boostT3;
		case 4:
			return boostT4;
		default:
			return 0;
		}
	}

	public static int GetBoosterDuration(int i) 
	{
		switch (i) 
		{
		case 1:
			return 20;
		case 2:
			return 30;
		case 3:
			return 40;
		case 4:
			return 40;
		default:
			return 20;
		}
	}
}
