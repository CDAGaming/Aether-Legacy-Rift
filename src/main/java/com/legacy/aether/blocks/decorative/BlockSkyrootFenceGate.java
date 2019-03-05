package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.FenceGateBlock;

import com.legacy.aether.blocks.BlocksAether;

public class BlockSkyrootFenceGate extends FenceGateBlock
{

	public BlockSkyrootFenceGate()
	{
		super(FabricBlockSettings.copy(BlocksAether.skyroot_planks).build());
	}

}