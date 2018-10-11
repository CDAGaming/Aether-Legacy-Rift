package com.legacy.aether;

import net.minecraft.util.ResourceLocation;
import org.dimdev.riftloader.listener.InitializationListener;

public class Aether implements InitializationListener {

    public static ResourceLocation locate(String location) {
        return new ResourceLocation("aether_legacy", location);
    }

    @Override
    public void onInitialization() {

    }

}