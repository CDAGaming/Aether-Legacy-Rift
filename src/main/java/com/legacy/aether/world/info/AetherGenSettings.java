package com.legacy.aether.world.info;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.IChunkGenSettings;

public class AetherGenSettings implements IChunkGenSettings
{

	@Override
	public int func_204026_h()
	{
		return 0;
	}

	@Override
	public int func_204748_h()
	{
		return 0;
	}

	@Override
	public int func_211727_m() 
	{
		return 0;
	}

	@Override
	public int func_211730_k() 
	{
		return 0;
	}

	@Override
	public int func_211731_i() 
	{
		return 0;
	}

	@Override
	public int getBiomeFeatureDistance()
	{
		return 0;
	}

	@Override
	public IBlockState getDefaultBlock()
	{
		return BlocksAether.holystone.getDefaultState();
	}

	@Override
	public IBlockState getDefaultFluid() 
	{
		return Blocks.WATER.getDefaultState();
	}

	@Override
	public int getEndCityDistance()
	{
		return 0;
	}

	@Override
	public int getEndCitySeparation() {
		return 0;
	}

	@Override
	public int getMansionDistance() 
	{
		return 0;
	}

	@Override
	public int getMansionSeparation() {
		return 0;
	}

	@Override
	public int getOceanMonumentSeparation()
	{
		return 0;
	}

	@Override
	public int getOceanMonumentSpacing()
	{
		return 0;
	}

	@Override
	public int getStrongholdCount() 
	{
		return 0;
	}

	@Override
	public int getStrongholdDistance() 
	{
		return 0;
	}

	@Override
	public int getStrongholdSpread() 
	{
		return 0;
	}

	@Override
	public int getVillageDistance() 
	{
		return 0;
	}

	@Override
	public int getVillageSeparation() {
		return 0;
	}

}