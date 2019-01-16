package com.legacy.aether.world.dimension;

import com.legacy.aether.world.WorldAether;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorSettings;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class TheAetherDimension extends Dimension
{

	private final float[] sunriseSunsetColors = new float[4];

	public TheAetherDimension(World worldIn, DimensionType typeIn)
	{
		super(worldIn, typeIn);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator()
	{
		return WorldAether.AETHER_ISLANDS.create(this.world, BiomeSourceType.FIXED.applyConfig(new FixedBiomeSourceConfig().method_8782(WorldAether.AETHER_HIGHLANDS)), new AetherChunkGeneratorSettings());
	}

	@Override
	public BlockPos method_12452(ChunkPos var1, boolean var2) //findSpawn
	{
		return null;
	}

	@Override
	public BlockPos method_12444(int var1, int var2, boolean var3) //findSpawn
	{
		return null;
	}

	@Override
	public float getSkyAngle(long var1, float var3)
	{
		int int_1 = (int)(var1 % 24000L);
	    float float_2 = ((float)int_1 + var1) / 24000.0F - 0.25F;

	    if (float_2 < 0.0F)
	    {
	         ++float_2;
	    }

	    if (float_2 > 1.0F)
	    {
	         --float_2;
	    }

	    float float_3 = float_2;
	    float_2 = 1.0F - (float)((Math.cos((double)float_2 * 3.141592653589793D) + 1.0D) / 2.0D);
	    float_2 = float_3 + (float_2 - float_3) / 3.0F;

	    return float_2;
	}

	@Override
	public boolean hasVisibleSky()
	{
		return true;
	}

	@Override
	public Vec3d method_12445(float var1, float var2)
	{
		int i = 0x8080a0;

		float f2 = MathHelper.cos(var1 * 3.141593F * 2.0F) * 2.0F + 0.5F;

		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);

		float f3 = (i >> 16 & 0xff) / 255F;
		float f4 = (i >> 8 & 0xff) / 255F;
		float f5 = (i & 0xff) / 255F;

		f3 *= f2 * 0.94F + 0.06F;
		f4 *= f2 * 0.94F + 0.06F;
		f5 *= f2 * 0.91F + 0.09F;

		return new Vec3d(f3, f4, f5);
	}

	@Environment(EnvType.CLIENT)
	public float[] method_12446(float var1, float var2)
	{
		float f2 = 0.4F;
		float f3 = MathHelper.cos(var1 * 3.141593F * 2.0F) - 0.0F;
		float f4 = -0F;

		if (f3 >= f4 - f2 && f3 <= f4 + f2)
		{
			float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
			f6 *= f6;
			this.sunriseSunsetColors[0] = f5 * 0.3F + 0.1F;
			this.sunriseSunsetColors[1] = f5 * f5 * 0.7F + 0.2F;
			this.sunriseSunsetColors[2] = f5 * f5 * 0.7F + 0.2F;
			this.sunriseSunsetColors[3] = f6;

			return this.sunriseSunsetColors;
		}

		return null;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float method_12455()
	{
		return 8.0F;
	}

	@Override
	public boolean method_12448()
	{
		return true;
	}

	@Override
	public boolean method_12453(int var1, int var2)
	{
		return false;
	}

	@Override
	public DimensionType getType()
	{
		return WorldAether.THE_AETHER;
	}

}