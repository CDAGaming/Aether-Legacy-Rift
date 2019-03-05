package com.legacy.aether.blocks.natural.ore;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

import com.legacy.aether.blocks.BlockFloating;

public class BlockGravititeOre extends BlockFloating
{

	public BlockGravititeOre()
	{
		super(FabricBlockSettings.of(Material.STONE).strength(3.0F, 5.0F).sounds(BlockSoundGroup.STONE), true);
	}

}