package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.TorchBlock;
import net.minecraft.sound.BlockSoundGroup;

public class BlockAmbrosiumTorch extends TorchBlock
{

	public BlockAmbrosiumTorch()
	{
		super(FabricBlockSettings.of(Material.PART).collidable(false).breakByHand(true).ticksRandomly().lightLevel(15).sounds(BlockSoundGroup.WOOD).build());
	}

}