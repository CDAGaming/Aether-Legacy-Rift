package com.legacy.aether.world.biome;

import com.legacy.aether.world.WorldAether;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;

public class AetherBiomeProvider extends SingleBiomeProvider {

    public AetherBiomeProvider() {
        super(new SingleBiomeProviderSettings().setBiome(WorldAether.aetherBiome));
    }

}