package com.legacy.aether.blocks.natural.ore;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import com.legacy.aether.blocks.BlockFloating;

public class BlockGravititeOre extends BlockFloating
{

	public BlockGravititeOre()
	{
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 5.0F).sound(SoundType.STONE), true);
	}

}