package com.iamshift.blocks.colorabletorch;

import com.iamshift.References;

import net.minecraft.util.ResourceLocation;

public class PurpleTorch extends BaseTorch
{
	public PurpleTorch(String name) 
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
	}
}
