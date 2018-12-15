package com.legacy.aether.inventory.container;

import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.inventory.slot.SlotAccessory;
import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.container.PlayerContainer;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;

public class ContainerAccessories extends PlayerContainer
{

	public ContainerAccessories(IEntityPlayerAether playerAetherIn)
	{
		super(((PlayerEntity)playerAetherIn.getInstance()).inventory, !playerAetherIn.getInstance().world.isRemote, (PlayerEntity) playerAetherIn.getInstance());

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