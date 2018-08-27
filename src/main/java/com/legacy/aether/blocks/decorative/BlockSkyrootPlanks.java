package com.legacy.aether.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSkyrootPlanks extends Block
{

	public BlockSkyrootPlanks()
	{
		super(Block.Builder.create(Material.WOOD).hardnessAndResistance(2.0F, 5.0F).soundType(SoundType.WOOD));
	}

}