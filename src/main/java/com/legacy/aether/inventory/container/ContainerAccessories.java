package com.legacy.aether.inventory.container;

import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.inventory.slot.SlotAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAccessories extends ContainerPlayer
{

	public ContainerAccessories(IEntityPlayerAether playerAetherIn)
	{
		super(((EntityPlayer)playerAetherIn.getInstance()).inventory, !playerAetherIn.getInstance().world.isRemote, (EntityPlayer) playerAetherIn.getInstance());

		for (Slot slot : this.inventorySlots)
		{
			if (slot.slotNumber > 0 && slot.slotNumber < 5)
			{
				slot.xPos += 18;
			}

			if (slot.slotNumber > 4 && slot.slotNumber < 9)
			{
				slot.xPos += 51;
			}
			else if (slot.slotNumber == 45)
			{
				slot.xPos += 39;
			}
		}

		int slotID = 0;

		for (int x = 1; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				AccessoryType type = playerAetherIn.getPlayerAether().getAccessories().type[slotID];

				this.addSlot(new SlotAccessory(playerAetherIn.getPlayerAether().getAccessories(), slotID, type, 59 + x * 18, 8 + y * 18));

				slotID++;
			}
		}
	}

}