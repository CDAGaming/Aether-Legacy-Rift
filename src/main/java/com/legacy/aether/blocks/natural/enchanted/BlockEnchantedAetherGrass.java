package com.legacy.aether.blocks.natural.enchanted;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.natural.BlockAetherDirt;

public class BlockEnchantedAetherGrass extends Block
{

	public BlockEnchantedAetherGrass()
	{
		super(Block.Builder.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.2F, -1.0F).sound(SoundType.PLANT));
	}

	@Override
	public IItemProvider getItemDropped(IBlockState stateIn, World worldIn, BlockPos posIn, int fortune)
	{
		return BlocksAether.aether_dirt;
	}

	@Override
	public void tick(IBlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
        if (!worldIn.isRemote)
        {
            if (worldIn.getLight(posIn.up()) < 4)
            {
            	worldIn.setBlockState(posIn, BlocksAether.aether_dirt.getDefaultState().with(BlockAetherDirt.DOUBLE_DROP, false));
            }
        }
    }

}