package com.legacy.aether.world.biome.builder;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.config.surfacebuilder.SurfaceConfig;

public class AetherSurfaceBuilderConfig implements SurfaceConfig
{

	@Override
	public BlockState getUnderMaterial()
	{
		return BlocksAether.aether_dirt.getDefaultState();
	}

	@Override
	public BlockState getTopMaterial()
	{
		return BlocksAether.aether_grass.getDefaultState();
	}

}