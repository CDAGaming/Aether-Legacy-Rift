package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.client.render.entity.feature.FeatureRenderer;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.entities.passive.EntityMoa;

public class MoaSaddleLayer extends FeatureRenderer<EntityMoa, MoaModel> {

    private final MoaRenderer moaRenderer;

    private final MoaModel moaModel = new MoaModel(0.25F);

    public MoaSaddleLayer(MoaRenderer moaRendererIn)
    {
        super(moaRendererIn);
        this.moaRenderer = moaRendererIn;
    }

    @Override
    public void render(EntityMoa moa, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (moa.getSaddled())
        {
        	this.moaModel.setAngles(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            this.moaRenderer.bindTexture(moa.getMoaType().getTexture(true));
            this.moaModel.setAttributes(this.moaRenderer.getModel());
            this.moaModel.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean method_4200()
    {
        return false;
    }
}