package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Material;
import net.minecraft.block.PaneBlock;
import net.minecraft.sound.BlockSoundGroup;

public class BlockQuicksoilGlassPane extends PaneBlock
{

	public BlockQuicksoilGlassPane()
	{
		super(FabricBlockSettings.of(Material.GLASS).friction(1.1F).lightLevel(14).strength(0.2F, -1.0F).sounds(BlockSoundGroup.GLASS).build());
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

}