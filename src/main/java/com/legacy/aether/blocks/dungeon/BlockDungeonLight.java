package com.legacy.aether.blocks.dungeon;

import net.minecraft.block.state.IBlockState;

public class BlockDungeonLight extends BlockDungeon
{

	public BlockDungeonLight(boolean isLocked) 
	{
		super(isLocked);
	}

	@Override
    public int getLightValue(IBlockState state)
    {
		return state.getBlock() == this ? 11 : state.getLightValue();
    }

}