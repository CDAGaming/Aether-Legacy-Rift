package com.legacy.aether.entities.passive;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.item.ItemsAether;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public abstract class EntityAetherAnimal extends EntityAnimal {

    protected Random random = new Random();

    public EntityAetherAnimal(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);

        this.spawnableBlock = BlocksAether.aether_grass;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ItemsAether.blueberry;
    }

}