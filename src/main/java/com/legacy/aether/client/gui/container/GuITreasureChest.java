package com.legacy.aether.client.gui.container;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.container.ContainerScreen54;
import net.minecraft.container.GenericContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.TranslatableTextComponent;

import com.legacy.aether.blocks.entity.TreasureChestBlockEntity;

public class GuITreasureChest extends ContainerScreen54
{

	public GuITreasureChest(int syncId, PlayerInventory playerInventory, TreasureChestBlockEntity inventory)
	{
		super(GenericContainer.createGeneric9x3(syncId, playerInventory, inventory), playerInventory, getTextComponent(inventory));
	}

	@Override
	public void initialize(MinecraftClient client, int width, int height)
	{
		super.initialize(client, width, height);

		client.player.container = this.container;
	}

	private static TranslatableTextComponent getTextComponent(TreasureChestBlockEntity inventory)
	{
		if (inventory.getDungeonType() == 0)
		{
			return new TranslatableTextComponent("aether_legacy.treasure_chest.bronze", new Object[0]);
		}
		else if (inventory.getDungeonType() == 1)
		{
			return new TranslatableTextComponent("aether_legacy.treasure_chest.silver", new Object[0]);
		}
		else if (inventory.getDungeonType() == 2)
		{
			return new TranslatableTextComponent("aether_legacy.treasure_chest.golden", new Object[0]);
		}

		return new TranslatableTextComponent("aether_legacy.treasure_chest.platinum", new Object[0]); 
	}

}