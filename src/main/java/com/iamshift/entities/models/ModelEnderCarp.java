package com.iamshift.entities.models;

import com.iamshift.entities.EnderCarp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEnderCarp extends ModelBase
{
	private final ModelRenderer model;
	private final ModelRenderer[] body;
	private final ModelRenderer[] fins;
	private final ModelRenderer[] horns;
	private final ModelRenderer[] tail;
	
	public ModelEnderCarp() 
	{
		this.textureHeight = 128;
		this.textureWidth = 64;
		this.model = new ModelRenderer(this);
		
		this.body = new ModelRenderer[6];
		this.fins = new ModelRenderer[2];
		this.horns = new ModelRenderer[4];
		this.tail = new ModelRenderer[3];
		
		this.body[0] = new ModelRenderer(this, 0, 0);
		this.body[0].addBox(-6F, 0F, 0F, 12, 6, 16);
		this.body[0].setRotationPoint(0F, 16F, -8F);
		this.model.addChild(this.body[0]);

		this.body[1] = new ModelRenderer(this, 0, 24);
		this.body[1].addBox(-2F, 0F, 0F, 2, 6, 12);
		this.body[1].setRotationPoint(-6F, 16F, -6F);
		this.model.addChild(this.body[1]);
		
		this.body[2] = new ModelRenderer(this, 0, 24);
		this.body[2].addBox(0F, 0F, 0F, 2, 6, 12, true);
		this.body[2].setRotationPoint(6F, 16F, -6F);
		this.model.addChild(this.body[2]);
		
		this.body[3] = new ModelRenderer(this, 0, 44);
		this.body[3].addBox(-6F, -2F, 0F, 12, 2, 12);
		this.body[3].setRotationPoint(0F, 16F, -6F);
		this.model.addChild(this.body[3]);
		
		this.body[4] = new ModelRenderer(this, 0, 44);
		this.body[4].addBox(-6F, 0F, 0F, 12, 2, 12);
		this.body[4].setRotationPoint(0F, 22F, -6F);
		this.model.addChild(this.body[4]);
		
		this.body[5] = new ModelRenderer(this, 30, 24);
		this.body[5].addBox(-3F, 0F, 0F, 6, 5, 2);
		this.body[5].setRotationPoint(0F, 16.5F, 8F);
		this.model.addChild(this.body[5]);

		this.fins[0] = new ModelRenderer(this, 0, 94);
		this.fins[0].addBox(0F, 0F, 0F, 6, 1, 8);
		this.fins[0].setRotationPoint(-8.5F, 19F, -4.5F);
		this.model.addChild(this.fins[0]);
		
		this.fins[1] = new ModelRenderer(this, 0, 94);
		this.fins[1].addBox(-6F, 0F, 0F, 6, 1, 8);
		this.fins[1].setRotationPoint(8.5F, 19.5F, -4.5F);
		this.model.addChild(this.fins[1]);
		
		this.horns[0] = new ModelRenderer(this, 0, 106);
		this.horns[0].addBox(0F, -5.5F, 0F, 2, 7, 2);
		this.horns[0].setRotationPoint(-5F, 16.5F, -6F);
		this.model.addChild(this.horns[0]);
		
		this.horns[1] = new ModelRenderer(this, 12, 106);
		this.horns[1].addBox(0F, -2.5F, 0F, 1, 3, 1);
		this.horns[1].setRotationPoint(-6.2F, 13.4F, -9.8F);
		this.model.addChild(this.horns[1]);
		
		this.horns[2] = new ModelRenderer(this, 0, 106);
		this.horns[2].addBox(-2F, -5.5F, 0F, 2, 7, 2);
		this.horns[2].setRotationPoint(5F, 16.5F, -6F);
		this.model.addChild(this.horns[2]);
		
		this.horns[3] = new ModelRenderer(this, 12, 106);
		this.horns[3].addBox(-1F, -2.5F, 0F, 1, 3, 1);
		this.horns[3].setRotationPoint(6.2F, 13.4F, -9.8F);
		this.model.addChild(this.horns[3]);
		
		this.tail[0] = new ModelRenderer(this, 0, 60);
		this.tail[0].addBox(-2F, 0F, 0F, 4, 4, 8);
		this.tail[0].setRotationPoint(0F, 17F, 7F);
		this.model.addChild(this.tail[0]);
		
		this.tail[1] = new ModelRenderer(this, 28, 60);
		this.tail[1].addBox(-1.5F, 0F, 0F, 3, 3, 7);
		this.tail[1].setRotationPoint(0F, 0.5F, 6F);
		this.tail[0].addChild(this.tail[1]);
		
		this.tail[2] = new ModelRenderer(this);
		this.tail[2].setTextureOffset(0, 74).addBox(-1F, 0, 0, 2, 2, 6);
		this.tail[2].setTextureOffset(20, 74).addBox(0F, -3.5F, 3F, 1, 9, 9);
		this.tail[2].setRotationPoint(0F, 0.5F, 5F);
		this.tail[1].addChild(this.tail[2]);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.model.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
	{
		EnderCarp carp = (EnderCarp) entityIn;
		float f = ageInTicks - (float) carp.ticksExisted;
		this.model.rotateAngleY = netHeadYaw * 0.017453292F;
		this.model.rotateAngleX = headPitch * 0.017453292F;
		
		this.setRotation(this.fins[0], 0D, -15D, 0D);
		this.setRotation(this.fins[1], 0D, 15D, 0D);
		
		this.setRotation(this.horns[0], 60D, 25D, 0D);
		this.setRotation(this.horns[1], 75D, 25D, 0D);
		this.setRotation(this.horns[2], 60D, -25D, 0D);
		this.setRotation(this.horns[3], 75D, -25D, 0D);

		float f2 = carp.getTailAnimation(f);
        this.tail[0].rotateAngleY = MathHelper.sin(f2) * (float)Math.PI * 0.05F;
        this.tail[1].rotateAngleY = MathHelper.sin(f2) * (float)Math.PI * 0.1F;
        this.tail[2].rotateAngleY = MathHelper.sin(f2) * (float)Math.PI * 0.15F;
	}

	private void setRotation(ModelRenderer model, double x, double y, double z)
	{
		model.rotateAngleX = (float) Math.toRadians(x);
		model.rotateAngleY = (float) Math.toRadians(y);
		model.rotateAngleZ = (float) Math.toRadians(z);
	}
}
