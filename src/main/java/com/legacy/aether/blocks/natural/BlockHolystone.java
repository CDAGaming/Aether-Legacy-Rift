package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHolystone extends Block {

    public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

    public BlockHolystone() {
        super(Block.Builder.create(Material.ROCK).hardnessAndResistance(0.5F, -1.0F).sound(SoundType.STONE));

        this.setDefaultState(this.getDefaultState().withProperty(DOUBLE_DROP, true));
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
    public int getItemsToDropCount(IBlockState stateIn, int fortuneIn, World worldIn, BlockPos posIn, Random randomIn) {
        return stateIn.getValue(DOUBLE_DROP) ? 2 : 1;
    }

}