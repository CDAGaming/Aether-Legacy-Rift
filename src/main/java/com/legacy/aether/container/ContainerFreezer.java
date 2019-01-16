package com.legacy.aether.container;

import net.minecraft.class_3917;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.blocks.entity.AetherBlockEntity;
import com.legacy.aether.container.slot.SlotOutput;

public class ContainerFreezer extends Container
{

	private Inventory freezer;

	public ContainerFreezer(int syncId, Inventory inventoryIn, AetherBlockEntity freezerIn)
	{
		super(syncId);

		method_17359(freezerIn, 3); //check correct size
		method_17361(freezerIn, 3); //check correct data size

		this.freezer = freezerIn;

		this.addSlot(new Slot(freezerIn, 0, 56, 17));
		this.addSlot(new Slot(freezerIn, 1, 56, 53));
		this.addSlot(new SlotOutput(freezerIn, 2, 116, 35));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlot(new Slot(inventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlot(new Slot(inventoryIn, i, 8 + i * 18, 142));
		}

		this.method_17360(freezerIn);
	}

	@Override
	public boolean canUse(PlayerEntity playerIn)
	{
		return this.freezer.canPlayerUseInv(playerIn);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity par1EntityPlayer, int par2)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slotList.get(par2);

		if (slot != null && slot.hasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.insertItem(itemstack1, 3, 39, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onStackChanged(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				if (AetherAPI.instance().isFreezable(itemstack1))
				{
					if (!this.insertItem(itemstack1, 0, 1, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (AetherAPI.instance().isFreezerFuel(itemstack1))
				{
					if (!this.insertItem(itemstack1, 1, 2, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if (!this.insertItem(itemstack1, 30, 39, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.insertItem(itemstack1, 3, 30, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.insertItem(itemstack1, 3, 39, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.getAmount() == 0)
			{
				slot.setStack(ItemStack.EMPTY);
			}
			else
			{
				slot.markDirty(); // TODO: ?
			}

			if (itemstack1.getAmount() == itemstack.getAmount())
			{
				return ItemStack.EMPTY;
			}

            slot.onTakeItem(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

	@Override
	public class_3917<?> method_17358()
	{
		return null;
	}

}