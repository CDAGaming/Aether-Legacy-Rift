package com.legacy.aether.client.model;

import com.legacy.aether.entities.hostile.EntityChestMimic;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ChestMimicModel extends ModelBase {

    public ModelRenderer box, boxLid;

    public ModelRenderer leftLeg, rightLeg;

    public ChestMimicModel() {
        this.box = new ModelRenderer(this, 0, 0);
        this.box.addBox(-8F, 0F, -8F, 16, 10, 16);
        this.box.setRotationPoint(0F, -24F, 0F);

        this.boxLid = new ModelRenderer(this, 16, 10);
        this.boxLid.addBox(0F, 0F, 0F, 16, 6, 16);
        this.boxLid.setRotationPoint(-8F, -24F, 8F);

        this.leftLeg = new ModelRenderer(this, 0, 0);
        this.leftLeg.addBox(-3F, 0F, -3F, 6, 15, 6);
        this.leftLeg.setRotationPoint(-4F, -15F, 0F);

        this.rightLeg = new ModelRenderer(this, 0, 0);
        this.rightLeg.addBox(-3F, 0F, -3F, 6, 15, 6);
        this.rightLeg.setRotationPoint(4F, -15F, 0F);

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        EntityChestMimic mimic = (EntityChestMimic) entityIn;

        this.boxLid.rotateAngleX = 3.14159265F - mimic.mouth;
        this.rightLeg.rotateAngleX = mimic.legs;
        this.leftLeg.rotateAngleX = -mimic.legs;
    }

    public void renderHead(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityChestMimic mimic) {
        this.box.render(scale);
    }

    public void renderLegs(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityChestMimic mimic) {
        boxLid.render(scale);
        leftLeg.render(scale);
        rightLeg.render(scale);
    }

}