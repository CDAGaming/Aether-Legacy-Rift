package com.legacy.aether.entities.hostile;

import net.minecraft.class_1370;
import net.minecraft.class_1394;
import net.minecraft.class_1399;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;

public class EntityChestMimic extends HostileEntity
{

	public float mouth, legs;

	private float legsDirection = 1;

	public EntityChestMimic(World world)
	{
		super(EntityTypesAether.CHEST_MIMIC, world);

		this.setSize(1.0F, 2.0F);
	}

	@Override
	protected void method_5959()
	{
		super.method_5959();

		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.add(5, new class_1370(this, 1.0D));
		this.goalSelector.add(7, new class_1394(this, 1.0D));
		this.targetSelector.add(1, new class_1399(this, new Class[0]));
		this.targetSelector.add(2, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.28000000417232513D);
		this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(40.0D);
	}

	@Override
	public void update()
	{
		super.update();

		this.mouth = (float) ((Math.cos((float) this.age / 10F * 3.14159265F)) + 1F) * 0.6F;
		this.legs *= 0.9F;

		if (this.prevX - this.x != 0 || this.prevZ - this.z != 0)
		{
			this.legs += legsDirection * 0.2F;

			if (this.legs > 1.0F)
			{
				this.legsDirection = -1;
			}

			if (this.legs < -1.0F)
			{
				this.legsDirection = 1;
			}
		}
		else
		{
			this.legs = 0.0F;
		}
	}

	@Override
	public boolean damage(DamageSource damageSource, float damage)
	{
		if (damageSource.getAttacker() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) damageSource.getAttacker();

			if (player.getMainHandStack().getItem() instanceof AxeItem)
			{
				damage *= 1.25F;
			}
		}

		return super.damage(damageSource, damage);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.BLOCK_WOOD_HIT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.BLOCK_CHEST_CLOSE;
	}

}