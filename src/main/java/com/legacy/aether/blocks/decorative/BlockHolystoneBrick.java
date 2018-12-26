package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockHolystoneBrick extends Block
{
	public BlockHolystoneBrick() 
	{
		super(FabricBlockSettings.of(Material.STONE).strength(0.5F, 10.0F).sounds(BlockSoundGroup.STONE).build());
	}
}