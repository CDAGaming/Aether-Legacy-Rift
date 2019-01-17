package com.legacy.aether.blocks.natural;

import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockBerryBush extends PlantBlock
{

	public BlockBerryBush()
	{
		super(FabricBlockSettings.of(Material.PLANT).hardness(2.0F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public boolean isTranslucent(BlockState stateIn, BlockView blockViewIn, BlockPos posIn)
	{
		return true;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState stateIn, BlockView blockViewIn, BlockPos posIn, VerticalEntityPosition verticalPosIn)
	{
		return VoxelShapes.cube(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}

	@Override
	protected boolean canPlantOnTop(BlockState stateIn, BlockView blockViewIn, BlockPos posIn)
	{
		Block block = stateIn.getBlock();

		return block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || super.canPlantOnTop(stateIn, blockViewIn, posIn);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean skipRenderingSide(BlockState blockState_1, BlockState blockState_2, Direction direction_1)
	{
		return false;
	}
}