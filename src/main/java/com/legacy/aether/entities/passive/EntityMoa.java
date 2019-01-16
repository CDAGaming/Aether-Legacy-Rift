package com.legacy.aether.entities.passive;

import net.minecraft.class_1361;
import net.minecraft.class_1376;
import net.minecraft.class_1394;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.moa.MoaType;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.EntitySaddleMount;
import com.legacy.aether.item.ItemMoaEgg;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;

public class EntityMoa extends EntitySaddleMount
{
	public static final TrackedData<Integer> MOA_TYPE_ID = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.INTEGER);

	public static final TrackedData<Integer> REMAINING_JUMPS = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.INTEGER);

	public static final TrackedData<Byte> AMMOUNT_FEED = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.BYTE);

	public static final TrackedData<Boolean> PLAYER_GROWN = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.BOOLEAN);

	public static final TrackedData<Boolean> HUNGRY = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.BOOLEAN);

	public static final TrackedData<Boolean> SITTING = DataTracker.registerData(EntityMoa.class, TrackedDataHandlerRegistry.BOOLEAN);

	public float wingRotation, destPos, prevDestPos, prevWingRotation;

	protected int maxJumps, ticksOffGround, ticksUntilFlap, secsUntilFlying, secsUntilWalking, secsUntilHungry, secsUntilEgg;

	public EntityMoa(World world)
	{
		super(EntityTypesAether.MOA, world);

		this.setSize(1.0F, 2.0F);

		this.stepHeight = 1.0F;
		this.secsUntilEgg = this.getRandomEggTime();
	}

	public EntityMoa(World world, MoaType type)
	{
		this(world);

		this.setMoaType(type);
	}

	@Override
	protected void method_5959()
    {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(2, new class_1394(this, 0.30F)); //WanderGoal
		this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(ItemsAether.nature_staff), false));
		this.goalSelector.add(4, new class_1361(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(5, new class_1376(this)); //LookGoal
		this.goalSelector.add(6, new AnimalMateGoal(this, 0.25F));
    }

	@Override
    public void move(MovementType type, double x, double y, double z)
    {
		if (!this.isSitting())
		{
			super.move(type, x, y, z);
		}
		else
		{
			super.move(type, 0, y, 0);
		}
    }

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		MoaType moaType = AetherAPI.instance().getMoa();

		this.dataTracker.startTracking(MOA_TYPE_ID, AetherAPI.instance().getMoaId(moaType));
		this.dataTracker.startTracking(REMAINING_JUMPS, moaType.getMoaProperties().getMaxJumps());

		this.dataTracker.startTracking(PLAYER_GROWN, false);
		this.dataTracker.startTracking(AMMOUNT_FEED, (byte) 0);
		this.dataTracker.startTracking(HUNGRY, false);
		this.dataTracker.startTracking(SITTING, false);
	}

	@Override
    protected void initAttributes()
    {
        super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(35.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
	}

	public int getRandomEggTime()
	{
		return 775 + this.random.nextInt(50);
	}

	public boolean isSitting()
	{
		return this.dataTracker.get(SITTING);
	}

	public void setSitting(boolean isSitting)
	{
		this.dataTracker.set(SITTING, isSitting);
	}

	public boolean isHungry()
	{
		return this.dataTracker.get(HUNGRY);
	}

	public void setHungry(boolean hungry)
	{
		this.dataTracker.set(HUNGRY, hungry);
	}

	public byte getAmountFed()
	{
		return this.dataTracker.get(AMMOUNT_FEED);
	}

	public void setAmountFed(int amountFed)
	{
		this.dataTracker.set(AMMOUNT_FEED, (byte) amountFed);
	}

	public void increaseAmountFed(int amountFed)
	{
		this.setAmountFed(this.getAmountFed() + amountFed);
	}

	public boolean isPlayerGrown()
	{
		return this.dataTracker.get(PLAYER_GROWN);
	}

	public void setPlayerGrown(boolean playerGrown)
	{
		this.dataTracker.set(PLAYER_GROWN, playerGrown);
	}

	public int getMaxJumps()
	{
		return this.maxJumps;
	}

	public void setMaxJumps(int maxJumps)
	{
		this.maxJumps = maxJumps;
	}

	public int getRemainingJumps()
	{
		return this.dataTracker.get(REMAINING_JUMPS);
	}

	public void setRemainingJumps(int jumps)
	{
		this.dataTracker.set(REMAINING_JUMPS, jumps);
	}

	public MoaType getMoaType()
	{
		return AetherAPI.instance().getMoa(this.dataTracker.get(MOA_TYPE_ID));
	}

	public void setMoaType(MoaType moa)
	{
		this.dataTracker.set(MOA_TYPE_ID, AetherAPI.instance().getMoaId(moa));
	}

	@Override
	public void update()
	{
		super.update();

		this.setMaxJumps(this.getMoaType().getMoaProperties().getMaxJumps());

		if (this.field_6282)
		{
			this.velocityY += 0.05F;
		}

		this.updateWingRotation();
		this.fall();
		
		if (this.secsUntilHungry > 0)
		{
			if (this.age % 20 == 0)
			{
				this.secsUntilHungry--;
			}
		}
		else if (!this.isHungry())
		{
			this.setHungry(true);
		}
		
		if(this.world.isClient && this.isHungry() && this.isChild())
		{
			if(this.random.nextInt(10) == 0)
			{
				this.world.addParticle(ParticleTypes.ANGRY_VILLAGER, this.x + (this.random.nextDouble() - 0.5D) * (double)this.width, this.y + 1, this.z + (this.random.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
			}
		}

		if (!this.world.isClient && !this.isChild() && this.getPassengerList().isEmpty())
		{
			if (this.secsUntilEgg > 0)
			{
				if (this.age % 20 == 0)
				{
					this.secsUntilEgg--;
				}
			}
			else
			{
				this.playSoundAtEntity(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.dropStack(ItemMoaEgg.getStack(this.getMoaType()), 0);

				this.secsUntilEgg = this.getRandomEggTime();
			}
		}

		this.fallDistance = 0.0F;
	}

	public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

	public void resetHunger()
	{
		if (!this.world.isClient)
		{
			this.setHungry(false);
		}

		this.secsUntilHungry = 40 + this.random.nextInt(40);
	}

	public void updateWingRotation()
	{
		if (!this.onGround)
		{
			if (this.ticksUntilFlap == 0)
			{
				this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.15F, MathHelper.clamp(this.random.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp(this.random.nextFloat(), 0f, 0.3f));

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

		if (onGround)
		{
			this.destPos = 0.0F;
		}

		this.wingRotation += 1.233F;
	}

	@Override
	public void onMountedJump(float par1, float par2)
	{
		if (this.getRemainingJumps() > 0 && this.velocityY < 0.0D)
		{
			if (!this.onGround)
			{
				this.velocityY = 0.7D;
				this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.15F, MathHelper.clamp(this.random.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp(this.random.nextFloat(), 0f, 0.3f));

				if (!this.world.isClient)
				{
					this.setRemainingJumps(this.getRemainingJumps() - 1);
				}

				if (!this.world.isClient)
				{
					this.method_5990();
				}
			}
			else
			{
				this.velocityY = 0.89D;
			}
		}
	}

	@Override
	public float getMountedMoveSpeed()
	{
		return this.getMoaType().getMoaProperties().getMoaSpeed();
	}

	public void setToAdult()
	{
		this.setBreedingAge(0);
	}

	@Override
    public boolean interactMob(PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		if (stack != null && this.isPlayerGrown())
		{
			Item currentItem = stack.getItem();

			if (this.isChild() && this.isHungry())
			{
				if (this.getAmountFed() < 3 && currentItem == ItemsAether.aechor_petal)
				{
					if (!player.abilities.creativeMode)
					{
						stack.subtractAmount(1);
					}

					this.increaseAmountFed(1);

					if (this.getAmountFed() >= 3)
					{
						this.setToAdult();
					}
					else
					{
						this.resetHunger();
					}
				}
			}
			
			if (currentItem == ItemsAether.nature_staff)
			{
				stack.applyDamage(2, player);

				this.setSitting(!this.isSitting());

				if (!this.world.isClient)
				{
					this.method_5990();
				}

				return true;
			}
		}

		return super.interactMob(player, hand);
	}

	public boolean canSaddle()
	{
		return !this.isChild() && this.isPlayerGrown();
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);

		compound.putBoolean("playerGrown", this.isPlayerGrown());
		compound.putInt("remainingJumps", this.getRemainingJumps());
		compound.putByte("amountFed", this.getAmountFed());
		compound.putBoolean("isHungry", this.isHungry());
		compound.putBoolean("isSitting", this.isSitting());
		compound.putInt("typeId", AetherAPI.instance().getMoaId(this.getMoaType()));
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);

		this.setPlayerGrown(compound.getBoolean("playerGrown"));
		this.setRemainingJumps(compound.getInt("remainingJumps"));
		this.setMoaType(AetherAPI.instance().getMoa(compound.getInt("typeId")));
		this.setAmountFed(compound.getByte("amountFed"));
		this.setHungry(compound.getBoolean("isHungry"));
		this.setSitting(compound.getBoolean("isSitting"));
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

	@Override
	protected void playStepSound(BlockPos posIn, BlockState stateIn)
	{
		this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_PIG_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	public void fall()
	{
		boolean blockBeneath = !this.world.isAir(new BlockPos(this).down());

		if (this.velocityY < 0.0D && !this.isSneaking())
		{
			this.velocityY *= 0.6D;
		}

		if (blockBeneath)
		{
			this.setRemainingJumps(this.maxJumps);
		}
	}

	@Override
	public void doJump(boolean jump)
	{
		super.doJump(jump);
	}

	@Override
	public double getMountedHeightOffset()
	{
		return this.isSitting() ? 0.25D: 1.25D;
	}

	@Override
	public PassiveEntity createChild(PassiveEntity matingAnimal)
	{
		return new EntityMoa(this.world, this.getMoaType());
	}

	/*
	@Override
	public Identifier getLootTableId()
	{
		return AetherLootTableList.ENTITIES_MOA;
	}*/

}