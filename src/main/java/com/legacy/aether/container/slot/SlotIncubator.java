package com.legacy.aether.container.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;

public class SlotIncubator extends Slot
{

	public SlotIncubator(Inventory inv, int slot, int x, int y)
	{
		super(inv, slot, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack)
	{
		return stack.getItem() == ItemsAether.moa_egg;
	}

	@Override
	public int getMaxStackAmount()
	{
		return 1;
	}

}