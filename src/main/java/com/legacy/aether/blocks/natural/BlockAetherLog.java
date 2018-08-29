package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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
		super(MapColor.WOOD, Block.Builder.create(Material.WOOD).hardnessAndResistance(2.0F, -1.0F).soundType(SoundType.WOOD));

		this.setDefaultState(this.getDefaultState().withProperty(DOUBLE_DROP, true).withProperty(AXIS, EnumFacing.Axis.Y));
	}

	@Override
	public void addPropertiesToBuilder(StateContainer.Builder<Block, IBlockState> propertyBuilderIn)
	{
		super.addPropertiesToBuilder(propertyBuilderIn);

		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public IBlockState getBlockToPlaceOnUse(BlockItemUseContext context)
	{
		return super.getBlockToPlaceOnUse(context).withProperty(DOUBLE_DROP, false);
	}

}