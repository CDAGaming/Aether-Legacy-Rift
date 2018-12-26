package com.legacy.aether.blocks.dungeon;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPillar extends BlockRotatedPillar
{

	public BlockPillar()
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(5.0F, -1.0F).sound(SoundType.METAL));
	}

}