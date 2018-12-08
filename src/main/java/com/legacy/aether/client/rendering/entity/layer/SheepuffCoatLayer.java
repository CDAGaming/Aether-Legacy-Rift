package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.SheepuffModel;
import com.legacy.aether.client.model.SheepuffedModel;
import com.legacy.aether.client.rendering.entity.SheepuffRenderer;
import com.legacy.aether.entities.passive.EntitySheepuff;

public class SheepuffCoatLayer implements LayerRenderer<EntitySheepuff>
{

    private final ResourceLocation TEXTURE_FUR = Aether.locate("textures/entity/sheepuff/fur.png");

	private RenderManager renderManager;

	private SheepuffModel woolModel;

	private SheepuffedModel puffedModel;

	private SheepuffRenderer render;

	public SheepuffCoatLayer(RenderManager manager, SheepuffRenderer render)
	{
		this.renderManager = manager;
		this.render = render;
		this.woolModel = new SheepuffModel();
		this.puffedModel = new SheepuffedModel();
	}

	@Override
	public void render(EntitySheepuff sheepuff, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) 
	{
        if (!sheepuff.getSheared() && !sheepuff.isInvisible())
        {
        	GlStateManager.pushMatrix();
            this.renderManager.textureManager.bindTexture(TEXTURE_FUR);
            float[] dye = EntitySheep.getDyeRgb(sheepuff.getFleeceColor());
            GlStateManager.color3f(dye[0], dye[1], dye[2]);

            if (sheepuff.hasCustomName() && "jeb_".equals(sheepuff.getCustomName()))
            {
                int i = sheepuff.ticksExisted / 25 + sheepuff.getEntityId();
                int j1 = EnumDyeColor.values().length;
                int k = i % j1;
                int l = (i + 1) % j1;
                float f = ((float)(sheepuff.ticksExisted % 25) + partialTicks) / 25.0F;
                float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byId(k));
                float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byId(l));
                GlStateManager.color3f(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            }

            if (sheepuff.getPuffed())
            {
                this.puffedModel.setModelAttributes(this.render.getMainModel());
                this.puffedModel.setLivingAnimations(sheepuff, p_177141_2_, p_177141_3_, partialTicks);
                this.puffedModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
            }
            else
            {
                this.woolModel.setModelAttributes(this.render.getMainModel());
                this.woolModel.setLivingAnimations(sheepuff, p_177141_2_, p_177141_3_, partialTicks);
                this.woolModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
            }

            GlStateManager.popMatrix();
        }
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}