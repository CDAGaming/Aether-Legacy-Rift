package com.legacy.aether.world.biome.feature;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FlowerFeature;

public class AetherFlowersFeature extends FlowerFeature
{

	private BlockState flowerState;

	public AetherFlowersFeature(BlockState stateIn)
	{
		this.flowerState = stateIn;
	}

	@Override
	public BlockState method_13175(Random randIn, BlockPos posIn)
	{
		return this.flowerState;
	}

	@Override
	public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorSettings> chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
		return false; // TODO : STUBBED 1.14 Function
	}
}