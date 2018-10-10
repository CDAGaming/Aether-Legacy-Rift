package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.CockatriceModel;
import com.legacy.aether.entities.hostile.EntityCockatrice;

public class CockatriceRenderer extends RenderLiving<EntityCockatrice>
{

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/cockatrice/cockatrice.png");

	public CockatriceRenderer(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new CockatriceModel(), 1.0F);
	}

    @Override
    protected float handleRotationFloat(EntityCockatrice cockatrice, float f)
    {
        float f1 = cockatrice.prevWingRotation + (cockatrice.wingRotation - cockatrice.prevWingRotation) * f;
        float f2 = cockatrice.prevDestPos + (cockatrice.destPos - cockatrice.prevDestPos) * f;

        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    @Override
    protected void preRenderCallback(EntityCockatrice cockatrice, float f)
    {
    	GlStateManager.scale(1.8F, 1.8F, 1.8F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityCockatrice cockatrice)
	{
		return TEXTURE;
	}

}