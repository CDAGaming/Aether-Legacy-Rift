package com.legacy.aether.world.gen.chunk;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class AetherChunkGeneratorType extends ChunkGeneratorType<AetherChunkGeneratorSettings, AetherChunkGenerator>
{

	public AetherChunkGeneratorType()
	{
		super(null, true, AetherChunkGeneratorSettings::new);
	}

	@Override
	public AetherChunkGenerator create(World worldIn, BiomeSource biomeSourceIn, AetherChunkGeneratorSettings settingsIn)
	{
		return new AetherChunkGenerator(worldIn, biomeSourceIn, settingsIn);
	}

}