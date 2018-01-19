package com.iamshift.mineaddons.utils;

import java.util.Random;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.MineAddons;
import com.iamshift.mineaddons.entities.AncientCarp;
import com.iamshift.mineaddons.entities.ModEntities;
import com.iamshift.mineaddons.entities.NoAiShulker;
import com.iamshift.mineaddons.entities.PeaceCreeper;
import com.iamshift.mineaddons.fluids.blocks.CursedWaterBlock;
import com.iamshift.mineaddons.fluids.blocks.SacredWaterBlock;
import com.iamshift.mineaddons.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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

	int count = 0;

	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if(event.world.isRemote)
			return;

		if(this.count > 1)
		{
			this.count--;
			return;
		}

		this.count = 20;

		if(Config.weathercyclecmd)
		{
			boolean isRaining = event.world.isRaining();

			if(event.world.getGameRules().getBoolean("doWeatherCycle"))
				return;

			if(isRaining == MineAddons.raining)
				return;
			else
			{
				System.out.println("set " + MineAddons.raining);
				event.world.getWorldInfo().setRaining(MineAddons.raining);
			}
		}
	}

	@SubscribeEvent
	public void onWeatherCommand(CommandEvent event)
	{
		if(Config.weathercyclecmd)
		{
			if(event.getCommand().getCommandName().toLowerCase().equals("toggledownfall"))
				MineAddons.raining = !MineAddons.raining;
			else
				if(event.getCommand().getCommandName().toLowerCase().equals("weather"))
					if(event.getParameters()[0].toLowerCase().equals("rain") || event.getParameters()[0].toLowerCase().equals("thunder"))
						MineAddons.raining = true;
					else if(event.getParameters()[0].toLowerCase().equals("clear"))
						MineAddons.raining = false;
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

		ItemStack stack = event.getItemStack();
		Entity target = event.getTarget();

		if(target instanceof PeaceCreeper)
		{
			if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
			{
				int meta = Config.captureItems.get(stack.getItem().getRegistryName());

				if(meta == -1 || meta == stack.getItemDamage())
				{
					event.setCanceled(true);
					return;
				}

			}
		}

		if(target instanceof AncientCarp)
		{
			if(Config.captureancientcarps)
			{
				if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
				{
					int meta = Config.captureItems.get(stack.getItem().getRegistryName());

					if(meta == -1 || meta == stack.getItemDamage())
					{
						event.setCanceled(true);
						return;
					}
				}
			}
		}

		if(target instanceof NoAiShulker)
		{
			if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
			{
				int meta = Config.captureItems.get(stack.getItem().getRegistryName());

				if(meta == -1 || meta == stack.getItemDamage())
				{
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onLeftClick(AttackEntityEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		Entity target = event.getTarget();
		ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);

		if(target instanceof PeaceCreeper)
		{
			if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
			{
				int meta = Config.captureItems.get(stack.getItem().getRegistryName());

				if(meta == -1 || meta == stack.getItemDamage())
				{
					event.setCanceled(true);
					return;
				}

			}
		}

		if(target instanceof AncientCarp)
		{
			if(Config.captureancientcarps)
			{
				if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
				{
					int meta = Config.captureItems.get(stack.getItem().getRegistryName());

					if(meta == -1 || meta == stack.getItemDamage())
					{
						event.setCanceled(true);
						return;
					}
				}
			}
		}

		if(target instanceof NoAiShulker)
		{
			if(Config.captureItems.containsKey(stack.getItem().getRegistryName()))
			{
				int meta = Config.captureItems.get(stack.getItem().getRegistryName());

				if(meta == -1 || meta == stack.getItemDamage())
				{
					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
