package com.legacy.aether.entities.passive;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
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
import com.legacy.aether.world.storage.loot.AetherLootTableList;

public class EntityMoa extends EntitySaddleMount
{

	public static final DataParameter<Integer> MOA_TYPE_ID = EntityDataManager.<Integer>createKey(EntityMoa.class, DataSerializers.VARINT);

	public static final DataParameter<Integer> REMAINING_JUMPS = EntityDataManager.<Integer>createKey(EntityMoa.class, DataSerializers.VARINT);

	public static final DataParameter<Byte> AMMOUNT_FEED = EntityDataManager.<Byte>createKey(EntityMoa.class, DataSerializers.BYTE);

	public static final DataParameter<Boolean> PLAYER_GROWN = EntityDataManager.<Boolean>createKey(EntityMoa.class, DataSerializers.BOOLEAN);

	public static final DataParameter<Boolean> HUNGRY = EntityDataManager.<Boolean>createKey(EntityMoa.class, DataSerializers.BOOLEAN);

	public static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityMoa.class, DataSerializers.BOOLEAN);

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
    protected void initEntityAI()
    {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 0.30F));
		this.tasks.addTask(2, new EntityAITempt(this, 1.25D, Ingredient.fromItems(ItemsAether.nature_staff), false));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIMate(this, 0.25F));
    }

	@Override
    public void move(MoverType type, double x, double y, double z)
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
	protected void registerData()
	{
		super.registerData();

		MoaType moaType = AetherAPI.instance().getMoa();

		this.dataManager.register(MOA_TYPE_ID, AetherAPI.instance().getMoaId(moaType));
		this.dataManager.register(REMAINING_JUMPS, moaType.getMoaProperties().getMaxJumps());

		this.dataManager.register(PLAYER_GROWN, false);
		this.dataManager.register(AMMOUNT_FEED, Byte.valueOf((byte) 0));
		this.dataManager.register(HUNGRY, false);
		this.dataManager.register(SITTING, false);
	}

	@Override
    protected void registerAttributes()
    {
        super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
	}

	public int getRandomEggTime()
	{
		return 775 + this.rand.nextInt(50);
	}

	public boolean isSitting()
	{
		return this.dataManager.get(SITTING).booleanValue();
	}

	public void setSitting(boolean isSitting)
	{
		this.dataManager.set(SITTING, isSitting);
	}

	public boolean isHungry()
	{
		return this.dataManager.get(HUNGRY).booleanValue();
	}

	public void setHungry(boolean hungry)
	{
		this.dataManager.set(HUNGRY, hungry);
	}

	public byte getAmountFed()
	{
		return this.dataManager.get(AMMOUNT_FEED).byteValue();
	}

	public void setAmountFed(int amountFed)
	{
		this.dataManager.set(AMMOUNT_FEED, Byte.valueOf((byte) amountFed));
	}

	public void increaseAmountFed(int amountFed)
	{
		this.setAmountFed(this.getAmountFed() + amountFed);
	}

	public boolean isPlayerGrown()
	{
		return this.dataManager.get(PLAYER_GROWN).booleanValue();
	}

	public void setPlayerGrown(boolean playerGrown)
	{
		this.dataManager.set(PLAYER_GROWN, playerGrown);
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
		return this.dataManager.get(REMAINING_JUMPS).intValue();
	}

	public void setRemainingJumps(int jumps)
	{
		this.dataManager.set(REMAINING_JUMPS, jumps);
	}

	public MoaType getMoaType()
	{
		return AetherAPI.instance().getMoa(this.dataManager.get(MOA_TYPE_ID).intValue());
	}

	public void setMoaType(MoaType moa)
	{
		this.dataManager.set(MOA_TYPE_ID, AetherAPI.instance().getMoaId(moa));
	}

	@Override
	public void tick()
	{
		super.tick();

		this.setMaxJumps(this.getMoaType().getMoaProperties().getMaxJumps());

		if (this.isJumping)
		{
			this.motionY += 0.05F;
		}

		this.updateWingRotation();
		this.fall();
		
		if (this.secsUntilHungry > 0)
		{
			if (this.ticksExisted % 20 == 0)
			{
				this.secsUntilHungry--;
			}
		}
		else if (!this.isHungry())
		{
			this.setHungry(true);
		}
		
		if(this.world.isRemote && isHungry() && isChild())
		{
			if(this.rand.nextInt(10) == 0)
			{
				this.world.addParticle(Particles.HAPPY_VILLAGER, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + 1, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
			}
		}

		if (!this.world.isRemote && !this.isChild() && this.getPassengers().isEmpty())
		{
			if (this.secsUntilEgg > 0)
			{
				if (this.ticksExisted % 20 == 0)
				{
					this.secsUntilEgg--;
				}
			}
			else
			{
				this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
				this.entityDropItem(ItemMoaEgg.getStack(this.getMoaType()), 0);

				this.secsUntilEgg = this.getRandomEggTime();
			}
		}

		this.fallDistance = 0.0F;
	}

	@Override
    public void travel(float strafe, float vertical, float forward)
	{
		super.travel(strafe, vertical, forward);
	}
	
	public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

	public void resetHunger()
	{
		if (!this.world.isRemote)
		{
			this.setHungry(false);
		}

		this.secsUntilHungry = 40 + this.rand.nextInt(40);
	}

	public void updateWingRotation()
	{
		if (!this.onGround)
		{
			if (this.ticksUntilFlap == 0)
			{
				this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.15F, MathHelper.clamp(this.rand.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp(this.rand.nextFloat(), 0f, 0.3f));

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
		if (this.getRemainingJumps() > 0 && this.motionY < 0.0D)
		{
			if (!this.onGround)
			{
				this.motionY = 0.7D;
				this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.15F, MathHelper.clamp(this.rand.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp(this.rand.nextFloat(), 0f, 0.3f));

				if (!this.world.isRemote)
				{
					this.setRemainingJumps(this.getRemainingJumps() - 1);
				}

				if (!this.world.isRemote)
				{
					this.spawnExplosionParticle();
				}
			}
			else
			{
				this.motionY = 0.89D;
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
		this.setGrowingAge(0);
	}

	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (stack != null && this.isPlayerGrown())
		{
			Item currentItem = stack.getItem();

			if (this.isChild() && this.isHungry())
			{
				if (this.getAmountFed() < 3 && currentItem == ItemsAether.aechor_petal)
				{
					if (!player.abilities.isCreativeMode)
					{
						stack.shrink(1);
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
				stack.damageItem(2, player);

				this.setSitting(this.isSitting() ? false : true);

				if (!this.world.isRemote)
				{
					this.spawnExplosionParticle();
				}

				return true;
			}
		}

		return super.processInteract(player, hand);
	}

	@Override
	public boolean canSaddle()
	{
		return !this.isChild() && this.isPlayerGrown();
	}

	@Override
	public void writeAdditional(NBTTagCompound nbt)
	{
		super.writeAdditional(nbt);

		nbt.putBoolean("playerGrown", this.isPlayerGrown());
		nbt.putInt("remainingJumps", this.getRemainingJumps());
		nbt.putByte("amountFed", this.getAmountFed());
		nbt.putBoolean("isHungry", this.isHungry());
		nbt.putBoolean("isSitting", this.isSitting());
		nbt.putInt("typeId", AetherAPI.instance().getMoaId(this.getMoaType()));
	}

	@Override
	public void readAdditional(NBTTagCompound nbt)
	{
		super.readAdditional(nbt);

		this.setPlayerGrown(nbt.getBoolean("playerGrown"));
		this.setRemainingJumps(nbt.getInt("remainingJumps"));
		this.setMoaType(AetherAPI.instance().getMoa(nbt.getInt("typeId")));
		this.setAmountFed(nbt.getByte("amountFed"));
		this.setHungry(nbt.getBoolean("isHungry"));
		this.setSitting(nbt.getBoolean("isSitting"));
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
	protected void playStepSound(BlockPos posIn, IBlockState stateIn)
	{
		this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PIG_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	public void fall()
	{
		boolean blockBeneath = !this.world.isAirBlock(new BlockPos(this).down());

		if (this.motionY < 0.0D && !this.isRiderSneaking())
		{
			this.motionY *= 0.6D;
		}

		if (blockBeneath)
		{
			this.setRemainingJumps(this.maxJumps);
		}
	}

	@Override
	public void jump()
	{
		if (!this.isSitting() && this.getPassengers().isEmpty())
		{
			super.jump();
		}
	}

	@Override
	public double getMountedYOffset()
	{
		return this.isSitting() ? 0.25D: 1.25D;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable matingAnimal)
	{
		return new EntityMoa(this.world, this.getMoaType());
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return AetherLootTableList.ENTITIES_MOA;
	}

}