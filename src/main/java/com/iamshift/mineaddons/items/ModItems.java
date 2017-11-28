package com.iamshift.mineaddons.items;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.MineAddons;
import com.iamshift.mineaddons.items.entities.EntityWaterChanger;
import com.iamshift.mineaddons.potions.BoosterPotion;

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
	
	public static Item sushi;
	
	public static Item ancientessence;
	
	public static Item noaishulkerspawnegg;
	
	public static Item timeskipclock;
	
	public static void init()
	{
		if(Config.buttbooster)
		{
			buttBooster = new Booster("booster").setCreativeTab(MineAddons.mineaddonstab);
			potionBooster = new BoosterPotion("booster");
		}
		
		if(Config.lavasponge || Config.sponge)
		{
			cellulose = new Cellulose("cellulose").setCreativeTab(MineAddons.mineaddonstab);
		}

		if(Config.sacredwater || Config.cursedwater)
			EntityRegistry.registerModEntity(EntityWaterChanger.class, EntityWaterChanger.class.getSimpleName(), 10, MineAddons.INSTANCE, 16, 4, true);
		
		if(Config.sacredwater)
			rainbowBottle = new RainbowBottle("rainbowbottle").setCreativeTab(MineAddons.mineaddonstab);
		
		if(Config.cursedwater)
			witherDust = new WitherDust("witherdust").setCreativeTab(MineAddons.mineaddonstab);
		
		if(Config.ironnuggets)
			ironnugget = new IronNugget("ironnugget").setCreativeTab(MineAddons.mineaddonstab);
		
		if(Config.supernametag)
			supernametag = new SuperNameTag("supernametag").setCreativeTab(MineAddons.mineaddonstab);
		
		if(Config.endexpansion)
		{
			if(Config.endercarp || Config.ancientcarp)
				sushi = new Sushi("sushi").setCreativeTab(MineAddons.mineaddonstab);
			
			if(Config.ancientcarp && Config.noaishulker)
				ancientessence = new AncientEssence("ancientessence").setCreativeTab(MineAddons.mineaddonstab);
			
			if(Config.noaishulker)
				noaishulkerspawnegg = new BrainlessShulkerEgg("noaishulkerspawnegg").setCreativeTab(MineAddons.mineaddonstab);
		}
		
		if(Config.timeskipclock)
			timeskipclock = new TimeSkipClock("timeskipclock").setCreativeTab(MineAddons.mineaddonstab);
	}
}
