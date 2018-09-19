package com.legacy.aether.world.biome.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import com.legacy.aether.world.biome.feature.config.AercloudConfig;

public class AercloudFeature extends Feature<AercloudConfig>
{

	@Override
	public boolean place(IWorld worldIn, IChunkGenerator<? extends IChunkGenSettings> generatorIn, Random randIn, BlockPos posIn, AercloudConfig configIn)
	{
		BlockPos origin = new BlockPos(posIn.getX(), (configIn.isFlat() ? 0 : randIn.nextInt(64)) + configIn.getY(), posIn.getZ());
        BlockPos.MutableBlockPos position = new BlockPos.MutableBlockPos();

		int xTendency = randIn.nextInt(3) - 1;
		int zTendency = randIn.nextInt(3) - 1;

		for (int amount = 0; amount < configIn.cloudAmount(); ++amount)
		{
			boolean offsetY = ((randIn.nextBoolean() && !configIn.isFlat()) || (configIn.isFlat() && randIn.nextInt(10) == 0));

			int xOffset = randIn.nextInt(3) - 1 + xTendency;
			int yOffset = (offsetY ? randIn.nextInt(3) - 1 : 0);
			int zOffset = randIn.nextInt(3) - 1 + zTendency;

			position.setPos(origin).add(xOffset, yOffset, zOffset);

			for (int x = position.getX(); x < position.getX() + randIn.nextInt(4) + 3 * configIn.cloudModifier(); ++x)
			{
				for (int y = position.getY(); y < position.getY() + randIn.nextInt(1) + 2; ++y)
				{
					for (int z = position.getZ(); z < position.getZ() + randIn.nextInt(4) + 3 * configIn.cloudModifier(); ++z)
					{
						BlockPos pos = new BlockPos(x, y, z);

						if (worldIn.isAirBlock(pos))
						{
							if (Math.abs(x - position.getX()) + Math.abs(y - position.getY()) + Math.abs(z - position.getZ()) < 4 * configIn.cloudModifier() + randIn.nextInt(2))
							{
								this.setBlockState(worldIn, pos, configIn.getCloudState());
							}
						}
					}
				}
			}
		}

		return true;
	}

}