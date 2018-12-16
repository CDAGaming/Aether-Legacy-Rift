package com.legacy.aether.item.food;

import com.legacy.aether.player.IEntityPlayerAether;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWhiteApple extends ItemAetherFood
{

	public ItemWhiteApple() 
	{
		super(0, 0.0F);
	}

	@Override
    protected void onConsumed(ItemStack stackIn, World worldIn, PlayerEntity playerIn)
    {
		((IEntityPlayerAether)playerIn).getPlayerAether().applyCure(300);
    }

}