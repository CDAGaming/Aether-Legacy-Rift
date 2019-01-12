package com.legacy.aether.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;

import net.minecraft.class_1946;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PortalBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.ChunkPos;

public class TeleporterAether extends class_1946
{

	private boolean portalSpawn;

    private final Random random;

    private final ServerWorld worldServerInstance;

    private final Long2ObjectMap<AetherPortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<AetherPortalPosition>(4096);

	public TeleporterAether(boolean portalSpawn, ServerWorld serverIn)
	{
		super(serverIn);

		this.portalSpawn = portalSpawn;
		this.worldServerInstance = serverIn;
        this.random = new Random(serverIn.getSeed());
	}

	@Override
	public void method_8655(Entity entityIn, float rotationYaw)
	{
    	if (!this.portalSpawn)
    	{
    		entityIn.setPositionAnglesAndUpdate(entityIn.x, 256, entityIn.z, rotationYaw, 0.0F);

    		return;
    	}

        if (!this.method_8653(entityIn, rotationYaw))
        {
            this.method_8654(entityIn);
            this.method_8653(entityIn, rotationYaw);
        }
	}

	@Override
	public boolean method_8653(Entity entityIn, float rotationYaw)
	{
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.x);
        int k = MathHelper.floor(entityIn.z);
        boolean flag = true;
        BlockPos blockpos = BlockPos.ORIGIN;
        long l = ChunkPos.toLong(j, k);

        if (this.destinationCoordinateCache.containsKey(l))
        {
        	AetherPortalPosition teleporter$portalposition = this.destinationCoordinateCache.get(l);
            d0 = 0.0D;
            blockpos = teleporter$portalposition;
            teleporter$portalposition.lastUpdateTime = this.worldServerInstance.getTime();
            flag = false;
        }
        else
        {
            BlockPos blockpos3 = new BlockPos(entityIn);

            for (int i1 = -128; i1 <= 128; ++i1)
            {
                BlockPos blockpos2;

                for (int j1 = -128; j1 <= 128; ++j1)
                {
                    for (BlockPos blockpos1 = blockpos3.add(i1, this.worldServerInstance.getHeight() - 1 - blockpos3.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2)
                    {
                        blockpos2 = blockpos1.down();

                        if (this.worldServerInstance.getBlockState(blockpos1).getBlock() == BlocksAether.aether_portal)
                        {
                            for (blockpos2 = blockpos1.down(); this.worldServerInstance.getBlockState(blockpos2).getBlock() == BlocksAether.aether_portal; blockpos2 = blockpos2.down())
                            {
                                blockpos1 = blockpos2;
                            }

                            double d1 = blockpos1.squaredDistanceTo(blockpos3);

                            if (d0 < 0.0D || d1 < d0)
                            {
                                d0 = d1;
                                blockpos = blockpos1;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.put(l, new AetherPortalPosition(blockpos, this.worldServerInstance.getTime()));
            }

            double d5 = (double)blockpos.getX() + 0.5D;
            double d7 = (double)blockpos.getZ() + 0.5D;

            BlockPattern.Result blockpattern$patternhelper = ((BlockAetherPortal) BlocksAether.aether_portal).method_10350(this.worldServerInstance, blockpos);
            boolean flag1 = blockpattern$patternhelper.getForwards().rotateYClockwise().getDirection() == Direction.AxisDirection.NEGATIVE;
            double d2 = blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? (double)blockpattern$patternhelper.getFrontTopLeft().getZ() : (double)blockpattern$patternhelper.getFrontTopLeft().getX();

            double d1 = blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? d7 : d5;
            d1 = Math.abs(MathHelper.method_15370(d1 - (double)(blockpattern$patternhelper.getForwards().rotateYClockwise().getDirection() == Direction.AxisDirection.NEGATIVE ? 1 : 0), d2, d2 - (double)blockpattern$patternhelper.getWidth()));
            double d22 = MathHelper.method_15370(blockpos.getY() - 1.0D, (double)blockpattern$patternhelper.getFrontTopLeft().getY(), (double)(blockpattern$patternhelper.getFrontTopLeft().getY() - blockpattern$patternhelper.getHeight()));

            double d6 = (double)(blockpattern$patternhelper.getFrontTopLeft().getY() + 1) - new Vec3d(d1, d22, 0.0D).y * (double)blockpattern$patternhelper.getHeight();

            if (flag1)
            {
                ++d2;
            }

            if (blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X)
            {
                d7 = d2 + (1.0D - new Vec3d(d1, d22, 0.0D).x) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateYClockwise().getDirection().offset();
            }
            else
            {
                d5 = d2 + (1.0D - new Vec3d(d1, d22, 0.0D).x) * (double)blockpattern$patternhelper.getWidth() * (double)blockpattern$patternhelper.getForwards().rotateYClockwise().getDirection().offset();
            }

            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;

            if (blockpattern$patternhelper.getForwards().getOpposite() == blockpattern$patternhelper.getForwards())
            {
                f = 1.0F;
                f1 = 1.0F;
            }
            else if (blockpattern$patternhelper.getForwards().getOpposite() == blockpattern$patternhelper.getForwards().getOpposite())
            {
                f = -1.0F;
                f1 = -1.0F;
            }
            else if (blockpattern$patternhelper.getForwards().getOpposite() == blockpattern$patternhelper.getForwards().rotateYClockwise())
            {
                f2 = 1.0F;
                f3 = -1.0F;
            }
            else
            {
                f2 = -1.0F;
                f3 = 1.0F;
            }

            double d3 = entityIn.velocityX;
            double d4 = entityIn.velocityZ;
            entityIn.velocityX = d3 * (double)f + d4 * (double)f3;
            entityIn.velocityZ = d3 * (double)f2 + d4 * (double)f1;
            entityIn.yaw = rotationYaw - (float)(blockpattern$patternhelper.getForwards().getOpposite().getHorizontal() * 90) + (float)(blockpattern$patternhelper.getForwards().getHorizontal() * 90);

            if (entityIn instanceof ServerPlayerEntity)
            {
                ((ServerPlayerEntity)entityIn).networkHandler.method_14363(d5, d6, d7, entityIn.yaw, entityIn.pitch);
                ((ServerPlayerEntity)entityIn).networkHandler.method_14372();
            }
            else
            {
                entityIn.setPositionAndAngles(d5, d6, d7, entityIn.yaw, entityIn.pitch);
            }

            return true;
        }
        else
        {
            return false;
        }
	}

	@Override
    public boolean method_8654(Entity entityIn)
    {
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.x);
        int k = MathHelper.floor(entityIn.y);
        int l = MathHelper.floor(entityIn.z);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

        for (int j2 = j - 16; j2 <= j + 16; ++j2)
        {
            double d1 = (double)j2 + 0.5D - entityIn.x;

            for (int l2 = l - 16; l2 <= l + 16; ++l2)
            {
                double d2 = (double)l2 + 0.5D - entityIn.z;
                label146:

                for (int j3 = this.worldServerInstance.getHeight() - 1; j3 >= 0; --j3)
                {
                    if (this.worldServerInstance.isAir(blockpos$mutableblockpos.set(j2, j3, l2)))
                    {
                        while (j3 > 0 && this.worldServerInstance.isAir(blockpos$mutableblockpos.set(j2, j3 - 1, l2)))
                        {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3)
                        {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;

                            if (k3 % 4 >= 2)
                            {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4)
                            {
                                for (int k4 = 0; k4 < 4; ++k4)
                                {
                                    for (int l4 = -1; l4 < 4; ++l4)
                                    {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutableblockpos.set(i5, j5, k5);

                                        if (l4 < 0 && !this.worldServerInstance.getBlockState(blockpos$mutableblockpos).getMaterial().method_15799() || l4 >= 0 && !this.worldServerInstance.isAir(blockpos$mutableblockpos))
                                        {
                                            continue label146;
                                        }
                                    }
                                }
                            }

                            double d5 = (double)j3 + 0.5D - entityIn.y;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;

                            if (d0 < 0.0D || d7 < d0)
                            {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (int l5 = j - 16; l5 <= j + 16; ++l5)
            {
                double d3 = (double)l5 + 0.5D - entityIn.x;

                for (int j6 = l - 16; j6 <= l + 16; ++j6)
                {
                    double d4 = (double)j6 + 0.5D - entityIn.z;
                    label567:

                    for (int i7 = this.worldServerInstance.getHeight() - 1; i7 >= 0; --i7)
                    {
                        if (this.worldServerInstance.isAir(blockpos$mutableblockpos.set(l5, i7, j6)))
                        {
                            while (i7 > 0 && this.worldServerInstance.isAir(blockpos$mutableblockpos.set(l5, i7 - 1, j6)))
                            {
                                --i7;
                            }

                            for (int k7 = i2; k7 < i2 + 2; ++k7)
                            {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10)
                                {
                                    for (int j11 = -1; j11 < 4; ++j11)
                                    {
                                        int j12 = l5 + (j10 - 1) * j8;
                                        int i13 = i7 + j11;
                                        int j13 = j6 + (j10 - 1) * j9;
                                        blockpos$mutableblockpos.set(j12, i13, j13);

                                        if (j11 < 0 && !this.worldServerInstance.getBlockState(blockpos$mutableblockpos).getMaterial().method_15799() || j11 >= 0 && !this.worldServerInstance.isAir(blockpos$mutableblockpos))
                                        {
                                            continue label567;
                                        }
                                    }
                                }

                                double d6 = (double)i7 + 0.5D - entityIn.y;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;

                                if (d0 < 0.0D || d8 < d0)
                                {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = k7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;

        if (l1 % 4 >= 2)
        {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D)
        {
            j1 = MathHelper.clamp(j1, 70, this.worldServerInstance.getHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7)
            {
                for (int l7 = 1; l7 < 3; ++l7)
                {
                    for (int k8 = -1; k8 < 3; ++k8)
                    {
                        int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
                        int k10 = k2 + k8;
                        int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        this.worldServerInstance.setBlockState(new BlockPos(k9, k10, k11), flag ? Blocks.GLOWSTONE.getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        BlockState iblockstate = BlocksAether.aether_portal.getDefaultState().with(PortalBlock.field_11310, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for (int i8 = 0; i8 < 4; ++i8)
        {
            for (int l8 = 0; l8 < 4; ++l8)
            {
                for (int l9 = -1; l9 < 4; ++l9)
                {
                    int l10 = i6 + (l8 - 1) * l6;
                    int l11 = k2 + l9;
                    int k12 = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
                    this.worldServerInstance.setBlockState(new BlockPos(l10, l11, k12), flag1 ? Blocks.GLOWSTONE.getDefaultState() : iblockstate, flag1 ? 3 : 18);
                }
            }
        }

        return true;
    }

}