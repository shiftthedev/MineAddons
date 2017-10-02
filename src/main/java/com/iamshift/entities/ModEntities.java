package com.iamshift.entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.iamshift.Config;
import com.iamshift.MineAddons;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void init()
	{
		if(Config.truecreeper)
		{
			EntityRegistry.registerModEntity(EntityTrueCreeper.class, "truecreeper", 1, MineAddons.INSTANCE, 128, 2, true, new Color(50, 50, 50).getRGB(), new Color(255, 192, 0).getRGB());
			EntityRegistry.addSpawn(EntityTrueCreeper.class, 6, 1, 3, EnumCreatureType.MONSTER, getBiomes());
		}

		if(Config.peacecreeper)
			EntityRegistry.registerModEntity(EntityPeaceCreeper.class, "peacecreeper", 2, MineAddons.INSTANCE, 128, 2, true, new Color(255, 0, 125).getRGB(), new Color(255, 0, 75).getRGB());
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

