package com.legacy.aether.client.gui;

import net.fabricmc.fabric.api.client.gui.GuiProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerFactory;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.TreasureChestBlockEntity;
import com.legacy.aether.client.gui.container.GuITreasureChest;
import com.legacy.aether.client.gui.container.GuiAccessories;

public class GuiFactoryAether implements ContainerFactory<ContainerGui>
{

	private static final GuiFactoryAether INSTANCE = new GuiFactoryAether();

	public static void registerGUIs()
	{
		GuiProviderRegistry.INSTANCE.registerFactory(Aether.locate("accessories"), INSTANCE);
		GuiProviderRegistry.INSTANCE.registerFactory(Aether.locate("treasure_chest"), INSTANCE);
	}

	@Override
	public ContainerGui create(Identifier identifierIn, PlayerEntity playerIn, PacketByteBuf byteBuf)
	{
		String guiName = identifierIn.getPath();

		if ("accessories".equals(guiName))
		{
			return new GuiAccessories(playerIn);
		}
		else if ("treasure_chest".equals(guiName))
		{
			return new GuITreasureChest(playerIn.inventory, (TreasureChestBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}

		return null;
	}

}