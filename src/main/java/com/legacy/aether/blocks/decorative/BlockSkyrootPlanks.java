package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockSkyrootPlanks extends Block
{

	public BlockSkyrootPlanks()
	{
		super(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 5.0F).sounds(BlockSoundGroup.WOOD).build());
	}

}