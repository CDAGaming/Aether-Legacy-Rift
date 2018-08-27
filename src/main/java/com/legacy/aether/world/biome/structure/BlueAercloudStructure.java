package com.legacy.aether.world.biome.structure;

import java.util.Random;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;

import com.legacy.aether.world.WorldAether;
import com.legacy.aether.world.biome.structure.components.BlueAercloudPiece;

public class BlueAercloudStructure extends Structure<NoFeatureConfig>
{

	public BlueAercloudStructure()
	{
		
	}

	@Override
	protected boolean func_202372_a(IChunkGenerator<?> chunkGeneratorIn, Random randIn, int chunkX, int chunkZ) 
	{
		return randIn.nextInt(26) == 0;
	}

	@Override
	public int func_202367_b() 
	{
		return 9;
	}

	@Override
	protected String getStructureName()
	{
		return "aether_legacy:blue_aercloud";
	}

	@Override
	protected boolean isEnabled(IWorld worldIn) 
	{
		return true;
	}

	@Override
	protected StructureStart func_202369_a(IWorld worldIn, IChunkGenerator<?> chunkGeneratorIn, SharedSeedRandom randIn, int chunkX, int chunkZ) 
	{
		return new Start(worldIn, randIn, chunkX, chunkZ);
	}

	public static class Start extends StructureStart
	{
		public Start()
		{
			
		}

		public Start(IWorld worldIn, SharedSeedRandom randIn, int chunkX, int chunkZ)
		{
            super(chunkX, chunkZ, WorldAether.aetherBiome, randIn, worldIn.getSeed());

            this.create(worldIn, randIn, chunkX, chunkZ);
		}

		private void create(IWorld worldIn, SharedSeedRandom randIn, int chunkX, int chunkZ)
		{
			this.components.add(new BlueAercloudPiece(randIn, (chunkX << 4) + 2, randIn.nextInt(64) + 32, (chunkZ << 4) + 2));

			this.func_202500_a(worldIn);
		}

	}

}