package com.legacy.aether.blocks.natural;

import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAetherGrass extends Block {

    public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

    public BlockAetherGrass() {
        super(Block.Builder.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.2F, -1.0F).sound(SoundType.PLANT));

        this.setDefaultState(this.getDefaultState().withProperty(DOUBLE_DROP, true));
    }

    @Override
    public IItemProvider getItemDropped(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune) {
        return BlocksAether.aether_dirt;
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, IBlockState> propertyBuilderIn) {
        propertyBuilderIn.add(DOUBLE_DROP);
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).withProperty(DOUBLE_DROP, false);
    }

    @Override
    public int quantityDropped(IBlockState stateIn, Random randIn) {
        return stateIn.getValue(DOUBLE_DROP) ? 2 : 1;
    }

    @Override
    public void tick(IBlockState stateIn, World worldIn, BlockPos posIn, Random randIn) {
        if (worldIn.isRemote) {
            return;
        }

        if (worldIn.getLight(posIn.up()) < 4) {
            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = posIn.add(randIn.nextInt(3) - 1, randIn.nextInt(5) - 3, randIn.nextInt(3) - 1);
                IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
                    return;
                }

                if (iblockstate1.getBlock() == BlocksAether.aether_dirt && worldIn.getLight(blockpos.up()) >= 4) {
                    boolean shouldContainDoubleDrop = iblockstate1.getValue(BlockAetherDirt.DOUBLE_DROP);
                    worldIn.setBlockState(blockpos, BlocksAether.aether_grass.getDefaultState().withProperty(DOUBLE_DROP, shouldContainDoubleDrop));
                    return;
                }
            }
        }
    }

}