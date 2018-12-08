package com.legacy.aether.entities.ai;

import java.util.function.Predicate;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIEatAetherGrass extends EntityAIBase
{

    private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);

    private final EntityLiving grassEaterEntity;

    private final World entityWorld;

    private int eatingGrassTimer;

    public EntityAIEatAetherGrass(EntityLiving grassEaterEntityIn)
    {
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos)))
            {
                return true;
            }
            else
            {
                return this.entityWorld.getBlockState(blockpos.down()).getBlock() == BlocksAether.aether_grass;
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
        this.grassEaterEntity.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        this.eatingGrassTimer = 0;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.eatingGrassTimer > 0;
    }

    public int getEatingGrassTimer()
    {
        return this.eatingGrassTimer;
    }

    @Override
    public void tick()
    {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4)
        {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos)))
            {
                if (this.entityWorld.getGameRules().getBoolean("mobGriefing"))
                {
                    this.entityWorld.destroyBlock(blockpos, false);
                }

                this.grassEaterEntity.eatGrassBonus();
            }
            else
            {
                BlockPos blockpos1 = blockpos.down();

                if (this.entityWorld.getBlockState(blockpos1).getBlock() == BlocksAether.aether_grass)
                {
                    if (this.entityWorld.getGameRules().getBoolean("mobGriefing"))
                    {
                        this.entityWorld.playEvent(2001, blockpos1, Block.getStateId(BlocksAether.aether_grass.getDefaultState()));
                        this.entityWorld.setBlockState(blockpos1, BlocksAether.aether_dirt.getDefaultState(), 2);
                    }

                    this.grassEaterEntity.eatGrassBonus();
                }
            }
        }
    }

}