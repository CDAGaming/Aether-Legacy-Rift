package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.legacy.aether.blocks.BlocksAether;

@Mixin(PlantBlock.class)
public class MixinBlockBush 
{

	/**
	 * @author Modding Legacy
	 */
	@Overwrite
	public boolean canPlantOnTop(BlockState stateIn, BlockView readerIn, BlockPos posIn)
	{
        Block block = stateIn.getBlock();

        return block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND;
	}

}