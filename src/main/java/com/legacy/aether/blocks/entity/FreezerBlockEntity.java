package com.legacy.aether.blocks.entity;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.freezables.AetherFreezable;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.container.BlockWithAetherEntity;

public class FreezerBlockEntity extends AetherBlockEntity
{

	public int progress, ticksRequired, powerRemaining;

	private DefaultedList<ItemStack> frozenItemStacks = DefaultedList.create(3, ItemStack.EMPTY);

	private AetherFreezable currentFreezable;

	public FreezerBlockEntity() 
	{
		super("freezer", BlockEntitiesAether.FREEZER);
	}

	@Override
	public void tick()
	{
		boolean flag = this.isFreezing();

		if (this.powerRemaining > 0)
		{
			this.powerRemaining--;

			if (this.currentFreezable != null)
			{
				if (this.world.getBlockState(this.getPos().down()).getBlock() == BlocksAether.icestone)
				{
					this.progress += 2;
				}
				else
				{
					this.progress++;
				}
			}
		}

		if (this.currentFreezable != null)
		{
			if (this.progress >= this.currentFreezable.getTimeRequired())
			{
				if (!this.world.isClient)
				{
					ItemStack result = this.currentFreezable.getOutput().copy();

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

			if (this.getInvStack(0).isEmpty() || (!this.getInvStack(0).isEmpty() && AetherAPI.instance().getFreezable(this.getInvStack(0)) != this.currentFreezable))
			{
				this.currentFreezable = null;
				this.progress = 0;
			}

			if (this.powerRemaining <= 0)
			{
				if (!this.getInvStack(1).isEmpty() && AetherAPI.instance().isFreezerFuel(this.getInvStack(1)))
				{
					this.powerRemaining += AetherAPI.instance().getFreezableFuel(this.getInvStack(1)).getTimeGiven();

					if (!this.world.isClient)
					{
						this.takeInvStack(1, 1);
					}
				}
				else
				{
					this.currentFreezable = null;
					this.progress = 0;
				}
			}
		}
		else if (!this.getInvStack(0).isEmpty())
		{
			ItemStack itemstack = this.getInvStack(0);
			AetherFreezable freezable = AetherAPI.instance().getFreezable(itemstack);

			if (freezable != null)
			{
				if (this.getInvStack(2).isEmpty() || (freezable.getOutput().getItem() == this.getInvStack(2).getItem()))
				{
					this.currentFreezable = freezable;
					this.ticksRequired = this.currentFreezable.getTimeRequired();
					this.addEnchantmentWeight(itemstack);
				}
			}
		}

		if (flag != this.isFreezing())
		{
			this.markDirty();
			BlockWithAetherEntity.setState(this.world, this.pos, this.isFreezing());
		}
	}

	@Override
	public void onSlotChanged(int index)
	{

	}

	public void addEnchantmentWeight(ItemStack stack)
	{
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);

		if (!enchantments.isEmpty())
		{
			for (int levels : enchantments.values())
			{
				this.ticksRequired += (levels * 1250);
			}
		}
	}

	public int getFreezingProgressScaled(int i)
	{
		if (this.ticksRequired == 0)
		{
			return 0;
		}
		return (this.progress * i) / this.ticksRequired;
	}

	public int getFreezingTimeRemaining(int i)
	{
		return (this.powerRemaining * i) / 500;
	}

	public boolean isFreezing()
	{
		return this.powerRemaining > 0;
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
	public boolean isValidInvStack(int slot, ItemStack stackInSlot)
	{
		if (stackInSlot != null)
		{
			if (AetherAPI.instance().isFreezable(stackInSlot))
			{
				return true;
			}
			else if (slot == 1 && AetherAPI.instance().isFreezerFuel(stackInSlot))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public int get(int id)
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
	public void set(int id, int value) 
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
	public int size() 
	{
		return 3; 
	}

	@Override
	public DefaultedList<ItemStack> getInventory()
	{
		return this.frozenItemStacks;
	}

	@Override
	public int[] getInvAvailableSlots(Direction direction) 
	{
		return direction == Direction.DOWN ? new int[] {2} : new int[] {0, 1};
	}

}