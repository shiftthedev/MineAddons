package com.iamshift.fluids;

import com.iamshift.References;

import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SacredWaterFluid extends Fluid
{
	public SacredWaterFluid() 
	{
		super("sacredwater", new ResourceLocation(References.MODID, "fluids/sacredwater_still"), new ResourceLocation(References.MODID, "fluids/sacredwater_flow"));
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
		setTemperature(150);
		setRarity(EnumRarity.RARE);
	}
}
