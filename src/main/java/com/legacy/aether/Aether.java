package com.legacy.aether;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.container.ContainerFactoryAether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.AetherMoaTypes;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.network.ServerNetworkAether;
import com.legacy.aether.sounds.SoundsAether;
import com.legacy.aether.world.WorldAether;

public class Aether implements ModInitializer
{

	public static Identifier locate(String location)
	{
		return new Identifier("aether_legacy", location);
	}

	@Override
	public void onInitialize()
	{
		SoundsAether.registerSounds();
		ItemsAether.registerItems();

		BlocksAether.registerBlocks();
		BlocksAether.registerItems();

		EntityTypesAether.registerRenderers();
		EntityTypesAether.registerEntityTypes();
		EntityTypesAether.registerItems();

		AetherMoaTypes.initialization();
		WorldAether.registerWorld();

		ContainerFactoryAether.registerContainers();
		ServerNetworkAether.initializePacketHandler();
	}

}