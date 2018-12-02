package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;

public class BlockAetherLog extends BlockLog
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAetherLog() 
	{
		super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, -1.0F).sound(SoundType.WOOD));

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true).with(AXIS, EnumFacing.Axis.Y));
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, IBlockState> propertyBuilderIn)
	{
		super.fillStateContainer(propertyBuilderIn);

		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		return super.getStateForPlacement(context).with(DOUBLE_DROP, false);
	}

}