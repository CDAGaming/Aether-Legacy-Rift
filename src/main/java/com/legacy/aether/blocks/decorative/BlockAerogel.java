package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

public class BlockAerogel extends Block
{

	public BlockAerogel()
	{
		super(FabricBlockSettings.of(Material.EARTH).strength(1.0F, 2000.0F).sounds(BlockSoundGroup.METAL).build());
	}

	@Override
	public int getBlockBrightness(BlockState stateIn, ExtendedBlockView blockReaderIn, BlockPos posIn)
	{
		return 3;
	}

	@Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

}