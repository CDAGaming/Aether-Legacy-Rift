package com.legacy.aether.api.player.util;

import java.util.Iterator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

import com.legacy.aether.api.accessories.AccessoryType;

public interface AccessoryInventory extends Inventory
{

	public void addListener(InventoryListener listener);

	public void removeListener(InventoryListener listener);

	public void damageAccessory(int damage, AccessoryType type);

	public void damageWornStack(int damage, ItemStack stack);

	public void setInvStack(AccessoryType type, ItemStack stack);

	public ItemStack getInvStack(AccessoryType type);

	public ItemStack removeInvStack(AccessoryType type);

	public boolean setAccessorySlot(ItemStack stack);

	public boolean wearingAccessory(ItemStack stack);

	public boolean wearingArmor(ItemStack stack);

	public boolean isWearingZaniteSet();

	public boolean isWearingGravititeSet();

	public boolean isWearingNeptuneSet();

	public boolean isWearingPhoenixSet();

	public boolean isWearingObsidianSet();

	public boolean isWearingValkyrieSet();

	public int getAccessoryCount(ItemStack stack);

	public DefaultedList<ItemStack> getInventory();

	public default CompoundTag serialize(CompoundTag compound)
	{
		return Inventories.toTag(compound, this.getInventory());
	}

	public default void deserialize(CompoundTag compound)
	{
		Inventories.fromTag(compound, this.getInventory());
	}

	@Override
	public default boolean isInvEmpty()
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
	public default void setInvStack(int slot, ItemStack stack)
	{
		this.getInventory().set(slot, stack);
	}

	@Override
	public default ItemStack getInvStack(int slot)
	{
		return this.getInventory().get(slot);
	}

	@Override
	public default ItemStack takeInvStack(int slot, int stackSize)
	{
		return Inventories.splitStack(this.getInventory(), slot, stackSize);
	}

	@Override
	public default ItemStack removeInvStack(int slot)
	{
		return Inventories.removeStack(this.getInventory(), slot);
	}

	@Override
	public default boolean canPlayerUseInv(PlayerEntity playerIn)
	{
		return !playerIn.invalid;
	}

	@Override
	public default int getInvSize()
	{
		return this.getInventory().size();
	}

	@Override
	public default void clear()
	{
		this.getInventory().clear();
	}

}