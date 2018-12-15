package com.legacy.aether.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;

import com.legacy.aether.client.gui.container.GuiAccessories;
import com.legacy.aether.client.gui.container.GuiEnchanter;
import com.legacy.aether.client.gui.container.GuiFreezer;
import com.legacy.aether.player.IEntityPlayerAether;

public class ClientGuiHandler implements GameGuiAdder
{

	@Override
	public void displayGui(PlayerEntity playerIn, String nameIn, IInteractionObject objectIn)
	{

	}

	@Override
	public void displayContainerGui(PlayerEntity playerIn, String nameIn, Inventory inventoryIn)
	{
		if ("aether_legacy:enchanter".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiEnchanter(playerIn.inventory, inventoryIn));
		}
		else if ("aether_legacy:freezer".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiFreezer(playerIn.inventory, inventoryIn));
		}
		else if ("aether_legacy:accessories".equals(nameIn))
		{
			MinecraftClient.getInstance().openGui(new GuiAccessories(((IEntityPlayerAether)playerIn)));
		}
	}

}