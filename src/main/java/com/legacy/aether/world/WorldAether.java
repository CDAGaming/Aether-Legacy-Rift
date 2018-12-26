package com.legacy.aether.world;

import java.util.Collection;
import java.util.Collections;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

import org.dimdev.rift.listener.BiomeAdder;
import org.dimdev.rift.listener.DimensionTypeAdder;

import com.legacy.aether.Aether;
import com.legacy.aether.world.biome.AetherBiome;

public class WorldAether implements BiomeAdder, DimensionTypeAdder
{

	public static AetherBiome aetherBiome;

	public static DimensionType AETHER;

	@Override
	public void registerDimensionTypes()
	{
		AETHER = DimensionTypeAdder.addDimensionType(12, Aether.locate("aether"), "_aether", AetherDimension::new);
	}

	@Override
	public void registerBiomes()
	{
		aetherBiome = new AetherBiome();

		Biome.register(525, "aether_legacy:aether_highlands", aetherBiome);
	}

	@Override
	public Collection<Biome> getOverworldBiomes()
	{
		return Collections.emptyList();
	}

}