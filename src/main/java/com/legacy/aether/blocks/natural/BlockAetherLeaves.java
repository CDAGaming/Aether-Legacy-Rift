package com.legacy.aether.blocks.natural;

import java.util.Random;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockAetherLeaves extends LeavesBlock
{

	public BlockAetherLeaves(MaterialColor color)
	{
		super(FabricBlockSettings.of(Material.LEAVES, color).ticksRandomly().strength(0.2F, -1.0F).sounds(BlockSoundGroup.GRASS).build());
	}

	@Override
	public boolean matches(Tag<Block> tag)
	{
		return tag == BlockTags.LEAVES ? true : super.matches(tag);
	}

	@Override
	public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1)
	{
		world_1.setBlockState(blockPos_1, updateDistanceFromLogs(blockState_1, world_1, blockPos_1), 3);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		int distance = getDistanceFromLog(neighborState) + 1;

		if (distance != 1 || (Integer) state.get(DISTANCE) != distance)
		{
			world.getBlockTickScheduler().schedule(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistanceFromLogs(BlockState blockState_1, IWorld iWorld_1, BlockPos blockPos_1)
	{
		int distance = 7;
		BlockPos.PooledMutable blockPos$PooledMutable_1 = BlockPos.PooledMutable.get();
		Throwable var5 = null;

		try
		{
			Direction[] directions = Direction.values();

			for (int var8 = 0; var8 < directions.length; ++var8)
			{
				Direction direction = directions[var8];

				blockPos$PooledMutable_1.method_10114(blockPos_1).method_10118(direction);
				distance = Math.min(distance, getDistanceFromLog(iWorld_1.getBlockState(blockPos$PooledMutable_1)) + 1);

				if (distance == 1)
				{
					break;
				}
			}
		}
		catch (Throwable var17)
		{
			var5 = var17;

			throw var17;
		}
		finally
		{
			if (blockPos$PooledMutable_1 != null)
			{
				if (var5 != null)
				{
					try
					{
						blockPos$PooledMutable_1.close();
					}
					catch (Throwable var16)
					{
						var5.addSuppressed(var16);
					}
				}
				else
				{
					blockPos$PooledMutable_1.close();
				}
			}
		}

		return (BlockState) blockState_1.with(DISTANCE, distance);
	}

	private static int getDistanceFromLog(BlockState state)
	{
		if (state.matches(BlockTags.LOGS))
		{
			return 0;
		}
		else
		{
			return state.getBlock() instanceof LeavesBlock ? (Integer) state.get(DISTANCE) : 7;
		}
	}

}