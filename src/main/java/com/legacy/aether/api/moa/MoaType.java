package com.legacy.aether.api.moa;

import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class MoaType {

    public int hexColor;
    private ResourceLocation registryName;
    private MoaProperties properties;
    private ItemGroup group;

    public MoaType(int hexColor, MoaProperties properties) {
        this.hexColor = hexColor;
        this.properties = properties;
    }

    public MoaType(int hexColor, ItemGroup group, MoaProperties properties) {
        this(hexColor, properties);

        this.group = group;
    }

    public ResourceLocation getTexture(boolean saddled) {
        return this.properties.getCustomTexture(saddled);
    }

    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    public void setRegistryName(ResourceLocation location) {
        this.registryName = location;
    }

    public MoaProperties getMoaProperties() {
        return this.properties;
    }

    public ItemGroup getItemGroup() {
        return this.group;
    }

    public int getMoaEggColor() {
        return this.hexColor;
    }

}