package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class BlockAetherStairs extends StairsBlock
{

	public BlockAetherStairs(BlockState state)
	{
		super(state, FabricBlockSettings.copy(state.getBlock()).build());
	}

}
