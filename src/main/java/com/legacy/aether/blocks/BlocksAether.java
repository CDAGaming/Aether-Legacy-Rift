package com.legacy.aether.blocks;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.container.BlockEnchanter;
import com.legacy.aether.blocks.container.BlockFreezer;
import com.legacy.aether.blocks.decorative.*;
import com.legacy.aether.blocks.natural.*;
import com.legacy.aether.blocks.natural.aercloud.BlockAercloud;
import com.legacy.aether.blocks.natural.enchanted.BlockEnchantedAetherGrass;
import com.legacy.aether.blocks.natural.enchanted.BlockEnchantedGravitite;
import com.legacy.aether.blocks.natural.ore.BlockAmbrosiumOre;
import com.legacy.aether.blocks.natural.ore.BlockGravititeOre;
import com.legacy.aether.blocks.natural.ore.BlockZaniteOre;
import com.legacy.aether.blocks.natural.tree.GoldenOakTree;
import com.legacy.aether.blocks.natural.tree.SkyrootTree;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

public class BlocksAether implements BlockAdder, ItemAdder {

    public static final Block[] blockList = new Block[75];
    public static final Item[] itemBlockList = new Item[blockList.length];
    public static Block aether_grass, enchanted_aether_grass, aether_dirt;
    public static Block holystone, mossy_holystone, holystone_brick;
    public static Block cold_aercloud, blue_aercloud, golden_aercloud, pink_aercloud;
    public static Block white_dyed_aercloud, orange_dyed_aercloud, magenta_dyed_aercloud, light_blue_dyed_aercloud, yellow_dyed_aercloud, lime_dyed_aercloud, pink_dyed_aercloud;
    public static Block grey_dyed_aercloud, light_grey_dyed_aercloud, cyan_dyed_aercloud, purple_dyed_aercloud, blue_dyed_aercloud, brown_dyed_aercloud, green_dyed_aercloud;
    public static Block red_dyed_aercloud, black_dyed_aercloud;
    public static Block quicksoil, icestone;
    public static Block ambrosium_ore, zanite_ore, gravitite_ore;
    public static Block skyroot_leaves, skyroot_log, golden_oak_leaves, golden_oak_log, crystal_leaves, skyroot_planks;
    public static Block quicksoil_glass, aerogel;
    public static Block enchanted_gravitite, zanite_block;
    public static Block berry_bush, berry_bush_stem;
    public static Block enchanter, freezer, incubator;
    public static Block ambrosium_torch, ambrosium_torch_wall;
    public static Block aether_portal;
    public static Block chest_mimic, treasure_chest;
    public static Block carved_stone, angelic_stone, hellfire_stone, sentry_stone, light_angelic_stone, light_hellfire_stone;
    public static Block locked_carved_stone, locked_angelic_stone, locked_hellfire_stone, locked_sentry_stone, locked_light_angelic_stone, locked_light_hellfire_stone;
    public static Block carved_stone_trap, angelic_stone_trap, hellfire_stone_trap, sentry_stone_trap, light_angelic_stone_trap, light_hellfire_stone_trap;
    public static Block purple_flower, white_flower;
    public static Block skyroot_sapling, golden_oak_sapling, crystal_sapling;
    public static Block pillar, pillar_top;
    public static Block skyroot_fence, skyroot_fence_gate;
    public static Block carved_stairs, angelic_stairs, hellfire_stairs, skyroot_stairs, mossy_holystone_stairs, holystone_stairs, holystone_brick_stairs, aerogel_stairs;
    public static Block carved_slab, angelic_slab, hellfire_slab, skyroot_slab, holystone_slab, holystone_brick_slab, mossy_holystone_slab, aerogel_slab;
    public static Block carved_double_slab, angelic_double_slab, hellfire_double_slab, skyroot_double_slab, holystone_double_slab, holystone_brick_double_slab, mossy_holystone_double_slab, aerogel_double_slab;
    public static Block holystone_wall, mossy_holystone_wall, holystone_brick_wall, carved_wall, angelic_wall, hellfire_wall, aerogel_wall;
    public static Block holiday_leaves, present;
    public static Block sun_altar;
    public static Block skyroot_bookshelf;
    private static int availableId;

    public static Block register(String name, Block block) {
        Block.register(Aether.locate(name), block);

        blockList[availableId] = block;

        ++availableId;

        return block;
    }

    public static Block register(String name, Block block, ItemBlock itemBlock) {
        itemBlockList[availableId] = itemBlock;

        return register(name, block);
    }

    @Override
    public void registerBlocks() {
        aether_grass = register("aether_grass", new BlockAetherGrass());
        enchanted_aether_grass = register("enchanted_aether_grass", new BlockEnchantedAetherGrass());
        aether_dirt = register("aether_dirt", new BlockAetherDirt());
        holystone = register("holystone", new BlockHolystone());
        mossy_holystone = register("mossy_holystone", new BlockHolystone());
        holystone_brick = register("holystone_brick", new BlockHolystoneBrick());
        cold_aercloud = register("cold_aercloud", new BlockAercloud());
        blue_aercloud = register("blue_aercloud", new BlockAercloud());
        golden_aercloud = register("golden_aercloud", new BlockAercloud());
        white_dyed_aercloud = register("white_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.WHITE));
        orange_dyed_aercloud = register("orange_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.ORANGE));
        magenta_dyed_aercloud = register("magenta_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.MAGENTA));
        light_blue_dyed_aercloud = register("light_blue_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.LIGHT_BLUE));
        yellow_dyed_aercloud = register("yellow_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.YELLOW));
        lime_dyed_aercloud = register("lime_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.LIME));
        pink_dyed_aercloud = register("pink_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.PINK));
        grey_dyed_aercloud = register("grey_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.GRAY));
        light_grey_dyed_aercloud = register("light_grey_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.LIGHT_GRAY));
        cyan_dyed_aercloud = register("cyan_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.CYAN));
        purple_dyed_aercloud = register("purple_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.PURPLE));
        blue_dyed_aercloud = register("blue_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.BLUE));
        brown_dyed_aercloud = register("brown_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.BROWN));
        green_dyed_aercloud = register("green_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.GREEN));
        red_dyed_aercloud = register("red_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.RED));
        black_dyed_aercloud = register("black_dyed_aercloud", new BlockColoredAercloud(EnumDyeColor.BLACK));
        pink_aercloud = register("pink_aercloud", new BlockAercloud());
        quicksoil = register("quicksoil", new BlockQuicksoil());
        icestone = register("icestone", new BlockIcestone());
        ambrosium_ore = register("ambrosium_ore", new BlockAmbrosiumOre());
        zanite_ore = register("zanite_ore", new BlockZaniteOre());
        gravitite_ore = register("gravitite_ore", new BlockGravititeOre());
        skyroot_leaves = register("skyroot_leaves", new BlockAetherLeaves());
        skyroot_log = register("skyroot_log", new BlockAetherLog());
        golden_oak_leaves = register("golden_oak_leaves", new BlockAetherLeaves());
        golden_oak_log = register("golden_oak_log", new BlockAetherLog());
        crystal_leaves = register("crystal_leaves", new BlockAetherLeaves());
        skyroot_planks = register("skyroot_planks", new BlockSkyrootPlanks());
        quicksoil_glass = register("quicksoil_glass", new BlockQuicksoilGlass());
        aerogel = register("aerogel", new BlockAerogel());
        enchanted_gravitite = register("enchanted_gravitite", new BlockEnchantedGravitite());
        zanite_block = register("zanite_block", new BlockZanite());

        enchanter = register("enchanter", new BlockEnchanter());
        freezer = register("freezer", new BlockFreezer());

        ambrosium_torch = register("ambrosium_torch", new BlockAmbrosiumTorch());
        ambrosium_torch_wall = register("ambrosium_wall_torch", new BlockAmbrosiumTorchWall());

        itemBlockList[availableId] = new ItemBlock(ambrosium_torch_wall, new Item.Builder());
        itemBlockList[availableId - 1] = new ItemWallOrFloor(ambrosium_torch, ambrosium_torch_wall, new Item.Builder());

        aether_portal = register("aether_portal", new BlockAetherPortal());

		/*carved_stone = register("carved_stone", new BlockDungeon(false));
		angelic_stone = register("angelic_stone", new BlockDungeon(false));
		hellfire_stone = register("hellfire_stone", new BlockDungeon(false));
		sentry_stone = register("sentry_stone", new BlockDungeonLight(false));
		light_angelic_stone = register("light_angelic_stone", new BlockDungeonLight(false));
		light_hellfire_stone = register("light_hellfire_stone", new BlockDungeonLight(false));

		locked_carved_stone = register("locked_carved_stone", new BlockDungeon(true));
		locked_angelic_stone = register("locked_angelic_stone", new BlockDungeon(true));
		locked_hellfire_stone = register("locked_hellfire_stone", new BlockDungeon(true));
		locked_sentry_stone = register("locked_sentry_stone", new BlockDungeonLight(true));
		locked_light_angelic_stone = register("locked_light_angelic_stone", new BlockDungeonLight(true));
		locked_light_hellfire_stone = register("locked_light_hellfire_stone", new BlockDungeonLight(true));

		carved_stone_trap = register("carved_stone_trap", new BlockDungeonTrap(carved_stone.getDefaultState()));
		angelic_stone_trap = register("angelic_stone_trap", new BlockDungeonTrap(angelic_stone.getDefaultState()));
		hellfire_stone_trap = register("hellfire_stone_trap", new BlockDungeonTrap(hellfire_stone.getDefaultState()));
		locked_sentry_stone = register("locked_sentry_stone", new BlockDungeonTrapLight(sentry_stone.getDefaultState()));
		locked_light_angelic_stone = register("locked_light_angelic_stone", new BlockDungeonTrapLight(light_angelic_stone.getDefaultState()));
		locked_light_hellfire_stone = register("locked_light_hellfire_stone", new BlockDungeonTrapLight(light_hellfire_stone.getDefaultState()));*/

        purple_flower = register("purple_flower", new BlockAetherFlower());
        white_flower = register("white_flower", new BlockAetherFlower());
        skyroot_sapling = register("skyroot_sapling", new BlockAetherSapling(new SkyrootTree()));
        golden_oak_sapling = register("golden_oak_sapling", new BlockAetherSapling(new GoldenOakTree()));
    }

    @Override
    public void registerItems() {
        for (int i = 0; i < blockList.length; ++i) {
            Block block = blockList[i];
            Item itemBlock = itemBlockList[i];

            if (block == null) {
                continue;
            }

            if (itemBlock == null) {
                Item.register(block, ItemGroup.BUILDING_BLOCKS);
            } else {
                Item.register(block, itemBlock);
            }
        }
    }

}