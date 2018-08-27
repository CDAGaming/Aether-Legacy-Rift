package com.legacy.aether.world.biome.structure.components;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import com.legacy.aether.blocks.BlocksAether;

public class ColdAercloudPiece extends StructurePiece
{

	private int xTendency, zTendency;

	private int randX, randY, randZ;

    public ColdAercloudPiece()
    {

    }

	public ColdAercloudPiece(SharedSeedRandom random, int chunkX, int chunkY, int chunkZ)
	{
        this.setCoordBaseMode(EnumFacing.NORTH);

        this.randX = random.nextInt(4);
        this.randY = random.nextInt(1);
        this.randZ = random.nextInt(4);
        this.xTendency = random.nextInt(3) - 1;
        this.zTendency = random.nextInt(3) - 1;

        this.boundingBox = new MutableBoundingBox(chunkX, chunkY, chunkZ, chunkX + 100, chunkY + 10, chunkZ + 100);
	}

	@Override
	public boolean addComponentParts(IWorld worldIn, Random randIn, MutableBoundingBox boundingBoxIn, ChunkPos chunkPosIn)
	{
		/*int x = 0, y = 0, z = 0;
        int xTendency = randIn.nextInt(3) - 1;
        int zTendency = randIn.nextInt(3) - 1;

        for (int n = 0; n < 16; ++n)
        {
            x += randIn.nextInt(3) - 1 + xTendency;

            if (randIn.nextBoolean())
            {
                y += randIn.nextInt(3) - 1;
            }

            z += randIn.nextInt(3) - 1 + zTendency;

            for (int x1 = x; x1 < x + randIn.nextInt(4) + 3; ++x1)
            {
                for (int y1 = y; y1 < y + randIn.nextInt(1) + 2; ++y1)
                {
                    for (int z1 = z; z1 < z + randIn.nextInt(4) + 3; ++z1)
                    {
                        if (this.getBlockStateFromPos(worldIn, x1, y1, z1, boundingBoxIn).getBlock() == Blocks.AIR)
                        {
                        	if (Math.abs(x1 - x) + Math.abs(y1 - y) + Math.abs(z1 - z) < 4 + randIn.nextInt(2))
                        	{
                        		this.setBlockState(worldIn, BlocksAether.cold_aercloud.getDefaultState(), x1, y1, z1, boundingBoxIn);
                        	}
                        }
                    }
                }
            }
        }

        return true;*/
		int x = 0, y = 0, z = 0;

        for (int n = 0; n < 16; ++n)
        {
            x += randIn.nextInt(3) - 1 + this.xTendency;

            if (randIn.nextBoolean())
            {
                y += randIn.nextInt(3) - 1;
            }

            z += randIn.nextInt(3) - 1 + this.zTendency;

            for (int x1 = x; x1 < x + this.randX + 3; ++x1)
            {
                for (int y1 = y; y1 < y + this.randY + 2; ++y1)
                {
                    for (int z1 = z; z1 < z + this.randZ + 3; ++z1)
                    {
                        if (this.getBlockStateFromPos(worldIn, x1, y1, z1, boundingBoxIn).isAir())
                        {
                        	if (Math.abs(x1 - x) + Math.abs(y1 - y) + Math.abs(z1 - z) < 4 + randIn.nextInt(2))
                        	{
                        		this.setBlockState(worldIn, BlocksAether.cold_aercloud.getDefaultState(), x1, y1, z1, boundingBoxIn);
                        	}
                        }
                    }
                }
            }
        }

        return true;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("xTendency", this.xTendency);
		compound.setInteger("zTendency", this.zTendency);

		compound.setInteger("randX", this.randX);
		compound.setInteger("randY", this.randY);
		compound.setInteger("randZ", this.randZ);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound compound, TemplateManager templateIn) 
	{
        this.xTendency = compound.getInteger("zTendency");
        this.zTendency = compound.getInteger("zTendency");

        this.randX = compound.getInteger("randX");
        this.randY = compound.getInteger("randY");
        this.randZ = compound.getInteger("randZ");
	}

}