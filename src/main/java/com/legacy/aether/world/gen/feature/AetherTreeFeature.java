package com.legacy.aether.world.gen.feature;

import java.util.Random;
import java.util.Set;

import net.minecraft.class_3747;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.config.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import com.legacy.aether.blocks.BlocksAether;

public abstract class AetherTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig>
{

	public AetherTreeFeature()
	{
		super(DefaultFeatureConfig::deserialize, true);
	}

	protected abstract boolean generateTree(Set<BlockPos> posListIn, class_3747 worldIn, Random randomIn, BlockPos posIn);

	protected static boolean canGrowInto(BlockState state)
	{
		Block block = state.getBlock();

		return state.isAir() || block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.skyroot_leaves || block == BlocksAether.golden_oak_leaves;
	}

	@Override
	protected void method_16427(class_3747 worldIn, BlockPos posIn)
	{
		if (worldIn.method_16358(posIn, (state) -> state.getBlock() != BlocksAether.aether_grass))
		{
			this.addBlockState(worldIn, posIn, BlocksAether.aether_dirt.getDefaultState());
		}
	}

	@Override
	protected boolean method_12775(Set<BlockPos> var1, class_3747 var2, Random var3, BlockPos var4)
	{
		return this.generateTree(var1, var2, var3, var4);
	}

}