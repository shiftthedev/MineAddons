package com.iamshift.achievements;

import com.iamshift.blocks.lavasponge.LavaSponge;
import com.iamshift.items.Booster;
import com.iamshift.items.RainbowBottle;
import com.iamshift.items.WitherDust;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AchievementsEvents 
{
	@SubscribeEvent
	public void onItemPickUp(EntityItemPickupEvent event)
	{
		if(event.getEntityPlayer() == null)
			return;
		
		EntityPlayer player = event.getEntityPlayer();
		
		if(event.getItem().getEntityItem().getItem() instanceof RainbowBottle && !player.hasAchievement(AchievementHandler.achievementRainbowBottle))
		{
			player.addStat(AchievementHandler.achievementRainbowBottle);
			return;
		}
		
		if(event.getItem().getEntityItem().getItem() instanceof WitherDust && !player.hasAchievement(AchievementHandler.achievementWitherDust))
		{
			player.addStat(AchievementHandler.achievementWitherDust);
			return;
		}
	}
	
	@SubscribeEvent
	public void onItemCrafting(PlayerEvent.ItemCraftedEvent event)
	{
		if(event.crafting.getItem() instanceof Booster)
		{
			if(!event.player.hasAchievement(AchievementHandler.achievementButtBooster))
				event.player.addStat(AchievementHandler.achievementButtBooster);
			
			return;
		}
		
		if(event.crafting.getItem().getUnlocalizedName().equals("tile.lavasponge"))
		{
			if(!event.player.hasAchievement(AchievementHandler.achievementLavaSponge))
				event.player.addStat(AchievementHandler.achievementLavaSponge);
			
			return;
		}
	}
}
