package com.iamshift.entities.models;

import com.iamshift.entities.AncientCarp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAncientCarp extends ModelBase
{
	private final ModelRenderer model;
	private final ModelRenderer[] body;
	private final ModelRenderer[] tail;
	private final ModelRenderer[] fins;
	private final ModelRenderer[] horns;

	public ModelAncientCarp() 
	{
		this.textureHeight = 512;
		this.textureWidth = 512;
		this.model = new ModelRenderer(this);
		
		this.body = new ModelRenderer[6];
		this.tail = new ModelRenderer[3];
		this.fins = new ModelRenderer[2];
		this.horns = new ModelRenderer[4];

		//down
		this.body[0] = new ModelRenderer(this, 0, 0);
		this.body[0].addBox(-24F, -2F, -24F, 48, 4, 48);
		this.body[0].setRotationPoint(0F, 22F, 0F);
		this.model.addChild(this.body[0]);

		//center
		this.body[1] = new ModelRenderer(this, 206, 0);
		this.body[1].addBox(-24F, -12F, -28F, 48, 24, 56);
		this.body[1].setRotationPoint(0F, 8F, 0F);
		this.model.addChild(this.body[1]);
				
		//up
		this.body[2] = new ModelRenderer(this, 0, 66);
		this.body[2].addBox(-24F, -2F, -24F, 48, 4, 48);
		this.body[2].setRotationPoint(0F, -6F, 0F);
		this.model.addChild(this.body[2]);
		
		//left
		this.body[3] = new ModelRenderer(this, 206, 96);
		this.body[3].addBox(-2F, -12F, -24F, 4, 24, 48);
		this.body[3].setRotationPoint(-26F, 8F, 0F);
		this.model.addChild(this.body[3]);
		
		//right
		this.body[4] = new ModelRenderer(this, 206, 96);
		this.body[4].addBox(-2F, -12F, -24F, 4, 24, 48, true);
		this.body[4].setRotationPoint(26F, 8F, 0F);
		this.model.addChild(this.body[4]);
		
		//back
		this.body[5] = new ModelRenderer(this, 426, 0);
		this.body[5].addBox(-12F, -10F, -2F, 24, 20, 4);
		this.body[5].setRotationPoint(0F, 8F, 30F);
		this.model.addChild(this.body[5]);
	
		//tail
		this.tail[0] = new ModelRenderer(this, 0, 132);
		this.tail[0].addBox(-8F, -8F, 0F, 16, 16, 32);
		this.tail[0].setRotationPoint(0F, 8F, 28F);
		this.model.addChild(this.tail[0]);
		
		this.tail[1] = new ModelRenderer(this, 106, 132);
		this.tail[1].addBox(-6F, -6F, 0F, 12, 12, 28);
		this.tail[1].setRotationPoint(0F, 0F, 24F);
		this.tail[0].addChild(this.tail[1]);
		
		this.tail[2] = new ModelRenderer(this);
		this.tail[2].setTextureOffset(0, 190).addBox(-4F, -4F, 0F, 8, 8, 24);
		this.tail[2].setTextureOffset(76, 190).addBox(-0.5F, -18F, 12F, 1, 36, 36);
		this.tail[2].setRotationPoint(0F, 0F, 20F);
		this.tail[1].addChild(this.tail[2]);
		
		//fins
		this.fins[0] = new ModelRenderer(this, 322, 96);
		this.fins[0].addBox(0F, -2F, 0F, 16, 4, 32);
		this.fins[0].setRotationPoint(-30F, 8F, -16F);
		this.model.addChild(this.fins[0]);
		
		this.fins[1] = new ModelRenderer(this, 322, 96);
		this.fins[1].addBox(-16F, -2F, 0F, 16, 4, 32);
		this.fins[1].setRotationPoint(30F, 8F, -16F);
		this.model.addChild(this.fins[1]);
		
		//horns
		this.horns[0] = new ModelRenderer(this, 426, 32);
		this.horns[0].addBox(-2.5F, -18F, -2.5F, 5, 28, 5);
		this.horns[0].setRotationPoint(-20F, -5F, -24F);
		this.model.addChild(this.horns[0]);
		
		this.horns[1] = new ModelRenderer(this, 452, 32);
		this.horns[1].addBox(-1F, -6.5F, -1F, 2, 8, 2);
		this.horns[1].setRotationPoint(-26.5F, -14.5F, -38.5F);
		this.model.addChild(this.horns[1]);
		
		this.horns[2] = new ModelRenderer(this, 426, 32);
		this.horns[2].addBox(-2.5F, -18F, -2.5F, 5, 28, 5, true);
		this.horns[2].setRotationPoint(20F, -5F, -24F);
		this.model.addChild(this.horns[2]);
		
		this.horns[3] = new ModelRenderer(this, 452, 32);
		this.horns[3].addBox(-1F, -6.5F, -1F, 2, 8, 2, true);
		this.horns[3].setRotationPoint(26.5F, -14.5F, -38.5F);
		this.model.addChild(this.horns[3]);
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
		AncientCarp carp = (AncientCarp) entityIn;
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
