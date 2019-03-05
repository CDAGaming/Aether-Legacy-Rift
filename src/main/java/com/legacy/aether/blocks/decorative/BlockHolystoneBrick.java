package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;

public class BlockHolystoneBrick extends Block
{
	public BlockHolystoneBrick() 
	{
		super(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).strength(0.5F, 10.0F).sounds(BlockSoundGroup.STONE).build());
	}
}