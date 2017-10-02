package com.iamshift;

import com.iamshift.entities.EntityTrueCreeper;
import com.iamshift.fluids.blocks.CursedWaterBlock;
import com.iamshift.fluids.blocks.SacredWaterBlock;
import com.iamshift.items.ModItems;

import net.minecraft.init.Biomes;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}
