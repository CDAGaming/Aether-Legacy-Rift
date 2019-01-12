package com.legacy.aether.api.player.util;

import java.util.Iterator;

import com.legacy.aether.api.accessories.AccessoryType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.InventoryUtil;

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

	public default void serialize(CompoundTag compound)
	{
		InventoryUtil.deserialize(compound, this.getInventory());
	}

	public default void deserialize(CompoundTag compound)
	{
		InventoryUtil.serialize(compound, this.getInventory());
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
		return InventoryUtil.splitStack(this.getInventory(), slot, stackSize);
	}

	@Override
	public default ItemStack removeInvStack(int slot)
	{
		return InventoryUtil.removeStack(this.getInventory(), slot);
	}

	@Override
	public default TextComponent getName()
	{
		return new TranslatableTextComponent("inventory.aether_legacy.accessories", new Object[0]);
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
	public default void clearInv()
	{
		this.getInventory().clear();
	}

}