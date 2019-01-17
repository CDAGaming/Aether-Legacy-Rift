package com.legacy.aether.blocks.decorative;

import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class BlockAetherStairs extends StairsBlock
{

	public BlockAetherStairs(BlockState state)
	{
		super(state, FabricBlockSettings.copy(state.getBlock()).build());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean skipRenderingSide(BlockState blockState_1, BlockState blockState_2, Direction direction_1)
	{
		return this.isAerogel() && blockState_2.getBlock() == this && blockState_2.get(FACING) == blockState_1.get(FACING) ? true : super.skipRenderingSide(blockState_1, blockState_2, direction_1);
	}

	@Override
	public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return this.isAerogel();
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
        return this.isAerogel() ? BlockRenderLayer.TRANSLUCENT : super.getRenderLayer();
    }

	private boolean isAerogel()
	{
		return this == BlocksAether.aerogel_stairs;
	}

}
