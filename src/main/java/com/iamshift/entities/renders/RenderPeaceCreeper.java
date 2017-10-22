package com.iamshift.entities.renders;

import com.iamshift.References;
import com.iamshift.entities.PeaceCreeper;
import com.iamshift.entities.models.ModelPeaceCreeper;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeaceCreeper extends RenderLiving<PeaceCreeper>
{
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(References.MODID, "textures/entities/peacecreeper.png");

    public RenderPeaceCreeper(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPeaceCreeper(), 0.5F);
        this.addLayer(new LayerPeaceCreeperHeadband(this));
    }

	@Override
	protected ResourceLocation getEntityTexture(PeaceCreeper entity) 
	{
		return CREEPER_TEXTURES;
	}
}