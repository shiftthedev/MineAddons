package com.iamshift.potions;

import com.iamshift.References;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BoosterPotion extends Potion
{
	public BoosterPotion(String name)
	{
		super(false, 6618980);
		setPotionName("potion." + name);
		setRegistryName(References.MODID, name);
		setIconIndex(0, 0);
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return true;
	}

	public boolean shouldRender(PotionEffect effect) 
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

	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon()
	{
		return false;
	}
}
