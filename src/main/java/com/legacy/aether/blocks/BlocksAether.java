package com.legacy.aether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.item.block.WallStandingBlockItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.container.BlockChestMimic;
import com.legacy.aether.blocks.container.BlockEnchanter;
import com.legacy.aether.blocks.container.BlockFreezer;
import com.legacy.aether.blocks.container.BlockIncubator;
import com.legacy.aether.blocks.container.BlockTreasureChest;
import com.legacy.aether.blocks.decorative.BlockAerogel;
import com.legacy.aether.blocks.decorative.BlockAetherFlowerPot;
import com.legacy.aether.blocks.decorative.BlockAetherGrassPath;
import com.legacy.aether.blocks.decorative.BlockAetherSlab;
import com.legacy.aether.blocks.decorative.BlockAetherStairs;
import com.legacy.aether.blocks.decorative.BlockAetherWall;
import com.legacy.aether.blocks.decorative.BlockAmbrosiumTorch;
import com.legacy.aether.blocks.decorative.BlockAmbrosiumTorchWall;
import com.legacy.aether.blocks.decorative.BlockColoredAercloud;
import com.legacy.aether.blocks.decorative.BlockHolystoneBrick;
import com.legacy.aether.blocks.decorative.BlockQuicksoilGlass;
import com.legacy.aether.blocks.decorative.BlockSkyrootFence;
import com.legacy.aether.blocks.decorative.BlockSkyrootFenceGate;
import com.legacy.aether.blocks.decorative.BlockSkyrootPlanks;
import com.legacy.aether.blocks.decorative.BlockZanite;
import com.legacy.aether.blocks.dungeon.BlockDungeon;
import com.legacy.aether.blocks.dungeon.BlockDungeonLight;
import com.legacy.aether.blocks.natural.BlockAetherDirt;
import com.legacy.aether.blocks.natural.BlockAetherFlower;
import com.legacy.aether.blocks.natural.BlockAetherGrass;
import com.legacy.aether.blocks.natural.BlockAetherLeaves;
import com.legacy.aether.blocks.natural.BlockAetherLog;
import com.legacy.aether.blocks.natural.BlockAetherSapling;
import com.legacy.aether.blocks.natural.BlockHolystone;
import com.legacy.aether.blocks.natural.BlockIcestone;
import com.legacy.aether.blocks.natural.BlockQuicksoil;
import com.legacy.aether.blocks.natural.aercloud.BlockAercloud;
import com.legacy.aether.blocks.natural.enchanted.BlockEnchantedAetherGrass;
import com.legacy.aether.blocks.natural.enchanted.BlockEnchantedGravitite;
import com.legacy.aether.blocks.natural.ore.BlockAmbrosiumOre;
import com.legacy.aether.blocks.natural.ore.BlockGravititeOre;
import com.legacy.aether.blocks.natural.ore.BlockZaniteOre;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.world.gen.GoldenOakSaplingGenerator;
import com.legacy.aether.world.gen.SkyrootSaplingGenerator;

public class BlocksAether
{

	public static final Block aether_grass = register("aether_grass", new BlockAetherGrass());

	public static final Block enchanted_aether_grass = register("enchanted_aether_grass", new BlockEnchantedAetherGrass());

	public static final Block aether_dirt = register("aether_dirt", new BlockAetherDirt());

	public static final Block holystone = register("holystone", new BlockHolystone());

	public static final Block mossy_holystone = register("mossy_holystone", new BlockHolystone());

	public static final Block skyroot_planks = register("skyroot_planks", new BlockSkyrootPlanks());

	public static final Block skyroot_sapling = register("skyroot_sapling", new BlockAetherSapling(new SkyrootSaplingGenerator()));

	public static final Block golden_oak_sapling = register("golden_oak_sapling", new BlockAetherSapling(new GoldenOakSaplingGenerator()));

	public static final Block quicksoil = register("quicksoil", new BlockQuicksoil());

	public static final Block icestone = register("icestone", new BlockIcestone());

	public static final Block cold_aercloud = register("cold_aercloud", new BlockAercloud(MaterialColor.WHITE));

	public static final Block blue_aercloud = register("blue_aercloud", new BlockAercloud(MaterialColor.BLUE));

	public static final Block golden_aercloud = register("golden_aercloud", new BlockAercloud(MaterialColor.YELLOW));

	public static final Block white_dyed_aercloud = register("white_dyed_aercloud", new BlockColoredAercloud(DyeColor.WHITE));

	public static final Block orange_dyed_aercloud = register("orange_dyed_aercloud", new BlockColoredAercloud(DyeColor.ORANGE));

	public static final Block magenta_dyed_aercloud = register("magenta_dyed_aercloud", new BlockColoredAercloud(DyeColor.MAGENTA));

	public static final Block light_blue_dyed_aercloud = register("light_blue_dyed_aercloud", new BlockColoredAercloud(DyeColor.LIGHT_BLUE));

	public static final Block yellow_dyed_aercloud = register("yellow_dyed_aercloud", new BlockColoredAercloud(DyeColor.YELLOW));

	public static final Block lime_dyed_aercloud = register("lime_dyed_aercloud", new BlockColoredAercloud(DyeColor.LIME));

	public static final Block pink_dyed_aercloud = register("pink_dyed_aercloud", new BlockColoredAercloud(DyeColor.PINK));

	public static final Block grey_dyed_aercloud = register("grey_dyed_aercloud", new BlockColoredAercloud(DyeColor.GRAY));

	public static final Block light_grey_dyed_aercloud = register("light_grey_dyed_aercloud", new BlockColoredAercloud(DyeColor.LIGHT_GRAY));

	public static final Block cyan_dyed_aercloud = register("cyan_dyed_aercloud", new BlockColoredAercloud(DyeColor.CYAN));

	public static final Block purple_dyed_aercloud = register("purple_dyed_aercloud", new BlockColoredAercloud(DyeColor.PURPLE));

	public static final Block blue_dyed_aercloud = register("blue_dyed_aercloud", new BlockColoredAercloud(DyeColor.BLUE));

	public static final Block brown_dyed_aercloud = register("brown_dyed_aercloud", new BlockColoredAercloud(DyeColor.BROWN));

	public static final Block green_dyed_aercloud = register("green_dyed_aercloud", new BlockColoredAercloud(DyeColor.GREEN));

	public static final Block red_dyed_aercloud = register("red_dyed_aercloud", new BlockColoredAercloud(DyeColor.RED));

	public static final Block black_dyed_aercloud = register("black_dyed_aercloud", new BlockColoredAercloud(DyeColor.BLACK));

	public static final Block pink_aercloud = register("pink_aercloud", new BlockAercloud(MaterialColor.PINK));

	public static final Block ambrosium_ore = register("ambrosium_ore", new BlockAmbrosiumOre());

	public static final Block zanite_ore = register("zanite_ore", new BlockZaniteOre());

	public static final Block gravitite_ore = register("gravitite_ore", new BlockGravititeOre());

	public static final Block zanite_block = register("zanite_block", new BlockZanite());

	public static final Block enchanted_gravitite = register("enchanted_gravitite", new BlockEnchantedGravitite());

	public static final Block holystone_brick = register("holystone_brick", new BlockHolystoneBrick());

	public static final Block skyroot_log = register("skyroot_log", new BlockAetherLog());

	public static final Block golden_oak_log = register("golden_oak_log", new BlockAetherLog());

	public static final Block skyroot_leaves = register("skyroot_leaves", new BlockAetherLeaves(MaterialColor.FOLIAGE));

	public static final Block golden_oak_leaves = register("golden_oak_leaves", new BlockAetherLeaves(MaterialColor.GOLD));

	public static final Block crystal_leaves = register("crystal_leaves", new BlockAetherLeaves(MaterialColor.DIAMOND));

	public static final Block crystal_fruit_leaves = register("crystal_fruit_leaves", new BlockAetherLeaves(MaterialColor.DIAMOND));

	public static final Block holiday_leaves = register("holiday_leaves", new BlockAetherLeaves(MaterialColor.PURPLE_TERRACOTTA));

	public static final Block decorated_holiday_leaves = register("decorated_holiday_leaves", new BlockAetherLeaves(MaterialColor.PURPLE_TERRACOTTA));

	public static final Block aerogel = register("aerogel", new BlockAerogel());

	public static final Block ambrosium_torch = register("ambrosium_torch", new BlockAmbrosiumTorch());

	public static final Block ambrosium_torch_wall = register("ambrosium_wall_torch", new BlockAmbrosiumTorchWall());

	public static final Block enchanter = register("enchanter", new BlockEnchanter());

	public static final Block freezer = register("freezer", new BlockFreezer());

	public static final Block incubator = register("incubator", new BlockIncubator());

	public static final Block treasure_chest = register("treasure_chest", new BlockTreasureChest());

	public static final Block chest_mimic = register("chest_mimic", new BlockChestMimic());

	public static final Block carved_stone = register("carved_stone", new BlockDungeon(false));

	public static final Block angelic_stone = register("angelic_stone", new BlockDungeon(false));

	public static final Block hellfire_stone = register("hellfire_stone", new BlockDungeon(false));

	public static final Block sentry_stone = register("sentry_stone", new BlockDungeonLight(false));

	public static final Block light_angelic_stone = register("light_angelic_stone", new BlockDungeonLight(false));

	public static final Block light_hellfire_stone = register("light_hellfire_stone", new BlockDungeonLight(false));

	public static final Block locked_carved_stone = register("locked_carved_stone", new BlockDungeon(true));

	public static final Block locked_angelic_stone = register("locked_angelic_stone", new BlockDungeon(true));

	public static final Block locked_hellfire_stone = register("locked_hellfire_stone", new BlockDungeon(true));

	public static final Block locked_sentry_stone = register("locked_sentry_stone", new BlockDungeonLight(true));

	public static final Block locked_light_angelic_stone = register("locked_light_angelic_stone", new BlockDungeonLight(true));

	public static final Block locked_light_hellfire_stone = register("locked_light_hellfire_stone", new BlockDungeonLight(true));

	public static final Block purple_flower = register("purple_flower", new BlockAetherFlower(StatusEffects.BAD_OMEN, 5));

	public static final Block white_flower = register("white_flower", new BlockAetherFlower(StatusEffects.HEALTH_BOOST, 20));

	public static final Block potted_purple_flower = register("potted_purple_flower", new BlockAetherFlowerPot(purple_flower));

	public static final Block potted_white_flower = register("potted_white_flower", new BlockAetherFlowerPot(white_flower));

	public static final Block aether_grass_path = register("aether_grass_path", new BlockAetherGrassPath());

	public static final Block aether_portal = register("aether_portal", new BlockAetherPortal());

	public static final Block quicksoil_glass = register("quicksoil_glass", new BlockQuicksoilGlass());

	public static final Block skyroot_stairs = register("skyroot_stairs", new BlockAetherStairs(skyroot_planks.getDefaultState()));

	public static final Block holystone_stairs = register("holystone_stairs", new BlockAetherStairs(holystone.getDefaultState()));

	public static final Block holystone_brick_stairs = register("holystone_brick_stairs", new BlockAetherStairs(holystone_brick.getDefaultState()));

	public static final Block mossy_holystone_stairs = register("mossy_holystone_stairs", new BlockAetherStairs(mossy_holystone.getDefaultState()));

	public static final Block aerogel_stairs = register("aerogel_stairs", new BlockAetherStairs(aerogel.getDefaultState()));

	public static final Block carved_stairs = register("carved_stairs", new BlockAetherStairs(carved_stone.getDefaultState()));

	public static final Block angelic_stairs = register("angelic_stairs", new BlockAetherStairs(angelic_stone.getDefaultState()));

	public static final Block hellfire_stairs = register("hellfire_stairs", new BlockAetherStairs(hellfire_stone.getDefaultState()));

	public static final Block skyroot_slab = register("skyroot_slab", new BlockAetherSlab(skyroot_planks.getDefaultState()));

	public static final Block holystone_slab = register("holystone_slab", new BlockAetherSlab(holystone.getDefaultState()));

	public static final Block holystone_brick_slab = register("holystone_brick_slab", new BlockAetherSlab(holystone_brick.getDefaultState()));

	public static final Block mossy_holystone_slab = register("mossy_holystone_slab", new BlockAetherSlab(mossy_holystone.getDefaultState()));

	public static final Block aerogel_slab = register("aerogel_slab", new BlockAetherSlab(aerogel.getDefaultState()));

	public static final Block carved_slab = register("carved_slab", new BlockAetherSlab(carved_stone.getDefaultState()));

	public static final Block angelic_slab = register("angelic_slab", new BlockAetherSlab(angelic_stone.getDefaultState()));

	public static final Block hellfire_slab = register("hellfire_slab", new BlockAetherSlab(hellfire_stone.getDefaultState()));

	public static final Block skyroot_fence = register("skyroot_fence", new BlockSkyrootFence());

	public static final Block skyroot_fence_gate = register("skyroot_fence_gate", new BlockSkyrootFenceGate());

	public static final Block holystone_wall = register("holystone_wall", new BlockAetherWall(holystone.getDefaultState()));

	public static final Block holystone_brick_wall = register("holystone_brick_wall", new BlockAetherWall(holystone_brick.getDefaultState()));

	public static final Block mossy_holystone_wall = register("mossy_holystone_wall", new BlockAetherWall(mossy_holystone.getDefaultState()));

	public static final Block carved_wall = register("carved_wall", new BlockAetherWall(carved_stone.getDefaultState()));

	public static final Block angelic_wall = register("angelic_wall", new BlockAetherWall(angelic_stone.getDefaultState()));

	public static final Block hellfire_wall = register("hellfire_wall", new BlockAetherWall(hellfire_stone.getDefaultState()));

	public static final Block aerogel_wall = register("aerogel_wall", new BlockAetherWall(aerogel.getDefaultState()));

	/*

	public static Block berry_bush, berry_bush_stem;

	public static Block carved_stone_trap, angelic_stone_trap, hellfire_stone_trap, sentry_stone_trap, light_angelic_stone_trap, light_hellfire_stone_trap;

	public static Block crystal_sapling;

	public static Block pillar, pillar_top;

	public static Block present;

	public static Block sun_altar;
	
	public static Block skyroot_bookshelf;*/

	public static void register()
	{
		Aether.log("Registering Aether Blocks");

		register(aether_grass);
		register(enchanted_aether_grass);
		register(aether_dirt);
		register(holystone);
		register(mossy_holystone);
		register(skyroot_planks);
		register(skyroot_sapling, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(golden_oak_sapling, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(quicksoil);
		register(icestone);
		register(cold_aercloud);
		register(blue_aercloud);
		register(golden_aercloud);
		register(white_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(orange_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(magenta_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(light_blue_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(yellow_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(lime_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(pink_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(grey_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(light_grey_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(cyan_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(purple_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(blue_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(brown_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(green_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(red_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(black_dyed_aercloud, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(pink_aercloud);
		register(ambrosium_ore);
		register(zanite_ore);
		register(gravitite_ore);
		register(zanite_block);
		register(enchanted_gravitite);
		register(holystone_brick);
		register(skyroot_log);
		register(golden_oak_log);
		register(skyroot_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(golden_oak_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(crystal_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(crystal_fruit_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(holiday_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(decorated_holiday_leaves, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(aerogel, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(ambrosium_torch, new WallStandingBlockItem(ambrosium_torch, ambrosium_torch_wall, new Item.Settings().itemGroup(AetherItemGroup.AETHER_BLOCKS)));
		register(ambrosium_torch_wall, new BlockItem(ambrosium_torch_wall, new Item.Settings()));
		register(enchanter);
		register(freezer);
		register(incubator);
		register(treasure_chest);
		register(chest_mimic);
		register(carved_stone);
		register(angelic_stone);
		register(hellfire_stone);
		register(sentry_stone);
		register(light_angelic_stone);
		register(light_hellfire_stone);
		register(locked_carved_stone);
		register(locked_angelic_stone);
		register(locked_hellfire_stone);
		register(locked_sentry_stone);
		register(locked_light_angelic_stone);
		register(locked_light_hellfire_stone);
		register(purple_flower, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(white_flower, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(potted_purple_flower, (ItemGroup) null);
		register(potted_white_flower, (ItemGroup) null);
		register(aether_grass_path, AetherItemGroup.AETHER_DECORATION_BLOCKS);
		register(aether_portal);
		register(quicksoil_glass);
		register(skyroot_stairs);
		register(holystone_stairs);
		register(holystone_brick_stairs);
		register(mossy_holystone_stairs);
		register(aerogel_stairs);
		register(carved_stairs);
		register(angelic_stairs);
		register(hellfire_stairs);
		register(skyroot_slab);
		register(holystone_slab);
		register(holystone_brick_slab);
		register(mossy_holystone_slab);
		register(aerogel_slab);
		register(aerogel_wall);
		register(carved_slab);
		register(angelic_slab);
		register(hellfire_slab);
		register(skyroot_fence);
		register(skyroot_fence_gate);
		register(holystone_wall);
		register(holystone_brick_wall);
		register(mossy_holystone_wall);
		register(carved_wall);
		register(angelic_wall);
		register(hellfire_wall);
		register(aerogel_wall);

		/*
		carved_stone_trap = register("carved_stone_trap", new BlockDungeonTrap(carved_stone.getDefaultState()));
		angelic_stone_trap = register("angelic_stone_trap", new BlockDungeonTrap(angelic_stone.getDefaultState()));
		hellfire_stone_trap = register("hellfire_stone_trap", new BlockDungeonTrap(hellfire_stone.getDefaultState()));
		locked_sentry_stone = register("locked_sentry_stone", new BlockDungeonTrapLight(sentry_stone.getDefaultState()));
		locked_light_angelic_stone = register("locked_light_angelic_stone", new BlockDungeonTrapLight(light_angelic_stone.getDefaultState()));
		locked_light_hellfire_stone = register("locked_light_hellfire_stone", new BlockDungeonTrapLight(light_hellfire_stone.getDefaultState()));*/
	}

	private static BlockItem register(Block item)
	{
		return register(item, AetherItemGroup.AETHER_BLOCKS);
	}

	private static BlockItem register(Block block, ItemGroup group)
	{
		return register(block, new BlockItem(block, new Item.Settings().itemGroup(group)));
	}

	private static BlockItem register(Block block, BlockItem item)
	{
		return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), item);
	}

	private static <T extends Block> T register(String name, T block)
	{
		return Registry.register(Registry.BLOCK, Aether.locate(name), block);
	}

}