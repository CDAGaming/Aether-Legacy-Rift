package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TransparentBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockAerogel extends TransparentBlock
{

	public BlockAerogel()
	{
		super(FabricBlockSettings.of(Material.EARTH).strength(1.0F, 2000.0F).sounds(BlockSoundGroup.METAL).build());
	}

	@Override
	public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return true;
	}

	@Override
	public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return false;
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

}