package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

public class BlockHolystone extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockHolystone() 
	{
		super(Block.Builder.create(Material.ROCK).hardnessAndResistance(0.5F, -1.0F).soundType(SoundType.STONE));

		this.setDefaultState(this.getDefaultState().withProperty(DOUBLE_DROP, true));
	}

	@Override
	public void addPropertiesToBuilder(StateContainer.Builder<Block, IBlockState> propertyBuilderIn)
	{
		propertyBuilderIn.addProperties(DOUBLE_DROP);
	}

	@Override
	public IBlockState getBlockToPlaceOnUse(BlockItemUseContext context)
	{
		return super.getBlockToPlaceOnUse(context).withProperty(DOUBLE_DROP, false);
	}

	@Override
	public int getItemsToDropCount(IBlockState stateIn, Random randIn)
	{
		return stateIn.getValue(DOUBLE_DROP).booleanValue() ? 2 : 1;
	}

}