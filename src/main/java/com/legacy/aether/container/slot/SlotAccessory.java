package com.legacy.aether.container.slot;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.Slot;
import net.minecraft.item.ItemStack;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.accessories.AetherAccessory;
import com.legacy.aether.api.player.util.AccessoryInventory;

public class SlotAccessory extends Slot
{

	private AccessoryType type;

	public SlotAccessory(AccessoryInventory inventory, AccessoryType type, int slotID, int x, int y)
	{
		super(inventory, slotID, x, y);

		this.type = type;
	}

	@Override
	public boolean canInsert(ItemStack stack)
	{
		AetherAccessory accessory = AetherAPI.instance().getAccessory(stack);

		return accessory != null && (accessory.getAccessoryType() == this.type || accessory.getExtraType() == this.type);
	}

	@Environment(EnvType.CLIENT)
	public String getBackgroundSprite()
	{
		return "aether_legacy:item/sprite/" + this.type.getDisplayName();
	}

	public AccessoryType getType()
	{
		return this.type;
	}

}