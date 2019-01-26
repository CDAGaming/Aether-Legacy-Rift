package com.legacy.aether.entities.hostile;

import net.minecraft.class_1361;
import net.minecraft.class_1376;
import net.minecraft.class_1394;
import net.minecraft.class_1399;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttacker;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.sounds.SoundsAether;

public class EntityCockatrice extends MobEntityWithAi implements RangedAttacker
{

	public float wingRotation, destPos, prevDestPos, prevWingRotation;

	public int shootTime, ticksUntilFlap;

	public EntityCockatrice(World world)
	{
		super(EntityTypesAether.COCKATRICE, world);

		this.stepHeight = 1.0F;
		this.setSize(1.0F, 2.0F);
	}

	@Override
	protected void method_5959()
	{
		super.method_5959();

		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(4, new ProjectileAttackGoal(this, 0.0D, 30, 12.0F));
		//this.goalSelector.add(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.goalSelector.add(5, new class_1394(this, 1.0D));
		this.goalSelector.add(6, new class_1361(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(6, new class_1376(this));
		this.targetSelector.add(1, new class_1399(this, new Class[0]));
		this.targetSelector.add(2, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	}

	@Override
	public void update()
	{
		super.update();

		if (!this.onGround && this.velocityY < 0.0D)
		{
			this.velocityY *= 0.59999999999999998D;
		}

		if (!this.onGround)
		{
			if (this.ticksUntilFlap == 0)
			{
				this.world.playSound(null, new BlockPos(this), SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.15F, MathHelper.clamp(this.random.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp(this.random.nextFloat(), 0f, 0.3f));

				this.ticksUntilFlap = 8;
			}
			else
			{
				this.ticksUntilFlap--;
			}
		}

		this.prevWingRotation = this.wingRotation;
		this.prevDestPos = this.destPos;

		this.destPos += 0.2D;
		this.destPos = Math.min(1.0F, Math.max(0.01F, this.destPos));

		if (this.onGround) {
			this.destPos = 0.0F;
		}

		this.wingRotation += 1.233F;
	}

	@Override
	public boolean hasArmsRaised()
	{
		return false;
	}

	@Override
	public void setArmsRaised(boolean isSwinging)
	{

	}

	@Override
	public void attack(LivingEntity targetIn, float arg1)
	{
		double x = targetIn.x - this.x;
		double z = targetIn.z - this.z;
		double y = 0.1D + (Math.sqrt((x * x) + (z * z) + 0.1D) * 0.5D) + ((this.y - targetIn.y) * 0.25D);

		double distance = 1.5D / Math.sqrt((x * x) + (z * z) + 0.1D);

		EntityPoisonNeedle needle = new EntityPoisonNeedle(this, this.world);

		needle.setVelocity(x * distance, y, z * distance, 0.285F + ((float) y * 0.05F), 1.0F);

		this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.2F / (this.getRand().nextFloat() * 0.2F + 0.9F));

		this.world.spawnEntity(needle);
	}

	@Override
	public void handleFallDamage(float distance, float damageMultiplier)
	{

	}

	@Override
	public boolean isPotionEffective(StatusEffectInstance effect)
	{
		return effect.getEffectType() == StatusEffects.POISON ? false : super.isPotionEffective(effect);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundsAether.moa_say;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundsAether.moa_say;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.moa_say;
	}

}