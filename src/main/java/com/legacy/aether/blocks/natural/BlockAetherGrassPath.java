package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherGrassPath extends BlockGrassPath
{

	public BlockAetherGrassPath()
	{
		super(Block.Properties.create(Material.GROUND).hardnessAndResistance(0.65F, -1.0F).sound(SoundType.PLANT));
	}

	@Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? Block.nudgeEntitiesWithNewState(this.getDefaultState(), BlocksAether.aether_dirt.getDefaultState(), context.getWorld(), context.getPos()) : this.getDefaultState();
    }

	@Override
    public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
    {
        worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, BlocksAether.aether_dirt.getDefaultState(), worldIn, pos));
    }

	@Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
    	return BlocksAether.aether_dirt;
    }

}