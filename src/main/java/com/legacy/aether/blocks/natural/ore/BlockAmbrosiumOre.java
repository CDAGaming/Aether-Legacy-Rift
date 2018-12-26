package com.legacy.aether.blocks.natural.ore;

import java.util.Random;

import com.legacy.aether.item.ItemsAether;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockAmbrosiumOre extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockAmbrosiumOre()
	{
		super(FabricBlockSettings.of(Material.STONE).strength(3.0F, 5.0F).sounds(BlockSoundGroup.STONE).build());

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.with(DOUBLE_DROP);
	}

}