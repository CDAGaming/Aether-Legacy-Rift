package com.legacy.aether.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAmbrosiumTorch extends BlockTorch
{

	public BlockAmbrosiumTorch()
	{
		super(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().needsRandomTick().lightValue(14).sound(SoundType.WOOD));
	}

}