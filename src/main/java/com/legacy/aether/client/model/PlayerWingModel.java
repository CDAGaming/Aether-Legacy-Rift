package com.legacy.aether.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public class PlayerWingModel extends BipedEntityModel<AbstractClientPlayerEntity>
{

	public Cuboid wingLeft;

	public Cuboid wingRight;

	public float sinage;

	public boolean gonRound;

	public PlayerWingModel()
	{
		this(0.0F);
	}

	public PlayerWingModel(float f)
	{
		this(f, 0.0F);
	}

	public PlayerWingModel(float f, float f1)
	{
        this.armPoseLeft = BipedEntityModel.ArmPose.EMPTY;
        this.armPoseRight = BipedEntityModel.ArmPose.EMPTY;
		this.isSneaking = false;

		this.wingLeft = new Cuboid(this, 24, 31);
		this.wingLeft.addBox(0F, -7F, 1F, 20, 8, 0, f);
		this.wingLeft.setRotationPoint(0.5F, 5F + f1, 2.625F);

		this.wingRight = new Cuboid(this, 24, 31);
		this.wingRight.mirror = true;
		this.wingRight.addBox(-19F, -7F, 1F, 20, 8, 0, f);
		this.wingRight.setRotationPoint(-0.5F, 5F + f1, 2.625F);
	}

	@Override
	public void render(AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		super.setAngles(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.wingLeft.method_2852(scale);
		this.wingRight.method_2852(scale);
	}

	@Override
	public void setAngles(AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.gonRound = player.onGround;

		this.wingLeft.yaw = -0.4F;
		this.wingRight.yaw = 0.4F;
		this.wingLeft.roll = -0.125F;
		this.wingRight.roll = 0.125F;

		if (player.isSneaking())
		{
			this.wingLeft.pitch = 0.45F;
			this.wingRight.pitch = 0.45F;
			this.wingLeft.y = -0.17F;
			this.wingRight.y = -0.17F;
			this.wingLeft.z = 0.112F;
			this.wingRight.z = 0.112F;
		}
		else
		{
			this.wingLeft.pitch = this.wingLeft.z = this.wingLeft.y =
			this.wingRight.pitch = this.wingRight.z = this.wingRight.y = 0.0F;
		}

		this.wingLeft.yaw += Math.sin(this.sinage) / 6F;
		this.wingRight.yaw -= Math.sin(this.sinage) / 6F;
		this.wingLeft.roll += Math.cos(this.sinage) / (this.gonRound ? 8F : 3F);
		this.wingRight.roll -= Math.cos(this.sinage) / (this.gonRound ? 8F : 3F);
	}

}