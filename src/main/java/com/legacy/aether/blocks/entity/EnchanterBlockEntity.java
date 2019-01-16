package com.legacy.aether.blocks.entity;

import java.util.Map;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.enchantments.AetherEnchantment;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.container.BlockWithAetherEntity;

public class EnchanterBlockEntity extends AetherBlockEntity
{

	public int progress, ticksRequired, powerRemaining;

	private DefaultedList<ItemStack> stacks = DefaultedList.create(3, ItemStack.EMPTY);

	private AetherEnchantment enchantment;

	public EnchanterBlockEntity() 
	{
		super("enchanter", BlockEntitiesAether.ENCHANTER);
	}

	@Override
	public void tick()
	{
		boolean isActive = this.isEnchanting();

		if (this.powerRemaining > 0)
		{
			--this.powerRemaining;

			if (this.world.getBlockState(this.getPos().down()).getBlock() == BlocksAether.enchanted_gravitite)
			{
				this.progress += 2.5F;
			}
			else
			{
				this.progress++;
			}
		}

		if (this.enchantment != null)
		{
			if (this.progress >= this.ticksRequired)
			{
				if (!this.world.isClient)
				{
					ItemStack result = this.enchantment.getOutput().copy();

					EnchantmentHelper.set(EnchantmentHelper.getEnchantments(this.getInvStack(0)), result);

					if (!this.getInvStack(2).isEmpty())
					{
						result.setAmount(this.getInvStack(2).getAmount() + 1);

						this.setInvStack(2, result);
					}
					else
					{
						this.setInvStack(2, result);
					}

					if (this.getInvStack(0).getItem().hasRecipeRemainder())
					{
						this.setInvStack(0, new ItemStack(this.getInvStack(0).getItem().getRecipeRemainder()));
					}
					else
					{
						this.takeInvStack(0, 1);
					}
				}

				this.progress = 0;
			}

			if (this.getInvStack(0).isEmpty() || (!this.getInvStack(0).isEmpty() && AetherAPI.instance().getEnchantment(this.getInvStack(0)) != this.enchantment))
			{
				this.enchantment = null;
				this.progress = 0;
			}

			if (this.powerRemaining <= 0)
			{
				if (!this.getInvStack(1).isEmpty() && AetherAPI.instance().isEnchantmentFuel(this.getInvStack(1)))
				{
					this.powerRemaining += AetherAPI.instance().getEnchantmentFuel(this.getInvStack(1)).getTimeGiven();

					if (!this.world.isClient)
					{
						this.takeInvStack(1, 1);
					}
				}
				else
				{
					this.enchantment = null;
					this.progress = 0;
				}
			}
		}
		else if (!this.getInvStack(0).isEmpty())
		{
			ItemStack itemstack = this.getInvStack(0);
			AetherEnchantment enchantment = AetherAPI.instance().getEnchantment(itemstack);

			if (enchantment != null)
			{
				if (this.getInvStack(2).isEmpty() || (enchantment.getOutput().getItem() == this.getInvStack(2).getItem()))
				{
					this.enchantment = enchantment;
					this.ticksRequired = this.enchantment.getTimeRequired();
					this.addEnchantmentWeight(itemstack);
				}
			}
		}

		if (isActive != this.isEnchanting())
		{
			this.markDirty();
			BlockWithAetherEntity.setState(this.world, this.pos, this.isEnchanting());
		}
	}

	@Override
	public void fromTag(CompoundTag compound)
	{
		super.fromTag(compound);

		this.progress = compound.getInt("progress");
		this.powerRemaining = compound.getInt("powerRemaining");
		this.ticksRequired = compound.getInt("ticksRequired");
	}

	@Override
	public CompoundTag toTag(CompoundTag compound)
	{
		compound.putInt("progress", this.progress);
		compound.putInt("powerRemaining", this.powerRemaining);
		compound.putInt("ticksRequired", this.ticksRequired);

		return super.toTag(compound);
	}

	@Override
	public void onSlotChanged(int index)
	{

	}

	public void addEnchantmentWeight(ItemStack stack)
	{
		Map<net.minecraft.enchantment.Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);

		if (!enchantments.isEmpty())
		{
			for (int levels : enchantments.values())
			{
				this.ticksRequired += (levels * 1250);
			}
		}
	}

	public boolean isEnchanting()
	{
		return this.getProperty(1) > 0;
	}

	@Override
	public boolean isValidInvStack(int slot, ItemStack stackInSlot)
	{
		if (slot == 2)
		{
			return false;
		}
		else if (slot == 1 && AetherAPI.instance().isEnchantmentFuel(stackInSlot))
		{
			return true;
		}
		else return slot == 0 && AetherAPI.instance().isEnchantable(stackInSlot);

	}

	@Override
	public int getProperty(int id)
	{
		if (id == 0)
		{
			return this.progress;
		}
		else if (id == 1)
		{
			return this.powerRemaining;
		}
		else if (id == 2)
		{
			return this.ticksRequired;
		}

		return 0; 
	}

	@Override
	public void setProperty(int id, int value) 
	{ 
		if (id == 0)
		{
			this.progress = value;
		}
		else if (id == 1)
		{
			this.powerRemaining = value;
		}
		else if (id == 2)
		{
			this.ticksRequired = value;
		}
	}

	@Override
	public int getPropertySize() 
	{
		return 3; 
	}

	@Override
	public DefaultedList<ItemStack> getInventory()
	{
		return this.stacks;
	}

	@Override
	public int[] getInvAvailableSlots(Direction direction)
	{
		return direction == Direction.DOWN ? new int[] {2} : new int[] {0, 1};
	}

}