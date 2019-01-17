package com.legacy.aether.container;

import net.minecraft.container.Container;
import net.minecraft.container.CraftingResultSlot;
import net.minecraft.container.PlayerContainer;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Int2ObjectBiMap;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.accessories.AetherAccessory;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.container.slot.SlotAccessory;
import com.legacy.aether.util.Reflection;

public class ContainerAccessories extends PlayerContainer
{

	public ContainerAccessories(int syncId, PlayerEntity playerIn)
	{
		super(playerIn.inventory, !playerIn.world.isClient, playerIn);

		Reflection.setField(Container.class, (Container) this, 4, syncId);

		for (Slot slot : this.slotList)
		{
			if (slot.id > 0 && slot.id < 5)
			{
				slot.xPosition += 18;
			}

			if (slot.id > 4 && slot.id < 9)
			{
				slot.xPosition += 51;
			}
			else if (slot.id == 45)
			{
				slot.xPosition += 39;
			}
		}

		IPlayerAether playerAether = AetherAPI.get(playerIn);
		Int2ObjectBiMap<AccessoryType> types = AccessoryType.createCompleteList();
		int slotID = 0;

		for (int x = 1; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				this.addSlot(new SlotAccessory(playerAether.getAccessoryInventory(), types.getInt(slotID), slotID, 59 + x * 18, 8 + y * 18));
				++slotID;
			}
		}
	}

	public int getAccessorySlot(AetherAccessory type)
	{
		for (int index = 0; index < this.slotList.size(); ++index)
		{
			Slot slot = this.slotList.get(index);

			if (slot instanceof SlotAccessory && !slot.hasStack())
			{
				SlotAccessory accessorySlot = (SlotAccessory) slot;

				if (accessorySlot.getType() == type.getAccessoryType() || accessorySlot.getType() == type.getExtraType())
				{
					return index;
				}
			}
		}

		return -1;
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index)
	{
		Slot slot = (Slot) this.slotList.get(index);

		if (slot != null && slot.hasStack())
		{
			ItemStack stack = slot.getStack();

			if (!(slot instanceof SlotAccessory) && !(slot instanceof CraftingResultSlot))
			{
				int newSlotIndex = -1;

				if (AetherAPI.instance().isAccessory(stack))
				{
					newSlotIndex = this.getAccessorySlot(AetherAPI.instance().getAccessory(stack));
				}

				if (newSlotIndex != -1)
				{
					Slot accessorySlot = (SlotAccessory) this.slotList.get(newSlotIndex);

					accessorySlot.setStack(stack);
					slot.setStack(ItemStack.EMPTY);

					return stack;
				}
			}
		}

		return super.transferSlot(player, index);
	}

}