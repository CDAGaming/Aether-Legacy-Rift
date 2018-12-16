package com.legacy.aether.blocks;

import java.util.Random;

import com.legacy.aether.entities.block.EntityFloatingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockFloating extends Block
{

	public static boolean floatInstantly;

	private boolean constantlyPowered;

	public BlockFloating(Settings properties, boolean constantlyPowered)
	{
		super(properties.needsRandomTick());

		this.constantlyPowered = constantlyPowered;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos posIn, BlockState stateIn, LivingEntity entityIn, ItemStack itemIn)
	{
		worldIn.getBlockTickScheduler().schedule(posIn, this, this.tickRate(worldIn));
	}

	@Override
    public BlockState updatePostPlacement(BlockState stateIn, EnumFacing facingIn, BlockState neighborIn, IWorld worldIn, BlockPos posIn, BlockPos neighborPosIn)
    {
		worldIn.getBlockTickScheduler().schedule(posIn, this, this.tickRate(worldIn));

    	return super.updatePostPlacement(stateIn, facingIn, neighborIn, worldIn, posIn, neighborPosIn);
    }

	@Override
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
		if (!worldIn.isRemote)
		{
			if (this.constantlyPowered || (!this.constantlyPowered && worldIn.isBlockPowered(posIn)))
			{
				this.checkFloatable(worldIn, posIn);
			}
		}
	}

	private void checkFloatable(World worldIn, BlockPos posIn)
	{
		if (canFloatThrough(worldIn.getBlockState(posIn.up())) && posIn.getY() <= worldIn.getActualHeight())
		{
            if (!floatInstantly && worldIn.isAreaLoaded(posIn.add(-32, -32, -32), posIn.add(32, 32, 32)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFloatingBlock floatingBlock = new EntityFloatingBlock(worldIn, (double)posIn.getX() + 0.5D, (double)posIn.getY(), (double)posIn.getZ() + 0.5D, worldIn.getBlockState(posIn));

                    this.onStartFloating(floatingBlock);

                    worldIn.spawnEntity(floatingBlock);
                }
            }
            else
            {
                if (worldIn.getBlockState(posIn).getBlock() == this)
                {
                    worldIn.isAir(posIn);
                }

                BlockPos blockpos;

                for (blockpos = posIn.up(); canFloatThrough(worldIn.getBlockState(blockpos)) && blockpos.getY() <= worldIn.getActualHeight(); blockpos = blockpos.up())
                {
                    ;
                }

                if (blockpos.getY() < worldIn.getHeight())
                {
                    worldIn.setBlockState(blockpos.up(), this.getDefaultState());
                }
            }
		}
	}

    public void onStartFloating(EntityFloatingBlock entityIn)
    {

    }

    public void onStopFloating(World worldIn, BlockPos posIn, BlockState stateIn, BlockState ceilingStateIn)
    {

    }

	@Override
	public int tickRate(IWorldReaderBase worldIn)
	{
		return 2;
	}

	public static boolean canFloatThrough(BlockState state)
	{
		return state.isAir() || state.getBlock() == Blocks.FIRE || state.getMaterial().isLiquid();
	}

}