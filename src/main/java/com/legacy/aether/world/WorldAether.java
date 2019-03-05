package com.legacy.aether.world;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import com.legacy.aether.Aether;
import com.legacy.aether.world.biome.AetherHighlandsBiome;
import com.legacy.aether.world.dimension.AetherDimensionType;
import com.legacy.aether.world.gen.chunk.AetherChunkGenerator;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorSettings;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorType;

public class WorldAether
{

	public static final DimensionType THE_AETHER = Registry.register(Registry.DIMENSION, 4, "aether_legacy:the_aether", new AetherDimensionType());

	public static AetherHighlandsBiome AETHER_HIGHLANDS;

	public static final ChunkGeneratorType<AetherChunkGeneratorSettings, AetherChunkGenerator> AETHER_ISLANDS = Registry.register(Registry.CHUNK_GENERATOR_TYPE, Aether.locate("aether_islands"), new AetherChunkGeneratorType());

	public static void registerWorld()
	{
		AETHER_HIGHLANDS = Registry.register(Registry.BIOME, Aether.locate("aether_highlands"), new AetherHighlandsBiome());
	}
}