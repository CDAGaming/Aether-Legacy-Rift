package com.legacy.aether.world.biome.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;

import java.util.Random;

public class AetherFlowersFeature extends AbstractFlowersFeature {

    private IBlockState flowerState;

    public AetherFlowersFeature(IBlockState stateIn) {
        this.flowerState = stateIn;
    }

    @Override
    public IBlockState func_202355_a(Random randIn, BlockPos posIn) {
        return this.flowerState;
    }

}