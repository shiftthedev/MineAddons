package com.iamshift.fluids;

import com.iamshift.References;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CursedWaterFluid extends Fluid 
{
	public CursedWaterFluid() 
	{
		super("cursedwater", new ResourceLocation(References.MODID, "fluids/cursedwater_still"), new ResourceLocation(References.MODID, "fluids/cursedwater_flow"));
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
	}
}
