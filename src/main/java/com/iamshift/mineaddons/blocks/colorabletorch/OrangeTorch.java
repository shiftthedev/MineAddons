package com.iamshift.mineaddons.blocks.colorabletorch;

import com.iamshift.mineaddons.References;

import net.minecraft.util.ResourceLocation;

public class OrangeTorch extends BaseTorch
{
	public OrangeTorch(String name) 
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
	}
}
