package com.legacy.aether.entities.ai;

import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class EatAetherGrassGoal extends Goal
{
	private static final Predicate<BlockState> grass = BlockStatePredicate.forBlock(BlocksAether.aether_grass);

	private final MobEntity owner;
	private final World world;
	private int timer;

	public EatAetherGrassGoal(MobEntity entity)
	{
		this.owner = entity;
		this.world = entity.world;

		this.setControlBits(7);
	}

	@Override
	public boolean canStart()
	{
		if (this.owner.getRand().nextInt(this.owner.isChild() ? 50 : 1000) != 0)
		{
			return false;
		}
		else
		{
			BlockPos pos = new BlockPos(this.owner.x, this.owner.y, this.owner.z);

			if (grass.test(this.world.getBlockState(pos)))
			{
				return true;
			}
			else
			{
				return this.world.getBlockState(pos.down()).getBlock() == BlocksAether.aether_grass;
			}
		}
	}

	@Override
	public void start()
	{
		this.timer = 40;
		this.world.summonParticle(this.owner, (byte) 10);
		this.owner.getNavigation().method_6340();
	}

	@Override
	public void onRemove()
	{
		this.timer = 0;
	}

	@Override
	public boolean shouldContinue()
	{
		return this.timer > 0;
	}

	@Override
	public void tick()
	{
		this.timer = Math.max(0, this.timer - 1);

		if (this.timer == 4)
		{
			BlockPos pos = new BlockPos(this.owner.x, this.owner.y, this.owner.z);

			if (grass.test(this.world.getBlockState(pos)))
			{
				if (this.world.getGameRules().getBoolean("mobGriefing"))
				{
					this.world.breakBlock(pos, false);
				}

				this.owner.method_5983();
			} 
			else
			{
				BlockPos downPos = pos.down();

				if (this.world.getBlockState(downPos).getBlock() == BlocksAether.aether_grass)
				{
					if (this.world.getGameRules().getBoolean("mobGriefing"))
					{
						this.world.fireWorldEvent(2001, downPos, Block.getRawIdFromState(BlocksAether.aether_grass.getDefaultState()));
						this.world.setBlockState(downPos, BlocksAether.aether_dirt.getDefaultState(), 2);
					}

					this.owner.method_5983();
				}
			}

		}
	}

	public int getTimer()
	{
		return this.timer;
	}

}