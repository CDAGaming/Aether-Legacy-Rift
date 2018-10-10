package com.legacy.aether.entities.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public abstract class EntitySaddleMount extends EntityMountable
{

	public static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityMountable.class, DataSerializers.BOOLEAN);

	public EntitySaddleMount(EntityType<? extends Entity> type, World world)
	{
		super(type, world);
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
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(SADDLED, false);
	}

	public boolean getSaddled()
	{
		return this.dataManager.get(SADDLED);
	}
	
	public boolean canSaddle()
	{
		return true;
	}

	@Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand)
	{
		ItemStack heldItem = entityplayer.getHeldItem(hand);

		if (!this.canSaddle())
		{
			return super.processInteract(entityplayer, hand);
		}
		
		if (!this.getSaddled())
		{
			if (heldItem.getItem() == Items.SADDLE && !this.isChild())
			{
				if (!entityplayer.capabilities.isCreativeMode)
				{
					entityplayer.setHeldItem(hand, ItemStack.EMPTY);
				}
				
				if (entityplayer.world.isRemote)
				{
					entityplayer.world.playSound(entityplayer, entityplayer.getPosition(), SoundEvents.ENTITY_PIG_SADDLE, SoundCategory.AMBIENT, 1.0F, 1.0F);
				}

				this.setSaddled(true);

				return true;
			}
		}
		else
		{
			if (!this.isBeingRidden() && !this.isRiding())
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
		if (!this.getPassengers().isEmpty())
		{
			return false;
		}

		return super.isEntityInsideOpaqueBlock();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		compound.setBoolean("isSaddled", this.getSaddled());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		this.setSaddled(compound.getBoolean("isSaddled"));
	}

	public void setSaddled(boolean saddled)
	{
		this.dataManager.set(SADDLED, saddled);
	}
	
	/*@Override
	public boolean shouldRiderFaceForward(EntityPlayer player)
	{
		return false;
	}*/

}