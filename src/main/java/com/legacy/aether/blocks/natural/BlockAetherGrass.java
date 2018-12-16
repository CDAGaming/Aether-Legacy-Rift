package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherGrass extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAetherGrass() 
	{
		super(Block.Settings.of(Material.ORGANIC).needsRandomTick().strength(0.2F, -1.0F).sound(SoundType.PLANT));

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public IItemProvider getItemDropped(BlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return BlocksAether.aether_dirt;
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		return super.getStateForPlacement(context).with(DOUBLE_DROP, false);
	}

	@Override
	public int quantityDropped(BlockState stateIn, Random randIn)
	{
		return stateIn.get(DOUBLE_DROP) ? 2 : 1;
	}
 
	@Override
	public void tick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
		if (worldIn.isRemote)
		{
			return;
		}

		if (worldIn.getLight(posIn.up()) < 4)
		{
			for (int i = 0; i < 4; ++i)
			{
                BlockPos blockpos = posIn.add(randIn.nextInt(3) - 1, randIn.nextInt(5) - 3, randIn.nextInt(3) - 1);
                BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                {
                    return;
                }

                if (iblockstate1.getBlock() == BlocksAether.aether_dirt && worldIn.getLight(blockpos.up()) >= 4)
                {
                    boolean shouldContainDoubleDrop = iblockstate1.get(BlockAetherDirt.DOUBLE_DROP);
                    worldIn.setBlockState(blockpos, BlocksAether.aether_grass.getDefaultState().with(DOUBLE_DROP, shouldContainDoubleDrop));
                	return;
                }
			}
		}
	}

}