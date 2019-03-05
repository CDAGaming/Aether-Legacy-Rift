package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockZanite extends Block
{

	public BlockZanite()
	{
		super(FabricBlockSettings.of(Material.METAL).strength(3.0F, -1.0F).sounds(BlockSoundGroup.METAL).build());
	}

}