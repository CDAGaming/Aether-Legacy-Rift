package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.AerwhaleModel;
import com.legacy.aether.entities.passive.EntityAerwhale;

public class AerwhaleRenderer extends Render<EntityAerwhale>
{

	private static final ResourceLocation AERWHALE_TEXTURE = Aether.locate("textures/entity/aerwhale/aerwhale.png");

    private ModelBase model = new AerwhaleModel();

	public AerwhaleRenderer(RenderManager renderManager) 
	{
		super(renderManager);
	}

	@Override
    public void doRender(EntityAerwhale aerwhale, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.renderManager.textureManager.bindTexture(AERWHALE_TEXTURE);
        GlStateManager.translated(x, y + 2.0D, z);
        GlStateManager.rotatef(90.0F - (float) aerwhale.aerwhaleRotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(180.0F - (float) aerwhale.aerwhaleRotationPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.scaled(2.0F, 2.0F, 2.0F);
        this.model.render(aerwhale, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityAerwhale aerwhale) 
	{
        return AERWHALE_TEXTURE;
	}

}