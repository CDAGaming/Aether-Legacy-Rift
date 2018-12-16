package com.legacy.aether.world.biome.feature;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import com.legacy.aether.blocks.BlocksAether;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class AetherLiquidFeature extends Feature<LiquidsConfig>
{

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends ChunkGeneratorSettings> chunkGeneratorIn, Random rand, BlockPos position, LiquidsConfig config)
	{
        if (worldIn.getBlockState(position.up()).getBlock() != BlocksAether.holystone)
        {
            return false;
        }
        else if (worldIn.getBlockState(position.down()).getBlock() != BlocksAether.holystone)
        {
            return false;
        }
        else
        {
            BlockState iblockstate = worldIn.getBlockState(position);

            if (!iblockstate.isAir() && iblockstate.getBlock() != BlocksAether.holystone)
            {
                return false;
            }
            else
            {
                int i = 0;

                if (worldIn.getBlockState(position.west()).getBlock() == BlocksAether.holystone)
                {
                    ++i;
                }

                if (worldIn.getBlockState(position.east()).getBlock() == BlocksAether.holystone)
                {
                    ++i;
                }

                if (worldIn.getBlockState(position.north()).getBlock() == BlocksAether.holystone)
                {
                    ++i;
                }

                if (worldIn.getBlockState(position.south()).getBlock() == BlocksAether.holystone)
                {
                    ++i;
                }

                int j = 0;

                if (worldIn.isAir(position.west()))
                {
                    ++j;
                }

                if (worldIn.isAir(position.east()))
                {
                    ++j;
                }

                if (worldIn.isAir(position.north()))
                {
                    ++j;
                }

                if (worldIn.isAir(position.south()))
                {
                    ++j;
                }

                if (i == 3 && j == 1)
                {
                	worldIn.setBlockState(position, config.fluid.getDefaultState().getBlockState(), 2);
                	worldIn.getFluidTickScheduler().schedule(position, config.fluid, 0);
                    ++i;
                }

                return i > 0;
            }
        }
	}

}
