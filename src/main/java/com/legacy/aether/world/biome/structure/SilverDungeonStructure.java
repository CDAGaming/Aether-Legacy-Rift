package com.legacy.aether.world.biome.structure;

import java.util.Random;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;

import com.legacy.aether.world.StructuresAether;
import com.legacy.aether.world.WorldAether;
import com.legacy.aether.world.biome.structure.components.SilverDungeonPiece;
import com.legacy.aether.world.biome.structure.config.SilverDungeonConfig;

public class SilverDungeonStructure extends Structure<SilverDungeonConfig>
{

	@Override
	protected boolean hasStartAt(IChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ)
	{
        int i = chunkPosX >> 4;
        int j = chunkPosZ >> 4;

        rand.setSeed((long)(i ^ j << 4) ^ chunkGen.getSeed());
        rand.nextInt();

        if (rand.nextInt(4) != 0)
        {
            return false;
        }
        else if (chunkPosX != (i << 4) + 4 + rand.nextInt(8))
        {
            return false;
        }
        else if (chunkPosZ != (j << 4) + 4 + rand.nextInt(8))
        {
            return false;
        }

        return chunkGen.hasStructure(WorldAether.aetherBiome, StructuresAether.SILVER_DUNGEON);
	}

	@Override
	protected boolean isEnabledIn(IWorld worldIn)
	{
		return true;
	}

	@Override
	protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator, SharedSeedRandom random, int x, int z)
	{
		return new SilverDungeonStructure.Start(worldIn, random, x, z);
	}

	@Override
	protected String getStructureName()
	{
		return "aether_legacy:silver_dungeon";
	}

	@Override
	public int getSize()
	{
		return 8;
	}

    public static class Start extends StructureStart
    {

        public Start()
        {

        }

        public Start(IWorld world, SharedSeedRandom seed, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ, WorldAether.aetherBiome, seed, world.getSeed());

            this.components.add(new SilverDungeonPiece((chunkX << 4) + 2, (chunkZ << 4) + 2));

            this.recalculateStructureSize(world);
        }

    }

}