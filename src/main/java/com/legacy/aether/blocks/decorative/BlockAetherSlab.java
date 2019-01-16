package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;

public class BlockAetherSlab extends SlabBlock
{

	public BlockAetherSlab(BlockState state)
	{
		super(FabricBlockSettings.copy(state.getBlock()).build());
	}

}