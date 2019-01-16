package com.legacy.aether.blocks.decorative;

import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.FenceBlock;

public class BlockSkyrootFence extends FenceBlock
{

	public BlockSkyrootFence()
	{
		super(FabricBlockSettings.copy(BlocksAether.skyroot_planks).build());
	}

}