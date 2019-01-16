package com.legacy.aether.world.gen.surfacebuilder;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import com.legacy.aether.blocks.BlocksAether;

public class HighlandsSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig>
{

	public HighlandsSurfaceBuilder()
	{
		super(TernarySurfaceConfig::deserialize);
	}

	@Override
	public void generate(Random var1, Chunk var2, Biome var3, int var4, int var5, int var6, double var7, BlockState var9, BlockState var10, int var11, long var12, TernarySurfaceConfig var14)
	{
		BlockState blockState_6 = BlocksAether.aether_grass.getDefaultState();
		BlockState blockState_7 = BlocksAether.aether_dirt.getDefaultState();
		BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();

		int int_5 = -1;
		int int_6 = (int) (3.0D + var1.nextDouble() * 0.25D);
		int int_7 = var4;
		int int_8 = var5;

		for (int int_9 = 128; int_9 >= 0; --int_9)
		{
			blockPos$Mutable_1.set(int_7, int_9, int_8);

			BlockState blockState_8 = var2.getBlockState(blockPos$Mutable_1);

			if (blockState_8.isAir())
			{
				int_5 = -1;
			}
			else if (blockState_8.getBlock() == var9.getBlock())
			{
				if (int_5 == -1)
				{
					if (int_6 <= 0)
					{
						blockState_6 = Blocks.AIR.getDefaultState();
						blockState_7 = var9;
					}

					int_5 = int_6;

					if (int_9 >= 0)
					{
						var2.setBlockState(blockPos$Mutable_1, blockState_6, false);
					}
					else
					{
						var2.setBlockState(blockPos$Mutable_1, blockState_7, false);
					}
				}
				else if (int_5 > 0)
				{
					--int_5;

					var2.setBlockState(blockPos$Mutable_1, blockState_7, false);
				}
			}
		}
	}

}