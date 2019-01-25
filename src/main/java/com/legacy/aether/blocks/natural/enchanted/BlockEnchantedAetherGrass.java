package com.legacy.aether.blocks.natural.enchanted;

import java.util.Random;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.natural.BlockAetherDirt;

public class BlockEnchantedAetherGrass extends Block
{

	public BlockEnchantedAetherGrass()
	{
		super(FabricBlockSettings.of(Material.ORGANIC, MaterialColor.YELLOW).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public void onScheduledTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
        if (!worldIn.isClient)
        {
            if (worldIn.method_8602(posIn.up()) < 4)
            {
            	worldIn.setBlockState(posIn, BlocksAether.aether_dirt.getDefaultState().with(BlockAetherDirt.DOUBLE_DROP, false));
            }
        }
    }

}