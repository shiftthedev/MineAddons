package com.iamshift.items;

import com.iamshift.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Cellulose extends Item
{
	public Cellulose(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);
		
		setCreativeTab(CreativeTabs.MATERIALS);
		
		GameRegistry.register(this);
	}
}
