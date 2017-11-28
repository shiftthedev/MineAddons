package com.iamshift.mineaddons.entities.renders;

import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.entities.AncientCarp;
import com.iamshift.mineaddons.entities.models.ModelAncientCarp;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAncientCarp extends RenderLiving<AncientCarp>
{
	public RenderAncientCarp(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new ModelAncientCarp(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(AncientCarp entity) 
	{
		return new ResourceLocation(References.MODID, "textures/entities/ancientcarp.png");
	}
}
