package com.legacy.aether.world.biome.structure.components;

import java.util.Random;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class QuicksoilPiece extends StructurePiece
{

	public QuicksoilPiece()
	{
		
	}

	public QuicksoilPiece(SharedSeedRandom random, int chunkX, int chunkY, int chunkZ)
	{
        this.setCoordBaseMode(EnumFacing.NORTH);

        this.boundingBox = new MutableBoundingBox(chunkX, chunkY, chunkZ, chunkX + 20, chunkY + 1, chunkZ + 20);
	}

	@Override
	public boolean addComponentParts(IWorld worldIn, Random randIn, MutableBoundingBox boundingBoxIn, ChunkPos chunkPosIn)
	{
		if (randIn.nextInt(10) == 0)
		{
			for (int x = 3; x < 12; x++)
			{
				for (int z = 3; z < 12; z++)
				{
					for (int n = 3; n < 48; n++)
					{
						if (this.getBlockStateFromPos(worldIn, x, n, z, boundingBoxIn).getBlock() == Blocks.AIR && this.getBlockStateFromPos(worldIn, x, n + 1, z, boundingBoxIn).getBlock() == BlocksAether.aether_grass && this.getBlockStateFromPos(worldIn, x, n + 2, z, boundingBoxIn).getBlock() == Blocks.AIR)
						{
							this.generate(worldIn, x, n, z, boundingBoxIn);
							n += 128;
						}
					}
				}
			}
		}

        return true;
	}

    private void generate(IWorld worldIn, int posX, int posY, int posZ, MutableBoundingBox boundingBoxIn)
    {
		for(int x = posX - 3; x < posX + 4; x++)
		{
			for(int z = posZ - 3; z < posZ + 4; z++)
			{
				if(this.getBlockStateFromPos(worldIn, x, posY, z, boundingBoxIn).getBlock() == Blocks.AIR && ((x - posX) * (x - posX) + (z - posZ) * (z - posZ)) < 12)
				{
					this.setBlockState(worldIn, BlocksAether.quicksoil.getDefaultState(), x, posY, z, boundingBoxIn);
				}
			}
		}
    }

	@Override
	protected void writeStructureToNBT(NBTTagCompound compound) 
	{

	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound compound, TemplateManager templateIn) 
	{

	}

}