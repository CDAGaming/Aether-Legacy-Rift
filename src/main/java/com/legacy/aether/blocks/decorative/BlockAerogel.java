package com.legacy.aether.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockAerogel extends BlockBreakable
{

	public BlockAerogel()
	{
		super(Block.Builder.create(Material.ROCK).hardnessAndResistance(1.0F, 2000.0F).soundType(SoundType.METAL));
	}

	@Override
	public int getOpacity(IBlockState stateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return 3;
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

}