package com.legacy.aether.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.Heightmap;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.world.biome.AetherBiomeProvider;
import com.legacy.aether.world.info.AetherGenSettings;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ChunkGeneratorAether extends ChunkGenerator<AetherGenSettings> {

    private OctaveSimplexNoiseSampler noiseGen1, perlinNoise1;

    private final AetherGenSettings chunkGenSettings;

    private final AetherBiomeProvider biomeProvider;

    private final Random random;

    public ChunkGeneratorAether(IWorld world, BiomeSource biomeProvider) {
        super(world, biomeProvider);

        this.random = new Random(world.getSeed());
        this.biomeProvider = new AetherBiomeProvider();
        this.chunkGenSettings = new AetherGenSettings();

        this.noiseGen1 = new OctaveSimplexNoiseSampler(this.random, 16);
        this.perlinNoise1 = new OctaveSimplexNoiseSampler(this.random, 8);

    }

    @Override
    public AetherGenSettings getSettings() {
        return this.chunkGenSettings;
    }

    @Override
    public double[] generateNoiseRegion(int i, int i1) {
        return new double[3366];
    }

    public void setBlocksInChunk(int x, int z, Chunk chunk) {
        BlockPos.MutableBlockPos mutedPos = new BlockPos.MutableBlockPos();

        double[] buffer = this.setupNoiseGenerators(x * 2, z * 2);

        for (int i1 = 0; i1 < 2; i1++) {
            for (int j1 = 0; j1 < 2; j1++) {
                for (int k1 = 0; k1 < 32; k1++) {
                    double d1 = buffer[(i1 * 3 + j1) * 33 + k1];
                    double d2 = buffer[(i1 * 3 + (j1 + 1)) * 33 + k1];
                    double d3 = buffer[((i1 + 1) * 3 + j1) * 33 + k1];
                    double d4 = buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + k1];

                    double d5 = (buffer[(i1 * 3 + j1) * 33 + (k1 + 1)] - d1) * 0.25D;
                    double d6 = (buffer[(i1 * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d2) * 0.25D;
                    double d7 = (buffer[((i1 + 1) * 3 + j1) * 33 + (k1 + 1)] - d3) * 0.25D;
                    double d8 = (buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d4) * 0.25D;

                    for (int l1 = 0; l1 < 4; l1++) {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for (int i2 = 0; i2 < 8; i2++) {
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for (int k2 = 0; k2 < 8; k2++) {
                                int x1 = i2 + i1 * 8;
                                int y = l1 + k1 * 4;
                                int z1 = k2 + j1 * 8;

                                BlockState filler = Blocks.AIR.getDefaultState();

                                if (d15 > 0.0D) {
                                    filler = BlocksAether.holystone.getDefaultState();
                                }

                                chunk.setBlockState(mutedPos.setPos(x1, y, z1), filler, false);

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

    private double[] setupNoiseGenerators(int x, int z) {
        double[] buffer = new double[3366];

        double d = 1368.824D;
        double d1 = 684.41200000000003D;

        double[] pnr = this.perlinNoise1.func_202647_a(x, 0, z, 3, 33, 3, d / 80D, d1 / 160D, d / 80D);
        double[] ar = this.noiseGen1.func_202647_a(x, 0, z, 3, 33, 3, d, d1, d);
        double[] br = this.noiseGen1.func_202647_a(x, 0, z, 3, 33, 3, d, d1, d);

        int id = 0;

        for (int j2 = 0; j2 < 3; j2++) {
            for (int l2 = 0; l2 < 3; l2++) {
                for (int j3 = 0; j3 < 33; j3++) {
                    double d8;

                    double d10 = ar[id] / 512D;
                    double d11 = br[id] / 512D;
                    double d12 = (pnr[id] / 10D + 1.0D) / 2D;

                    if (d12 < 0.0D) {
                        d8 = d10;
                    } else if (d12 > 1.0D) {
                        d8 = d11;
                    } else {
                        d8 = d10 + (d11 - d10) * d12;
                    }

                    d8 -= 8D;

                    if (j3 > 33 - 32) {
                        double d13 = (float) (j3 - (33 - 32)) / ((float) 32 - 1.0F);
                        d8 = d8 * (1.0D - d13) + -30D * d13;
                    }

                    if (j3 < 8) {
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
    public int getGroundHeight() {
        return 20;
    }

    @Override
    public int getMaxHeight() {
        return 256;
    }

    @Override
    public List<Biome.SpawnEntry> getEntitySpawnList(EntityCategory typeIn, BlockPos posIn) {
        Biome biome = this.world.getBiome(posIn);

        return biome.getEntitySpawnList(typeIn);
    }

    @Override
    public void buildSurface(Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();

        int x = chunkPos.x;
        int z = chunkPos.z;

        this.random.setSeed(chunkPos.toLong());

        Biome[] biomes = this.biomeProvider.getBiomes(x * 16, z * 16, 16, 16);

        chunk.setBiomes(biomes);

        this.setBlocksInChunk(x, z, chunk);

        chunk.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);

        this.buildSurface(chunk, biomes, this.random, 0);

        chunk.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);

        chunk.setStatus(ChunkStatus.BASE);
    }

    @Override
    public void spawnMobs(WorldGenRegion regionIn) 
    {
        int i = regionIn.getMainChunkX();
        int j = regionIn.getMainChunkZ();
        Biome biome = regionIn.getChunk(i, j).getBiomes()[0];

        this.random.setSeed(regionIn.getSeed());

        WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i, j, this.random);
    }

    @Override
    public int spawnMobs(World arg0, boolean arg1, boolean arg2) {
        return 0;
    }

}