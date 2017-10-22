package com.iamshift.entities.renders;

import com.iamshift.References;
import com.iamshift.entities.AncientCarp;
import com.iamshift.entities.models.ModelAncientCarp;

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
