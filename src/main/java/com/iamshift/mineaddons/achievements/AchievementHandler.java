package com.iamshift.mineaddons.achievements;

import java.util.ArrayList;
import java.util.List;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.blocks.ModBlocks;
import com.iamshift.mineaddons.fluids.ModFluids;
import com.iamshift.mineaddons.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class AchievementHandler 
{
	private static List<Achievement> achievements = new ArrayList<Achievement>();

	public static Achievement achievementWitherDust;
	public static Achievement achievementCursedWater;

	public static Achievement achievementRainbowBottle;
	public static Achievement achievementSacredWater;

	public static Achievement achievementButtBooster;
	
	public static Achievement achievementLavaSponge;
	
	public static Achievement achievementTamePeaceCreeper;
	
	public static Achievement achievementKillAncientCarp;
	
	public static Achievement achievementSpawnBrainlessShulker;
	
	public static Achievement achievementTimeSkipClock;

	public static void init()
	{
		if(Config.buttbooster)
			achievementButtBooster = createAchievement("buttbooster", 1, 0, ModItems.buttBooster, null);

		if(Config.cursedwater)
		{
			achievementWitherDust = createAchievement("witherdust", 0, 2, ModItems.witherDust, null);
			achievementCursedWater = createAchievement("cursedwater", 0, 3, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.cursedwater), achievementWitherDust);
		}

		if(Config.sacredwater)
		{
			achievementRainbowBottle = createAchievement("rainbowbottle", 2, 2, ModItems.rainbowBottle, null);
			achievementSacredWater = createAchievement("sacredwater", 2, 3, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.sacredwater), achievementRainbowBottle);
		}
		
		if(Config.lavasponge)
			achievementLavaSponge = createAchievement("lavasponge", 1, 5, ModBlocks.lavasponge, null);
		
		if(Config.truecreeper)
			achievementTamePeaceCreeper = createAchievement("peacecreeper", 4, 1, Items.GUNPOWDER, null);
		
		if(Config.endexpansion)
		{
			if(Config.ancientcarp)
				achievementKillAncientCarp = createAchievement("ancientcarp", 4, 4, ModItems.ancientessence, null);
			
			if(Config.ancientcarp && Config.noaishulker)
				achievementSpawnBrainlessShulker = createAchievement("brainshulker", 5, 4, ModItems.noaishulkerspawnegg, achievementKillAncientCarp);
			else if(Config.noaishulker)
				achievementSpawnBrainlessShulker = createAchievement("brainshulker", 4, 4, ModItems.noaishulkerspawnegg, null);
		}
		
		if(Config.timeskipclock)
			achievementTimeSkipClock = createAchievement("timeskipclock", 5, 1, ModItems.timeskipclock, null);
	}

	public static void registerAchievements()
	{
		if(isEmpty())
			return;
		
		Achievement[] achievementArray = new Achievement[achievements.size()];

		for(Achievement achievement : achievements)
		{
			achievement.registerStat();
			achievementArray[achievements.indexOf(achievement)] = achievement;
		}
		AchievementPage.registerAchievementPage(new AchievementPage(References.MODNAME, achievementArray));
	}

	private static Achievement createAchievement(String name, int column, int row, Item item, Achievement parent)
	{
		Achievement achievement = new Achievement("achievement." + name, name, column, row, item, parent);
		achievements.add(achievement);
		return achievement;
	}

	private static Achievement createAchievement(String name, int column, int row, ItemStack stack, Achievement parent)
	{
		Achievement achievement = new Achievement("achievement." + name, name, column, row, stack, parent);
		achievements.add(achievement);
		return achievement;
	}

	private static Achievement createAchievement(String name, int column, int row, Block block, Achievement parent)
	{
		Achievement achievement = new Achievement("achievement." + name, name, column, row, block, parent);
		achievements.add(achievement);
		return achievement;
	}

	public static boolean isEmpty() 
	{
		return achievements.size() == 0;
	}
}
