package com.legacy.aether.blocks.natural.ore;

import java.util.Random;

import com.legacy.aether.item.ItemsAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockAmbrosiumOre extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAmbrosiumOre()
	{
		super(Block.Settings.of(Material.EARTH).strength(3.0F, 5.0F).sound(SoundType.STONE));

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public IItemProvider getItemDropped(BlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return ItemsAether.ambrosium_shard;
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public void dropBlockAsItemWithChance(BlockState stateIn, World worldIn, BlockPos posIn, float chanceIn, int fortuneIn)
	{
		super.dropBlockAsItemWithChance(stateIn, worldIn, posIn, chanceIn, fortuneIn);

		super.dropXpOnBlockBreak(worldIn, posIn, MathHelper.getInt(new Random().toString(), 0, 2));
	}

	@Override
    public int getItemsToDropCount(BlockState stateIn, int fortuneIn, World worldIn, BlockPos posIn, Random randomIn)
    {
        if (fortuneIn > 0)
        {
            int i = randomIn.nextInt(fortuneIn + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.getItemsToDropCount(stateIn, randomIn) * (i + 1);
        }
        else
        {
            return this.getItemsToDropCount(stateIn, randomIn);
        }
    }

	public int getItemsToDropCount(BlockState stateIn, Random randIn)
	{
		return stateIn.get(DOUBLE_DROP) ? 2 : 1;
	}

}