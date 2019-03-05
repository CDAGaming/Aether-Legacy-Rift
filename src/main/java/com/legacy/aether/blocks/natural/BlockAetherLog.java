package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

public class BlockAetherLog extends LogBlock
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAetherLog() 
	{
		super(MaterialColor.WOOD, FabricBlockSettings.of(Material.WOOD).strength(2.0F, -1.0F).sounds(BlockSoundGroup.WOOD).build());

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true).with(AXIS, Direction.Axis.Y));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		super.appendProperties(propertyBuilderIn);

		propertyBuilderIn.with(DOUBLE_DROP);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context)
	{
		return super.getPlacementState(context).with(DOUBLE_DROP, false);
	}

}