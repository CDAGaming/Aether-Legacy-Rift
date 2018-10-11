package com.legacy.aether.client.rendering.entity.layer;

import com.legacy.aether.client.model.MoaModel;
import com.legacy.aether.client.rendering.entity.MoaRenderer;
import com.legacy.aether.entities.passive.EntityMoa;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class MoaSaddleLayer implements LayerRenderer<EntityMoa> {

    private final MoaRenderer moaRenderer;

    private final MoaModel moaModel = new MoaModel(0.25F);

    public MoaSaddleLayer(MoaRenderer moaRendererIn) {
        this.moaRenderer = moaRendererIn;
    }

    public void doRenderLayer(EntityMoa moa, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (moa.getSaddled()) {
            this.moaModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, moa);
            this.moaRenderer.bindTexture(moa.getMoaType().getTexture(true));
            this.moaModel.setModelAttributes(this.moaRenderer.getMainModel());
            this.moaModel.render(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}