package com.iamshift.mineaddons.entities.renders;

import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.entities.EnderCarp;
import com.iamshift.mineaddons.entities.models.ModelEnderCarp;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEnderCarp extends RenderLiving<EnderCarp> 
{
	public RenderEnderCarp(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new ModelEnderCarp(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EnderCarp entity) 
	{
		return new ResourceLocation(References.MODID, "textures/entities/endercarp_" + entity.getTexture() + ".png");
	}

	@Override
	protected void preRenderCallback(EnderCarp entitylivingbaseIn, float partialTickTime) 
	{
		GlStateManager.scale(.5F * entitylivingbaseIn.getCarpSize(), .5F * entitylivingbaseIn.getCarpSize(), .5F * entitylivingbaseIn.getCarpSize());
		this.shadowSize = 0.25f * entitylivingbaseIn.getCarpSize();
	}
}
