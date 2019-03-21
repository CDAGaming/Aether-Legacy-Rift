package com.legacy.aether.item.food;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.item.AetherItemGroup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWhiteApple extends Item
{

	public ItemWhiteApple() 
	{
		super(new Item.Settings().itemGroup(AetherItemGroup.AETHER_FOOD).food(AetherFood.WHITE_APPLE));
	}

	@Override
    public ItemStack onItemFinishedUsing(ItemStack stackIn, World worldIn, LivingEntity entityIn)
    {
		if (entityIn instanceof PlayerEntity)
		{
			AetherAPI.get((PlayerEntity) entityIn).inflictCure(300);
		}

		return super.onItemFinishedUsing(stackIn, worldIn, entityIn);
    }

}