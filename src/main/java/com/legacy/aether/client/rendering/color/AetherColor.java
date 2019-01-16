package com.legacy.aether.client.rendering.color;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.decorative.BlockColoredAercloud;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockColorMapper;
import net.minecraft.client.render.item.ItemColorMapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import com.legacy.aether.item.ItemMoaEgg;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.item.armor.ItemAetherArmor;

import net.minecraft.world.ExtendedBlockView;

public class AetherColor implements BlockColorMapper, ItemColorMapper
{

	@Override
	public int getColor(BlockState blockstate, ExtendedBlockView world, BlockPos pos, int renderPass)
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
		else if (block instanceof BlockColoredAercloud)
		{
			return ((BlockColoredAercloud)block).getColor();
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
		else if (block instanceof BlockColoredAercloud)
		{
			return ((BlockColoredAercloud)block).getColor();
		}
		else if (stack.getItem() instanceof ItemAetherArmor)
		{
			return ((ItemAetherArmor)stack.getItem()).getColor();
		}
		else if (stack.getItem() == ItemsAether.moa_egg)
		{
			return ((ItemMoaEgg)stack.getItem()).getColor(stack);
		}
		else if (stack.getItem() instanceof ItemAccessory)
		{
			return ((ItemAccessory)stack.getItem()).getColor();
		}

		return 0;
	}

}