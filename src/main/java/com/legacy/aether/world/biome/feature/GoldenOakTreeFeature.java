package com.legacy.aether.world.biome.feature;

import java.util.Random;
import java.util.Set;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class GoldenOakTreeFeature extends AetherTreeFeature
{

	@Override
	protected boolean generateTree(Set<BlockPos> posListIn, IWorld worldIn, Random randomIn, BlockPos posIn) 
	{
		int height = 9;

        BlockState ground = worldIn.getBlockState(posIn.down());

        if(ground.getBlock() != BlocksAether.aether_grass && ground.getBlock() != BlocksAether.aether_dirt)
        {
            return false;
        }

		for(int x1 = posIn.getX() - 3; x1 < posIn.getX() + 4; x1++)
		{
			for(int y1 = posIn.getY() + 5; y1 < posIn.getY() + 12; y1++)
			{
				for(int z1 = posIn.getZ() - 3; z1 < posIn.getZ() + 4; z1++)
				{
					BlockPos newPos = new BlockPos(x1, y1, z1);

					if((x1 - posIn.getX()) * (x1 - posIn.getX()) + (y1 - posIn.getY() - 8) * (y1 - posIn.getY() - 8) + (z1 - posIn.getZ()) * (z1 - posIn.getZ()) < 12 + randomIn.nextInt(5) && this.canGrowInto(worldIn.getBlockState(newPos).getBlock()))
					{
						this.setBlockState(worldIn, newPos, BlocksAether.golden_oak_leaves.getDefaultState());
					}
				}
			}		
		}

		for(int n = 0; n < height; n++)
		{
			if(n > 4)
			{
				if(randomIn.nextInt(3) > 0)
				{
					branch(worldIn, randomIn, posIn.up(n), n / 4 - 1);
				}
			}

			this.setBlockState(worldIn, posIn.up(n), BlocksAether.golden_oak_log.getDefaultState());
		}

        return true;
	}

	public boolean branch(IWorld worldIn, Random random, BlockPos posIn, int slant)
    {
		int directionX = random.nextInt(3) - 1;
		int directionY = slant;
		int directionZ = random.nextInt(3) - 1;
		int x = posIn.getX();
		int y = posIn.getY();
		int z = posIn.getZ();
		int i = x;
		int k = z;

		for(int n = 0; n < random.nextInt(2); n++)
		{
			x += directionX;
			y += directionY;
			z += directionZ;
			i -= directionX;
			k -= directionZ;

			if(this.canGrowInto(worldIn.getBlockState(posIn).getBlock()))
			{
				this.setBlockState(worldIn, new BlockPos(x, y, z), BlocksAether.golden_oak_log.getDefaultState());
				this.setBlockState(worldIn, new BlockPos(i, y, k), BlocksAether.golden_oak_log.getDefaultState());
			}
		}

		return true;
	}

}