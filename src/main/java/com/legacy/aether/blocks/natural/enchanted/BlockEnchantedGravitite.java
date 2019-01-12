package com.legacy.aether.blocks.natural.enchanted;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

import com.legacy.aether.blocks.BlockFloating;

public class BlockEnchantedGravitite extends BlockFloating
{

	public BlockEnchantedGravitite()
	{
		super(FabricBlockSettings.of(Material.METAL).strength(5.0F, -1.0F).sounds(BlockSoundGroup.METAL), false);
	}

}