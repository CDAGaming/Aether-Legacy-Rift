package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;

import com.legacy.aether.entities.passive.EntityPhyg;
import com.mojang.blaze3d.platform.GlStateManager;

public class PhygWingModel extends EntityModel<EntityPhyg>
{

	private Cuboid leftWingInner = new Cuboid(this, 0, 0);

	private Cuboid leftWingOuter = new Cuboid(this, 20, 0);

	private Cuboid rightWingInner = new Cuboid(this, 0, 0);

	private Cuboid rightWingOuter = new Cuboid(this, 40, 0);

	public PhygWingModel()
	{
		this.leftWingInner.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
		this.leftWingOuter.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
		this.rightWingInner.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
		this.rightWingOuter.addBox(-1.0F, -8.0F, -4.0F, 2, 16, 8, 0.0F);
		this.rightWingOuter.yaw = (float) Math.PI;
	}

	@Override
	public void render(EntityPhyg phyg, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		float wingBend;
		float x;
		float y;
		float z;
		float x2;
		float y2;
		if (phyg.isChild())
		{
			wingBend = -((float) Math.acos((double) phyg.wingFold));
			GlStateManager.scalef(1.0F / 2.0F, 1.0F / 2.0F, 1.0F / 2.0F);
			GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
			x = -((float) Math.acos((double) phyg.wingFold));
			y = 32.0F * phyg.wingFold / 4.0F;
			z = -32.0F * (float) Math.sqrt((double) (1.0F - phyg.wingFold * phyg.wingFold)) / 4.0F;
			x2 = 0.0F;
			y2 = y * (float) Math.cos((double) phyg.wingAngle) - z * (float) Math.sin((double) phyg.wingAngle);
			float y21 = y * (float) Math.sin((double) phyg.wingAngle) + z * (float) Math.cos((double) phyg.wingAngle);
			this.leftWingInner.setRotationPoint(4.0F + y2, y21 + 12.0F, x2);
			this.rightWingInner.setRotationPoint(-4.0F - y2, y21 + 12.0F, x2);
			y *= 3.0F;
			y2 = y * (float) Math.cos((double) phyg.wingAngle) - z * (float) Math.sin((double) phyg.wingAngle);
			y21 = y * (float) Math.sin((double) phyg.wingAngle) + z * (float) Math.cos((double) phyg.wingAngle);
			this.leftWingOuter.setRotationPoint(4.0F + y2, y21 + 12.0F, x2);
			this.rightWingOuter.setRotationPoint(-4.0F - y2, y21 + 12.0F, x2);
			this.leftWingInner.roll = phyg.wingAngle + wingBend + ((float) Math.PI / 2F);
			this.leftWingOuter.roll = phyg.wingAngle - wingBend + ((float) Math.PI / 2F);
			this.rightWingInner.roll = -(phyg.wingAngle + wingBend - ((float) Math.PI / 2F));
			this.rightWingOuter.roll = -(phyg.wingAngle - wingBend + ((float) Math.PI / 2F));
			this.leftWingOuter.render(scale);
			this.leftWingInner.render(scale);
			this.rightWingOuter.render(scale);
			this.rightWingInner.render(scale);
		}
		else
		{
			wingBend = -((float) Math.acos((double) phyg.wingFold));
			x = 32.0F * phyg.wingFold / 4.0F;
			y = -32.0F * (float) Math.sqrt((double) (1.0F - phyg.wingFold * phyg.wingFold)) / 4.0F;
			z = 0.0F;
			x2 = x * (float) Math.cos((double) phyg.wingAngle) - y * (float) Math.sin((double) phyg.wingAngle);
			y2 = x * (float) Math.sin((double) phyg.wingAngle) + y * (float) Math.cos((double) phyg.wingAngle);
			this.leftWingInner.setRotationPoint(4.0F + x2, y2 + 12.0F, z);
			this.rightWingInner.setRotationPoint(-4.0F - x2, y2 + 12.0F, z);
			x *= 3.0F;
			x2 = x * (float) Math.cos((double) phyg.wingAngle) - y * (float) Math.sin((double) phyg.wingAngle);
			y2 = x * (float) Math.sin((double) phyg.wingAngle) + y * (float) Math.cos((double) phyg.wingAngle);
			this.leftWingOuter.setRotationPoint(4.0F + x2, y2 + 12.0F, z);
			this.rightWingOuter.setRotationPoint(-4.0F - x2, y2 + 12.0F, z);
			this.leftWingInner.roll = phyg.wingAngle + wingBend + ((float) Math.PI / 2F);
			this.leftWingOuter.roll = phyg.wingAngle - wingBend + ((float) Math.PI / 2F);
			this.rightWingInner.roll = -(phyg.wingAngle + wingBend - ((float) Math.PI / 2F));
			this.rightWingOuter.roll = -(phyg.wingAngle - wingBend + ((float) Math.PI / 2F));
			this.leftWingOuter.render(scale);
			this.leftWingInner.render(scale);
			this.rightWingOuter.render(scale);
			this.rightWingInner.render(scale);
		}
	}

	@Override
	public void setAngles(EntityPhyg phyg, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
	}

}