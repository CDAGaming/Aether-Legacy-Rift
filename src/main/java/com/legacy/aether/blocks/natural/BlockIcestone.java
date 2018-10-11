package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIcestone extends Block {

    public BlockIcestone() {
        super(Block.Builder.create(Material.ROCK).hardnessAndResistance(3.0F, -1.0F).sound(SoundType.GLASS));
    }

    @Override
    public void onBlockAdded(IBlockState stateIn, World worldIn, BlockPos posIn, IBlockState neighborStateIn) {
        for (int x = posIn.getX() - 3; x <= (posIn.getX() + 3); ++x) {
            for (int y = posIn.getY() - 3; y <= (posIn.getY() + 3); ++y) {
                for (int z = posIn.getZ() - 3; z <= (posIn.getZ() + 3); ++z) {
                    BlockPos newPos = new BlockPos(x, y, z);
                    Block block = worldIn.getBlockState(newPos).getBlock();

                    if (block == Blocks.WATER) {
                        worldIn.setBlockState(newPos, Blocks.ICE.getDefaultState());
                    } else if (block == Blocks.LAVA) {
                        worldIn.setBlockState(newPos, Blocks.OBSIDIAN.getDefaultState());
                    }
                }
            }
        }
    }

}