package com.legacy.aether.blocks.dungeon;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockDungeonLight extends BlockDungeon
{

	public BlockDungeonLight(boolean isLocked)
	{
		super(FabricBlockSettings.of(Material.STONE).hardness(isLocked ? -1.0F : 0.5F).lightLevel(11).resistance(isLocked ? 6000000.0F : 1.0F).sounds(BlockSoundGroup.STONE).build());
	}

}