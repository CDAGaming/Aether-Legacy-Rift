package com.legacy.aether.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.utils.ReflectionUtils;

import com.legacy.aether.Aether;
import com.legacy.aether.item.material.ItemSkyrootStick;
import com.legacy.aether.item.tool.AetherMaterialType;
import com.legacy.aether.item.tool.ItemAetherAxe;
import com.legacy.aether.item.tool.ItemAetherPickaxe;
import com.legacy.aether.item.tool.ItemAetherShovel;

public class ItemsAether implements ItemAdder
{

	public static final EnumRarity AETHER_LOOT = ReflectionUtils.makeEnumInstance(EnumRarity.class, new Object[] {"AETHER_LOOT", -1, TextFormatting.GREEN});

	public static Item zanite_gemstone, ambrosium_shard, golden_amber, aechor_petal, swetty_ball;

	public static Item skyroot_pickaxe, skyroot_axe, skyroot_shovel, skyroot_sword;

	public static Item holystone_pickaxe, holystone_axe, holystone_shovel, holystone_sword;

	public static Item zanite_pickaxe, zanite_axe, zanite_shovel, zanite_sword;

	public static Item gravitite_pickaxe, gravitite_axe, gravitite_shovel, gravitite_sword;

	public static Item valkyrie_pickaxe, valkyrie_axe, valkyrie_shovel, valkyrie_sword;

	public static Item zanite_helmet, zanite_chestplate, zanite_leggings, zanite_boots;

	public static Item gravitite_helmet, gravitite_chestplate, gravitite_leggings, gravitite_boots;

	public static Item neptune_helmet, neptune_chestplate, neptune_leggings, neptune_boots;

	public static Item phoenix_helmet, phoenix_chestplate, phoenix_leggings, phoenix_boots;

	public static Item obsidian_helmet, obsidian_chestplate, obsidian_leggings, obsidian_boots;

	public static Item valkyrie_helmet, valkyrie_chestplate, valkyrie_leggings, valkyrie_boots;

	public static Item blue_berry, enchanted_blueberry, blue_gummy_swet, golden_gummy_swet, healing_stone, white_apple, ginger_bread_man, candy_cane;

	public static Item skyroot_stick, victory_medal;

	public static Item bronze_dungeon_key, silver_dungeon_key, golden_dungeon_key, skyroot_bucket, cloud_parachute, golden_parachute;

	public static Item nature_staff, cloud_staff, moa_egg;

	public static Item dart_shooter, phoenix_bow, dart;

	public static Item flaming_sword, lightning_sword, holy_sword;

	public static Item vampire_blade, pig_slayer, candy_cane_sword, notch_hammer, valkyrie_lance;

	public static Item leather_gloves, iron_gloves, golden_gloves, chain_gloves, diamond_gloves;

	public static Item zanite_gloves, gravitite_gloves, neptune_gloves, phoenix_gloves, obsidian_gloves, valkyrie_gloves;

	public static Item iron_ring, golden_ring, zanite_ring, ice_ring, iron_pendant, golden_pendant, zanite_pendant, ice_pendant;

	public static Item red_cape, blue_cape, yellow_cape, white_cape, swet_cape, invisibility_cape, agility_cape;

	public static Item golden_feather, regeneration_stone, iron_bubble, life_shard;

	public static Item sentry_boots, lightning_knife;

	public static Item aether_tune, ascending_dawn, welcoming_skies;

	public static Item repulsion_shield;

	public static Item lore_book;

	@Override
	public void registerItems()
	{
		skyroot_stick = register("skyroot_stick", new ItemSkyrootStick());

		skyroot_shovel = register("skyroot_shovel", new ItemAetherShovel(AetherMaterialType.Skyroot, AetherMaterialType.Skyroot.getDefaultTier(), 1.5F, -3.0F));
		skyroot_pickaxe = register("skyroot_pickaxe", new ItemAetherPickaxe(AetherMaterialType.Skyroot, AetherMaterialType.Skyroot.getDefaultTier(), 1, -2.8F));
		skyroot_axe = register("skyroot_axe", new ItemAetherAxe(AetherMaterialType.Skyroot, AetherMaterialType.Skyroot.getDefaultTier(), 6.0F, -3.2F));

		holystone_shovel = register("holystone_shovel", new ItemAetherShovel(AetherMaterialType.Holystone, AetherMaterialType.Holystone.getDefaultTier(), 1.5F, -3.0F));
		holystone_pickaxe = register("holystone_pickaxe", new ItemAetherPickaxe(AetherMaterialType.Holystone, AetherMaterialType.Holystone.getDefaultTier(), 1, -2.8F));
		holystone_axe = register("holystone_axe", new ItemAetherAxe(AetherMaterialType.Holystone, AetherMaterialType.Holystone.getDefaultTier(), 7.0F, -3.2F));

		zanite_shovel = register("zanite_shovel", new ItemAetherShovel(AetherMaterialType.Zanite, AetherMaterialType.Zanite.getDefaultTier(), 1.5F, -3.0F));
		zanite_pickaxe = register("zanite_pickaxe", new ItemAetherPickaxe(AetherMaterialType.Zanite, AetherMaterialType.Zanite.getDefaultTier(), 1, -2.8F));
		zanite_axe = register("zanite_axe", new ItemAetherAxe(AetherMaterialType.Zanite, AetherMaterialType.Zanite.getDefaultTier(), 6.0F, -3.1F));

		gravitite_shovel = register("gravitite_shovel", new ItemAetherShovel(AetherMaterialType.Gravitite, AetherMaterialType.Gravitite.getDefaultTier(), 1.5F, -3.0F));
		gravitite_pickaxe = register("gravitite_pickaxe", new ItemAetherPickaxe(AetherMaterialType.Gravitite, AetherMaterialType.Gravitite.getDefaultTier(), 1, -2.8F));
		gravitite_axe = register("gravitite_axe", new ItemAetherAxe(AetherMaterialType.Gravitite, AetherMaterialType.Gravitite.getDefaultTier(), 5.0F, -3.0F));

		valkyrie_shovel = register("valkyrie_shovel", new ItemAetherShovel(AetherMaterialType.Valkyrie, AETHER_LOOT, AetherMaterialType.Valkyrie.getDefaultTier(), 1.5F, -3.0F));
		valkyrie_pickaxe = register("valkyrie_pickaxe", new ItemAetherPickaxe(AetherMaterialType.Valkyrie, AETHER_LOOT, AetherMaterialType.Valkyrie.getDefaultTier(), 1, -2.8F));
		valkyrie_axe = register("valkyrie_axe", new ItemAetherAxe(AetherMaterialType.Valkyrie, AETHER_LOOT, AetherMaterialType.Valkyrie.getDefaultTier(), 4.0F, -2.9F));
	}

	public Item register(String name, Item item)
	{
		Item.registerItem(Aether.locate(name).toString(), item);

		return item;
	}

}