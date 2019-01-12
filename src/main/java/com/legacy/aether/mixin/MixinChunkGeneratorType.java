package com.legacy.aether.mixin;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import org.spongepowered.asm.mixin.Mixin;

import com.legacy.aether.world.gen.chunk.AetherChunkGenerator;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorSettings;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorType;

@Mixin(ChunkGeneratorType.class)
public class MixinChunkGeneratorType
{

	@SuppressWarnings("unused")
	private static final ChunkGeneratorType<AetherChunkGeneratorSettings, AetherChunkGenerator> AETHER_ISLANDS = Registry.register(Registry.CHUNK_GENERATOR_TYPE, "aether_islands", new AetherChunkGeneratorType());
}