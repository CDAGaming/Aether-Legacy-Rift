package com.legacy.aether.client.rendering.entity.layer;

import net.minecraft.class_3883;
import net.minecraft.class_3887;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.entities.passive.EntityMoa;

public class MoaSaddleLayer extends class_3887<EntityMoa, MoaModel> {

    private final MoaRenderer moaRenderer;

    private final MoaModel moaModel = new MoaModel(0.25F);

    public MoaSaddleLayer(MoaRenderer moaRendererIn)
    {
        super(moaRendererIn);
        this.moaRenderer = moaRendererIn;
    }

    @Override
    public void method_4199(EntityMoa moa, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (moa.canSaddle()) // TODO: getSaddled
        {
        	this.moaModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, moa);
            this.moaRenderer.bindTexture(moa.getMoaType().getTexture(true));
            this.moaModel.setAttributes(this.moaRenderer.method_4038());
            this.moaModel.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean method_4200()
    {
        return false;
    }
}