package com.legacy.aether.world;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

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
	public Set<? extends DimensionType> getDimensionTypes()
	{
		Set<DimensionType> dimensions = new TreeSet<>();

		AETHER = DimensionTypeAdder.newDimensionType(12, "aether", "_aether", AetherDimension::new);

		dimensions.add(AETHER);

		return dimensions;
	}

	@Override
	public void registerBiomes()
	{
		aetherBiome = new AetherBiome();

		Biome.registerBiome(525, "aether_legacy:aether_highlands", aetherBiome);
	}

	@Override
	public Collection<Biome> getOverworldBiomes()
	{
		return Collections.emptyList();
	}

}