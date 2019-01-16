package com.legacy.aether.world.gen;

import java.util.Random;

import com.legacy.aether.world.gen.feature.GoldenOakTreeFeature;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class GoldenOakSaplingGenerator extends SaplingGenerator
{

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random rand)
	{
		return new GoldenOakTreeFeature();
	}

}