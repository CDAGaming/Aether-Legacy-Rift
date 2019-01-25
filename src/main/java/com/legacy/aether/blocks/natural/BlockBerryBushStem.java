package com.legacy.aether.blocks.natural;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.Material;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockBerryBushStem extends PlantBlock implements Fertilizable
{

	public BlockBerryBushStem()
	{
		super(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().hardness(2.0F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (world.isClient)
		{
			return;
		}

		super.onScheduledTick(state, world, pos, random);

		if (world.method_8602(pos.up()) >= 9 && random.nextInt(60) == 0)
		{
			world.setBlockState(pos, BlocksAether.berry_bush.getDefaultState());
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState stateIn, BlockView blockViewIn, BlockPos posIn, VerticalEntityPosition verticalPosition)
	{
		return VoxelShapes.cube(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
	}

	@Override
	public boolean isFertilizable(BlockView var1, BlockPos var2, BlockState var3, boolean var4)
	{
		return true;
	}

	@Override
	public boolean canGrow(World var1, Random var2, BlockPos var3, BlockState var4)
	{
		return true;
	}

	@Override
	public void grow(World var1, Random var2, BlockPos var3, BlockState var4)
	{
		var1.setBlockState(var3, BlocksAether.berry_bush.getDefaultState());
	}

}