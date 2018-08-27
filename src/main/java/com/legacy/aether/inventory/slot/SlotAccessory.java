package com.legacy.aether.inventory.slot;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.inventory.InventoryAccessories;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAccessory extends Slot
{

	private final int slotIndex;

	private AccessoryType type;

	public SlotAccessory(IInventory inventoryIn, int indexIn, AccessoryType typeIn, int xIn, int zIn) 
	{
		super(inventoryIn, indexIn, xIn, zIn);

		this.type = typeIn;
		this.slotIndex = indexIn;
	}

	@Override
	public boolean isItemValid(ItemStack stackIn)
	{
		return AetherAPI.instance().isAccessory(stackIn) && AetherAPI.instance().getAccessory(stackIn).getType() == this.getType();
	}

	@Override
    public String getSlotTexture()
    {
        return "aether_legacy:item/slots/" + InventoryAccessories.EMPTY_SLOT_NAMES[this.slotIndex];
    }

	public AccessoryType getType()
	{
		return this.type;
	}

}