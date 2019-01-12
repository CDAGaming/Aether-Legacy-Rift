package com.legacy.aether.world.gen.chunk;

import net.minecraft.class_2919;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import com.legacy.aether.blocks.BlocksAether;

public class NewAetherChunkGenerator extends ChunkGenerator<AetherChunkGeneratorSettings>
{

	private double[] buffer;

	protected final class_2919 random;

	private final OctavePerlinNoiseSampler field_16574;
	private final OctavePerlinNoiseSampler field_16581;
	private final OctavePerlinNoiseSampler field_16575;

	public NewAetherChunkGenerator(IWorld iWorld_1, BiomeSource biomeSource_1, AetherChunkGeneratorSettings chunkGeneratorSettings_1)
	{
		super(iWorld_1, biomeSource_1, chunkGeneratorSettings_1);

		this.random = new class_2919(this.seed);
		this.field_16574 = new OctavePerlinNoiseSampler(this.random, 16);
		this.field_16581 = new OctavePerlinNoiseSampler(this.random, 16);
		this.field_16575 = new OctavePerlinNoiseSampler(this.random, 8);
	}

	private double sampleNoise(int int_1, int int_2, int int_3, double double_1, double double_2, double double_3, double double_4)
	{
		double double_5 = 0.0D;
		double double_6 = 0.0D;
		double double_7 = 0.0D;
		double double_8 = 1.0D;

		for (int int_4 = 0; int_4 < 16; ++int_4)
		{
			double double_9 = OctavePerlinNoiseSampler.maintainPrecision((double) int_1 * double_1 * double_8);
			double double_10 = OctavePerlinNoiseSampler.maintainPrecision((double) int_2 * double_2 * double_8);
			double double_11 = OctavePerlinNoiseSampler.maintainPrecision((double) int_3 * double_1 * double_8);
			double double_12 = double_2 * double_8;
			double_5 += this.field_16574.getOctave(int_4).sample(double_9, double_10, double_11, double_12, (double) int_2 * double_12) / double_8;
			double_6 += this.field_16581.getOctave(int_4).sample(double_9, double_10, double_11, double_12, (double) int_2 * double_12) / double_8;
			if (int_4 < 8)
			{
				double_7 += this.field_16575.getOctave(int_4).sample(OctavePerlinNoiseSampler.maintainPrecision((double) int_1 * double_3 * double_8), OctavePerlinNoiseSampler.maintainPrecision((double) int_2 * double_4 * double_8), OctavePerlinNoiseSampler.maintainPrecision((double) int_3 * double_3 * double_8), double_4 * double_8, (double) int_2 * double_4 * double_8) / double_8;
			}

			double_8 /= 2.0D;
		}

		return MathHelper.lerpClamped(double_5 / 512.0D, double_6 / 512.0D, (double_7 / 10.0D + 1.0D) / 2.0D);
	}

	@Override
	public void buildSurface(Chunk chunk)
	{
		ChunkPos chunkPos_1 = chunk.getPos();
		int int_1 = chunkPos_1.x;
		int int_2 = chunkPos_1.z;
		class_2919 class_2919_1 = new class_2919();
		class_2919_1.method_12659(int_1, int_2);
		ChunkPos chunkPos_2 = chunk.getPos();
		int int_3 = chunkPos_2.getXStart();
		int int_4 = chunkPos_2.getZStart();
		Biome[] biomes_1 = chunk.getBiomeArray();

		for (int int_5 = 0; int_5 < 16; ++int_5)
		{
			for (int int_6 = 0; int_6 < 16; ++int_6)
			{
				int int_7 = int_3 + int_5;
				int int_8 = int_4 + int_6;

				biomes_1[int_6 * 16 + int_5].buildSurface(class_2919_1, chunk, int_7, int_8, 0, 0.0D, this.getSettings().getDefaultBlock(), this.getSettings().getDefaultFluid(), 63, this.world.getSeed());
			}
		}
	}

	@Override
	public int method_12100()
	{
		return 0;
	}

	@Override
	public void populateNoise(IWorld world, Chunk chunk)
	{
		ChunkPos chunkPos = chunk.getPos();
		int x = chunkPos.x;
		int z = chunkPos.z;
		BlockPos.Mutable mutedBlockPos = new BlockPos.Mutable();

        this.buffer = this.setupNoiseGenerators(this.buffer, x * 2, z * 2);

        for(int i1 = 0; i1 < 2; i1++)
        {
            for(int j1 = 0; j1 < 2; j1++)
            {
                for(int k1 = 0; k1 < 32; k1++)
                {
                    double d1 = this.buffer[(i1 * 3 + j1) * 33 + k1];
                    double d2 = this.buffer[(i1 * 3 + (j1 + 1)) * 33 + k1];
                    double d3 = this.buffer[((i1 + 1) * 3 + j1) * 33 + k1];
                    double d4 = this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + k1];

                    double d5 = (this.buffer[(i1 * 3 + j1) * 33 + (k1 + 1)] - d1) * 0.25D;
                    double d6 = (this.buffer[(i1 * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d2) * 0.25D;
                    double d7 = (this.buffer[((i1 + 1) * 3 + j1) * 33 + (k1 + 1)] - d3) * 0.25D;
                    double d8 = (this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d4) * 0.25D;

                    for(int l1 = 0; l1 < 4; l1++)
                    {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for(int i2 = 0; i2 < 8; i2++)
                        {
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for(int k2 = 0; k2 < 8; k2++)
                            {
                            	mutedBlockPos.set(i2 + i1 * 8, l1 + k1 * 4, k2 + j1 * 8);

                                BlockState filler = Blocks.AIR.getDefaultState();

                                if(d15 > 0.0D)
                                {
                                	filler = BlocksAether.holystone.getDefaultState();
                                }

                                chunk.setBlockState(mutedBlockPos, filler, false);

                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
	}

    private double[] setupNoiseGenerators(double buffer[], int x, int z)
    {
        if(buffer == null)
        {
        	buffer = new double[3366];
        }

        int id = 0;

        for (int j2 = 0; j2 < 3; j2++)
        {
            for (int l2 = 0; l2 < 3; l2++)
            {
                for (int j3 = 0; j3 < 33; j3++)
                {
                    double d8;

                    d8 = this.sampleNoise(x + j2, j3, z + l2, 1368.824D, 684.412D, 17.110300000000002D, 4.277575000000001D);

                    d8 -= 8D;

                    if (j3 > 33 - 32)
                    {
                        double d13 = (float) (j3 - (33 - 32)) / ((float) 32 - 1.0F);
                        d8 = d8 * (1.0D - d13) + -30D * d13;
                    }

                    if (j3 < 8)
                    {
                        double d14 = (float) (8 - j3) / ((float) 8 - 1.0F);
                        d8 = d8 * (1.0D - d14) + -30D * d14;
                    }

                    buffer[id] = d8;

                    id++;
                }
            }
        }

        return buffer;
    }

	@Override
	public int produceHeight(int var1, int var2, Type var3)
	{
		return 0;
	}

}