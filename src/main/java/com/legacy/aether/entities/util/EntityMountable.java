package com.legacy.aether.entities.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.legacy.aether.entities.passive.EntityAetherAnimal;
import com.legacy.aether.player.IEntityPlayerAether;

public abstract class EntityMountable extends EntityAetherAnimal
{

	public static final DataParameter<Boolean> RIDER_SNEAKING = EntityDataManager.createKey(EntityMountable.class, DataSerializers.BOOLEAN);

	protected float jumpPower;

	protected boolean mountJumping;

	protected boolean playStepSound = false;

	protected boolean canJumpMidAir = false;

	public EntityMountable(EntityType<? extends Entity> type, World worldIn)
	{
		super(type, worldIn);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		// TODO: VERIFY IN 1.14
		this.dataTracker.startTracking(RIDER_SNEAKING, false);
	}

	/*@Override
    public boolean canRiderInteract()
    {
        return true;
    }*/

	@Override
    public boolean canBeRiddenInWater()
	{
        return false;
    }

	public boolean isRiderSneaking()
	{
		return this.dataTracker.get(RIDER_SNEAKING);
	}

	public void setRiderSneaking(boolean riderSneaking)
	{
		this.dataTracker.set(RIDER_SNEAKING, riderSneaking);
	}

	@Override
	public void update()
	{
		this.updateRider();

		super.update();
	}

	public void updateRider()
	{
		if (this.world.isRemote)
		{
			return;
		}

		if (this.isBeingRidden())
		{
			Entity passenger = this.getPassengerList().get(0);

			if (passenger.isSneaking())
			{
				if (this.onGround)
				{
					passenger.stopRiding();
					passenger.setSneaking(false);
					
					return;
				}

				this.setRiderSneaking(true);
			}
			else
			{
				this.setRiderSneaking(false);
			}

			passenger.setSneaking(false);
		}
	}

    private float updateRotation(float angle, float targetAngle, float maxIncrease)
    {
        float f = MathHelper.wrapDegrees(targetAngle - angle);

        if (f > maxIncrease)
        {
            f = maxIncrease;
        }

        if (f < -maxIncrease)
        {
            f = -maxIncrease;
        }

        return angle + f;
    }

	@Override
    public void move(float strafe, float vertical, float forward)
	{
		Entity entity = this.getPassengerList().isEmpty() ? null : this.getPassengers().get(0);

		if (entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;

			this.prevYaw = this.yaw = player.yaw;
			this.prevPitch = this.pitch = player.pitch;

			this.rotationYawHead = player.rotationYawHead;

			strafe = player.moveStrafing;
			vertical = player.moveVertical;
			forward = player.moveForward;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;
			}

	        double d01 = player.posX - this.posX;
	        double d2 = player.posZ - this.posZ;

	        float f = (float)(MathHelper.atan2(d2, d01) * (180D / Math.PI)) - 90.0F;

			if (player.moveStrafing != 0.0F && player.world.isRemote)
			{
		        this.yaw = this.updateRotation(this.yaw, f, 40.0F);
			}

			if (((IEntityPlayerAether)player).getPlayerAether().isJumping())
			{
				onMountedJump(strafe, forward);
			}

			if (this.jumpPower > 0.0F && !this.isMountJumping() && (this.onGround || this.canJumpMidAir))
			{
				this.velocityY = this.getMountJumpStrength() * (double) this.jumpPower;

				if (this.hasPotionEffect(StatusEffects.JUMP_BOOST))
				{
					this.velocityY += (double) ((float) (this.getPotionEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setMountJumping(true);
				this.isAirBorne = true;

				this.jumpPower = 0.0F;

				if (!this.world.isRemote)
				{
					this.move(MovementType.SELF, this.velocityX, this.velocityY, this.velocityZ);
				}
			}

			this.velocityX *= 0.35F;
			this.velocityZ *= 0.35F;

			this.stepHeight = 1.0F;

			if (!this.world.isRemote)
			{
				this.jumpPower = this.getAIMoveSpeed() * 0.6F;
				super.travel(strafe, vertical, forward);
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setMountJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d0 = this.posX - this.prevPosX;
			double d1 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

			if (f4 > 1.0F)
			{
				f4 = 1.0F;
			}

			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(strafe, vertical, forward);
		}
	}

	@Override
    public float getAIMoveSpeed()
    {
        return this.getMountedMoveSpeed();
    }

	public float getMountedMoveSpeed()
	{
		return 0.15F;
	}

	@Override
    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_)
    {

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