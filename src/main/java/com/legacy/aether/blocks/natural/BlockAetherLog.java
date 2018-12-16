package com.legacy.aether.blocks.natural;

import net.minecraft.block.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

public class BlockAetherLog extends LogBlock
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAetherLog() 
	{
		super(MaterialColor.WOOD, Block.Settings.of(Material.WOOD).strength(2.0F, -1.0F).sound(SoundType.WOOD));

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true).with(AXIS, Direction.Axis.Y));
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> propertyBuilderIn)
	{
		super.fillStateContainer(propertyBuilderIn);

		propertyBuilderIn.add(DOUBLE_DROP);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return super.getStateForPlacement(context).with(DOUBLE_DROP, false);
	}

}