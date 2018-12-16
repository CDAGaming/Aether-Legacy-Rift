package com.legacy.aether.blocks.natural.ore;

import net.minecraft.block.Block;

import com.legacy.aether.blocks.BlockFloating;
import net.minecraft.block.Material;

public class BlockGravititeOre extends BlockFloating
{

	public BlockGravititeOre()
	{
		super(Block.Settings.of(Material.EARTH).strength(3.0F, 5.0F).sound(SoundType.STONE), true);
	}

}