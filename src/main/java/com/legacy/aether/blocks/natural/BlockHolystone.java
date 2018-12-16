package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHolystone extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockHolystone() 
	{
		super(Block.Settings.of(Material.EARTH).strength(0.5F, -1.0F).sound(SoundType.STONE));

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return super.getStateForPlacement(context).with(DOUBLE_DROP, false);
	}

	@Override
	public int getItemsToDropCount(BlockState stateIn, int fortuneIn, World worldIn, BlockPos posIn, Random randomIn)
	{
		return stateIn.get(DOUBLE_DROP) ? 2 : 1;
	}

}