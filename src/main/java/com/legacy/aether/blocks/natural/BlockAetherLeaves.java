package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class BlockAetherLeaves extends LeavesBlock
{

	public BlockAetherLeaves()
	{
		super(FabricBlockSettings.of(Material.LEAVES).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());
	}

}