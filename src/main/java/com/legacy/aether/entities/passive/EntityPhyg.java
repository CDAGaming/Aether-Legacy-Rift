package com.legacy.aether.entities.passive;

import net.minecraft.class_1361;
import net.minecraft.class_1374;
import net.minecraft.class_1376;
import net.minecraft.class_1394;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.EntitySaddleMount;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;

public class EntityPhyg extends EntitySaddleMount
{

	public float wingFold;

	public float wingAngle;

	private float aimingForFold;

	public int maxJumps;

	public int jumpsRemaining;

	public int ticks;

	public EntityPhyg(World world)
	{
		super(EntityTypesAether.PHYG, world);

		this.jumpsRemaining = 0;
		this.maxJumps = 1;
		this.stepHeight = 1.0F;

		this.ignoreCameraFrustum = true;
		this.canJumpMidAir = true;

		this.setSize(0.9F, 1.3F);
	}

	@Override
    protected void method_5959()
    {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new class_1374(this, 1.25D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(ItemsAether.blueberry), false));
		this.goalSelector.add(4, new class_1361(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(5, new class_1376(this));
		this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.add(6, new class_1394(this, 1.0D));
    }

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);

		if (this.getSaddled())
		{
			this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(20.0D);
			this.setHealth(20);
		}
	}

	@Override
	public void update()
	{
		super.update();

		if (this.onGround)
		{
			this.wingAngle *= 0.8F;
			this.aimingForFold = 0.1F;
			this.jumpsRemaining = this.maxJumps;
		}
		else
		{
			this.aimingForFold = 1.0F;
		}

		this.ticks++;

		this.wingAngle = this.wingFold * (float) Math.sin(this.ticks / 31.83098862F);
		this.wingFold += (this.aimingForFold - this.wingFold) / 5F;
		this.fallDistance = 0;
		this.fall();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.phyg_death;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundsAether.phyg_hurt;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundsAether.phyg_say;
	}

	@Override
	public double getMountedHeightOffset()
	{
		return 0.65D;
	}

	@Override
	public float getMountedMoveSpeed()
	{
		return 0.3F;
	}

	@Override
	public void doJump(boolean jump)
	{
		super.doJump(jump);
	}

	private void fall()
	{
		if (this.velocityY < 0.0D && !this.isSneaking())
		{
			this.velocityY *= 0.6D;
		}

		if (!this.onGround && !this.field_5953)
		{
			if (this.onGround && !this.world.isClient)
			{
				this.jumpsRemaining = this.maxJumps;
			}
		}
	}

	@Override
	protected double getMountJumpStrength()
	{
		return 5.0D;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState par4)
	{
		this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_PIG_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);

		this.maxJumps = compound.getInt("maxJumps");
		this.jumpsRemaining = compound.getInt("remainingJumps");
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);

		compound.putInt("maxJumps", this.maxJumps);
		compound.putInt("remainingJumps", this.jumpsRemaining);
	}

	@Override
	public PassiveEntity createChild(PassiveEntity entityageable)
	{
		return new EntityPhyg(this.world);
	}

}