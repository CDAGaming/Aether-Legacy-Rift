package com.legacy.aether.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockZanite extends Block
{

	public BlockZanite()
	{
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F, -1.0F).sound(SoundType.METAL));
	}

}