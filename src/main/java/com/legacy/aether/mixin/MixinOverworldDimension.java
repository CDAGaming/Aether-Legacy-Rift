package com.legacy.aether.mixin;

import net.minecraft.datafixers.NbtOps;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.level.LevelGeneratorType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.legacy.aether.world.WorldAether;
import com.legacy.aether.world.gen.chunk.AetherChunkGeneratorSettings;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;

@Mixin(OverworldDimension.class)
public abstract class MixinOverworldDimension extends Dimension
{

    public MixinOverworldDimension(World worldIn, DimensionType typeIn)
    {
		super(worldIn, typeIn);
	}

	@Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
	public void createAetherBuffet(CallbackInfoReturnable<ChunkGenerator<? extends ChunkGeneratorConfig>> info)
	{
		LevelGeneratorType levelGeneratorType = this.world.getLevelProperties().getGeneratorType();

        if (levelGeneratorType == LevelGeneratorType.BUFFET)
        {
			BiomeSource biomeSource_1 = null;
			JsonElement jsonElement_1 = (JsonElement) Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, this.world.getLevelProperties().getGeneratorOptions());
			JsonObject jsonObject_1 = jsonElement_1.getAsJsonObject();

			if (jsonObject_1.has("biome_source") && jsonObject_1.getAsJsonObject("biome_source").has("type") && jsonObject_1.getAsJsonObject("biome_source").has("options"))
			{
				JsonObject jsonObject_2 = jsonObject_1.getAsJsonObject("biome_source").getAsJsonObject("options");
				Biome[] biomes_1 = new Biome[] { Biomes.OCEAN };

				if (jsonObject_2.has("biomes"))
				{
					JsonArray jsonArray_1 = jsonObject_2.getAsJsonArray("biomes");

					biomes_1 = jsonArray_1.size() > 0 ? new Biome[jsonArray_1.size()] : new Biome[] { Biomes.OCEAN };

					for (int int_1 = 0; int_1 < jsonArray_1.size(); ++int_1)
					{
						biomes_1[int_1] = (Biome) Registry.BIOME.getOrEmpty(new Identifier(jsonArray_1.get(int_1).getAsString())).orElse(Biomes.OCEAN);
					}
				}
			}

			if (biomeSource_1 == null)
			{
				biomeSource_1 = BiomeSourceType.FIXED.applyConfig(((FixedBiomeSourceConfig) BiomeSourceType.FIXED.getConfig()).setBiome(Biomes.OCEAN));
			}

			if (jsonObject_1.has("chunk_generator") && jsonObject_1.getAsJsonObject("chunk_generator").has("type"))
			{
				ChunkGeneratorType<?, ?> chunkGeneratorType_6 = (ChunkGeneratorType<?, ?>) Registry.CHUNK_GENERATOR_TYPE.get(new Identifier(jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonPrimitive("type").getAsString()));

            	if (chunkGeneratorType_6 == WorldAether.AETHER_ISLANDS)
            	{
            		AetherChunkGeneratorSettings aetherChunkGeneratorSettings = (AetherChunkGeneratorSettings)chunkGeneratorType_6.createSettings();

            		info.setReturnValue(WorldAether.AETHER_ISLANDS.create(this.world, biomeSource_1, aetherChunkGeneratorSettings));
            	}
			}
        }
	}

}