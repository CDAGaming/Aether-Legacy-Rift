package com.legacy.aether.world.biome.feature;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class QuicksoilFeature extends Feature<NoFeatureConfig>
{

	@Override
	public boolean place(IWorld worldIn, IChunkGenerator<? extends IChunkGenSettings> chunkGeneratorIn, Random rand, BlockPos posIn, NoFeatureConfig config)
	{
		BlockPos spawnPos = BlockPos.ORIGIN;

		for (int x = -3; x < 12; x++)
		{
			for (int z = -3; z < 12; z++)
			{
				for (int n = chunkGeneratorIn.getGroundHeight(); n < 48; n++)
				{
					BlockPos pos = posIn.add(x, n, z);

					if (worldIn.getBlockState(pos).isAir() && worldIn.getBlockState(pos.up()).getBlock() == BlocksAether.aether_grass && worldIn.getBlockState(pos.up(2)).isAir())
					{
						n += 128;
						spawnPos = new BlockPos(pos.getX(), n, pos.getZ());
					}
				}
			}
		}

		if (spawnPos.getY() < 128)
		{
			return false;
		}

		spawnPos = spawnPos.down(128);

		for(int x = spawnPos.getX() - 3; x < spawnPos.getX() + 4; x++)
		{
			for(int z = spawnPos.getZ() - 3; z < spawnPos.getZ() + 4; z++)
			{
				BlockPos newPos = new BlockPos(x, spawnPos.getY(), z);

				if(worldIn.getBlockState(newPos).isAir() && ((x - spawnPos.getX()) * (x - spawnPos.getX()) + (z - spawnPos.getZ()) * (z - spawnPos.getZ())) < 12)
				{
					this.setBlockState(worldIn, newPos, BlocksAether.quicksoil.getDefaultState());
				}
			}
		}

		return false;
	}

}