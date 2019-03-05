package com.legacy.aether.container;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.blocks.entity.AetherBlockEntity;
import com.legacy.aether.container.slot.SlotOutput;

public class ContainerEnchanter extends Container
{

	private Inventory enchanter;

	public int progress, ticksRequired, powerRemaining;

	public ContainerEnchanter(int syncId, Inventory inventoryIn, AetherBlockEntity enchanterIn)
	{
		super(null, syncId);

		checkContainerSize(enchanterIn, 3);
		checkContainerDataCount(enchanterIn, 3);

		this.enchanter = enchanterIn;

		this.addSlot(new Slot(enchanterIn, 0, 56, 17));
		this.addSlot(new Slot(enchanterIn, 1, 56, 53));
		this.addSlot(new SlotOutput(enchanterIn, 2, 116, 35));

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

		this.addProperties(enchanterIn);
	}

	@Override
	public boolean canUse(PlayerEntity playerIn)
	{
		return this.enchanter.canPlayerUseInv(playerIn);
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
				if (AetherAPI.instance().isEnchantable(itemstack))
				{
					if (!this.insertItem(itemstack1, 0, 1, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (AetherAPI.instance().isEnchantmentFuel(itemstack1))
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

}