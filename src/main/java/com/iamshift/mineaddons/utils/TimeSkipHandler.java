package com.iamshift.mineaddons.utils;

import java.util.Date;

import com.iamshift.mineaddons.Config;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class TimeSkipHandler
{
	private static boolean TIMESKIP = false;
	private static boolean start = true;
	private static int amountToSkip = 0;

	private static int delay;
	private static int cooldown = 0;
	private static int count = Config.timeskipdelay;

	private static World worldIn = null;
	
	public synchronized static void cancelTimeSkip()
	{
		if(!shouldTimeSkip())
			return;

		TIMESKIP = false;
		amountToSkip = 0;
		delay = 0;
		count = Config.timeskipdelay;

		for(EntityPlayer player : worldIn.playerEntities)
			player.addChatMessage(new TextComponentTranslation("text.timeskipclock.cancel"));
	}

	public static boolean shouldTimeSkip()
	{
		return TIMESKIP;
	}

	public synchronized static void startTimeSkip(int amount, World world, EntityPlayer sender)
	{
		if(shouldTimeSkip())
			return;
		
		if(cooldown > 0)
		{
			sender.addChatMessage(new TextComponentTranslation("text.timeskipclock.cooldown", cooldown));
			return;
		}

		if(!(world.provider instanceof WorldProviderSurface))
			return;
		
		worldIn = world;
		
		TIMESKIP = true;
		start = true;
		amountToSkip = amount * 1000;

		for(EntityPlayer player : worldIn.playerEntities)
		{
			player.addChatMessage(new TextComponentTranslation("text.timeskipclock.start", amountToSkip/1000, Config.timeskipdelay));
			player.addChatMessage(new TextComponentTranslation("text.timeskipclock.cancelmsg"));
		}
	}

	private synchronized static void terminateTimeSkip() 
	{
		TIMESKIP = false;
		amountToSkip = 0;
		delay = 0;
		count = Config.timeskipdelay;
		cooldown = Config.timeskipcooldown;

		for(EntityPlayer player : worldIn.playerEntities)
			player.addChatMessage(new TextComponentTranslation("text.timeskipclock.complete"));
	}


	@SubscribeEvent
	public void tick(WorldTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END)
			return;
		
		if(event.world.isRemote)
			return;
		
		if(!(event.world.provider instanceof WorldProviderSurface))
			return;

		if(delay < 20)
		{
			delay++;
			return;
		}
		else
			delay = 0;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		if(!shouldTimeSkip())
			return;
		
		if(count > 0)
		{
			count--;
			
			if(count == 10)
			{
				for(EntityPlayer player : worldIn.playerEntities)
				{
					player.addChatMessage(new TextComponentTranslation("text.timeskipclock.start", amountToSkip/1000, 10));
					player.addChatMessage(new TextComponentTranslation("text.timeskipclock.cancelmsg"));
				}
			}
			
			if(count == 5)
			{
				for(EntityPlayer player : worldIn.playerEntities)
				{
					player.addChatMessage(new TextComponentTranslation("text.timeskipclock.start", amountToSkip/1000, 5));
					player.addChatMessage(new TextComponentTranslation("text.timeskipclock.cancelmsg"));
				}
			}
			
			return;
		}

		if(start)
		{
			for(EntityPlayer player : worldIn.playerEntities)
				player.addChatMessage(new TextComponentTranslation("text.timeskipclock.skip"));
			
			start = false;
		}
		
		if(TIMESKIP)
		{
			if(amountToSkip > 0)
			{
				amountToSkip -= 200;
				worldIn.setWorldTime(worldIn.getWorldTime() + 200);
			}
			else
				terminateTimeSkip();
		}
	}
}
