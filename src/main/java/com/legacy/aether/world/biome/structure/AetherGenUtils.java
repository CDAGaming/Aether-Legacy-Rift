package com.legacy.aether.world.biome.structure;

import net.minecraft.block.state.IBlockState;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.world.biome.structure.components.AetherStructurePiece;

public class AetherGenUtils
{

	public static void generateFlower(AetherStructurePiece structure, IBlockState state, int offsetX, int offsetY, int offsetZ)
	{
		int x = offsetX;
		int y = offsetY;
		int z = offsetZ;

		for (int i = 0; i < 64; ++i)
		{
			x += structure.random.nextInt(8) - structure.random.nextInt(8);
			y += structure.random.nextInt(4) - structure.random.nextInt(4);
			z += structure.random.nextInt(8) - structure.random.nextInt(8);

			if (structure.getBlockStateWithOffset(x, y, z).isAir() && y < 255 && structure.getBlockStateWithOffset(x, y - 1, z).getBlock() == BlocksAether.aether_grass)
			{
				structure.setBlockWithOffset(x, y, z, state);
			}
		}
	}

	public static void generateGoldenOakTree(AetherStructurePiece structure, int offsetX, int offsetY, int offsetZ)
	{
		int x = offsetX;
		int y = offsetY;
		int z = offsetZ;

		if (structure.getBlockStateWithOffset(x, y - 1, z).getBlock() != BlocksAether.aether_grass && structure.getBlockStateWithOffset(x, y - 1, z).getBlock() != BlocksAether.aether_dirt)
		{
			return;
		}

		int height = 9;

		for (int x1 = x - 3; x1 < x + 4; x1++)
		{
			for (int y1 = y + 5; y1 < y + 12; y1++)
			{
				for (int z1 = z - 3; z1 < z + 4; z1++)
				{
					if ((x1 - x) * (x1 - x) + (y1 - y - 8) * (y1 - y - 8) + (z1 - z) * (z1 - z) < 12 + structure.random.nextInt(5))
					{
						structure.setBlockWithOffset(x1, y1, z1, BlocksAether.golden_oak_leaves.getDefaultState());
					}
				}
			}
		}

		for (int n = 0; n < height; n++)
		{
			if (n > 4)
			{
				if (structure.random.nextInt(3) > 0)
				{
					goldenOakBranch(structure, x, y + n, z, n / 4 - 1);
				}
			}

			structure.setBlockWithOffset(x, y + n, z, BlocksAether.golden_oak_log.getDefaultState());
		}
	}

	private static void goldenOakBranch(AetherStructurePiece structure, int x, int y, int z, int slant)
	{
		int directionX = structure.random.nextInt(3) - 1;
		int directionY = slant;
		int directionZ = structure.random.nextInt(3) - 1;
		int i = x;
		int k = z;

		for (int n = 0; n < structure.random.nextInt(2); n++)
		{
			x += directionX;
			y += directionY;
			z += directionZ;
			i -= directionX;
			k -= directionZ;

			if (structure.getBlockStateWithOffset(x, y, z).getBlock() == BlocksAether.golden_oak_leaves)
			{
				structure.setBlockWithOffset(x, y, z, BlocksAether.golden_oak_log.getDefaultState());
				structure.setBlockWithOffset(i, y, k, BlocksAether.golden_oak_log.getDefaultState());
			}
		}
	}

	public static void generateClouds(AetherStructurePiece structure, IBlockState state, boolean isFlat, int cloudSize, int offsetX, int offsetY, int offsetZ)
	{
		int x = offsetX;
		int y = offsetY;
		int z = offsetZ;
		int xTendency = structure.random.nextInt(3) - 1;
		int zTendency = structure.random.nextInt(3) - 1;

		for (int n = 0; n < cloudSize; ++n)
		{
			x += structure.random.nextInt(3) - 1 + xTendency;

			if (structure.random.nextBoolean() && !isFlat || isFlat && structure.random.nextInt(10) == 0)
			{
				y += structure.random.nextInt(3) - 1;
			}

			z += structure.random.nextInt(3) - 1 + zTendency;

			for (int x1 = x; x1 < x + structure.random.nextInt(4) + 3 * (isFlat ? 3 : 1); ++x1)
			{
				for (int y1 = y; y1 < y + structure.random.nextInt(1) + 2; ++y1)
				{
					for (int z1 = z; z1 < z + structure.random.nextInt(4) + 3 * (isFlat ? 3 : 1); ++z1)
					{
						if (structure.getBlockState(x1, y1, z1).isAir())
						{
							if (Math.abs(x1 - x) + Math.abs(y1 - y) + Math.abs(z1 - z) < 4 * (isFlat ? 3 : 1) + structure.random.nextInt(2))
							{
								structure.setBlockWithOffset(x1, y1, z1, state);
							}
						}
					}
				}
			}
		}
	}

}