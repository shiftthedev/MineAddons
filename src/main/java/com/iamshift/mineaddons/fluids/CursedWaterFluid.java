package com.iamshift.mineaddons.fluids;

import com.iamshift.mineaddons.References;

import net.minecraft.item.EnumRarity;
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
		setTemperature(1450);
		setRarity(EnumRarity.RARE);
	}
}
