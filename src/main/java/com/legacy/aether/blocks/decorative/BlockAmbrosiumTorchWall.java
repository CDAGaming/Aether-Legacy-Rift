package com.legacy.aether.blocks.decorative;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.sound.BlockSoundGroup;

public class BlockAmbrosiumTorchWall extends WallTorchBlock
{

	public BlockAmbrosiumTorchWall()
	{
		super(FabricBlockSettings.of(Material.PART).collidable(false).breakByHand(true).ticksRandomly().lightLevel(1).sounds(BlockSoundGroup.WOOD).build());
	}

}