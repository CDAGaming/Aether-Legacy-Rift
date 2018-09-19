package com.legacy.aether.world;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.structure.StructureIO;

import org.dimdev.rift.listener.BiomeAdder;
import org.dimdev.rift.listener.DimensionTypeAdder;

import com.legacy.aether.world.biome.AetherBiome;
import com.legacy.aether.world.biome.structure.ColdAercloudStructure;
import com.legacy.aether.world.biome.structure.components.ColdAercloudPiece;

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
		this.registerStructures();

		aetherBiome = new AetherBiome();

		Biome.registerBiome(525, "aether_legacy:aether_highlands", aetherBiome);
	}

	private void registerStructures() 
	{
		StructureIO.registerStructure(ColdAercloudStructure.Start.class, "aether_legacy:cold_aercloud_start");

		StructureIO.registerStructureComponent(ColdAercloudPiece.class, "aether_legacy:cold_aercloud_piece");
	}

	@Override
	public Collection<Biome> getOverworldBiomes()
	{
		return Collections.emptyList();
	}

}