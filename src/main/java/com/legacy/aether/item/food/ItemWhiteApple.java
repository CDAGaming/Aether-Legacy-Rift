package com.legacy.aether.item.food;

import com.legacy.aether.player.IEntityPlayerAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWhiteApple extends ItemAetherFood
{

	public ItemWhiteApple() 
	{
		super(0, 0.0F);
	}

	@Override
    protected void onFoodEaten(ItemStack stackIn, World worldIn, EntityPlayer playerIn)
    {
		((IEntityPlayerAether)playerIn).getPlayerAether().applyCure(300);
    }

}