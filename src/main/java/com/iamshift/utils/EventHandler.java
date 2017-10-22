package com.iamshift.utils;

import java.util.Random;

import com.iamshift.Config;
import com.iamshift.entities.AncientCarp;
import com.iamshift.entities.ModEntities;
import com.iamshift.entities.NoAiShulker;
import com.iamshift.entities.PeaceCreeper;
import com.iamshift.fluids.blocks.CursedWaterBlock;
import com.iamshift.fluids.blocks.SacredWaterBlock;
import com.iamshift.items.ModItems;

import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler 
{
	@SubscribeEvent
	public void createFluidSource(BlockEvent.CreateFluidSourceEvent event)
	{
		if(Config.sacredwater && event.getState().getBlock() instanceof SacredWaterBlock)
		{
			event.setResult(Event.Result.ALLOW);
			return;
		}

		if(Config.cursedwater && event.getState().getBlock() instanceof CursedWaterBlock)
		{
			event.setResult(Event.Result.ALLOW);
			return;
		}
	}

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event)
	{
		if (Config.cursedwater && event.getName().equals(LootTableList.ENTITIES_WITHER_SKELETON)) 
		{
			final LootPool pool2 = event.getTable().getPool("pool2");

			if (pool2 != null)
				pool2.addEntry(new LootEntryItem(ModItems.witherDust, 5, 0, new LootFunction[0], new LootCondition[0], "loottable:witherdust"));
		}

		if (Config.sacredwater && event.getName().equals(LootTableList.ENTITIES_GUARDIAN)) 
		{
			final LootPool pool2 = event.getTable().getPool("pool2");

			if (pool2 != null)
				pool2.addEntry(new LootEntryItem(ModItems.rainbowBottle, 5, 0, new LootFunction[0], new LootCondition[0], "loottable:rainbowbottle"));
		}
	}
	
	@SubscribeEvent
	public void onEntityDie(LivingDeathEvent event)
	{
		if(event.getEntity().worldObj.isRemote)
			return;
		
		if(Config.endexpansion && !Config.ancientcarp && Config.noaishulker)
		{
			if(event.getEntity() instanceof EntityShulker)
			{
				if(new Random().nextInt(5) == 0)
				{
					event.getEntity().dropItem(ModItems.noaishulkerspawnegg, 1);
				}
			}
		}
	}


	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if(event.world.isRemote)
			return;

		if(Config.weathercyclecmd)
		{
			if(!event.world.isRaining())
				return;
			
			if(!event.world.getGameRules().hasRule("doWeatherCycle"))
				return;

			if(event.world.getGameRules().getBoolean("doWeatherCycle"))
				return;

			event.world.getWorldInfo().setRaining(false);
		}
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if(event.getWorld().isRemote)
			return;

		if(event.getEntity() instanceof AncientCarp)
		{
			if(ModEntities.ANCIENT_LIMIT < Config.maxcarps)
				ModEntities.ANCIENT_LIMIT++;
			else
				event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onRightClick(EntityInteract event)
	{
		if(event.getWorld().isRemote)
			return;

		if(event.getItemStack() == null)
			return;

		if(event.getTarget() instanceof AncientCarp)
		{
			event.setCanceled(true);
			return;
		}

		if(event.getTarget() instanceof NoAiShulker)
		{
			event.setCanceled(true);
			return;
		}

		if(event.getTarget() instanceof PeaceCreeper)
		{
			event.setCanceled(true);
			return;
		}
	}
}
