package com.legacy.aether.api;

import com.legacy.aether.api.accessories.Accessory;
import com.legacy.aether.api.enchantment.Enchantment;
import com.legacy.aether.api.enchantment.EnchantmentFuel;
import com.legacy.aether.api.freezable.Freezable;
import com.legacy.aether.api.freezable.FreezableFuel;
import com.legacy.aether.api.moa.MoaType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;

import java.util.Random;

public class AetherAPI {

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, Accessory> ACCESSORY_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, Accessory>(new ResourceLocation("empty"));

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, Enchantment> ENCHANTMENT_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, Enchantment>(new ResourceLocation("empty"));

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, Freezable> FREEZABLE_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, Freezable>(new ResourceLocation("empty"));

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, EnchantmentFuel> ENCHANTMENT_FUEL_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, EnchantmentFuel>(new ResourceLocation("empty"));

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, FreezableFuel> FREEZABLE_FUEL_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, FreezableFuel>(new ResourceLocation("empty"));

    private static final RegistryNamespacedDefaultedByKey<ResourceLocation, MoaType> MOA_REGISTRY = new RegistryNamespacedDefaultedByKey<ResourceLocation, MoaType>(new ResourceLocation("empty"));
    private static final AetherAPI INSTANCE = new AetherAPI();
    private static int moaListSize;

    public AetherAPI() {

    }

    public static AetherAPI instance() {
        return INSTANCE;
    }

    public void register(ResourceLocation registryName, MoaType moa) {
        moa.setRegistryName(registryName);

        MOA_REGISTRY.put(registryName, moa);
        ++moaListSize;
    }

    public void register(Accessory accessory) {
        ACCESSORY_REGISTRY.put(Item.REGISTRY.getKey(accessory.getItem()), accessory);
    }

    public void register(Freezable freezable) {
        FREEZABLE_REGISTRY.put(Item.REGISTRY.getKey(freezable.getInput()), freezable);
    }

    public void register(FreezableFuel fuel) {
        FREEZABLE_FUEL_REGISTRY.put(Item.REGISTRY.getKey(fuel.getFuel()), fuel);
    }

    public void register(Enchantment enchantment) {
        ENCHANTMENT_REGISTRY.put(Item.REGISTRY.getKey(enchantment.getInput()), enchantment);
    }

    public void register(EnchantmentFuel fuel) {
        ENCHANTMENT_FUEL_REGISTRY.put(Item.REGISTRY.getKey(fuel.getFuel()), fuel);
    }

    public Accessory getAccessory(ItemStack stack) {
        return ACCESSORY_REGISTRY.get(Item.REGISTRY.getKey(stack.getItem()));
    }

    public boolean isAccessory(ItemStack stack) {
        return ACCESSORY_REGISTRY.containsKey(Item.REGISTRY.getKey(stack.getItem()));
    }

    public Freezable getFreezable(ItemStack stack) {
        return FREEZABLE_REGISTRY.get(Item.REGISTRY.getKey(stack.getItem()));
    }

    public boolean isFreezable(ItemStack stack) {
        return FREEZABLE_REGISTRY.containsKey(Item.REGISTRY.getKey(stack.getItem()));
    }

    public FreezableFuel getFreezableFuel(ItemStack stack) {
        return FREEZABLE_FUEL_REGISTRY.get(Item.REGISTRY.getKey(stack.getItem()));
    }

    public boolean isFreezerFuel(ItemStack stack) {
        return FREEZABLE_FUEL_REGISTRY.containsKey(Item.REGISTRY.getKey(stack.getItem()));
    }

    public Enchantment getEnchantment(ItemStack stack) {
        return ENCHANTMENT_REGISTRY.get(Item.REGISTRY.getKey(stack.getItem()));
    }

    public boolean isEnchantable(ItemStack stack) {
        return ENCHANTMENT_REGISTRY.containsKey(Item.REGISTRY.getKey(stack.getItem()));
    }

    public EnchantmentFuel getEnchantmentFuel(ItemStack stack) {
        return ENCHANTMENT_FUEL_REGISTRY.get(Item.REGISTRY.getKey(stack.getItem()));
    }

    public boolean isEnchantmentFuel(ItemStack stack) {
        return ENCHANTMENT_FUEL_REGISTRY.containsKey(Item.REGISTRY.getKey(stack.getItem()));
    }

    public MoaType getMoa(Random random) {
        return MOA_REGISTRY.getRandom(random);
    }

    public MoaType getMoa(ResourceLocation registryName) {
        return MOA_REGISTRY.get(registryName);
    }

    public MoaType getMoa(int id) {
        return MOA_REGISTRY.get(id);
    }

    public int getMoaId(MoaType moa) {
        return MOA_REGISTRY.getId(moa);
    }

    public int getMoaRegistrySize() {
        return moaListSize;
    }

}