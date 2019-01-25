package com.legacy.aether.blocks.decorative;

import java.util.Random;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassPathBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherGrassPath extends GrassPathBlock
{

	public BlockAetherGrassPath()
	{
		super(FabricBlockSettings.of(Material.EARTH).strength(0.65F, 0.65F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random)
	{
		world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, BlocksAether.aether_dirt.getDefaultState(), world, pos));
	}

}