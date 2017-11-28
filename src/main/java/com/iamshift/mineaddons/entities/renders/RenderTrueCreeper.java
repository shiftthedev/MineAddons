package com.iamshift.mineaddons.entities.renders;

import com.iamshift.mineaddons.References;
import com.iamshift.mineaddons.entities.TrueCreeper;
import com.iamshift.mineaddons.entities.models.ModelTrueCreeper;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTrueCreeper extends RenderLiving<TrueCreeper>
{
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(References.MODID, "textures/entities/truecreeper.png");

    public RenderTrueCreeper(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelTrueCreeper(), 0.5F);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
    protected void preRenderCallback(TrueCreeper entitylivingbaseIn, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }

    /**
     * Gets an RGBA int color multiplier to apply.
     */
    @Override
    protected int getColorMultiplier(TrueCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

        if ((int)(f * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

	@Override
	protected ResourceLocation getEntityTexture(TrueCreeper entity) 
	{
		return CREEPER_TEXTURES;
	}
}