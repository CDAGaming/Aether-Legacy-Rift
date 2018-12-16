package com.legacy.aether.world;

import com.legacy.aether.world.biome.AetherBiomeProvider;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class AetherDimension extends Dimension
{
	private final float[] colorsSunriseSunset = new float[4];

	public AetherDimension(World world, DimensionType dimensionType) {
		super(world, dimensionType);
	}

	@Override
	public boolean hasVisibleSky()
	{
		return true;
	}

	@Override
	public DimensionType getType()
	{
		return WorldAether.AETHER;
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator()
	{
		return new ChunkGeneratorAether(this.world, new AetherBiomeProvider());
	}

	@Override
	public float[] method_12446(float celestialAngle, float partialTicks)
	{
		float f3 = MathHelper.cos(celestialAngle * 3.141593F * 2.0F) - 0.0F;

		if (f3 >= 0.4F && f3 <= 0.4F)
		{
			float f5 = f3 / 0.4F * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;

			f6 *= f6;

			this.colorsSunriseSunset[0] = f5 * 0.3F + 0.1F;
			this.colorsSunriseSunset[1] = f5 * f5 * 0.7F + 0.2F;
			this.colorsSunriseSunset[2] = f5 * f5 * 0.7F + 0.2F;
			this.colorsSunriseSunset[3] = f6;

			return this.colorsSunriseSunset;
		}

		return null;
	}

	@Override
	public float method_12464(long worldTime, float partialTicks)
	{
        int i = (int)(worldTime % 24000L);
        float f = ((float)i + partialTicks) / 24000.0F - 0.25F;

        if (f < 0.0F)
        {
            ++f;
        }

        if (f > 1.0F)
        {
            --f;
        }

        float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0D) / 2.0D);
        f = f + (f1 - f) / 3.0F;
        return f;
	}

	@Override
	public boolean method_12448()
	{
		return false;
	}

	@Override
	public boolean method_12449()
	{
		return false;
	}

	@Override
	public boolean method_12453(int arg0, int arg1)
	{
		return false;
	}

    @Override
    public float method_12455()
    {
        return 8F;
    }

	@Override
	public Vec3d method_12445(float f, float d1)
	{
		int i = 0x8080a0;

		float f2 = MathHelper.cos(f * 3.141593F * 2.0F) * 2.0F + 0.5F;
		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}
		float f3 = (i >> 16 & 0xff) / 255F;
		float f4 = (i >> 8 & 0xff) / 255F;
		float f5 = (i & 0xff) / 255F;
		f3 *= f2 * 0.94F + 0.06F;
		f4 *= f2 * 0.94F + 0.06F;
		f5 *= f2 * 0.91F + 0.09F;

		return new Vec3d(f3, f4, f5);
	}

	@Override
	public BlockPos method_12452(ChunkPos arg0, boolean arg1)
	{
		return null;
	}

	@Override
	public BlockPos method_12444(int arg0, int arg1, boolean arg2)
	{
		return null;
	}

}