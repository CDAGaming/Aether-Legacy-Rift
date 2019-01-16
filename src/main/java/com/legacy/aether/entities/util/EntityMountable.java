package com.legacy.aether.entities.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.entities.passive.EntityAetherAnimal;

public abstract class EntityMountable extends EntityAetherAnimal
{

	private static final TrackedData<Boolean> RIDER_SNEAKING = DataTracker.registerData(EntityMountable.class, TrackedDataHandlerRegistry.BOOLEAN);

	protected float jumpPower;

	protected boolean mountJumping;

	protected boolean playStepSound = false;

	protected boolean canJumpMidAir = false;

	public EntityMountable(EntityType<?> entityType, World world)
	{
		super(entityType, world);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.dataTracker.startTracking(RIDER_SNEAKING, false);
	}

	@Override
	public void method_6091(float strafe, float vertical, float forward)
	{
		Entity entity = this.getPassengerList().isEmpty() ? null : this.getPassengerList().get(0);

		if (entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;

			this.prevYaw = this.yaw = player.yaw;
			this.prevPitch = this.pitch = player.pitch;

			this.headYaw = player.headYaw;

			strafe = player.field_6212;
			vertical = player.field_6227;
			forward = player.field_6250;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;
			}

			if (AetherAPI.get(player).isJumping())
			{
				this.onMountedJump(strafe, forward);
			}

			if (this.jumpPower > 0.0F && !this.isMountJumping() && (this.onGround || this.canJumpMidAir))
			{
				this.velocityY = this.getMountJumpStrength() * (double) this.jumpPower;

				if (this.hasPotionEffect(StatusEffects.JUMP_BOOST))
				{
					this.velocityY += (double) ((float) (this.getPotionEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setMountJumping(true);
				this.velocityDirty = true;

				if (forward > 0.0F) 
				{
					this.velocityX *= 0.35F;
					this.velocityZ *= 0.35F;
				}

				this.jumpPower = 0.0F;
			}

			this.stepHeight = 1.0F;

			this.field_6281 = this.method_6029() * 0.1F;

			if (this.method_5787())
			{
				this.method_6125((float) this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue());
				super.method_6091(strafe, vertical, forward);
			} 
			else
			{
				this.velocityX = 0.0D;
				this.velocityY = 0.0D;
				this.velocityZ = 0.0D;
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setMountJumping(false);
			}

			this.field_6211 = this.field_6225;
			double d0 = this.x - this.prevX;
			double d1 = this.z - this.prevZ;
			float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}

			this.field_6225 += (f4 - this.field_6225) * 0.4F;
			this.field_6249 += this.field_6225;
		}
		else
		{
			this.stepHeight = 0.5F;
			this.field_6281 = 0.02F;
			super.method_6091(strafe, vertical, forward);
		}
	}

	@Override
    public float method_6029()
    {
        return this.getMountedMoveSpeed();
    }

	@Override
	protected void playStepSound(BlockPos pos, BlockState state)
	{
		
	}

	public float getMountedMoveSpeed()
	{
		return 0.15F;
	}

	protected double getMountJumpStrength()
	{
		return 1.0D;
	}

	protected void setMountJumping(boolean mountJumping)
	{
		this.mountJumping = mountJumping;
	}

	protected boolean isMountJumping()
	{
		return this.mountJumping;
	}

	public void onMountedJump(float par1, float par2)
	{
		this.jumpPower = 0.4F;
	}

}