package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;

public class BlockAetherFlowerPot extends FlowerPotBlock
{

	public BlockAetherFlowerPot(Block flower)
	{
		super(flower, FabricBlockSettings.of(Material.PART).breakInstantly().build());
	}

}