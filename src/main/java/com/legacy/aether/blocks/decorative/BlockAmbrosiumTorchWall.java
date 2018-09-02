package com.legacy.aether.blocks.decorative;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorchWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAmbrosiumTorchWall extends BlockTorchWall
{

	public BlockAmbrosiumTorchWall()
	{
		super(Block.Builder.create(Material.CIRCUITS).needsRandomTick().lightValue(1).sound(SoundType.WOOD));
	}

	@Override
	public IItemProvider getItemDropped(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return BlocksAether.ambrosium_torch;
	}

}