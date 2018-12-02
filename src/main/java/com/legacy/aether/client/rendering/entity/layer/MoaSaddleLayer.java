package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.entities.passive.EntityMoa;

public class MoaSaddleLayer implements LayerRenderer<EntityMoa>
{

    private final MoaRenderer moaRenderer;

    private final MoaModel moaModel = new MoaModel(0.25F);

    public MoaSaddleLayer(MoaRenderer moaRendererIn)
    {
        this.moaRenderer = moaRendererIn;
    }

    @Override
    public void render(EntityMoa moa, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (moa.getSaddled())
        {
        	this.moaModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, moa);
            this.moaRenderer.bindTexture(moa.getMoaType().getTexture(true));
            this.moaModel.setModelAttributes(this.moaRenderer.getMainModel());
            this.moaModel.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}