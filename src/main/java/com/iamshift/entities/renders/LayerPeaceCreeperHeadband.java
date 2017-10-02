package com.iamshift.entities.renders;

import com.iamshift.References;
import com.iamshift.entities.EntityPeaceCreeper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerPeaceCreeperHeadband implements LayerRenderer<EntityPeaceCreeper>
{
	private static final ResourceLocation HEADBAND = new ResourceLocation(References.MODID, "textures/entities/peacecreeper_headband.png");
	private final RenderPeaceCreeper peaceCreeperRender;
	
	public LayerPeaceCreeperHeadband(RenderPeaceCreeper render) 
	{
		this.peaceCreeperRender = render;
	}
	
	@Override
	public void doRenderLayer(EntityPeaceCreeper entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		if(entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible())
		{
			this.peaceCreeperRender.bindTexture(HEADBAND);
			EnumDyeColor color = EnumDyeColor.byMetadata(entitylivingbaseIn.getCollarColor().getMetadata());
			float[] afloat = EntitySheep.getDyeRgb(color);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			this.peaceCreeperRender.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}
	}
	
	@Override
	public boolean shouldCombineTextures() 
	{
		return true;
	}
}
