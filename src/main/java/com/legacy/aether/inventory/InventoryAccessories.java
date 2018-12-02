package com.legacy.aether.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.inventory.container.ContainerAccessories;
import com.legacy.aether.player.IEntityPlayerAether;

public class InventoryAccessories implements IInventory, IInteractionObject
{

	private IEntityPlayerAether playerAether;

    public NonNullList<ItemStack> stacks = NonNullList.withSize(8, ItemStack.EMPTY);

    public static final String[] EMPTY_SLOT_NAMES = new String[] {"pendant", "cape", "shield", "misc", "ring", "ring", "gloves", "misc"};

    public AccessoryType[] type = new AccessoryType[] {AccessoryType.PENDANT, AccessoryType.CAPE, AccessoryType.SHIELD, AccessoryType.MISC, AccessoryType.RING, AccessoryType.RING, AccessoryType.GLOVE, AccessoryType.MISC};

    public InventoryAccessories(IEntityPlayerAether playerAetherIn)
    {
    	this.playerAether = playerAetherIn;
    }

	public void dropAllAccessories()
	{
		for (int slot = 0; slot < this.stacks.size(); ++slot)
		{
			if (this.stacks.get(slot) != ItemStack.EMPTY)
			{
				InventoryHelper.dropInventoryItems(this.playerAether.getInstance().world, this.playerAether.getInstance(), this);

				this.stacks.set(slot, ItemStack.EMPTY);
			}
		}
	}

	@Override
	public Container createContainer(InventoryPlayer inventoryIn, EntityPlayer entityIn) 
	{
		return new ContainerAccessories(((IEntityPlayerAether)entityIn));
	}

	@Override
	public String getGuiID() 
	{
		return "aether_legacy:accessories";
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return this.stacks.get(index);
	}

	private int getAvailableSlot(AccessoryType type)
	{
		for (int index = 0; index < this.type.length; ++index)
		{
			ItemStack stack = this.getStackInSlot(index);

			if (stack.isEmpty() && AetherAPI.instance().getAccessory(stack).getType() == this.type[index])
			{
				return index;
			}
		}

		return -1;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		this.stacks.set(index, stack);
	}

	public boolean isAccessoryValidForSlot(AccessoryType type, ItemStack stack)
	{
		return AetherAPI.instance().isAccessory(stack) && this.type[this.getAvailableSlot(type)] == AetherAPI.instance().getAccessory(stack).getType();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return AetherAPI.instance().isAccessory(stack) && this.type[index] == AetherAPI.instance().getAccessory(stack).getType();
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.stacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.stacks, index);
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer playerIn)
	{
		return !playerIn.removed;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public int getSizeInventory() 
	{
		return this.stacks.size();
	}

	@Override
	public void clear() 
	{
		ItemStack itemstack;

		for (int slot = 0; slot < this.stacks.size(); ++slot)
		{
			itemstack = this.stacks.get(slot);

			if (itemstack != ItemStack.EMPTY)
			{
				this.stacks.set(slot, ItemStack.EMPTY);
			}
		}
	}

	@Override
	public boolean isEmpty() 
	{
		for (ItemStack stacks : this.stacks)
		{
			if (!stacks.isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public void openInventory(EntityPlayer playerIn)
	{

	}

	@Override
	public void closeInventory(EntityPlayer playerIn)
	{

	}

	@Override
	public void markDirty() 
	{

	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public ITextComponent getCustomName() 
	{
		return this.getName();
	}

	@Override
	public ITextComponent getName() 
	{
		return new TextComponentString("Accessories");
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

}