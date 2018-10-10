package com.legacy.aether.entities.passive;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.EntitySaddleMount;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;
import com.legacy.aether.world.storage.loot.AetherLootTableList;

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
		this.ignoreFrustumCheck = true;
		this.canJumpMidAir = true;

		this.setSize(0.9F, 1.3F);
	}

	@Override
    protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Ingredient.fromItems(ItemsAether.blueberry), false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
    }

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.motionY < 0.0D && !this.isRiderSneaking())
		{
			this.motionY *= 0.6D;
		}

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

		if (this.ticks >= 32)
		{
			this.ticks = 0;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);

		nbttagcompound.setShort("jumps", (short) this.maxJumps);
		nbttagcompound.setShort("jumpsRemaining", (short) this.jumpsRemaining);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);

		this.maxJumps = nbttagcompound.getShort("jumps");
		this.jumpsRemaining = nbttagcompound.getShort("jumpsRemaining");
	}

	@Override
	public double getMountedYOffset()
	{
		return 1.15D;
	}

	@Override
	public float getMountedMoveSpeed()
	{
		return 0.3F;
	}

	@Override
	protected void jump()
	{
		if (!this.isBeingRidden())
		{
			super.jump();
		}
	}

	@Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack heldItem = player.getHeldItem(hand);

		if (heldItem.getItem() == ItemsAether.skyroot_bucket && !player.capabilities.isCreativeMode)
		{
			heldItem.shrink(1);

			if (heldItem.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(ItemsAether.skyroot_milk_bucket));
			}
			else if (!player.inventory.addItemStackToInventory(new ItemStack(ItemsAether.skyroot_milk_bucket)))
			{
				player.dropItem(new ItemStack(ItemsAether.skyroot_milk_bucket), false);
			}

			return true;
		}
		else if (heldItem.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode)
		{
			heldItem.shrink(1);

			if (heldItem.isEmpty())
			{
				player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
			}
			else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
			{
				player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
			}

			return true;
		}
		else
		{
			return super.processInteract(player, hand);
		}
	}

	@Override
	protected double getMountJumpStrength()
	{
		return 5.0D;
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
    protected void playStepSound(BlockPos posIn, IBlockState stateIn)
	{
		this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_COW_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	@Override
	public EntityFlyingCow createChild(EntityAgeable entityageable)
	{
		return new EntityFlyingCow(this.world);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return AetherLootTableList.ENTITIES_FLYING_COW;
	}

}