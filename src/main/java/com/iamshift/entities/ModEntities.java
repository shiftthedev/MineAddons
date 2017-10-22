package com.iamshift.entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.iamshift.Config;
import com.iamshift.MineAddons;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	private static int ENTITY_ID = 0;

	public static int ANCIENT_LIMIT = 0;
	
	public static void init()
	{
		if(Config.truecreeper)
		{
			EntityRegistry.registerModEntity(TrueCreeper.class, "truecreeper", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true, new Color(50, 50, 50).getRGB(), new Color(255, 192, 0).getRGB());
			EntityRegistry.addSpawn(TrueCreeper.class, 8, 1, 3, EnumCreatureType.MONSTER, getBiomes());
		}

		if(Config.peacecreeper)
			EntityRegistry.registerModEntity(PeaceCreeper.class, "peacecreeper", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true, new Color(255, 0, 125).getRGB(), new Color(255, 0, 75).getRGB());

		
		if(Config.endexpansion)
		{
			if(Config.endercarp)
			{
				EntityRegistry.registerModEntity(EnderCarp.class, "endercarp", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true, new Color(150, 150, 150).getRGB(), new Color(255, 192, 0).getRGB());
				EntityRegistry.addSpawn(EnderCarp.class, 8, 2, 4, EnumCreatureType.MONSTER, Biomes.SKY);
			}

			if(Config.ancientcarp)
			{
				EntityRegistry.registerModEntity(AncientCarp.class, "ancientcarp", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true, new Color(150, 150, 150).getRGB(), new Color(255, 255, 255).getRGB());
				EntityRegistry.addSpawn(AncientCarp.class, 3, 1, 1, EnumCreatureType.MONSTER, Biomes.SKY);
			}

			if(Config.noaishulker)
				EntityRegistry.registerModEntity(NoAiShulker.class, "noaishulker", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true);
			
			if(Config.voidcreeper)
			{
				EntityRegistry.registerModEntity(VoidCreeper.class, "voidcreeper", ++ENTITY_ID, MineAddons.INSTANCE, 128, 2, true, new Color(25, 25, 25).getRGB(), new Color(125, 0, 125).getRGB());
				EntityRegistry.addSpawn(VoidCreeper.class, 8, 1, 2, EnumCreatureType.MONSTER, Biomes.SKY);
			}
		}
	}

	private static Biome[] getBiomes()
	{
		ArrayList<Biome> biomes = new ArrayList<Biome>();

		ArrayList<BiomeDictionary.Type> biome = new ArrayList<BiomeDictionary.Type>();
		biome.addAll(Arrays.asList(BiomeDictionary.Type.values()));

		for(BiomeDictionary.Type type : biome)
			biomes.addAll(Arrays.asList(BiomeDictionary.getBiomesForType(type)));

		return biomes.toArray(new Biome[biomes.size()]);
	}
}

