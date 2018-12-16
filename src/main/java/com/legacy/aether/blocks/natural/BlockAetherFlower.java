package com.legacy.aether.blocks.natural;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BlockAetherFlower extends BlockBush
{

	public BlockAetherFlower() 
	{
		super(Block.Settings.of(Material.PLANT).sound(SoundType.PLANT));
	}

	@Override
	public boolean isValidGround(BlockState stateIn, IBlockReader worldIn, BlockPos posIn)
	{
		return stateIn.getBlock() == BlocksAether.aether_grass || stateIn.getBlock() == BlocksAether.aether_dirt || stateIn.getBlock() == Blocks.GRASS || stateIn.getBlock() == Blocks.DIRT;
	}

	@Override
	public VoxelShape getShape(BlockState blockstateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return VoxelShapes.cube(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState blockstateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return VoxelShapes.create(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	}

	@Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

}