package com.iamshift.mineaddons.items;

import com.iamshift.mineaddons.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class IronNugget extends Item
{
	public IronNugget(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
		setMaxStackSize(64);
		
		GameRegistry.register(this);
	}
}
