package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.ModelPig;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.client.rendering.entity.PhygRenderer;
import com.legacy.aether.entities.passive.EntityPhyg;

public class PhygSaddleLayer implements LayerRenderer<EntityPhyg>
{

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");

    private final PhygRenderer phygRenderer;

    private final ModelPig pigModel = new ModelPig(0.5F);

    public PhygSaddleLayer(PhygRenderer phygRendererIn)
    {
        this.phygRenderer = phygRendererIn;
    }

    public void doRenderLayer(EntityPhyg entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSaddled())
        {
            this.phygRenderer.bindTexture(TEXTURE);
            this.pigModel.setModelAttributes(this.phygRenderer.getMainModel());
            this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}