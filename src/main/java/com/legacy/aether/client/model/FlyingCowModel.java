package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

import org.lwjgl.opengl.GL11;

import com.legacy.aether.entities.passive.EntityFlyingCow;

public class FlyingCowModel extends QuadrupedEntityModel<EntityFlyingCow>
{

	private Cuboid udders;

	private Cuboid horn1;

	private Cuboid horn2;

    public FlyingCowModel(float scale)
    {
        super(12, scale);
        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.horn1 = new Cuboid(this, 22, 0);
        this.horn1.addBox(-4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.horn1.setRotationPoint(0.0F, 3.0F, -7.0F);
        this.horn2 = new Cuboid(this, 22, 0);
        this.horn2.addBox(3.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.horn2.setRotationPoint(0.0F, 3.0F, -7.0F);
        this.udders = new Cuboid(this, 52, 0);
        this.udders.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 2, 0.0F);
        this.udders.setRotationPoint(0.0F, 14.0F, 6.0F);
        this.udders.pitch = ((float)Math.PI / 2F);
        this.body = new Cuboid(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, scale);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);

        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        this.leg1.rotationPointZ += 0.0F;
        this.leg2.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
    }

    @Override
    public void render(EntityFlyingCow entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * scale, 0.0F);
            this.horn1.render(scale);
            this.horn2.render(scale);
            this.udders.render(scale);
        }
        else
        {
            this.horn1.render(scale);
            this.horn2.render(scale);
            this.udders.render(scale);
        }
    }

    @Override
    public void setAngles(EntityFlyingCow entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        super.setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.horn1.yaw = this.head.yaw;
        this.horn1.pitch = this.head.pitch;
        this.horn2.yaw = this.head.yaw;
        this.horn2.pitch = this.head.pitch;
    }

}