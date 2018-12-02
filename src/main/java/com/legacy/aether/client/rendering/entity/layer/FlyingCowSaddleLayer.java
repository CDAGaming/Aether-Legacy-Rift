package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.FlyingCowModel;
import com.legacy.aether.client.rendering.entity.FlyingCowRenderer;
import com.legacy.aether.entities.passive.EntityFlyingCow;

public class FlyingCowSaddleLayer implements LayerRenderer<EntityFlyingCow>
{

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/flying_cow/saddle.png");

    private final FlyingCowRenderer cowRenderer;

    private final FlyingCowModel cowModel = new FlyingCowModel(0.5F);

    public FlyingCowSaddleLayer(FlyingCowRenderer cowRendererIn)
    {
        this.cowRenderer = cowRendererIn;
    }

    @Override
    public void render(EntityFlyingCow entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSaddled())
        {
            this.cowRenderer.bindTexture(TEXTURE);
            this.cowModel.setModelAttributes(this.cowRenderer.getMainModel());
            this.cowModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }

}