package com.legacy.aether.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.inventory.slot.SlotOutput;

public class ContainerEnchanter extends Container
{

	private IInventory enchanter;

	public int progress, ticksRequired, powerRemaining;

	public ContainerEnchanter(InventoryPlayer inventoryIn, IInventory enchanterIn)
	{
		this.enchanter = enchanterIn;

		this.addSlotToContainer(new Slot(enchanterIn, 0, 56, 17));
		this.addSlotToContainer(new Slot(enchanterIn, 1, 56, 53));
		this.addSlotToContainer(new SlotOutput(enchanterIn, 2, 116, 35));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(inventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventoryIn, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.enchanter.isUsableByPlayer(playerIn);
	}

    @Override
    public void addListener(IContainerListener listenerIn)
    {
        super.addListener(listenerIn);

        listenerIn.sendAllWindowProperties(this, this.enchanter);
    }

	@Override
	public void updateProgressBar(int id, int value)
	{
		this.enchanter.setField(id, value);
	}

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

		for (IContainerListener listener : this.listeners) {

			if (this.progress != this.enchanter.getField(0)) {
				listener.sendWindowProperty(this, 0, this.enchanter.getField(0));
			} else if (this.powerRemaining != this.enchanter.getField(1)) {
				listener.sendWindowProperty(this, 1, this.enchanter.getField(1));
			} else if (this.ticksRequired != this.enchanter.getField(2)) {
				listener.sendWindowProperty(this, 2, this.enchanter.getField(2));
			}
		}

        this.progress = this.enchanter.getField(0);
        this.powerRemaining = this.enchanter.getField(1);
        this.ticksRequired = this.enchanter.getField(2);
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 1 && par2 != 0)
			{
				if (AetherAPI.instance().isEnchantable(itemstack))
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (AetherAPI.instance().isEnchantmentFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

            slot.onTake(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}