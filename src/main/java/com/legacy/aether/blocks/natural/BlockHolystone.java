package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;

public class BlockHolystone extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockHolystone() 
	{
		super(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).strength(0.5F, -1.0F).sounds(BlockSoundGroup.STONE).build());

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.with(DOUBLE_DROP);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context)
	{
		return super.getPlacementState(context).with(DOUBLE_DROP, false);
	}
}