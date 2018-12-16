package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherLeaves extends LeavesBlock
{

	public BlockAetherLeaves()
	{
		super(Block.Settings.of(Material.ORGANIC).needsRandomTick().strength(0.2F, -1.0F).sound(SoundType.PLANT));
	}

	@Override
	public IItemProvider getItemDropped(BlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return this == BlocksAether.skyroot_leaves ? BlocksAether.skyroot_sapling : BlocksAether.golden_oak_sapling;
	}

}