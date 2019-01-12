package com.legacy.aether.blocks.natural.ore;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockZaniteOre extends Block
{

	public BlockZaniteOre()
	{
		super(FabricBlockSettings.of(Material.STONE).strength(3.0F, 5.0F).sounds(BlockSoundGroup.STONE).build());
	}

}