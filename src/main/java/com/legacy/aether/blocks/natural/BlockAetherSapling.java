package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

public class BlockAetherSapling extends SaplingBlock
{

	public BlockAetherSapling(SaplingGenerator generator)
	{
		super(generator, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	}

}
