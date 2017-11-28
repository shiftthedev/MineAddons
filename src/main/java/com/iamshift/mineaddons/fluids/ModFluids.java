package com.iamshift.mineaddons.fluids;

import com.iamshift.mineaddons.Config;
import com.iamshift.mineaddons.fluids.blocks.CursedWaterBlock;
import com.iamshift.mineaddons.fluids.blocks.SacredWaterBlock;
import com.iamshift.mineaddons.potions.MobChangerPotion;

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
