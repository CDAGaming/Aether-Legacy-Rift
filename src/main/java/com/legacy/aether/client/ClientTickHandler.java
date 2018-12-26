package com.legacy.aether.client;

import com.legacy.aether.client.gui.container.GuiAccessories;
import com.legacy.aether.client.gui.container.GuiEnchanter;
import com.legacy.aether.client.gui.container.GuiFreezer;
import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;

public class ClientTickHandler
{
	public static PlayerEntity currentPlayer;
	public static MinecraftClient instance;
	public static String requestedGUIName;

	public static void clientTick()
	{
		instance = MinecraftClient.getInstance();
		currentPlayer = instance.player;

		if (requestedGUIName != null) {
			displayContainerGui(currentPlayer, requestedGUIName, currentPlayer.inventory);
		}
	}

	public static void displayContainerGui(PlayerEntity playerIn, String nameIn, Inventory inventoryIn)
	{
		if ("enchanter".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiEnchanter(playerIn.inventory, inventoryIn));
		}
		else if ("freezer".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiFreezer(playerIn.inventory, inventoryIn));
		}
		else if ("accessories".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiAccessories(playerIn));
		}
		requestedGUIName = null;
	}

}