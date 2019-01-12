package com.legacy.aether.blocks.entity;

import java.util.Iterator;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.InventoryUtil;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

public abstract class AetherBlockEntity extends BlockEntity implements SidedInventory, Tickable
{

	private String name = "generic";

	private TextComponent customName;

	public AetherBlockEntity(String name, BlockEntityType<?> blockEntityType_1)
	{
		super(blockEntityType_1);

		this.name = name;
	}

	public abstract DefaultedList<ItemStack> getInventory();

	public abstract void onSlotChanged(int slot);

	@Override
	public int getInvSize()
	{
		return this.getInventory().size();
	}

	@Override
	public boolean isInvEmpty()
	{
		Iterator<ItemStack> iterator = this.getInventory().iterator();

		ItemStack stack;

		do
		{
			if (!iterator.hasNext())
			{
				return true;
			}

			stack = iterator.next();
		}
		while (stack.isEmpty());

		return false;
	}

	@Override
	public ItemStack getInvStack(int slot)
	{
		return this.getInventory().get(slot);
	}

	@Override
	public ItemStack takeInvStack(int slot, int stackSize)
	{
		return InventoryUtil.splitStack(this.getInventory(), slot, stackSize);
	}

	@Override
	public ItemStack removeInvStack(int slot)
	{
		return InventoryUtil.removeStack(this.getInventory(), slot);
	}

	@Override
	public void setInvStack(int slot, ItemStack stackIn)
	{
		ItemStack itemStack_2 = (ItemStack) this.getInventory().get(slot);
		boolean isClean = !stackIn.isEmpty() && stackIn.isEqualIgnoreTags(itemStack_2) && ItemStack.areTagsEqual(stackIn, itemStack_2);

		this.getInventory().set(slot, stackIn);

		if (stackIn.getAmount() > this.getInvMaxStackAmount())
		{
			stackIn.setAmount(this.getInvMaxStackAmount());
		}

		if (!isClean)
		{
			this.onSlotChanged(slot);
			this.markDirty();
		}

	}

	@Override
	public void fromTag(CompoundTag compound)
	{
		super.fromTag(compound);

		this.getInventory().clear();
		InventoryUtil.deserialize(compound, this.getInventory());

		if (compound.containsKey("customName", 8))
		{
			this.customName = TextComponent.Serializer.fromJsonString(compound.getString("customName"));
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag compound)
	{
		InventoryUtil.serialize(compound, this.getInventory());

		if (this.hasCustomName())
		{
			compound.putString("customName", TextComponent.Serializer.toJsonString(this.customName));
		}

		return super.toTag(compound);
	}

	@Override
	public boolean canPlayerUseInv(PlayerEntity playerIn)
	{
		return playerIn.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void clearInv()
	{
		this.getInventory().clear();
	}

	public void setCustomName(TextComponent textComponentIn)
	{
		this.customName = textComponentIn;
	}

	@Override
	public TextComponent getName()
	{
		return this.customName != null ? this.customName : new TranslatableTextComponent("container.aether_legacy." + this.name, new Object[0]);
	}

	@Override
	public TextComponent getCustomName()
	{
		return this.customName;
	}

	@Override
	public boolean canInsertInvStack(int slot, ItemStack stack, Direction direction)
	{
		return true;
	}

	@Override
	public boolean canExtractInvStack(int slot, ItemStack stack, Direction direction)
	{
		return this.isValidInvStack(slot, stack);
	}

}