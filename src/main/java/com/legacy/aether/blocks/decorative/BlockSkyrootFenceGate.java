package com.legacy.aether.blocks.decorative;

import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.FenceGateBlock;

public class BlockSkyrootFenceGate extends FenceGateBlock
{

	public BlockSkyrootFenceGate()
	{
		super(FabricBlockSettings.copy(BlocksAether.skyroot_planks).build());
	}

}