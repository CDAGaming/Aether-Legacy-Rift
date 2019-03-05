package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.FenceBlock;

import com.legacy.aether.blocks.BlocksAether;

public class BlockSkyrootFence extends FenceBlock
{

	public BlockSkyrootFence()
	{
		super(FabricBlockSettings.copy(BlocksAether.skyroot_planks).build());
	}

}