package com.legacy.aether.container;

import net.fabricmc.fabric.api.container.ContainerFactory;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.Container;
import net.minecraft.container.GenericContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;

public class ContainerFactoryAether implements ContainerFactory<Container>
{

	private static final ContainerFactoryAether INSTANCE = new ContainerFactoryAether();

	public static void registerContainers()
	{
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("accessories"), INSTANCE);
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("treasure_chest"), INSTANCE);
	}

	@Override
	public Container create(Identifier identifierIn, PlayerEntity playerIn, PacketByteBuf byteBuf) 
	{
		String containerName = identifierIn.getPath();

		if ("accessories".equals(containerName))
		{
			return new ContainerAccessories(playerIn);
		}
		else if ("treasure_chest".equals(containerName))
		{
			return new GenericContainer(playerIn.inventory, (Inventory) playerIn.world.getBlockEntity(byteBuf.readBlockPos()), playerIn);
		}

		return null;
	}

}