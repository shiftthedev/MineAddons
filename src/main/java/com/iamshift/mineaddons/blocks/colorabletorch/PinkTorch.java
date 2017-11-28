package com.iamshift.mineaddons.blocks.colorabletorch;

import com.iamshift.mineaddons.References;

import net.minecraft.util.ResourceLocation;

public class PinkTorch extends BaseTorch
{
	public PinkTorch(String name) 
	{
		super();
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(References.MODID, name));
	}
}
