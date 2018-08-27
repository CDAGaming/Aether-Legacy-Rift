package com.legacy.aether.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot
{

	public SlotOutput(IInventory inventoryIn, int indexIn, int xIn, int yIn) 
	{
		super(inventoryIn, indexIn, xIn, yIn);
	}

	@Override
	public boolean isItemValid(ItemStack stackIn)
	{
		return false;
	}

}