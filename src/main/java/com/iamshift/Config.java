package com.iamshift;

import java.io.File;
import java.util.ArrayList;

import com.iamshift.interfaces.IMobChanger;

import net.minecraftforge.common.config.Configuration;

public class Config 
{
	//ITEMS
	public static boolean buttbooster;
	public static boolean ironnuggets;
	public static boolean supernametag;

	//BLOCKS
	public static boolean concrete;
	public static boolean terracottablocks;
	public static boolean lavasponge;
	public static boolean sponge;
	public static boolean observer;

	//FLUIDS
	public static boolean sacredwater;
	public static boolean cursedwater;
	
	//ENTITIES
	public static boolean truecreeper;
	public static boolean peacecreeper;
	
	//SHULKER BOX
	//LLAMA
	//PARROTS
	
	//BOOSTER
	private static float boostT1;
	private static float boostT2;
	private static float boostT3;
	private static float boostT4;

	private static Configuration conf;

	public static void Load(File configFile)
	{
		if (conf == null) 
			conf = new Configuration(configFile);

		buttbooster = conf.getBoolean("ButtBooster", "Modules", true, "");

		concrete = conf.getBoolean("Concrete", "Modules", true, "");
		terracottablocks = conf.getBoolean("Terracotta Blocks", "Modules", true, "");
		lavasponge = conf.getBoolean("Lava Sponge", "Modules", true, "");
		sponge = conf.getBoolean("Sponge", "Modules", true, "");

		sacredwater = conf.getBoolean("Sacred Water", "Modules", true, "");
		cursedwater = conf.getBoolean("Cursed Water", "Modules", true, "");
		
		truecreeper = conf.getBoolean("True Creeper Mob", "Modules", true, "");
		peacecreeper = conf.getBoolean("Peacefull Creeper Mob", "Modules", true, "");
		
		ironnuggets = conf.getBoolean("Iron Nuggets", "Modules", true, "");
		
		observer = conf.getBoolean("Observer", "Modules", true, "");
		
		supernametag = conf.getBoolean("Super Name Tag", "Modules", true, "");
		
		if(buttbooster)
		{
			boostT1 = 0.5f;
			boostT2 = 1.0f;
			boostT3 = 1.5f;

			boostT4 = 1.5f;
		}
		
		if (conf.hasChanged()) 
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
