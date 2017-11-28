package com.iamshift.mineaddons.proxy;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.achievements.AchievementHandler;
import com.iamshift.mineaddons.achievements.AchievementsEvents;
import com.iamshift.mineaddons.blocks.ModBlocks;
import com.iamshift.mineaddons.utils.EventHandler;
import com.iamshift.mineaddons.utils.LootManager;
import com.iamshift.mineaddons.utils.TimeSkipHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy 
{
	private static EventHandler events;
	private static AchievementsEvents achievements;

	private static TimeSkipHandler timeskiphandler;

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

		if(Config.timeskipclock)
		{
			timeskiphandler = new TimeSkipHandler();
			MinecraftForge.EVENT_BUS.register(timeskiphandler);
		}
	}

	public void unregisterEvents()
	{
		MinecraftForge.EVENT_BUS.unregister(events);

		if(Config.observer)
			MinecraftForge.EVENT_BUS.unregister(ModBlocks.observer);

		if(!AchievementHandler.isEmpty())
			MinecraftForge.EVENT_BUS.unregister(achievements);

		if(Config.timeskipclock)
			MinecraftForge.EVENT_BUS.unregister(timeskiphandler);
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
