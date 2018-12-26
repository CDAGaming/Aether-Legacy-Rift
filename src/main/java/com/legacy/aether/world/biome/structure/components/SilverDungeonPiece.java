package com.legacy.aether.world.biome.structure.components;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.world.biome.structure.AetherGenUtils;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MutableBoundingBox;

public class SilverDungeonPiece extends AetherStructurePiece
{

	private int[][][] rooms = new int[3][3][3];

	private int firstStaircaseZ;

	private int secondStaircaseZ;

	private int finalStaircaseZ;

	private int xTendency;

	private int zTendency;

	public SilverDungeonPiece()
	{
		
	}

	public SilverDungeonPiece(int chunkX, int chunkZ)
	{
		this.setCoordBaseMode(EnumFacing.NORTH);
		this.boundingBox = new MutableBoundingBox(chunkX, 100, chunkZ, chunkX + 150, 190, chunkZ + 150);
	}

	public void setStaircasePosition(int first, int second, int third)
	{
		this.firstStaircaseZ = first;
		this.secondStaircaseZ = second;
		this.finalStaircaseZ = third;
	}

	public void setCloudTendencies(int xTendency, int zTendency)
	{
		this.xTendency = xTendency;
		this.zTendency = zTendency;
	}

	@Override
	public boolean generate()
	{
		for (int tries = 0; tries < 150; ++tries)
		{
			AetherGenUtils.generateClouds(this, BlocksAether.cold_aercloud.getDefaultState(), false, 10, 52 + this.random.nextInt(77), 8, 52 + this.random.nextInt(50));
		}

		this.replaceSolid = true;

		System.out.println(this.boundingBox);

		this.setBlocks(BlocksAether.aether_dirt.getDefaultState(), BlocksAether.aether_dirt.getDefaultState(), 10);
		this.addHollowBox(1, 1, 1, 149, 1, 149);

		this.replaceAir = true;

		this.setBlocks(BlocksAether.holystone.getDefaultState(), BlocksAether.mossy_holystone.getDefaultState(), 30);

		this.addSolidBox(60, 10, 60, 55, 5, 30);

		for (int x = 0; x < 55; x += 4)
		{
			this.generatePillar(60 + x, 15, 60 + 0, 14);
			this.generatePillar(60 + x, 15, 60 + 27, 14);
		}

		for (int z = 0; z < 12; z += 4)
		{
			this.generatePillar(60 + 0, 15, 60 + z, 14);
			this.generatePillar(60 + 52, 15, 60 + z, 14);
		}

		for (int z = 19; z < 30; z += 4)
		{
			this.generatePillar(60 + 0, 15, 60 + z, 14);
			this.generatePillar(60 + 52, 15, 60 + z, 14);
		}

		//this.addLineY(1, 1, 1, 100);
		//this.fillWithBlocks(this.world, this.boundingBox, 1, 1, 1, 20, 2, 20, BlocksAether.red_dyed_aercloud.getDefaultState(), Blocks.AIR.getDefaultState(), false);
		//this.setBlock(1, 1, 1, BlocksAether.red_dyed_aercloud.getDefaultState());

		/*this.setStructureOffset(21, 17, 20);

		for (int tries = 0; tries < 100; ++tries)
		{
			//spawn aerclouds
		}

		this.setStructureOffset(31, 24, 30);*/

		return true;
	}

	public void generateGoldenOakSapling(int x, int y, int z)
	{
		
	}

	public void generateChandelier(int x, int y, int z, int height)
	{

	}

	public void generatePillar(int x, int y, int z, int yRange)
	{
		this.setBlocks(BlocksAether.locked_angelic_stone.getDefaultState(), BlocksAether.locked_light_angelic_stone.getDefaultState(), 20);
		this.addPlaneY(x, y, z, 3, 3);
		this.addPlaneY(x, y + yRange, z, 3, 3);
		this.setBlocks(BlocksAether.pillar.getDefaultState(), BlocksAether.pillar.getDefaultState(), 0);
		this.addLineY(x + 1, y, z + 1, yRange - 1);
		this.setBlockWithOffset(x + 1, y + (yRange - 1), z + 1, BlocksAether.pillar_top.getDefaultState());
	}

	public void generateStaircase(int x, int y, int z, int height)
	{
		
	}

	public void generateDoorX(int x, int y, int z, int yF, int zF)
	{
		
	}

	public void generateDoorZ(int z, int x, int y, int xF, int yF)
	{
		
	}

}