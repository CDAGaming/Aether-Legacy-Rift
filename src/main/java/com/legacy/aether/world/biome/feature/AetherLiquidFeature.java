package com.legacy.aether.world.biome.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LiquidsConfig;

import com.legacy.aether.blocks.BlocksAether;

public class AetherLiquidFeature extends Feature<LiquidsConfig>
{

	@Override
	public boolean place(IWorld worldIn, IChunkGenerator<? extends IChunkGenSettings> chunkGeneratorIn, Random rand, BlockPos position, LiquidsConfig config)
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
            IBlockState iblockstate = worldIn.getBlockState(position);

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

                if (worldIn.isAirBlock(position.west()))
                {
                    ++j;
                }

                if (worldIn.isAirBlock(position.east()))
                {
                    ++j;
                }

                if (worldIn.isAirBlock(position.north()))
                {
                    ++j;
                }

                if (worldIn.isAirBlock(position.south()))
                {
                    ++j;
                }

                if (i == 3 && j == 1)
                {
                	worldIn.setBlockState(position, config.field_202459_a.getDefaultState().getBlockState(), 2);
                	worldIn.getPendingFluidTicks().scheduleTick(position, config.field_202459_a, 0);
                    ++i;
                }

                return i > 0;
            }
        }
	}

}
