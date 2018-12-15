package com.legacy.aether.inventory;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.inventory.container.ContainerAccessories;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.util.DefaultedList;

public class InventoryAccessories implements Inventory, InteractionObject
{

	private IEntityPlayerAether playerAether;

    public DefaultedList<ItemStack> stacks = DefaultedList.create(8, ItemStack.EMPTY);

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
	public Container createContainer(Inventory inventoryIn, PlayerEntity entityIn)
	{
		return new ContainerAccessories(((IEntityPlayerAether)entityIn));
	}

	@Override
	public String getGuiID() 
	{
		return "aether_legacy:accessories";
	}

	@Override
	public ItemStack getInvStack(int index)
	{
		return this.stacks.get(index);
	}

	public int getAvailableSlot(AccessoryType type)
	{
		for (int index = 0; index < this.type.length; ++index)
		{
			ItemStack stack = this.getInvStack(index);

			if (stack.isEmpty() && type == this.type[index])
			{
				return index;
			}
		}

		return -1;
	}

	public boolean setAccessory(ItemStack stack)
	{
		boolean flag = false;
		int slot = this.getAvailableSlot(((ItemAccessory)stack.getItem()).getType());

		if (this.getInvStack(slot).isEmpty())
		{
			this.setInvStack(slot, stack);
			flag = true;
		}

		return flag;
	}

	@Override
	public void setInvStack(int index, ItemStack stack)
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
	public ItemStack takeInvStack(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.stacks, index, count);
	}

	@Override
	public ItemStack removeInvStack(int index)
	{
		return ItemStackHelper.getAndRemove(this.stacks, index);
	}

	@Override
	public boolean canPlayerUseInv(PlayerEntity playerIn)
	{
		return !playerIn.removed;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public int getInvSize()
	{
		return this.stacks.size();
	}

	@Override
	public void clearInv()
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
	public boolean isInvEmpty()
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
	public void openInventory(PlayerEntity playerIn)
	{

	}

	@Override
	public void closeInventory(PlayerEntity playerIn)
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
	public TextComponent getCustomName()
	{
		return this.getName();
	}

	@Override
	public TextComponent getName()
	{
		return new StringTextComponent("Accessories");
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

}