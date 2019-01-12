package com.legacy.aether.world;

import com.legacy.aether.Aether;
import com.legacy.aether.world.biome.AetherHighlandsBiome;
import com.legacy.aether.world.gen.chunk.AetherChunkGenerator;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorSettings;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class WorldAether
{

	public static final DimensionType THE_AETHER = Registry.DIMENSION.get(new Identifier("the_aether"));

	public static AetherHighlandsBiome AETHER_HIGHLANDS;

	@SuppressWarnings("unchecked")
	public static final ChunkGeneratorType<AetherChunkGeneratorSettings, AetherChunkGenerator> AETHER_ISLANDS = (ChunkGeneratorType<AetherChunkGeneratorSettings, AetherChunkGenerator>) Registry.CHUNK_GENERATOR_TYPE.get(new Identifier("aether_islands"));

	public static void registerWorld()
	{
		AETHER_HIGHLANDS = Registry.register(Registry.BIOME, Aether.locate("aether_highlands"), new AetherHighlandsBiome());
	}
}