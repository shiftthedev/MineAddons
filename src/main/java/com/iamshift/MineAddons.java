package com.iamshift;

import com.iamshift.achievements.AchievementHandler;
import com.iamshift.blocks.ModBlocks;
import com.iamshift.entities.ModEntities;
import com.iamshift.fluids.ModFluids;
import com.iamshift.items.ModItems;
import com.iamshift.proxy.CommonProxy;
import com.iamshift.utils.RecipeHandler;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid=References.MODID, name=References.MODNAME, version=References.MODVERSION, acceptedMinecraftVersions="[1.10,)")
public class MineAddons 
{
	@Mod.Instance
	public static MineAddons INSTANCE;

	@SidedProxy(clientSide=References.CLIENT, serverSide=References.SERVER)
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper network;

	static 
	{
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.preInit(event.getSuggestedConfigurationFile());
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);

		ModFluids.register();

		ModItems.init();

		ModBlocks.init();
		ModBlocks.register();
		
		ModEntities.init();

		proxy.registerRenders();
		proxy.registerLootTables();
		proxy.registerSounds();
		
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
		proxy.registerEvents();
	}
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{
		if(Config.weathercyclecmd)
			event.getServer().getEntityWorld().getGameRules().setOrCreateGameRule("doWeatherCycle", "true");
	}
	
	@Mod.EventHandler
	public void serverStopped(FMLServerStoppedEvent event)
	{
		proxy.unregisterEvents();
	}
}
