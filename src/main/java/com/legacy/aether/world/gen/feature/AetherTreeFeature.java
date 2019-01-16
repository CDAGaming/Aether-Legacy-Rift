package com.legacy.aether.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import com.legacy.aether.blocks.BlocksAether;

public abstract class AetherTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig>
{

	public AetherTreeFeature()
	{
		super(DefaultFeatureConfig::deserialize, true);
	}

	protected static boolean canGrowInto(BlockState state)
	{
		Block block = state.getBlock();

		return state.isAir() || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.skyroot_leaves || block == BlocksAether.golden_oak_leaves;
	}

	@Override
	protected void setToDirt(ModifiableTestableWorld worldIn, BlockPos posIn)
	{
		if (worldIn.test(posIn, (state) -> state.getBlock() != BlocksAether.aether_dirt))
		{
			this.setBlockState(worldIn, posIn, BlocksAether.aether_dirt.getDefaultState());
		}
	}

}