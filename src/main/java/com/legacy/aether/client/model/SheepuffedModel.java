package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

import com.legacy.aether.entities.passive.EntitySheepuff;

public class SheepuffedModel extends QuadrupedEntityModel<EntitySheepuff>
{

    private float headRotationAngleX;

    public SheepuffedModel()
    {
        super(12, 0.0F);

        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);

        this.body = new Cuboid(this, 28, 8);
        this.body.addBox(-4.0F, -8.0F, -7.0F, 8, 16, 6, 3.75F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);

        this.leg1 = new Cuboid(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);

        this.leg2 = new Cuboid(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);

        this.leg3 = new Cuboid(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);

        this.leg4 = new Cuboid(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
    }

    @Override
    public void animateModel(EntitySheepuff sheepuff, float limbSwing, float prevLimbSwing, float partialTickTime)
    {
        super.animateModel(sheepuff, limbSwing, prevLimbSwing, partialTickTime);

        this.head.rotationPointY = 6.0F + sheepuff.getHeadRotationPointY(partialTickTime) * 9.0F;
        this.headRotationAngleX = sheepuff.getHeadRotationAngleX(partialTickTime);
    }

    @Override
    public void setAngles(EntitySheepuff sheepuff, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        super.setAngles(sheepuff, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.head.pitch = this.headRotationAngleX;
    }

}