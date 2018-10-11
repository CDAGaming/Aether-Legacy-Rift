package com.legacy.aether.world.biome.feature;

import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;

public abstract class AetherTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {

    public AetherTreeFeature() {
        super(true);
    }

    protected abstract boolean generateTree(Set<BlockPos> posListIn, IWorld worldIn, Random randomIn, BlockPos posIn);

    @Override
    protected boolean canGrowInto(Block blockType) {
        return blockType.getDefaultState().isAir() || blockType == BlocksAether.aether_grass || blockType == BlocksAether.aether_dirt || blockType == BlocksAether.skyroot_leaves || blockType == BlocksAether.golden_oak_leaves;
    }

    @Override
    protected void setDirtAt(IWorld worldIn, BlockPos posIn) {
        if (worldIn.getBlockState(posIn).getBlock() != BlocksAether.aether_dirt) {
            this.setBlockState(worldIn, posIn, BlocksAether.aether_dirt.getDefaultState());
        }
    }

    @Override
    protected boolean func_208519_a(Set<BlockPos> posListIn, IWorld worldIn, Random randomIn, BlockPos posIn) {
        return this.generateTree(posListIn, worldIn, randomIn, posIn);
    }

}