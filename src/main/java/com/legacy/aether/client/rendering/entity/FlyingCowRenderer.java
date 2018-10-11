package com.legacy.aether.client.rendering.entity;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.FlyingCowModel;
import com.legacy.aether.client.rendering.entity.layer.FlyingCowSaddleLayer;
import com.legacy.aether.client.rendering.entity.layer.FlyingCowWingLayer;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class FlyingCowRenderer extends RenderLiving<EntityFlyingCow> {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/entity/flying_cow/flying_cow.png");

    public FlyingCowRenderer(RenderManager renderManager) {
        super(renderManager, new FlyingCowModel(0.0F), 0.7F);

        this.addLayer(new FlyingCowSaddleLayer(this));
        this.addLayer(new FlyingCowWingLayer(renderManager));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFlyingCow entity) {
        return TEXTURE;
    }

}