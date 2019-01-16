package com.legacy.aether.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.item.armor.AetherArmorType;
import com.legacy.aether.item.armor.ItemAetherArmor;
import com.legacy.aether.item.dungeon.ItemDungeonKey;
import com.legacy.aether.item.food.ItemAetherFood;
import com.legacy.aether.item.food.ItemGummySwet;
import com.legacy.aether.item.food.ItemHealingStone;
import com.legacy.aether.item.food.ItemLifeShard;
import com.legacy.aether.item.food.ItemWhiteApple;
import com.legacy.aether.item.material.ItemAechorPetal;
import com.legacy.aether.item.material.ItemAmbrosiumShard;
import com.legacy.aether.item.material.ItemGoldenAmber;
import com.legacy.aether.item.material.ItemSkyrootStick;
import com.legacy.aether.item.material.ItemSwetBall;
import com.legacy.aether.item.material.ItemZaniteGemstone;
import com.legacy.aether.item.staff.ItemCloudStaff;
import com.legacy.aether.item.staff.ItemNatureStaff;
import com.legacy.aether.item.tool.ItemAetherAxe;
import com.legacy.aether.item.tool.ItemAetherPickaxe;
import com.legacy.aether.item.tool.ItemAetherShovel;
import com.legacy.aether.item.tool.ItemParachute;
import com.legacy.aether.item.tool.bucket.ItemSkyrootBucket;
import com.legacy.aether.item.util.AetherTier;
import com.legacy.aether.item.weapon.ItemAetherSword;
import com.legacy.aether.item.weapon.ItemCandyCaneSword;
import com.legacy.aether.item.weapon.ItemElementalSword;
import com.legacy.aether.item.weapon.ItemPhoenixBow;
import com.legacy.aether.item.weapon.ItemPigSlayer;
import com.legacy.aether.item.weapon.ItemVampireBlade;
import com.legacy.aether.sounds.SoundsAether;
import com.legacy.aether.util.Reflection;

public class ItemsAether
{
	public static final Rarity AETHER_LOOT = Reflection.createRarity("AETHER_LOOT", TextFormat.GREEN);

	public static Item zanite_gemstone, ambrosium_shard, golden_amber, aechor_petal, swet_ball;

	public static Item skyroot_stick, victory_medal;

	public static Item skyroot_pickaxe, skyroot_axe, skyroot_shovel, skyroot_sword;

	public static Item holystone_pickaxe, holystone_axe, holystone_shovel, holystone_sword;

	public static Item zanite_pickaxe, zanite_axe, zanite_shovel, zanite_sword;

	public static Item gravitite_pickaxe, gravitite_axe, gravitite_shovel, gravitite_sword;

	public static Item valkyrie_pickaxe, valkyrie_axe, valkyrie_shovel;

	public static Item zanite_helmet, zanite_chestplate, zanite_leggings, zanite_boots;

	public static Item gravitite_helmet, gravitite_chestplate, gravitite_leggings, gravitite_boots;

	public static Item neptune_helmet, neptune_chestplate, neptune_leggings, neptune_boots;

	public static Item phoenix_helmet, phoenix_chestplate, phoenix_leggings, phoenix_boots;

	public static Item obsidian_helmet, obsidian_chestplate, obsidian_leggings, obsidian_boots;

	public static Item valkyrie_helmet, valkyrie_chestplate, valkyrie_leggings, valkyrie_boots;

	public static Item blueberry, enchanted_blueberry, blue_gummy_swet, golden_gummy_swet, healing_stone, white_apple, ginger_bread_man, candy_cane;

	public static Item skyroot_bucket, skyroot_water_bucket, skyroot_poison_bucket, skyroot_remedy_bucket, skyroot_milk_bucket;

	public static Item cloud_parachute, golden_cloud_parachute;

	public static Item bronze_key, silver_key, golden_key, platinum_key;

	public static Item nature_staff, cloud_staff, moa_egg;

	public static Item golden_dart, enchanted_dart, poison_dart;

	public static Item golden_dart_shooter, enchanted_dart_shooter, poison_dart_shooter;

	public static Item phoenix_bow;

	public static Item flaming_sword, lightning_sword, holy_sword;

	public static Item vampire_blade, pig_slayer, candy_cane_sword, notch_hammer, valkyrie_lance;

	public static Item leather_gloves, iron_gloves, golden_gloves, chain_gloves, diamond_gloves;

	public static Item zanite_gloves, gravitite_gloves, neptune_gloves, phoenix_gloves, obsidian_gloves, valkyrie_gloves;

	public static Item iron_ring, golden_ring, zanite_ring, ice_ring, iron_pendant, golden_pendant, zanite_pendant, ice_pendant;

	public static Item red_cape, blue_cape, yellow_cape, white_cape, swet_cape, invisibility_cape, agility_cape;

	public static Item golden_feather, regeneration_stone, iron_bubble;

	public static Item life_shard;

	public static Item sentry_boots, lightning_knife;

	public static Item aether_tune, ascending_dawn, welcoming_skies, legacy;

	public static Item repulsion_shield;

	public static Item lore_book;

	public static void registerItems()
	{
		zanite_gemstone = register("zanite_gemstone", new ItemZaniteGemstone());
		ambrosium_shard = register("ambrosium_shard", new ItemAmbrosiumShard());
		golden_amber = register("golden_amber", new ItemGoldenAmber());
		aechor_petal = register("aechor_petal", new ItemAechorPetal());
		swet_ball = register("swet_ball", new ItemSwetBall());

		skyroot_stick = register("skyroot_stick", new ItemSkyrootStick());

		skyroot_shovel = register("skyroot_shovel", new ItemAetherShovel(AetherTier.Skyroot, 1.5F, -3.0F));
		skyroot_pickaxe = register("skyroot_pickaxe", new ItemAetherPickaxe(AetherTier.Skyroot, 1, -2.8F));
		skyroot_axe = register("skyroot_axe", new ItemAetherAxe(AetherTier.Skyroot, 6.0F, -3.2F));
		skyroot_sword = register("skyroot_sword", new ItemAetherSword(AetherTier.Skyroot, 3, -2.4F));

		holystone_shovel = register("holystone_shovel", new ItemAetherShovel(AetherTier.Holystone, 1.5F, -3.0F));
		holystone_pickaxe = register("holystone_pickaxe", new ItemAetherPickaxe(AetherTier.Holystone, 1, -2.8F));
		holystone_axe = register("holystone_axe", new ItemAetherAxe(AetherTier.Holystone, 7.0F, -3.2F));
		holystone_sword = register("holystone_sword", new ItemAetherSword(AetherTier.Holystone, 3, -2.4F));

		zanite_shovel = register("zanite_shovel", new ItemAetherShovel(AetherTier.Zanite, 1.5F, -3.0F));
		zanite_pickaxe = register("zanite_pickaxe", new ItemAetherPickaxe(AetherTier.Zanite, 1, -2.8F));
		zanite_axe = register("zanite_axe", new ItemAetherAxe(AetherTier.Zanite, 6.0F, -3.1F));
		zanite_sword = register("zanite_sword", new ItemAetherSword(AetherTier.Zanite, 3, -2.4F));

		gravitite_shovel = register("gravitite_shovel", new ItemAetherShovel(AetherTier.Gravitite, Rarity.RARE, 1.5F, -3.0F));
		gravitite_pickaxe = register("gravitite_pickaxe", new ItemAetherPickaxe(AetherTier.Gravitite, Rarity.RARE, 1, -2.8F));
		gravitite_axe = register("gravitite_axe", new ItemAetherAxe(AetherTier.Gravitite, Rarity.RARE, 5.0F, -3.0F));
		gravitite_sword = register("gravitite_sword", new ItemAetherSword(AetherTier.Gravitite, Rarity.RARE, 3, -2.4F));

		valkyrie_shovel = register("valkyrie_shovel", new ItemAetherShovel(AetherTier.Valkyrie, AETHER_LOOT, 1.5F, -3.0F));
		valkyrie_pickaxe = register("valkyrie_pickaxe", new ItemAetherPickaxe(AetherTier.Valkyrie, AETHER_LOOT, 1, -2.8F));
		valkyrie_axe = register("valkyrie_axe", new ItemAetherAxe(AetherTier.Valkyrie, AETHER_LOOT, 4.0F, -2.9F));

		zanite_helmet = register("zanite_helmet", new ItemAetherArmor(AetherArmorType.Zanite, EquipmentSlot.HEAD));
		zanite_chestplate = register("zanite_chestplate", new ItemAetherArmor(AetherArmorType.Zanite, EquipmentSlot.CHEST));
		zanite_leggings = register("zanite_leggings", new ItemAetherArmor(AetherArmorType.Zanite, EquipmentSlot.LEGS));
		zanite_boots = register("zanite_boots", new ItemAetherArmor(AetherArmorType.Zanite, EquipmentSlot.FEET));

		gravitite_helmet = register("gravitite_helmet", new ItemAetherArmor(AetherArmorType.Gravitite, Rarity.RARE, EquipmentSlot.HEAD));
		gravitite_chestplate = register("gravitite_chestplate", new ItemAetherArmor(AetherArmorType.Gravitite, Rarity.RARE, EquipmentSlot.CHEST));
		gravitite_leggings = register("gravitite_leggings", new ItemAetherArmor(AetherArmorType.Gravitite, Rarity.RARE, EquipmentSlot.LEGS));
		gravitite_boots = register("gravitite_boots", new ItemAetherArmor(AetherArmorType.Gravitite, Rarity.RARE, EquipmentSlot.FEET));

		neptune_helmet = register("neptune_helmet", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EquipmentSlot.HEAD));
		neptune_chestplate = register("neptune_chestplate", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EquipmentSlot.CHEST));
		neptune_leggings = register("neptune_leggings", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EquipmentSlot.LEGS));
		neptune_boots = register("neptune_boots", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EquipmentSlot.FEET));

		phoenix_helmet = register("phoenix_helmet", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EquipmentSlot.HEAD));
		phoenix_chestplate = register("phoenix_chestplate", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EquipmentSlot.CHEST));
		phoenix_leggings = register("phoenix_leggings", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EquipmentSlot.LEGS));
		phoenix_boots = register("phoenix_boots", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EquipmentSlot.FEET));

		obsidian_helmet = register("obsidian_helmet", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EquipmentSlot.HEAD));
		obsidian_chestplate = register("obsidian_chestplate", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EquipmentSlot.CHEST));
		obsidian_leggings = register("obsidian_leggings", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EquipmentSlot.LEGS));
		obsidian_boots = register("obsidian_boots", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EquipmentSlot.FEET));

		valkyrie_helmet = register("valkyrie_helmet", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EquipmentSlot.HEAD));
		valkyrie_chestplate = register("valkyrie_chestplate", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EquipmentSlot.CHEST));
		valkyrie_leggings = register("valkyrie_leggings", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EquipmentSlot.LEGS));
		valkyrie_boots = register("valkyrie_boots", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EquipmentSlot.FEET));

		sentry_boots = register("sentry_boots", new ItemAetherArmor("sentry", AetherArmorType.Valkyrie, AETHER_LOOT, EquipmentSlot.FEET));

		blueberry = register("blueberry", new ItemAetherFood(2, 2.0F));
		enchanted_blueberry = register("enchanted_blueberry", new ItemAetherFood(new Item.Settings().itemGroup(AetherItemGroup.AETHER_FOOD).rarity(Rarity.RARE), 8, 10.0F));
		white_apple = register("white_apple", new ItemWhiteApple());
		blue_gummy_swet = register("blue_gummy_swet", new ItemGummySwet());
		golden_gummy_swet = register("golden_gummy_swet", new ItemGummySwet());
		healing_stone = register("healing_stone", new ItemHealingStone());
		candy_cane = register("candy_cane", new ItemAetherFood(2, 1.0F));
		ginger_bread_man = register("gingerbread_man", new ItemAetherFood(2, 1.0F));

		skyroot_bucket = register("skyroot_bucket", new ItemSkyrootBucket());
		skyroot_water_bucket = register("skyroot_water_bucket", new ItemSkyrootBucket(Fluids.WATER, skyroot_bucket));
		skyroot_milk_bucket = register("skyroot_milk_bucket", new ItemSkyrootBucket(skyroot_bucket));
		skyroot_poison_bucket = register("skyroot_poison_bucket", new ItemSkyrootBucket(skyroot_bucket));
		skyroot_remedy_bucket = register("skyroot_remedy_bucket", new ItemSkyrootBucket(skyroot_bucket));

		cloud_parachute = register("cloud_parachute", new ItemParachute());
		golden_cloud_parachute = register("golden_cloud_parachute", new ItemParachute(20));

		bronze_key = register("bronze_key", new ItemDungeonKey());
		silver_key = register("silver_key", new ItemDungeonKey());
		golden_key = register("golden_key", new ItemDungeonKey());
		platinum_key = register("platinum_key", new ItemDungeonKey());

		//golden_dart = register("golden_dart", new ItemDart());
		//enchanted_dart = register("enchanted_dart", new ItemDart(Rarity.RARE));
		//poison_dart = register("poison_dart", new ItemDart());

		//golden_dart_shooter = register("golden_dart_shooter", new ItemDartShooter((ItemDart) golden_dart));
		//enchanted_dart_shooter = register("enchanted_dart_shooter", new ItemDartShooter((ItemDart) enchanted_dart));
		//poison_dart_shooter = register("poison_dart_shooter", new ItemDartShooter((ItemDart) poison_dart));

		phoenix_bow = register("phoenix_bow", new ItemPhoenixBow());

		flaming_sword = register("flaming_sword", new ItemElementalSword());
		lightning_sword = register("lightning_sword", new ItemElementalSword());
		holy_sword = register("holy_sword", new ItemElementalSword());

		vampire_blade = register("vampire_blade", new ItemVampireBlade());
		pig_slayer = register("pig_slayer", new ItemPigSlayer());
		candy_cane_sword = register("candy_cane_sword", new ItemCandyCaneSword());

		leather_gloves = register("leather_gloves", new ItemAccessory(AccessoryType.GLOVES, 0xC65C35).setDamageMultiplier(1.5F));
		iron_gloves = register("iron_gloves", new ItemAccessory(AccessoryType.GLOVES).setDamageMultiplier(2.5F));
		golden_gloves = register("golden_gloves", new ItemAccessory(AccessoryType.GLOVES, 0xFBF424).setDamageMultiplier(2.0F));
		chain_gloves = register("chain_gloves", new ItemAccessory("chain", AccessoryType.GLOVES).setDamageMultiplier(2.0F));
		diamond_gloves = register("diamond_gloves", new ItemAccessory(AccessoryType.GLOVES, 0x33EBCB).setDamageMultiplier(4.5F));
		zanite_gloves = register("zanite_gloves", new ItemAccessory(AccessoryType.GLOVES, 0x711AE8).setDamageMultiplier(3.0F));
		gravitite_gloves = register("gravitite_gloves", new ItemAccessory(AccessoryType.GLOVES, Rarity.RARE, 0xE752DB).setDamageMultiplier(4.0F));
		neptune_gloves = register("neptune_gloves", new ItemAccessory(AccessoryType.GLOVES, AETHER_LOOT, 0x2654FF).setDamageMultiplier(4.5F));
		phoenix_gloves = register("phoenix_gloves", new ItemAccessory("phoenix", AccessoryType.GLOVES, AETHER_LOOT, 0xff7700).setDamageMultiplier(4.0F));
		obsidian_gloves = register("obsidian_gloves", new ItemAccessory(AccessoryType.GLOVES, 0x1B1447).setDamageMultiplier(5.0F));
		valkyrie_gloves = register("valkyrie_gloves", new ItemAccessory("valkyrie", AccessoryType.GLOVES, AETHER_LOOT).setDamageMultiplier(5.0F));

		iron_ring = register("iron_ring", new ItemAccessory(AccessoryType.RING));
		golden_ring = register("golden_ring", new ItemAccessory(AccessoryType.RING, 0xEAEE57));
		zanite_ring = register("zanite_ring", new ItemAccessory(AccessoryType.RING, 0x711AE8));
		ice_ring = register("ice_ring", new ItemAccessory(AccessoryType.RING, Rarity.RARE, 0x95E6E7));

		iron_pendant = register("iron_pendant", new ItemAccessory(AccessoryType.PENDANT));
		golden_pendant = register("golden_pendant", new ItemAccessory(AccessoryType.PENDANT, 0xEAEE57));
		zanite_pendant = register("zanite_pendant", new ItemAccessory(AccessoryType.PENDANT, 0x711AE8));
		ice_pendant = register("ice_pendant", new ItemAccessory(AccessoryType.PENDANT, Rarity.RARE, 0x95E6E7));

		white_cape = register("white_cape", new ItemAccessory(AccessoryType.RING));
		red_cape = register("red_cape", new ItemAccessory(AccessoryType.RING, 0xE81111));
		blue_cape = register("blue_cape", new ItemAccessory(AccessoryType.RING, 0x137FB7));
		yellow_cape = register("yellow_cape", new ItemAccessory(AccessoryType.RING, 0xCDCB0E));
		swet_cape = register("swet_cape", new ItemAccessory("swet", AccessoryType.RING, AETHER_LOOT));
		agility_cape = register("agility_cape", new ItemAccessory("agility", AccessoryType.RING, AETHER_LOOT));
		invisibility_cape = register("invisibility_cape", new ItemAccessory(AccessoryType.RING, AETHER_LOOT));

		golden_feather = register("golden_feather", new ItemAccessory(AccessoryType.RING, AETHER_LOOT));
		regeneration_stone = register("regeneration_stone", new ItemAccessory(AccessoryType.RING, AETHER_LOOT));
		iron_bubble = register("iron_bubble", new ItemAccessory(AccessoryType.RING, AETHER_LOOT));

		life_shard = register("life_shard", new ItemLifeShard());

		cloud_staff = register("cloud_staff", new ItemCloudStaff());
		nature_staff = register("nature_staff", new ItemNatureStaff());

		moa_egg = register("moa_egg", new ItemMoaEgg());

		aether_tune = register("aether_tune", new ItemAetherDisc(20, SoundsAether.aether_tune));
		ascending_dawn = register("ascending_dawn", new ItemAetherDisc(12, SoundsAether.ascending_dawn));
		welcoming_skies = register("welcoming_skies", new ItemAetherDisc(10, SoundsAether.welcoming_skies));
		legacy = register("legacy", new ItemAetherDisc(6, SoundsAether.legacy));
	}

	public static Item register(String name, Item item)
	{
		Registry.register(Registry.ITEM, Aether.locate(name), item);

		if (item instanceof ItemAccessory)
		{
			((ItemAccessory)item).register();
		}

		return item;
	}

}