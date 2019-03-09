package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;

public class BlockAetherSapling extends SaplingBlock
{

	public BlockAetherSapling(SaplingGenerator generator)
	{
		super(generator, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public boolean matches(Tag<Block> tag)
	{
		return tag == BlockTags.SAPLINGS ? true : super.matches(tag);
	}

}
