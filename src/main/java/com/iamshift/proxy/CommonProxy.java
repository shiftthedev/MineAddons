package com.iamshift.proxy;

import com.iamshift.Config;
import com.iamshift.References;
import com.iamshift.achievements.AchievementHandler;
import com.iamshift.achievements.AchievementsEvents;
import com.iamshift.blocks.ModBlocks;
import com.iamshift.utils.EventHandler;
import com.iamshift.utils.LootManager;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy 
{
	private static EventHandler events;
	private static AchievementsEvents achievements;
	
	public void registerEvents() 
	{
		events = new EventHandler();
		MinecraftForge.EVENT_BUS.register(events);

		if(Config.observer)
			MinecraftForge.EVENT_BUS.register(ModBlocks.observer);

		if(!AchievementHandler.isEmpty())
		{
			achievements = new AchievementsEvents();
			MinecraftForge.EVENT_BUS.register(achievements);
		}
	}

	public void unregisterEvents()
	{
		MinecraftForge.EVENT_BUS.unregister(events);

		if(!AchievementHandler.isEmpty())
			MinecraftForge.EVENT_BUS.unregister(achievements);
	}

	public void registerRenders() {}

	public void registerLootTables()
	{
		if(Config.endercarp || Config.ancientcarp)
			LootManager.ENDERCARP = LootTableList.register(new ResourceLocation(References.MODID, "endercarp"));
		
		if(Config.ancientcarp)
			LootManager.ANCIENTCARP = LootTableList.register(new ResourceLocation(References.MODID, "ancientcarp"));
		
		if(Config.noaishulker)
			LootManager.NOAISHULKER = LootTableList.register(new ResourceLocation(References.MODID, "noaishulker"));
	}

	public void registerSounds() {}
}
