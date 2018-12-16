package com.legacy.aether.entities.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EntitySaddleMount extends EntityMountable
{

	public static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityMountable.class, DataSerializers.BOOLEAN);

	public EntitySaddleMount(EntityType<? extends Entity> type, World world)
	{
		super(type, world);
		super.initDataTracker();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		if (this.isBeingRidden() && this.getPassengers().get(0) == damagesource.getImmediateSource())
		{
			return false;
		}

		if (!this.world.isRemote)
		{
			if (this.getSaddled())
			{
				this.entityDropItem(Items.SADDLE, 1);
			}
		}

		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	public boolean canBeSteered()
	{
		return true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return this.onGround;
	}

	@Override
	protected void dropFewItems(boolean var1, int var2)
	{
		super.dropFewItems(var1, var2);
		
		this.dropSaddle();
	}

	protected void dropSaddle()
	{
		if (this.getSaddled())
		{
			this.entityDropItem(Items.SADDLE, 1);
		}
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.dataTracker.startTracking(SADDLED, false);
	}

	public boolean getSaddled()
	{
		return this.dataTracker.get(SADDLED);
	}
	
	public boolean canSaddle()
	{
		return true;
	}

	@Override
    public boolean processInteract(PlayerEntity entityplayer, Hand hand)
	{
		ItemStack heldItem = entityplayer.getStackInHand(hand);

		if (!this.canSaddle())
		{
			return super.processInteract(entityplayer, hand);
		}
		
		if (!this.getSaddled())
		{
			if (heldItem.getItem() == Items.SADDLE && !this.isChild())
			{
				if (!entityplayer.abilities.creativeMode)
				{
					entityplayer.setStackInHand(hand, ItemStack.EMPTY);
				}
				
				if (entityplayer.world.isRemote)
				{
					entityplayer.world.playSound(entityplayer, entityplayer.getPos(), SoundEvents.ENTITY_PIG_SADDLE, SoundCategory.AMBIENT, 1.0F, 1.0F);
				}

				this.setSaddled(true);

				return true;
			}
		}
		else
		{
			if (!this.isBeingRidden() && !this.isOnePlayerRiding())
			{
				if (!entityplayer.world.isRemote)
				{
					entityplayer.startRiding(this);
					entityplayer.prevRotationYaw = entityplayer.rotationYaw = this.rotationYaw;
				}

				return true;
			}
		}
		
		return super.processInteract(entityplayer, hand);
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		if (!this.getPassengerList().isEmpty())
		{
			return false;
		}

		return super.isEntityInsideOpaqueBlock();
	}

	@Override
	public void writeAdditional(CompoundTag compound)
	{
		super.writeAdditional(compound);

		compound.putBoolean("isSaddled", this.getSaddled());
	}

	@Override
	public void readAdditional(CompoundTag compound)
	{
		super.readAdditional(compound);

		this.setSaddled(compound.getBoolean("isSaddled"));
	}

	public void setSaddled(boolean saddled)
	{
		this.dataTracker.set(SADDLED, saddled);
	}
	
	/*@Override
	public boolean shouldRiderFaceForward(EntityPlayer player)
	{
		return false;
	}*/

}