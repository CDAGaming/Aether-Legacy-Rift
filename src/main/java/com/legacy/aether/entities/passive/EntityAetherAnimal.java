package com.legacy.aether.entities.passive;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityAetherAnimal extends EntityAnimal
{

	protected Random random = new Random();

	public EntityAetherAnimal(EntityType<? extends Entity> type, World worldIn) 
	{
		super(type, worldIn);

		//this.spawnableBlock = BlocksAether.aether_grass;
	}

	@Override
    public float getBlockPathWeight(BlockPos pos)
    {
		return this.getBlockPathWeight(pos);
    	//return this.world.getBlockState(pos.down()).getBlock() == this.spawnableBlock ? 10.0F : this.world.getBrightness(pos) - 0.5F;
    }

	public boolean isBreedingItem(ItemStack stack)
    {
		return false;
        //return stack.getItem() == ItemsAether.blue_berry;
    }

}