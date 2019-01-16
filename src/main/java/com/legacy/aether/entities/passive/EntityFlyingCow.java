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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.EntitySaddleMount;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;

public class EntityFlyingCow extends EntitySaddleMount
{

	public float wingFold;

	public float wingAngle;

	private float aimingForFold;

	public int maxJumps;

	public int jumpsRemaining;

	private int ticks;

	public EntityFlyingCow(World world)
	{
		super(EntityTypesAether.FLYING_COW, world);

		this.ticks = 0;
		this.maxJumps = 1;
		this.jumpsRemaining = 0;
		this.stepHeight = 1.0F;
		this.ignoreCameraFrustum = true;
		this.canJumpMidAir = true;

		this.setSize(0.9F, 1.3F);
	}

	@Override
    protected void method_5959()
    {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new class_1374(this, 2.0D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(ItemsAether.blueberry), false));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.add(5, new class_1394(this, 1.0D));
		this.goalSelector.add(6, new class_1361(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(7, new class_1376(this));
    }

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
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
	public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);

		compound.putInt("maxJumps", this.maxJumps);
		compound.putInt("remainingJumps", this.jumpsRemaining);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);

		this.maxJumps = compound.getInt("maxJumps");
		this.jumpsRemaining = compound.getInt("remainingJumps");
	}

	@Override
	public double getMountedHeightOffset()
	{
		return 1.15D;
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
		if (!this.onGround)
		{
			if (this.velocityY < 0.0D && !this.isSneaking())
			{
				this.velocityY *= 0.6D;
			}

			if (this.onGround && !this.world.isClient)
			{
				this.jumpsRemaining = this.maxJumps;
			}
		}
	}

	@Override
    public boolean interactMob(PlayerEntity player, Hand hand)
	{
		ItemStack currentStack = player.getStackInHand(hand);

		if (currentStack.getItem() == Items.BUCKET && !this.isChild())
		{
			if (currentStack.getAmount() == 1)
			{
				player.setStackInHand(hand, new ItemStack(Items.MILK_BUCKET));
			}
			else if (!player.inventory.insertStack(new ItemStack(Items.MILK_BUCKET)))
			{
				if (!this.world.isClient)
				{
					player.dropItem(new ItemStack(Items.MILK_BUCKET), false);

					if (!player.abilities.creativeMode)
					{
						currentStack.subtractAmount(1);
					}
				}
			}
			else if (!player.abilities.creativeMode)
			{
				currentStack.subtractAmount(1);
			}
		}

		return super.interactMob(player, hand);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundsAether.flyingcow_say;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundsAether.flyingcow_hurt;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.flyingcow_death;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state)
	{
		this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_COW_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	@Override
	public PassiveEntity createChild(PassiveEntity entity)
	{
		return new EntityFlyingCow(this.world);
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		/*int j = this.random.nextInt(3) + this.random.nextInt(1 + par2);
		int k;

		for (k = 0; k < j; ++k)
		{
			this.dropItem(Items.LEATHER, 1);
		}

		j = this.random.nextInt(3) + 1 + this.random.nextInt(1 + par2);

		for (k = 0; k < j; ++k)
		{
			if (this.isBurning())
			{
				this.dropItem(Items.COOKED_BEEF, 1);
			}
			else
			{
				this.dropItem(Items.BEEF, 1);
			}
		}

		if (this.getSaddled())
		{
			this.dropItem(Items.SADDLE, 1);
		}*/
	}

	@Override
	protected double getMountJumpStrength()
	{
		return 5.0D;
	}

}