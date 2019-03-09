package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;

public class BlockSkyrootPlanks extends Block
{

	public BlockSkyrootPlanks()
	{
		super(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 5.0F).sounds(BlockSoundGroup.WOOD).build());
	}

	@Override
	public boolean matches(Tag<Block> tag)
	{
		return tag == BlockTags.PLANKS ? true : super.matches(tag);
	}

}