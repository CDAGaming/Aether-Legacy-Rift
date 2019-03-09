package com.legacy.aether.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DoublePlantFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.world.gen.config.feature.AercloudConfig;
import com.legacy.aether.world.gen.feature.AercloudFeature;
import com.legacy.aether.world.gen.feature.GoldenOakTreeFeature;
import com.legacy.aether.world.gen.feature.QuicksoilFeature;
import com.legacy.aether.world.gen.feature.SkyrootTreeFeature;
import com.legacy.aether.world.gen.surfacebuilder.HighlandsSurfaceBuilder;

public class AetherHighlandsBiome extends Biome
{

	public AetherHighlandsBiome()
	{
		super(new Settings().configureSurfaceBuilder(new HighlandsSurfaceBuilder(), SurfaceBuilder.AIR_CONFIG).category(Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.5F).precipitation(Precipitation.NONE).downfall(0.0F).waterColor(0xA9F7FF).waterFogColor(0xA9F7FF));

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(4)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(BlocksAether.purple_flower.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(BlocksAether.white_flower.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.DOUBLE_PLANT, new DoublePlantFeatureConfig(Blocks.TALL_GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(4)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new SkyrootTreeFeature(), FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP, new CountDecoratorConfig(4)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new GoldenOakTreeFeature(), FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP, new ChanceDecoratorConfig(2)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new QuicksoilFeature(), FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP, new CountDecoratorConfig(2)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.cold_aercloud.getDefaultState(), false, 16, 64), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(10)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.blue_aercloud.getDefaultState(), false, 8, 32), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(20)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.golden_aercloud.getDefaultState(), false, 4, 96), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(30)));

		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityTypesAether.SHEEPUFF, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityTypesAether.PHYG, 12, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityTypesAether.MOA, 10, 3, 3));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityTypesAether.FLYING_COW, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityTypesAether.AERBUNNY, 11, 3, 3));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityTypesAether.COCKATRICE, 10, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityTypesAether.AECHOR_PLANT, 10, 4, 4));
	}

	@Override
	public int getSkyColor(float float_1)
	{
		return 0xC0C0FF; // Lavender Blue
	}

	@Override
	public int getGrassColorAt(BlockPos pos)
	{
		return 0xB1ffCB;
	}

	@Override
	public int getFoliageColorAt(BlockPos pos)
	{
		return 0xB1ffCB;
	}

}