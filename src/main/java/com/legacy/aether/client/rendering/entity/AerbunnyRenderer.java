package com.legacy.aether.client.rendering.entity;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.AerbunnyModel;
import com.legacy.aether.entities.passive.EntityAerbunny;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class AerbunnyRenderer extends RenderLiving<EntityAerbunny> {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/aerbunny/aerbunny.png");

    public AerbunnyModel model;

    public AerbunnyRenderer(RenderManager renderManager) {
        super(renderManager, new AerbunnyModel(), 0.3F);

        this.model = (AerbunnyModel) this.getMainModel();
    }

    @Override
    protected void preRenderCallback(EntityAerbunny entitybunny, float f) {
        if (!entitybunny.isRiding()) {
            GlStateManager.translate(0, 0.2D, 0);
        }

        if (!entitybunny.onGround) {
            if (entitybunny.motionY > 0.5D) {
                GlStateManager.rotate(15.0F, -1.0F, 0.0F, 0.0F);
            } else if (entitybunny.motionY < -0.5D) {
                GlStateManager.rotate(-15.0F, -1.0F, 0.0F, 0.0F);
            } else {
                GlStateManager.rotate((float) (entitybunny.motionY * 30.0D), -1.0F, 0.0F, 0.0F);
            }
        }

        this.model.puffiness = (float) (entitybunny.getRidingEntity() != null ? entitybunny.getPuffinessClient() : entitybunny.getPuffiness()) / 10.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAerbunny entity) {
        return TEXTURE;
    }

}
