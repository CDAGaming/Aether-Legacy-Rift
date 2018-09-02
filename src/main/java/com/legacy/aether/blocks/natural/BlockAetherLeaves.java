package com.legacy.aether.blocks.natural;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockAetherLeaves extends BlockLeaves
{

	public BlockAetherLeaves()
	{
		super(Block.Builder.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.2F, -1.0F).sound(SoundType.PLANT));
	}

	@Override
    public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facingIn, IBlockState neighborIn, IWorld worldIn, BlockPos posIn, BlockPos neighborPosIn)
    {
        int i = getDistanceFromLog(neighborIn) + 1;

        if (i != 1 || stateIn.get(DISTANCE) != i)
        {
        	worldIn.getPendingBlockTicks().scheduleTick(posIn, this, 1);
        }

        return stateIn;
    }

    private static IBlockState setDistanceFromBlock(IBlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_)
    {
        int i = 7;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
        Throwable throwable = null;

        try
        {
            for (EnumFacing enumfacing : EnumFacing.values())
            {
                blockpos$pooledmutableblockpos.setPos(p_208493_2_).move(enumfacing);
                i = Math.min(i, getDistanceFromLog(p_208493_1_.getBlockState(blockpos$pooledmutableblockpos)) + 1);

                if (i == 1)
                {
                    break;
                }
            }
        }
        catch (Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        finally
        {
            if (blockpos$pooledmutableblockpos != null)
            {
                if (throwable != null)
                {
                    try
                    {
                        blockpos$pooledmutableblockpos.close();
                    }
                    catch (Throwable throwable1)
                    {
                        throwable.addSuppressed(throwable1);
                    }
                }
                else
                {
                    blockpos$pooledmutableblockpos.close();
                }
            }
        }

        return p_208493_0_.with(DISTANCE, i);
    }

    private static int getDistanceFromLog(IBlockState stateIn)
    {
        if (stateIn.getBlock() instanceof BlockAetherLog)
        {
            return 0;
        }
        else
        {
            return stateIn.getBlock() instanceof BlockLeaves ? stateIn.get(DISTANCE) : 7;
        }
    }

    @Override
    public void tick(IBlockState stateIn, World worldIn, BlockPos posIn, Random randomIn)
    {
    	worldIn.setBlockState(posIn, setDistanceFromBlock(stateIn, worldIn, posIn), 3);
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext contextIn)
    {
        return setDistanceFromBlock(this.getDefaultState().with(PERSISTENT, Boolean.TRUE), contextIn.getWorld(), contextIn.getPos());
    }

	@Override
	public IItemProvider getItemDropped(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return this == BlocksAether.skyroot_leaves ? BlocksAether.skyroot_sapling : BlocksAether.golden_oak_sapling;
	}

}
