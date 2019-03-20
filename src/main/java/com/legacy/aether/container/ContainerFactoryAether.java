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
import com.legacy.aether.blocks.entity.AetherBlockEntity;

public class ContainerFactoryAether implements ContainerFactory<Container>
{

	private static final ContainerFactoryAether INSTANCE = new ContainerFactoryAether();

	public static void registerContainers()
	{
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("accessories"), INSTANCE);
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("enchanter"), INSTANCE);
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("freezer"), INSTANCE);
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("incubator"), INSTANCE);
		ContainerProviderRegistry.INSTANCE.registerFactory(Aether.locate("treasure_chest"), INSTANCE);
	}

	@Override
	public Container create(int syncId, Identifier identifierIn, PlayerEntity playerIn, PacketByteBuf byteBuf) 
	{
		String containerName = identifierIn.getPath();

		if ("accessories".equals(containerName))
		{
			return new ContainerAccessories(syncId, playerIn);
		}
		else if ("enchanter".equals(containerName))
		{
			return new ContainerEnchanter(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("freezer".equals(containerName))
		{
			return new ContainerFreezer(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("incubator".equals(containerName))
		{
			return new ContainerIncubator(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("treasure_chest".equals(containerName))
		{
			return GenericContainer.method_19245(syncId, playerIn.inventory, (Inventory) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}

		return null;
	}

}