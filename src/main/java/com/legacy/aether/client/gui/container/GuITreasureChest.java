package com.legacy.aether.client.gui.container;

import com.legacy.aether.blocks.entity.TreasureChestBlockEntity;

import net.minecraft.client.gui.container.ContainerGui54;
import net.minecraft.inventory.Inventory;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class GuITreasureChest extends ContainerGui54
{

	private final Inventory playerInventory;

	private final TextComponent chestName;

	public GuITreasureChest(Inventory playerInventory, TreasureChestBlockEntity inventory)
	{
		super(playerInventory, inventory);

		if (inventory.getDungeonType() == 0)
		{
			this.chestName = new TranslatableTextComponent("aether_legacy.treasure_chest.bronze", new Object[0]);
		}
		else if (inventory.getDungeonType() == 1)
		{
			this.chestName = new TranslatableTextComponent("aether_legacy.treasure_chest.silver", new Object[0]);
		}
		else if (inventory.getDungeonType() == 2)
		{
			this.chestName = new TranslatableTextComponent("aether_legacy.treasure_chest.golden", new Object[0]);
		}
		else
		{
			this.chestName = new TranslatableTextComponent("aether_legacy.treasure_chest.platinum", new Object[0]); 
		}

		this.playerInventory = playerInventory;
	}

	@Override
	protected void drawForeground(int par1, int par2)
	{
		this.fontRenderer.draw(this.chestName.getFormattedText(), 8.0F, 6.0F, 4210752);
		this.fontRenderer.draw(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.containerHeight - 96 + 2), 4210752);
	}

}