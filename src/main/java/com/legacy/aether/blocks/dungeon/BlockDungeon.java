package com.legacy.aether.blocks.dungeon;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockDungeon extends Block
{

	public BlockDungeon(boolean isLocked)
	{
		super(FabricBlockSettings.of(Material.STONE).hardness(isLocked ? -1.0F : 0.5F).resistance(isLocked ? 6000000.0F : 1.0F).sounds(BlockSoundGroup.STONE).build());
	}

}
