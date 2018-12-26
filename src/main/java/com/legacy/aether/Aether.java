package com.legacy.aether;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.ClientTickHandler;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.AetherMoaTypes;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.events.client.ClientTickEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.OverworldDimension;

public class Aether implements ModInitializer
{

	public static Identifier locate(String location)
	{
		return new Identifier("aether_legacy", location);
	}

	@Override
	public void onInitialize()
	{
		ItemsAether.registerItems();

		BlocksAether.registerBlocks();
		BlocksAether.registerItems();

		SoundsAether.registerSounds();

		EntityTypesAether.registerRenderers();
		EntityTypesAether.registerEntityTypes();
		EntityTypesAether.registerItems();

		AetherMoaTypes.initialization();

		ClientTickEvent.CLIENT.register(mineCraftClient -> ClientTickHandler.clientTick());
	}

}