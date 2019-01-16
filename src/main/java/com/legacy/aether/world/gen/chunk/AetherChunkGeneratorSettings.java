package com.legacy.aether.world.gen.chunk;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

import com.legacy.aether.blocks.BlocksAether;

public class AetherChunkGeneratorSettings extends ChunkGeneratorConfig
{

	public AetherChunkGeneratorSettings()
	{
		this.defaultBlock = BlocksAether.holystone.getDefaultState();
		this.defaultFluid = Blocks.AIR.getDefaultState();
	}

}