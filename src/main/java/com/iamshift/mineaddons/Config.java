package com.iamshift.mineaddons;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

public class Config
{
	public static Map<ResourceLocation, Integer> captureItems = new HashMap<ResourceLocation, Integer>();
	
	// CONTROL
	private static final int VERSION = 4;
	private static int ver;

	//MODULES
	public static boolean vanilla = true;
	public static boolean custom = true;
	public static boolean endexpansion = true;

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

	public static boolean endercarp = true;
	public static boolean ancientcarp = true;
	public static boolean noaishulker = true;
	public static boolean voidcreeper = true;

	public static boolean weathercyclecmd = true;

	// V1.3
	public static boolean timeskipclock = false;

	
	//BOOSTER
	private static float boostT1 = 0.5f;
	private static float boostT2 = 1.0f;
	private static float boostT3 = 1.5f;
	private static float boostT4 = 1.5f;

	//ANCIENT CARP
	public static int maxcarps = 20;
	public static boolean captureancientcarps = false;

	// TIME SKIP DELAY
	public static int timeskipdelay = 30;
	public static int timeskipcooldown = 60;

	private static Configuration conf;

	public static void preInit(File configFile)
	{
		if (conf == null) 
			conf = new Configuration(configFile);
		
		captureItems.put(new ResourceLocation("minefactoryreloaded", "safari_net_reusable"), -1);
		captureItems.put(new ResourceLocation("minefactoryreloaded", "safari_net_single_use"), -1);
		captureItems.put(new ResourceLocation("notenoughwands", "capturing_wand"), -1);
		captureItems.put(new ResourceLocation("rftools", "syringe"), -1);
		captureItems.put(new ResourceLocation("actuallyadditions", "itemSpawnerChanger"), -1);
		captureItems.put(new ResourceLocation("extrautils2", "goldenlasso"), -1);
		captureItems.put(new ResourceLocation("enderio", "itemSoulVessel"), -1);
		captureItems.put(new ResourceLocation("woot", "prism"), -1);

		if(!conf.hasKey("version", "version") && configFile.exists())
		{
			convertConfig(configFile);
		}
		else
		{
			ver = conf.get("version", "version", VERSION).getInt();

			// MODULES
			vanilla = conf.get("aModules", "Vanilla", true).getBoolean();
			custom = conf.get("aModules", "Custom", true).getBoolean();
			endexpansion = conf.get("aModules", "End Expansion", true).getBoolean();

			// VANILLA
			buttbooster = vanilla ? conf.get("Vanilla", "ButtBooster", true).getBoolean() : false;
			concrete = vanilla ? conf.get("Vanilla", "Concrete", true).getBoolean() : false;
			terracottablocks = vanilla ? conf.get("Vanilla", "Terracotta Blocks", true).getBoolean() : false;
			ironnuggets = vanilla ? conf.get("Vanilla", "Iron Nuggets", true).getBoolean() : false;
			observer = vanilla ? conf.get("Vanilla", "Observer", true).getBoolean() : false;
			weathercyclecmd = vanilla ? conf.get("Vanilla", "Weather Cycle Rule", true).getBoolean() : false;

			// CUSTOM
			lavasponge = custom ? conf.get("Custom", "Lava Sponge", true).getBoolean() : false;
			sponge = custom ? conf.get("Custom", "Sponge", true).getBoolean() : false;
			sacredwater = custom ? conf.get("Custom", "Sacred Water", true).getBoolean() : false;
			cursedwater = custom ? conf.get("Custom", "Cursed Water", true).getBoolean() : false;
			truecreeper = custom ? conf.get("Custom", "True Creeper Mob", true).getBoolean() : false;
			peacecreeper = custom ? conf.get("Custom", "Peacefull Creeper Mob", true).getBoolean() : false;
			supernametag = custom ? conf.get("Custom", "Super Name Tag", true).getBoolean() : false;
			colorabletorch = custom ? conf.get("Custom", "Colorable Torch", true).getBoolean() : false;
			timeskipclock = custom ? conf.get("Custom", "TimeSkip Clock", false).getBoolean() : false;
			timeskipdelay = conf.get("Custom", "TimeSkip Start Delay", 30).getInt();
			timeskipcooldown = conf.get("Custom", "TimeSkip Cooldown", 60).getInt();

			// END EXPANSION
			endercarp = endexpansion ? conf.get("End_Expansion", "Ender Carp", true).getBoolean() : false;
			ancientcarp = endexpansion ? conf.get("End_Expansion", "Ancient Carp", true).getBoolean() : false;
			noaishulker = endexpansion ? conf.get("End_Expansion", "Brainless Shulker", true).getBoolean() : false;
			voidcreeper = endexpansion ? conf.get("End_Expansion", "Void Creeper", true).getBoolean() : false;
			maxcarps = conf.get("End_Expansion", "Max Ancient Carps", 20).getInt();
			captureancientcarps = conf.get("End Expansion", "Capturable Ancient Carps", false).getBoolean();
			
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

		// MODULES
		vanilla = conf.get("aModules", "Vanilla", vanilla).getBoolean();
		custom = conf.get("aModules", "Custom", custom).getBoolean();
		endexpansion = conf.get("aModules", "End Expansion", endexpansion).getBoolean();

		// VANILLA
		buttbooster = vanilla ? conf.get("Vanilla", "ButtBooster", buttbooster).getBoolean() : false;
		concrete = vanilla ? conf.get("Vanilla", "Concrete", concrete).getBoolean() : false;
		terracottablocks = vanilla ? conf.get("Vanilla", "Terracotta Blocks", terracottablocks).getBoolean() : false;
		ironnuggets = vanilla ? conf.get("Vanilla", "Iron Nuggets", ironnuggets).getBoolean() : false;
		observer = vanilla ? conf.get("Vanilla", "Observer", observer).getBoolean() : false;
		weathercyclecmd = vanilla ? conf.get("Vanilla", "Weather Cycle Rule", weathercyclecmd).getBoolean() : false;

		// CUSTOM
		lavasponge = custom ? conf.get("Custom", "Lava Sponge", lavasponge).getBoolean() : false;
		sponge = custom ? conf.get("Custom", "Sponge", sponge).getBoolean() : false;
		sacredwater = custom ? conf.get("Custom", "Sacred Water", sacredwater).getBoolean() : false;
		cursedwater = custom ? conf.get("Custom", "Cursed Water", cursedwater).getBoolean() : false;
		truecreeper = custom ? conf.get("Custom", "True Creeper Mob", truecreeper).getBoolean() : false;
		peacecreeper = custom ? conf.get("Custom", "Peacefull Creeper Mob", peacecreeper).getBoolean() : false;
		supernametag = custom ? conf.get("Custom", "Super Name Tag", supernametag).getBoolean() : false;
		colorabletorch = custom ? conf.get("Custom", "Colorable Torch", colorabletorch).getBoolean() : false;
		timeskipclock = custom ? conf.get("Custom", "TimeSkip Clock", timeskipclock).getBoolean() : false;
		timeskipdelay = conf.get("Custom", "TimeSkip Start Delay", timeskipdelay).getInt();
		timeskipcooldown = conf.get("Custom", "TimeSkip Cooldown", timeskipcooldown).getInt();

		// END EXPANSION
		endercarp = endexpansion ? conf.get("End_Expansion", "Ender Carp", endercarp).getBoolean() : false;
		ancientcarp = endexpansion ? conf.get("End_Expansion", "Ancient Carp", ancientcarp).getBoolean() : false;
		noaishulker = endexpansion ? conf.get("End_Expansion", "Brainless Shulker", noaishulker).getBoolean() : false;
		voidcreeper = endexpansion ? conf.get("End_Expansion", "Void Creeper", voidcreeper).getBoolean() : false;
		maxcarps = conf.get("End_Expansion", "Max Ancient Carps", maxcarps).getInt();
		captureancientcarps = conf.get("End Expansion", "Capturable Ancient Carps", captureancientcarps).getBoolean();

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
