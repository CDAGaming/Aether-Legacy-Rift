package com.legacy.aether.world;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.legacy.aether.Aether;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.impl.registry.BootstrapBiomeRegistryListener;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

import org.dimdev.rift.listener.BiomeAdder;
import org.dimdev.rift.listener.DimensionTypeAdder;

import com.legacy.aether.world.biome.AetherBiome;

public class WorldAether implements BiomeAdder, DimensionTypeAdder
{

	public static AetherBiome aetherBiome;

	public static DimensionType AETHER;

	@Override
	public void registerDimensionTypes()
	{
		//Set<DimensionType> dimensions = new TreeSet<>();

		//AETHER; // TODO
		Registry.set(Registry.DIMENSION, 12, "_aether", AETHER);

		//dimensions.add(AETHER);
	}

	@Override
	public void registerBiomes()
	{
		aetherBiome = new AetherBiome();

		Registry.set(Registry.BIOME, 525, "aether_legacy:aether_highlands", aetherBiome);
	}

	@Override
	public Collection<Biome> getOverworldBiomes()
	{
		return Collections.emptyList();
	}

}