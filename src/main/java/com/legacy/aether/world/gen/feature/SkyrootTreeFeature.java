package com.legacy.aether.world.gen.feature;

import java.util.Random;
import java.util.Set;

import net.minecraft.class_3747;
import net.minecraft.util.math.BlockPos;

import com.legacy.aether.blocks.BlocksAether;

public class SkyrootTreeFeature extends AetherTreeFeature
{

	@Override
	protected boolean generateTree(Set<BlockPos> posListIn, class_3747 worldIn, Random randomIn, BlockPos posIn)
	{
		int l = randomIn.nextInt(3) + 4;

		if (worldIn.method_16358(posIn.down(), (ground) -> ground.getBlock() != BlocksAether.aether_grass))
		{
			return false;
		}

		for (int k1 = (posIn.getY() - 3) + l; k1 <= posIn.getY() + l; k1++)
		{
			int j2 = k1 - (posIn.getY() + l);
			int i3 = 1 - j2 / 2;

			for (int k3 = posIn.getX() - i3; k3 <= posIn.getX() + i3; k3++)
			{
				int l3 = k3 - posIn.getX();

				for (int i4 = posIn.getZ() - i3; i4 <= posIn.getZ() + i3; i4++)
				{
					int j4 = i4 - posIn.getZ();

					BlockPos newPos = new BlockPos(k3, k1, i4);

					if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || randomIn.nextInt(2) != 0 && j2 != 0))
					{
						this.addBlockState(worldIn, newPos, BlocksAether.skyroot_leaves.getDefaultState());
					}
				}
			}
		}

		for (int l1 = 0; l1 < l; l1++)
		{
			if (worldIn.method_16358(posIn.up(l1), (blockState) -> canGrowInto(blockState))) 
			{
				this.method_12773(posListIn, worldIn, posIn.up(l1), BlocksAether.skyroot_log.getDefaultState());
			}
		}

		return true;
	}

}