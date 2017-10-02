package com.iamshift.items;

import com.iamshift.Config;
import com.iamshift.MineAddons;
import com.iamshift.potions.BoosterPotion;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModItems 
{
	public static Item buttBooster;
	public static Potion potionBooster;
	
	public static Item cellulose;
	
	public static Item witherDust;
	public static Item rainbowBottle;
	
	public static Item ironnugget;
	
	public static Item supernametag;
	
	public static void init()
	{
		if(Config.buttbooster)
		{
			buttBooster = new Booster("booster");
			potionBooster = new BoosterPotion("booster");
		}
		
		if(Config.lavasponge || Config.sponge)
		{
			cellulose = new Cellulose("cellulose");
		}

		if(Config.sacredwater || Config.cursedwater)
			EntityRegistry.registerModEntity(EntityWaterChanger.class, EntityWaterChanger.class.getSimpleName(), 10, MineAddons.INSTANCE, 16, 4, true);
		
		if(Config.sacredwater)
			rainbowBottle = new RainbowBottle("rainbowbottle");
		
		if(Config.cursedwater)
			witherDust = new WitherDust("witherdust");
		
		if(Config.ironnuggets)
			ironnugget = new IronNugget("ironnugget");
		
		if(Config.supernametag)
			supernametag = new SuperNameTag("supernametag");
	}
}
