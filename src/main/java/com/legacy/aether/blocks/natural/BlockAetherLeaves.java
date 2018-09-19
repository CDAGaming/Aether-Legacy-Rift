package com.legacy.aether.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherLeaves extends BlockLeaves
{

	public BlockAetherLeaves()
	{
		super(Block.Builder.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.2F, -1.0F).sound(SoundType.PLANT));
	}

	@Override
	public IItemProvider getItemDropped(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return this == BlocksAether.skyroot_leaves ? BlocksAether.skyroot_sapling : BlocksAether.golden_oak_sapling;
	}

}