package com.iamshift.fluids;

import com.iamshift.Config;
import com.iamshift.fluids.blocks.CursedWaterBlock;
import com.iamshift.fluids.blocks.SacredWaterBlock;
import com.iamshift.potions.MobChangerPotion;

import net.minecraft.potion.Potion;

public class ModFluids 
{
	public static SacredWaterFluid sacredwater;
	public static SacredWaterBlock sacredwater_block;
	
	public static CursedWaterFluid cursedwater;
	public static CursedWaterBlock cursedwater_block;
	
	public static Potion potionMobChanger;
	
	public static void register()
	{
		if(Config.sacredwater || Config.cursedwater)
			potionMobChanger = new MobChangerPotion("mobchanger");
		
		if(Config.sacredwater)
		{
			sacredwater = new SacredWaterFluid();
			sacredwater_block = new SacredWaterBlock();
		}
		
		if(Config.cursedwater)
		{
			cursedwater = new CursedWaterFluid();
			cursedwater_block = new CursedWaterBlock();
		}
	}
	
	public static void renderFluids()
	{
		if(Config.sacredwater)
			sacredwater_block.render();
		
		if(Config.cursedwater)
			cursedwater_block.render();
	}
}
