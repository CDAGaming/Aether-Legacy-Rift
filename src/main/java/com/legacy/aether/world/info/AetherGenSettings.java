package com.legacy.aether.world.info;


import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class AetherGenSettings extends ChunkGeneratorSettings
{
	@Override
	public BlockState getDefaultBlock()
	{
		return BlocksAether.holystone.getDefaultState();
	}

	@Override
	public BlockState getDefaultFluid()
	{
		return Blocks.WATER.getDefaultState();
	}

}