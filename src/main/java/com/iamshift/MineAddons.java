package com.iamshift;

import com.iamshift.achievements.AchievementHandler;
import com.iamshift.achievements.AchievementsEvents;
import com.iamshift.blocks.ModBlocks;
import com.iamshift.entities.ModEntities;
import com.iamshift.fluids.ModFluids;
import com.iamshift.items.ModItems;
import com.iamshift.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

@Mod(modid=References.MODID, name=References.MODNAME, version=References.MODVERSION, acceptedMinecraftVersions="[1.10,)")
public class MineAddons 
{
	public static EventHandler events;
	public static AchievementsEvents achievements;

	@Mod.Instance
	public static MineAddons INSTANCE;

	@SidedProxy(clientSide=References.CLIENT, serverSide=References.SERVER)
	public static CommonProxy proxy;

	static 
	{
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.Load(event.getSuggestedConfigurationFile());

		ModFluids.register();

		ModItems.init();

		ModBlocks.init();
		ModBlocks.register();
		
		ModEntities.init();

		proxy.registerRenders();
		
		AchievementHandler.init();
		AchievementHandler.registerAchievements();
	}


	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		RecipeHandler.registerRecipies();
		RecipeHandler.registerSmeltings();
	}

	@Mod.EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent event)
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
	
	@Mod.EventHandler
	public void serverStopped(FMLServerStoppedEvent event)
	{
		MinecraftForge.EVENT_BUS.unregister(events);
		
		if(!AchievementHandler.isEmpty())
			MinecraftForge.EVENT_BUS.unregister(achievements);
	}
}
