package com.legacy.aether.world.gen.feature;

import java.util.Random;
import java.util.Set;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;

import com.legacy.aether.blocks.BlocksAether;

public class GoldenOakTreeFeature extends AetherTreeFeature
{

	@Override
	protected boolean generate(Set<BlockPos> posListIn, ModifiableTestableWorld worldIn, Random randomIn, BlockPos posIn)
	{
		int height = 9;

		if (worldIn.test(posIn.down(), (ground) -> ground.getBlock() != BlocksAether.aether_grass))
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

					if((x1 - posIn.getX()) * (x1 - posIn.getX()) + (y1 - posIn.getY() - 8) * (y1 - posIn.getY() - 8) + (z1 - posIn.getZ()) * (z1 - posIn.getZ()) < 12 + randomIn.nextInt(5) && worldIn.test(newPos, (state) -> canGrowInto(state)))
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

	public boolean branch(ModifiableTestableWorld worldIn, Random random, BlockPos posIn, int slant)
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

			if(worldIn.test(posIn, (state) -> canGrowInto(state)))
			{
				this.setBlockState(worldIn, new BlockPos(x, y, z), BlocksAether.golden_oak_log.getDefaultState());
				this.setBlockState(worldIn, new BlockPos(i, y, k), BlocksAether.golden_oak_log.getDefaultState());
			}
		}

		return true;
	}

}