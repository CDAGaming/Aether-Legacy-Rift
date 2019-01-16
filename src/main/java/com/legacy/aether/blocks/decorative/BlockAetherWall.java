package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;

public class BlockAetherWall extends WallBlock
{

	public BlockAetherWall(BlockState state)
	{
		super(FabricBlockSettings.copy(state.getBlock()).build());
	}

}