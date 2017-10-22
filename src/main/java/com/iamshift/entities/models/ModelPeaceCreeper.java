package com.iamshift.entities.models;

import com.iamshift.entities.PeaceCreeper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPeaceCreeper extends ModelBase
{
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;

	public ModelPeaceCreeper()
	{
		this(0.0F);
	}

	public ModelPeaceCreeper(float p_i46366_1_)
	{
		int i = 6;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_);
		this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46366_1_);
		this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 16);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
		this.leg1.setRotationPoint(-2.0F, 18.0F, 4.0F);
		this.leg2 = new ModelRenderer(this, 0, 16);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
		this.leg2.setRotationPoint(2.0F, 18.0F, 4.0F);
		this.leg3 = new ModelRenderer(this, 0, 16);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
		this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
		this.leg4 = new ModelRenderer(this, 0, 16);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
		this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.translate(0, 24F*scale, 0);

		this.head.render(scale);
		this.body.render(scale);
		this.leg1.render(scale);
		this.leg2.render(scale);
		this.leg3.render(scale);
		this.leg4.render(scale);

		GlStateManager.popMatrix();
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) 
	{
		PeaceCreeper creeper = (PeaceCreeper) entitylivingbaseIn;
		
		if(creeper.isSitting())
		{
			this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
			this.body.setRotationPoint(0.0F, 12.0F, 0.0F);
			this.leg1.setRotationPoint(-2.0F, 23.0F, 4.0F);
			this.leg2.setRotationPoint(2.0F, 23.0F, 4.0F);
			this.leg3.setRotationPoint(-2.0F, 23.0F, -4.0F);
			this.leg4.setRotationPoint(2.0F, 23.0F, -4.0F);
			
			this.leg1.rotateAngleZ = ((float)Math.PI * -3F / 2F);
			this.leg2.rotateAngleZ = ((float)Math.PI * 3F / 2F);
			this.leg3.rotateAngleZ = ((float)Math.PI * -3F / 2F);
			this.leg4.rotateAngleZ = ((float)Math.PI * 3F / 2F);
		}
		else
		{
			this.head.setRotationPoint(0.0F, 6.0F, 0.0F);
			this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
			this.leg1.setRotationPoint(-2.0F, 18.0F, 4.0F);
			this.leg2.setRotationPoint(2.0F, 18.0F, 4.0F);
			this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
			this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
			
			this.leg1.rotateAngleZ = 0.0F;
			this.leg2.rotateAngleZ = -0.0F;
			this.leg3.rotateAngleZ = -0.0F;
			this.leg4.rotateAngleZ = 0.0F;
		}
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	 * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}