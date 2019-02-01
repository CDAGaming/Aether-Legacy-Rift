package com.legacy.aether.world.gen.feature;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;

public class QuicksoilFeature extends AetherFeature<DefaultFeatureConfig>
{

	public QuicksoilFeature()
	{
		super(FeatureConfig.DEFAULT, true);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config)
	{
		BlockPos spawnPos = BlockPos.ORIGIN;

		for (int x = -3; x < 12; x++)
		{
			for (int z = -3; z < 12; z++)
			{
				for (int n = 20; n < 48; n++)
				{
					BlockPos newPos = pos.add(x, n, z);

					if (world.getBlockState(newPos).isAir() && world.getBlockState(newPos.up()).getBlock() == BlocksAether.aether_grass && world.getBlockState(newPos.up(2)).isAir())
					{
						n += 128;

						spawnPos = new BlockPos(newPos.getX(), n, newPos.getZ());
					}
				}
			}
		}

		if (spawnPos.getY() < 128)
		{
			return false;
		}

		spawnPos = spawnPos.down(128);

		if (world.getBlockState(spawnPos.up()).isAir())
		{
			return false;
		}

		for (int x = spawnPos.getX() - 3; x < spawnPos.getX() + 4; x++)
		{
			for (int z = spawnPos.getZ() - 3; z < spawnPos.getZ() + 4; z++)
			{
				BlockPos newPos = new BlockPos(x, spawnPos.getY(), z);

				if (world.getBlockState(newPos).isAir() && ((x - spawnPos.getX()) * (x - spawnPos.getX()) + (z - spawnPos.getZ()) * (z - spawnPos.getZ())) < 12)
				{
					this.setBlockState(world, newPos, BlocksAether.quicksoil.getDefaultState());
				}
			}
		}

		return false;
	}

}