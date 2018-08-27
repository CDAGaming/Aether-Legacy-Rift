package com.legacy.aether.client.rendering.color;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;

import com.legacy.aether.blocks.BlocksAether;

public class AetherColor implements IBlockColor, IItemColor
{

	@Override
	public int getColor(IBlockState blockstate, IWorldReaderBase world, BlockPos pos, int renderPass) 
	{
		Block block = blockstate.getBlock();

		if (block == BlocksAether.blue_aercloud)
		{
			return 0xCCFFFF;
		}
		else if (block == BlocksAether.golden_aercloud)
		{
			return 0xFFFF80;
		}

		return 0;
	}

	@Override
	public int getColor(ItemStack stack, int tintIndex) 
	{
		Block block = Block.getBlockFromItem(stack.getItem());

		if (block == BlocksAether.blue_aercloud)
		{
			return 0xCCFFFF;
		}
		else if (block == BlocksAether.golden_aercloud)
		{
			return 0xFFFF80;
		}

		return 0;
	}

}