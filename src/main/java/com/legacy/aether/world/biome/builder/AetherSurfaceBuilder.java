package com.legacy.aether.world.biome.builder;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class AetherSurfaceBuilder extends SurfaceBuilder<AetherSurfaceBuilderConfig> {

	@Override
	public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int y, double position, BlockState defaultBlock, BlockState defaultFluid, int waterLevel, long seed, AetherSurfaceBuilderConfig config)
	{
		MutableIntBoundingBox mutedPos = new MutableIntBoundingBox();

		int chunkX = x - chunk.getPos().getXStart();
		int chunkZ = z - chunk.getPos().getZStart();

		int j1 = -1;
        int i1 = (int)(3.0D + random.nextDouble() * 0.25D);

		BlockState top = config.getTop();
		BlockState filler = config.getMiddle();

        for (int chunkY = 127; chunkY >= 0; chunkY--)
		{
			Block block = chunk.getBlockState(mutedPos.setPos(chunkX, chunkY, chunkZ)).getBlock();

			if (block == Blocks.AIR)
			{
				j1 = -1;
			}
			else if (block == BlocksAether.holystone)
			{
				if (j1 == -1)
				{
					if (i1 <= 0)
					{
						top = Blocks.AIR.getDefaultState();
						filler = defaultBlock;
					}

					j1 = i1;

					if (chunkY >= 0)
					{
						chunk.setBlockState(mutedPos.setPos(chunkX, chunkY, chunkZ), top, false);
					}
					else
					{
						chunk.setBlockState(mutedPos.setPos(chunkX, chunkY, chunkZ), filler, false);
					}
				}
				else if (j1 > 0)
				{
					--j1;
					chunk.setBlockState(mutedPos.setPos(chunkX, chunkY, chunkZ), filler, false);
				}
			}
		}

	}

}