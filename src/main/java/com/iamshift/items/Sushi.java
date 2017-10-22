package com.iamshift.items;

import com.iamshift.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Sushi extends ItemFood 
{

	public Sushi(String name) 
	{
		super(10, 1.0F, false);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);
		
		setCreativeTab(CreativeTabs.FOOD);
		
		GameRegistry.register(this);
	}
}
