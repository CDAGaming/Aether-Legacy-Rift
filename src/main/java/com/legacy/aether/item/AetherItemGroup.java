package com.legacy.aether.item;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.BlocksAether;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AetherItemGroup
{

	public static final ItemGroup AETHER_BLOCKS = FabricItemGroupBuilder.build(Aether.locate("aether_blocks"), () -> new ItemStack(BlocksAether.aether_grass));

	public static final ItemGroup AETHER_DECORATION_BLOCKS = FabricItemGroupBuilder.build(Aether.locate("aether_decoration_blocks"), () -> new ItemStack(BlocksAether.purple_flower));

	public static final ItemGroup AETHER_TOOLS = FabricItemGroupBuilder.build(Aether.locate("aether_tools"), () -> new ItemStack(ItemsAether.gravitite_pickaxe));

	public static final ItemGroup AETHER_WEAPONS = FabricItemGroupBuilder.build(Aether.locate("aether_weapons"), () -> new ItemStack(ItemsAether.gravitite_sword));

	public static final ItemGroup AETHER_MISC = FabricItemGroupBuilder.build(Aether.locate("aether_misc"), () -> new ItemStack(ItemsAether.bronze_key));

	public static final ItemGroup AETHER_FOOD = FabricItemGroupBuilder.build(Aether.locate("aether_food"), () -> new ItemStack(ItemsAether.blueberry));

	public static final ItemGroup AETHER_ARMOR = FabricItemGroupBuilder.build(Aether.locate("aether_armor"), () -> new ItemStack(ItemsAether.gravitite_helmet));

	public static final ItemGroup AETHER_ACCESSORIES = FabricItemGroupBuilder.build(Aether.locate("aether_accessories"), () -> new ItemStack(ItemsAether.gravitite_gloves));

	public static final ItemGroup AETHER_MATERIALS = FabricItemGroupBuilder.build(Aether.locate("aether_materials"), () -> new ItemStack(ItemsAether.ambrosium_shard));

}