package com.legacy.aether.blocks.natural;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ShapeUtils;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockAetherFlower extends BlockBush
{

	public BlockAetherFlower() 
	{
		super(Builder.create(Material.PLANTS).sound(SoundType.PLANT));
	}

	@Override
	public boolean isValidGround(IBlockState stateIn, IBlockReader worldIn, BlockPos posIn)
	{
		return stateIn.getBlock() == BlocksAether.aether_grass || stateIn.getBlock() == BlocksAether.aether_dirt || stateIn.getBlock() == Blocks.GRASS || stateIn.getBlock() == Blocks.DIRT;
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState blockstateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return ShapeUtils.create(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
	}

	@Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

}