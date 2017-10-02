package com.iamshift.entities.renders;

import com.iamshift.References;
import com.iamshift.entities.EntityPeaceCreeper;
import com.iamshift.entities.EntityTrueCreeper;
import com.iamshift.entities.models.ModelPeaceCreeper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeaceCreeper extends RenderLiving<EntityPeaceCreeper>
{
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(References.MODID, "textures/entities/peacecreeper.png");

    public RenderPeaceCreeper(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPeaceCreeper(), 0.5F);
        this.addLayer(new LayerPeaceCreeperHeadband(this));
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityPeaceCreeper entity) 
	{
		return CREEPER_TEXTURES;
	}
}