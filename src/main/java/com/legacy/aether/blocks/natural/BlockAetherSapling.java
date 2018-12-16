package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class BlockAetherSapling extends BlockAetherFlower implements IGrowable
{

	private AbstractTree tree;

	public BlockAetherSapling(AbstractTree tree)
	{
		super();

		this.tree = tree;
	}

	@Override
	public void tick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
		if (!worldIn.isRemote)
		{
			super.tick(stateIn, worldIn, posIn, randIn);

			if (!worldIn.isAreaLoaded(posIn, 1)) return;
			if (worldIn.getLight(posIn.up()) >= 9 && randIn.nextInt(30) == 0)
			{
				this.growTree(worldIn, posIn, stateIn, randIn);
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState blockstateIn, IBlockReader blockReaderIn, BlockPos posIn)
	{
		return VoxelShapes.create(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos posIn, BlockState stateIn, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random randIn, BlockPos posIn, BlockState stateIn)
	{
		return true;
	}

	@Override
	public void grow(World worldIn, Random randIn, BlockPos posIn, BlockState stateIn)
	{
		if (worldIn.random.nextFloat() < 0.45D)
        {
            this.growTree(worldIn, posIn, stateIn, randIn);
        }
		
	}

	public void growTree(World worldIn, BlockPos posIn, BlockState stateIn, Random randIn)
	{
		this.tree.spawn(worldIn, posIn, stateIn, randIn);
	}

}