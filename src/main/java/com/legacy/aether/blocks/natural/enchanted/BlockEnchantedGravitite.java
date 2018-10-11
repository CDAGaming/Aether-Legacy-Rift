package com.legacy.aether.blocks.natural.enchanted;

import com.legacy.aether.blocks.BlockFloating;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockEnchantedGravitite extends BlockFloating {

    public BlockEnchantedGravitite() {
        super(Block.Builder.create(Material.IRON).hardnessAndResistance(5.0F, -1.0F).sound(SoundType.METAL), false);
    }

}