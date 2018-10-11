package com.legacy.aether.item;

import com.legacy.aether.Aether;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.item.accessory.ItemAccessory;
import com.legacy.aether.item.armor.AetherArmorType;
import com.legacy.aether.item.armor.ItemAetherArmor;
import com.legacy.aether.item.food.ItemAetherFood;
import com.legacy.aether.item.food.ItemGummySwet;
import com.legacy.aether.item.food.ItemHealingStone;
import com.legacy.aether.item.food.ItemLifeShard;
import com.legacy.aether.item.material.*;
import com.legacy.aether.item.tool.AetherToolType;
import com.legacy.aether.item.tool.ItemAetherAxe;
import com.legacy.aether.item.tool.ItemAetherPickaxe;
import com.legacy.aether.item.tool.ItemAetherShovel;
import com.legacy.aether.item.tool.bucket.ItemSkyrootBucket;
import com.legacy.aether.item.weapon.ItemAetherSword;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Builder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.text.TextFormatting;
import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.utils.ReflectionUtils;

public class ItemsAether implements ItemAdder {

    public static final EnumRarity AETHER_LOOT = ReflectionUtils.makeEnumInstance(EnumRarity.class, new Object[]{"AETHER_LOOT", -1, TextFormatting.GREEN});

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

    public static Item cloud_parachute, golden_parachute;

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

    public static Item aether_tune, ascending_dawn, welcoming_skies;

    public static Item repulsion_shield;

    public static Item lore_book;

    @Override
    public void registerItems() {
        zanite_gemstone = register("zanite_gemstone", new ItemZaniteGemstone());
        ambrosium_shard = register("ambrosium_shard", new ItemAmbrosiumShard());
        golden_amber = register("golden_amber", new ItemGoldenAmber());
        aechor_petal = register("aechor_petal", new ItemAechorPetal());
        swet_ball = register("swet_ball", new ItemSwetBall());

        skyroot_stick = register("skyroot_stick", new ItemSkyrootStick());

        skyroot_shovel = register("skyroot_shovel", new ItemAetherShovel(AetherToolType.Skyroot, AetherToolType.Skyroot.getDefaultTier(), 1.5F, -3.0F));
        skyroot_pickaxe = register("skyroot_pickaxe", new ItemAetherPickaxe(AetherToolType.Skyroot, AetherToolType.Skyroot.getDefaultTier(), 1, -2.8F));
        skyroot_axe = register("skyroot_axe", new ItemAetherAxe(AetherToolType.Skyroot, AetherToolType.Skyroot.getDefaultTier(), 6.0F, -3.2F));
        skyroot_sword = register("skyroot_sword", new ItemAetherSword(AetherToolType.Skyroot, AetherToolType.Skyroot.getDefaultTier(), 0, 0));

        holystone_shovel = register("holystone_shovel", new ItemAetherShovel(AetherToolType.Holystone, AetherToolType.Holystone.getDefaultTier(), 1.5F, -3.0F));
        holystone_pickaxe = register("holystone_pickaxe", new ItemAetherPickaxe(AetherToolType.Holystone, AetherToolType.Holystone.getDefaultTier(), 1, -2.8F));
        holystone_axe = register("holystone_axe", new ItemAetherAxe(AetherToolType.Holystone, AetherToolType.Holystone.getDefaultTier(), 7.0F, -3.2F));
        holystone_sword = register("holystone_sword", new ItemAetherSword(AetherToolType.Holystone, AetherToolType.Holystone.getDefaultTier(), 0, 0));

        zanite_shovel = register("zanite_shovel", new ItemAetherShovel(AetherToolType.Zanite, AetherToolType.Zanite.getDefaultTier(), 1.5F, -3.0F));
        zanite_pickaxe = register("zanite_pickaxe", new ItemAetherPickaxe(AetherToolType.Zanite, AetherToolType.Zanite.getDefaultTier(), 1, -2.8F));
        zanite_axe = register("zanite_axe", new ItemAetherAxe(AetherToolType.Zanite, AetherToolType.Zanite.getDefaultTier(), 6.0F, -3.1F));
        zanite_sword = register("zanite_sword", new ItemAetherSword(AetherToolType.Zanite, AetherToolType.Zanite.getDefaultTier(), 0, 0));

        gravitite_shovel = register("gravitite_shovel", new ItemAetherShovel(AetherToolType.Gravitite, EnumRarity.RARE, AetherToolType.Gravitite.getDefaultTier(), 1.5F, -3.0F));
        gravitite_pickaxe = register("gravitite_pickaxe", new ItemAetherPickaxe(AetherToolType.Gravitite, EnumRarity.RARE, AetherToolType.Gravitite.getDefaultTier(), 1, -2.8F));
        gravitite_axe = register("gravitite_axe", new ItemAetherAxe(AetherToolType.Gravitite, EnumRarity.RARE, AetherToolType.Gravitite.getDefaultTier(), 5.0F, -3.0F));
        gravitite_sword = register("gravitite_sword", new ItemAetherSword(AetherToolType.Gravitite, EnumRarity.RARE, AetherToolType.Gravitite.getDefaultTier(), 0, 0));

        valkyrie_shovel = register("valkyrie_shovel", new ItemAetherShovel(AetherToolType.Valkyrie, AETHER_LOOT, AetherToolType.Valkyrie.getDefaultTier(), 1.5F, -3.0F));
        valkyrie_pickaxe = register("valkyrie_pickaxe", new ItemAetherPickaxe(AetherToolType.Valkyrie, AETHER_LOOT, AetherToolType.Valkyrie.getDefaultTier(), 1, -2.8F));
        valkyrie_axe = register("valkyrie_axe", new ItemAetherAxe(AetherToolType.Valkyrie, AETHER_LOOT, AetherToolType.Valkyrie.getDefaultTier(), 4.0F, -2.9F));

        zanite_helmet = register("zanite_helmet", new ItemAetherArmor(AetherArmorType.Zanite, EntityEquipmentSlot.HEAD));
        zanite_chestplate = register("zanite_chestplate", new ItemAetherArmor(AetherArmorType.Zanite, EntityEquipmentSlot.CHEST));
        zanite_leggings = register("zanite_leggings", new ItemAetherArmor(AetherArmorType.Zanite, EntityEquipmentSlot.LEGS));
        zanite_boots = register("zanite_boots", new ItemAetherArmor(AetherArmorType.Zanite, EntityEquipmentSlot.FEET));

        gravitite_helmet = register("gravitite_helmet", new ItemAetherArmor(AetherArmorType.Gravitite, EnumRarity.RARE, EntityEquipmentSlot.HEAD));
        gravitite_chestplate = register("gravitite_chestplate", new ItemAetherArmor(AetherArmorType.Gravitite, EnumRarity.RARE, EntityEquipmentSlot.CHEST));
        gravitite_leggings = register("gravitite_leggings", new ItemAetherArmor(AetherArmorType.Gravitite, EnumRarity.RARE, EntityEquipmentSlot.LEGS));
        gravitite_boots = register("gravitite_boots", new ItemAetherArmor(AetherArmorType.Gravitite, EnumRarity.RARE, EntityEquipmentSlot.FEET));

        neptune_helmet = register("neptune_helmet", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EntityEquipmentSlot.HEAD));
        neptune_chestplate = register("neptune_chestplate", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EntityEquipmentSlot.CHEST));
        neptune_leggings = register("neptune_leggings", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EntityEquipmentSlot.LEGS));
        neptune_boots = register("neptune_boots", new ItemAetherArmor(AetherArmorType.Neptune, AETHER_LOOT, EntityEquipmentSlot.FEET));

        phoenix_helmet = register("phoenix_helmet", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EntityEquipmentSlot.HEAD));
        phoenix_chestplate = register("phoenix_chestplate", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EntityEquipmentSlot.CHEST));
        phoenix_leggings = register("phoenix_leggings", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EntityEquipmentSlot.LEGS));
        phoenix_boots = register("phoenix_boots", new ItemAetherArmor("phoenix", AetherArmorType.Phoenix, AETHER_LOOT, EntityEquipmentSlot.FEET));

        obsidian_helmet = register("obsidian_helmet", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EntityEquipmentSlot.HEAD));
        obsidian_chestplate = register("obsidian_chestplate", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EntityEquipmentSlot.CHEST));
        obsidian_leggings = register("obsidian_leggings", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EntityEquipmentSlot.LEGS));
        obsidian_boots = register("obsidian_boots", new ItemAetherArmor(AetherArmorType.Obsidian, AETHER_LOOT, EntityEquipmentSlot.FEET));

        valkyrie_helmet = register("valkyrie_helmet", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EntityEquipmentSlot.HEAD));
        valkyrie_chestplate = register("valkyrie_chestplate", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EntityEquipmentSlot.CHEST));
        valkyrie_leggings = register("valkyrie_leggings", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EntityEquipmentSlot.LEGS));
        valkyrie_boots = register("valkyrie_boots", new ItemAetherArmor("valkyrie", AetherArmorType.Valkyrie, AETHER_LOOT, EntityEquipmentSlot.FEET));

        sentry_boots = register("sentry_boots", new ItemAetherArmor("sentry", AetherArmorType.Valkyrie, AETHER_LOOT, EntityEquipmentSlot.FEET));

        blueberry = register("blueberry", new ItemAetherFood(2, 2.0F));
        enchanted_blueberry = register("enchanted_blueberry", new ItemAetherFood(new Builder().group(ItemGroup.FOOD).rarity(EnumRarity.RARE), 8, 10.0F));
        //white_apple = register("white_apple", new ItemWhiteApple());
        blue_gummy_swet = register("blue_gummy_swet", new ItemGummySwet());
        golden_gummy_swet = register("golden_gummy_swet", new ItemGummySwet());
        healing_stone = register("healing_stone", new ItemHealingStone());
        candy_cane = register("candy_cane", new ItemAetherFood(2, 1.0F));
        ginger_bread_man = register("gingerbread_man", new ItemAetherFood(2, 1.0F));

        skyroot_bucket = register("skyroot_bucket", new ItemSkyrootBucket());
        skyroot_water_bucket = register("skyroot_water_bucket", new ItemSkyrootBucket(skyroot_bucket));
        skyroot_milk_bucket = register("skyroot_milk_bucket", new ItemSkyrootBucket(skyroot_bucket));
        skyroot_poison_bucket = register("skyroot_poison_bucket", new ItemSkyrootBucket(skyroot_bucket));
        skyroot_remedy_bucket = register("skyroot_remedy_bucket", new ItemSkyrootBucket(skyroot_bucket));

        leather_gloves = register("leather_gloves", new ItemAccessory(AccessoryType.GLOVE, 0xC65C35));
        iron_gloves = register("iron_gloves", new ItemAccessory(AccessoryType.GLOVE));
        golden_gloves = register("golden_gloves", new ItemAccessory(AccessoryType.GLOVE, 0xFBF424));
        chain_gloves = register("chain_gloves", new ItemAccessory("chain", AccessoryType.GLOVE));
        diamond_gloves = register("diamond_gloves", new ItemAccessory(AccessoryType.GLOVE, 0x33EBCB));
        zanite_gloves = register("zanite_gloves", new ItemAccessory(AccessoryType.GLOVE, 0x711AE8));
        gravitite_gloves = register("gravitite_gloves", new ItemAccessory(AccessoryType.GLOVE, 0xE752DB));
        neptune_gloves = register("neptune_gloves", new ItemAccessory(AccessoryType.GLOVE, AETHER_LOOT, 0x2654FF));
        phoenix_gloves = register("phoenix_gloves", new ItemAccessory("phoenix", AccessoryType.GLOVE, AETHER_LOOT, 0xff7700));
        obsidian_gloves = register("obsidian_gloves", new ItemAccessory(AccessoryType.GLOVE, 0x1B1447));
        valkyrie_gloves = register("valkyrie_gloves", new ItemAccessory("valkyrie", AccessoryType.GLOVE, AETHER_LOOT));

        iron_ring = register("iron_ring", new ItemAccessory(AccessoryType.RING));
        golden_ring = register("golden_ring", new ItemAccessory(AccessoryType.RING, 0xEAEE57));
        zanite_ring = register("zanite_ring", new ItemAccessory(AccessoryType.RING, 0x711AE8));
        ice_ring = register("ice_ring", new ItemAccessory(AccessoryType.RING, EnumRarity.RARE, 0x95E6E7));

        iron_pendant = register("iron_pendant", new ItemAccessory(AccessoryType.PENDANT));
        golden_pendant = register("golden_pendant", new ItemAccessory(AccessoryType.PENDANT, 0xEAEE57));
        zanite_pendant = register("zanite_pendant", new ItemAccessory(AccessoryType.PENDANT, 0x711AE8));
        ice_pendant = register("ice_pendant", new ItemAccessory(AccessoryType.PENDANT, EnumRarity.RARE, 0x95E6E7));

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

        moa_egg = register("moa_egg", new ItemMoaEgg());
    }

    public Item register(String name, Item item) {
        Item.registerItem(Aether.locate(name).toString(), item);

        if (item instanceof ItemAccessory) {
            ((ItemAccessory) item).register();
        }

        return item;
    }

}