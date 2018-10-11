package com.legacy.aether.world.info;

import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.ChunkGenSettings;

public class AetherGenSettings extends ChunkGenSettings {

    @Override
    public IBlockState getDefaultBlock() {
        return BlocksAether.holystone.getDefaultState();
    }

    @Override
    public IBlockState getDefaultFluid() {
        return Blocks.WATER.getDefaultState();
    }

}