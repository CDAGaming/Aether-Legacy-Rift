package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;

public class BlockAetherLeaves extends LeavesBlock
{

	public BlockAetherLeaves(MaterialColor color)
	{
		super(FabricBlockSettings.of(Material.LEAVES, color).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());
	}

}