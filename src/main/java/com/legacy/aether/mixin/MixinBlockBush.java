package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.legacy.aether.blocks.BlocksAether;

@Mixin(BlockBush.class)
public class MixinBlockBush 
{

	/**
	 * @author Modding Legacy
	 */
	@Overwrite
	protected boolean isValidGround(IBlockState stateIn, IBlockReader readerIn, BlockPos posIn)
	{
        Block block = stateIn.getBlock();

        return block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND;
	}

}