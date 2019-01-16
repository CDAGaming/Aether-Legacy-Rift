package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;

import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.mojang.blaze3d.platform.GlStateManager;

public class FlyingCowWingModel extends EntityModel<EntityFlyingCow>
{

    private Cuboid leftWingInner = new Cuboid(this, 0, 0);
    private Cuboid leftWingOuter = new Cuboid(this, 20, 0);
    private Cuboid rightWingInner = new Cuboid(this, 0, 0);
    private Cuboid rightWingOuter = new Cuboid(this, 40, 0);

    public FlyingCowWingModel()
    {
        this.leftWingInner.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
        this.leftWingOuter.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
        this.rightWingInner.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
        this.rightWingOuter.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);

        this.rightWingOuter.yaw = (float)Math.PI;
    }

    @Override
    public void render(EntityFlyingCow entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GlStateManager.pushMatrix();

        GlStateManager.translatef(0.0F, -10.0F * scale, 0.0F);

        float wingBend = -((float)Math.acos((double)entityIn.wingFold));

        float x = 32.0F * entityIn.wingFold / 4.0F;
        float y = -32.0F * (float)Math.sqrt((double)(1.0F - entityIn.wingFold * entityIn.wingFold)) / 4.0F;

        float x2 = x * (float)Math.cos((double)entityIn.wingAngle) - y * (float)Math.sin((double)entityIn.wingAngle);
        float y2 = x * (float)Math.sin((double)entityIn.wingAngle) + y * (float)Math.cos((double)entityIn.wingAngle);

        this.leftWingInner.setRotationPoint(4.0F + x2, y2 + 12.0F, 0.0F);
        this.rightWingInner.setRotationPoint(-4.0F - x2, y2 + 12.0F, 0.0F);

        x *= 3.0F;
        x2 = x * (float)Math.cos((double)entityIn.wingAngle) - y * (float)Math.sin((double)entityIn.wingAngle);
        y2 = x * (float)Math.sin((double)entityIn.wingAngle) + y * (float)Math.cos((double)entityIn.wingAngle);

        this.leftWingOuter.setRotationPoint(4.0F + x2, y2 + 12.0F, 0.0F);
        this.rightWingOuter.setRotationPoint(-4.0F - x2, y2 + 12.0F, 0.0F);

        this.leftWingInner.roll = entityIn.wingAngle + wingBend + ((float)Math.PI / 2F);
        this.leftWingOuter.roll = entityIn.wingAngle - wingBend + ((float)Math.PI / 2F);
        this.rightWingInner.roll = -(entityIn.wingAngle + wingBend - ((float)Math.PI / 2F));
        this.rightWingOuter.roll = -(entityIn.wingAngle - wingBend + ((float)Math.PI / 2F));

        this.leftWingOuter.render(scale);
        this.leftWingInner.render(scale);
        this.rightWingOuter.render(scale);
        this.rightWingInner.render(scale);

        GlStateManager.popMatrix();
    }

    @Override
    public void setAngles(EntityFlyingCow entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {}

}