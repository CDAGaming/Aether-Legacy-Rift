package com.legacy.aether.world.info;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.ChunkGenSettings;

import com.legacy.aether.blocks.BlocksAether;

public class AetherGenSettings extends ChunkGenSettings
{

	@Override
	public IBlockState getDefaultBlock()
	{
		return BlocksAether.holystone.getDefaultState();
	}

	@Override
	public IBlockState getDefaultFluid() 
	{
		return Blocks.WATER.getDefaultState();
	}

}