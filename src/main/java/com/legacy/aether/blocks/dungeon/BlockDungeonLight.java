package com.legacy.aether.blocks.dungeon;

import net.minecraft.block.BlockState;

public class BlockDungeonLight extends BlockDungeon
{

	public BlockDungeonLight(boolean isLocked)
	{
		super(isLocked);
	}

	@Override
	public int getLuminance(BlockState state)
	{
		return state.getBlock() == this ? 11 : state.getLuminance();
	}

}