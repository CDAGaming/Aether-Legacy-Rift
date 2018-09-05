package com.legacy.aether.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.IInteractionObject;

import org.dimdev.rift.listener.client.GameGuiAdder;

import com.legacy.aether.client.gui.container.GuiAccessories;
import com.legacy.aether.client.gui.container.GuiEnchanter;
import com.legacy.aether.client.gui.container.GuiFreezer;
import com.legacy.aether.player.IPlayerAether;

public class ClientGuiHandler implements GameGuiAdder
{

	@Override
	public void displayGui(EntityPlayerSP playerIn, String nameIn, IInteractionObject objectIn) 
	{

	}

	@Override
	public void displayContainerGui(EntityPlayerSP playerIn, String nameIn, IInventory inventoryIn)
	{
		if ("aether_legacy:enchanter".equals(nameIn))
		{
			Minecraft.getInstance().displayGuiScreen(new GuiEnchanter(playerIn.inventory, inventoryIn));
		}
		else if ("aether_legacy:freezer".equals(nameIn))
		{
			Minecraft.getInstance().displayGuiScreen(new GuiFreezer(playerIn.inventory, inventoryIn));
		}
		else if ("aether_legacy:accessories".equals(nameIn))
		{
			Minecraft.getInstance().displayGuiScreen(new GuiAccessories(((IPlayerAether)playerIn)));
		}
	}

}