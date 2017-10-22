package com.iamshift.blocks.colorabletorch;

import com.iamshift.References;

import net.minecraft.util.ResourceLocation;

public class CyanTorch extends BaseTorch
{
	public CyanTorch(String name) 
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
	}
}
