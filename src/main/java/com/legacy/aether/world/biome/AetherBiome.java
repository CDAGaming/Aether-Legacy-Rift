package com.legacy.aether.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TallGrassConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.world.biome.builder.AetherSurfaceBuilder;
import com.legacy.aether.world.biome.builder.AetherSurfaceBuilderConfig;
import com.legacy.aether.world.biome.feature.AercloudFeature;
import com.legacy.aether.world.biome.feature.AetherFlowersFeature;
import com.legacy.aether.world.biome.feature.AetherLakeFeature;
import com.legacy.aether.world.biome.feature.AetherLiquidFeature;
import com.legacy.aether.world.biome.feature.GoldenOakTreeFeature;
import com.legacy.aether.world.biome.feature.QuicksoilFeature;
import com.legacy.aether.world.biome.feature.SkyrootTreeFeature;
import com.legacy.aether.world.biome.feature.config.AercloudConfig;

public class AetherBiome extends Biome
{

	private static final AetherSurfaceBuilder AETHER_SURFACE_BUILDER = new AetherSurfaceBuilder();

	private static final AetherSurfaceBuilderConfig AETHER_SURFACE = new AetherSurfaceBuilderConfig();

	public AetherBiome()
	{
		super(new BiomeBuilder().surfaceBuilder(new CompositeSurfaceBuilder<>(AetherBiome.AETHER_SURFACE_BUILDER, AetherBiome.AETHER_SURFACE)).precipitation(RainType.NONE).category(Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.0F).waterColor(0xA9F7FF).waterFogColor(0xA9F7FF).parent(null));

		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(new AetherLakeFeature(), new LakesConfig(Blocks.WATER), Biome.LAKE_WATER, new LakeChanceConfig(10)));
		this.addFeature(Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(new QuicksoilFeature(), IFeatureConfig.NO_FEATURE_CONFIG, Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AetherFlowersFeature(BlocksAether.white_flower.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, Biome.AT_SURFACE_RANDOM_COUNT, new FrequencyConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AetherFlowersFeature(BlocksAether.purple_flower.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG, Biome.AT_SURFACE_RANDOM_COUNT, new FrequencyConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.TALL_GRASS, new TallGrassConfig(Blocks.GRASS.getDefaultState()), Biome.TWICE_SURFACE, new FrequencyConfig(2)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.TALL_GRASS.getDefaultState()), Biome.TWICE_SURFACE, new FrequencyConfig(7)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new SkyrootTreeFeature(), new NoFeatureConfig(), Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(1)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new GoldenOakTreeFeature(), new NoFeatureConfig(), Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(40)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.cold_aercloud.getDefaultState(), false, 16, 64), Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(14)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.blue_aercloud.getDefaultState(), false, 8, 32), Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(26)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AercloudFeature(), new AercloudConfig(BlocksAether.golden_aercloud.getDefaultState(), false, 4, 96), Biome.AT_SURFACE_WITH_CHANCE, new ChanceConfig(50)));
		this.addFeature(Decoration.VEGETAL_DECORATION, createCompositeFeature(new AetherLiquidFeature(), new LiquidsConfig(Fluids.WATER), Biome.COUNT_RANGE, new CountRangeConfig(50, 20, 20, 100)));

		this.addSpawn(EnumCreatureType.WATER_CREATURE, new SpawnListEntry(EntityType.COD, 10, 1, 2));
		this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry(EntityTypesAether.AECHOR_PLANT, 8, 3, 3));
		this.addSpawn(EnumCreatureType.MONSTER, new SpawnListEntry(EntityTypesAether.COCKATRICE, 4, 4, 4));
		this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry(EntityTypesAether.MOA, 10, 3, 3));
		this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry(EntityTypesAether.PHYG, 12, 4, 4));
		this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry(EntityTypesAether.FLYING_COW, 10, 4, 4));
		this.addSpawn(EnumCreatureType.CREATURE, new SpawnListEntry(EntityTypesAether.AERBUNNY, 11, 3, 3));
	}

	@Override
    public int getSkyColorByTemp(float currentTemperature)
    {
    	return 0xC0C0FF; // Lavender Blue
    }

	@Override
    public int getGrassColor(BlockPos pos)
    {
        return 0xb1ffcb;
    }

	@Override
    public int getFoliageColor(BlockPos pos)
    {
        return 0xb1ffcb;
    }

}