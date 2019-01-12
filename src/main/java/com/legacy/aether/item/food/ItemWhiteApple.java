package com.legacy.aether.item.food;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.legacy.aether.api.AetherAPI;

public class ItemWhiteApple extends ItemAetherFood
{

	public ItemWhiteApple() 
	{
		super(0, 0.0F);
	}

	@Override
    protected void onConsumed(ItemStack stackIn, World worldIn, PlayerEntity playerIn)
    {
		AetherAPI.get(playerIn).inflictCure(300);
    }

}