package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherFlower extends FlowerBlock
{

	public BlockAetherFlower(StatusEffect effect, int duration)
	{
		super(effect, duration, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public boolean matches(Tag<Block> tag)
	{
		return tag == BlockTags.SMALL_FLOWERS ? true : super.matches(tag);
	}

	@Override
	protected boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos)
	{
		Block block = state.getBlock();

		return block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || super.canPlantOnTop(state, world, pos);
	}

}