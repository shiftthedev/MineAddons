package com.iamshift.potions;

import com.iamshift.References;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class MobChangerPotion extends Potion
{
	public MobChangerPotion(String name) 
	{
		super(false, 0000000);
		setPotionName("potion." + name);
		setRegistryName(References.MODID, name);
		setIconIndex(0, 0);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) 
	{
		return true;
	}
	
	public boolean shouldRender(net.minecraft.potion.PotionEffect effect) 
	{
		return false; 
	}
	
	@Override
	public boolean shouldRenderInvText(PotionEffect effect) 
	{
		return false; 
	}
	
	@Override
	public boolean shouldRenderHUD(PotionEffect effect) 
	{
		return false; 
	}
	
	@Override
	public boolean hasStatusIcon() 
	{
		return false; 
	}
}
