package com.legacy.aether.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;

import java.util.Random;

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
import net.minecraft.world.PortalForcer;
import net.minecraft.world.chunk.ChunkPos;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import com.mojang.datafixers.util.Pair;

public class TeleporterAether extends PortalForcer
{

	public final boolean portalSpawn;

	private final Random random;

	private final ServerWorld worldServerInstance;

	private final Long2ObjectMap<AetherPortalPosition> destinations = new Long2ObjectOpenHashMap<AetherPortalPosition>(4096);

	private Entity data;

	public TeleporterAether(boolean portalSpawn, ServerWorld serverIn)
	{
		super(serverIn);

		this.portalSpawn = portalSpawn;
		this.worldServerInstance = serverIn;
        this.random = new Random(serverIn.getSeed());
	}

	@Override
	public boolean method_8653(Entity entity, float yaw)
	{
		this.data = entity;

		Direction direction_1 = entity.method_5843();
		long long_1 = ChunkPos.toLong(MathHelper.floor(entity.x), MathHelper.floor(entity.z));
		Pair<Vec3d, Pair<Vec3d, Integer>> pair_1 = this.method_18475(new BlockPos(entity), entity.getVelocity(), long_1, direction_1, 0, 0);

		if (pair_1 == null)
		{
			return false;
		}
		else
		{
			Vec3d vec3d_2 = (Vec3d) pair_1.getFirst();
			Vec3d vec3d_3 = (Vec3d) ((Pair<Vec3d, Integer>) pair_1.getSecond()).getFirst();

			entity.setVelocity(vec3d_3);
			entity.yaw = yaw + (float) (Integer) ((Pair<Vec3d, Integer>) pair_1.getSecond()).getSecond();

			if (entity instanceof ServerPlayerEntity)
			{
				((ServerPlayerEntity) entity).networkHandler.teleportRequest(vec3d_2.x, vec3d_2.y, vec3d_2.z, entity.yaw, entity.pitch);
				((ServerPlayerEntity) entity).networkHandler.syncWithPlayerPosition();
			}
			else
			{
				entity.setPositionAndAngles(vec3d_2.x, vec3d_2.y, vec3d_2.z, entity.yaw, entity.pitch);
			}

			return true;
		}
	}

	@Override
	public Pair<Vec3d, Pair<Vec3d, Integer>> method_18475(BlockPos entityPos, Vec3d velocity, long chunkPos, Direction direction, double x, double y)
	{
		boolean shouldBeCreated = true;
		BlockPos portalPos = null;

		if (this.destinations.containsKey(chunkPos))
		{
			AetherPortalPosition portalForcer$class_1947_1 = (AetherPortalPosition) this.destinations.get(chunkPos);
			portalPos = portalForcer$class_1947_1;
			portalForcer$class_1947_1.lastUpdateTime = this.worldServerInstance.getTime();
			shouldBeCreated = false;
		}
		else
		{
			double double_3 = Double.MAX_VALUE;

			for (int checkX = -128; checkX <= 128; ++checkX)
			{
				BlockPos blockPos_4;

				for (int checkZ = -128; checkZ <= 128; ++checkZ)
				{
					for (BlockPos blockPos_3 = entityPos.add(checkX, this.worldServerInstance.getEffectiveHeight() - 1 - entityPos.getY(), checkZ); blockPos_3.getY() >= 0; blockPos_3 = blockPos_4)
					{
						blockPos_4 = blockPos_3.down();

						if (this.worldServerInstance.getBlockState(blockPos_3).getBlock() == BlocksAether.aether_portal)
						{
							for (blockPos_4 = blockPos_3.down(); this.worldServerInstance.getBlockState(blockPos_4).getBlock() == BlocksAether.aether_portal; blockPos_4 = blockPos_4.down())
							{
								blockPos_3 = blockPos_4;
							}

							double double_4 = blockPos_3.squaredDistanceTo(entityPos);

							if (double_3 < 0.0D || double_4 < double_3)
							{
								double_3 = double_4;
								portalPos = blockPos_3;
							}
						}
					}
				}
			}
		}

		if (portalPos == null)
		{
			return null;
		}
		else
		{
			if (shouldBeCreated)
			{
				this.destinations.put(chunkPos, new AetherPortalPosition(portalPos, this.worldServerInstance.getTime()));
			}

			BlockPattern.Result blockPattern$Result_1 = ((BlockAetherPortal) BlocksAether.aether_portal).method_10350(this.worldServerInstance, portalPos);

	        double double_1 = blockPattern$Result_1.getForwards().getAxis() == Direction.Axis.X ? (double)blockPattern$Result_1.getFrontTopLeft().getZ() : (double)blockPattern$Result_1.getFrontTopLeft().getX();
	        double double_2 = Math.abs(MathHelper.method_15370((blockPattern$Result_1.getForwards().getAxis() == Direction.Axis.X ? portalPos.getZ() + 0.5D : portalPos.getX() + 0.5D) - (double)(blockPattern$Result_1.getForwards().rotateYClockwise().getDirection() == Direction.AxisDirection.NEGATIVE ? 1 : 0), double_1, double_1 - (double)blockPattern$Result_1.getWidth()));
	        double double_3 = MathHelper.method_15370(portalPos.getY() - 1.0D, (double)blockPattern$Result_1.getFrontTopLeft().getY(), (double)(blockPattern$Result_1.getFrontTopLeft().getY() - blockPattern$Result_1.getHeight()));

			return blockPattern$Result_1.method_18478(blockPattern$Result_1.getForwards(), portalPos, double_3, velocity, double_2);
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

        BlockState iblockstate = BlocksAether.aether_portal.getDefaultState().with(PortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

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

	@Override
	public void tick(long time)
	{
		if (time % 100L == 0L)
		{
			long long_2 = time - 300L;
			ObjectIterator<AetherPortalPosition> iterator = this.destinations.values().iterator();

			while (true)
			{
				AetherPortalPosition portalPosition;

				do
				{
					if (!iterator.hasNext())
					{
						return;
					}

					portalPosition = (AetherPortalPosition) iterator.next();
				}
				while (portalPosition != null && portalPosition.lastUpdateTime >= long_2);

				iterator.remove();
			}
		}
	}

}