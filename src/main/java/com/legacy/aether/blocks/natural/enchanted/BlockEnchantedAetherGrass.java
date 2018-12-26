package com.legacy.aether.blocks.natural.enchanted;

import java.util.Random;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.natural.BlockAetherDirt;

public class BlockEnchantedAetherGrass extends Block
{

	public BlockEnchantedAetherGrass()
	{
		super(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos posIn, Random randIn)
	{
        if (!worldIn.isRemote)
        {
            if (worldIn.getLightLevel(LightType.SKY, posIn.up()) < 4)
            {
            	worldIn.setBlockState(posIn, BlocksAether.aether_dirt.getDefaultState().with(BlockAetherDirt.DOUBLE_DROP, false));
            }
        }
    }

}