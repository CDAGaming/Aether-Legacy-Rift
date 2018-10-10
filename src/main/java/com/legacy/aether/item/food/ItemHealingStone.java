package com.legacy.aether.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHealingStone extends ItemAetherFood
{

	public ItemHealingStone() 
	{
		super(new Builder().group(ItemGroup.FOOD).rarity(EnumRarity.RARE), 1, 0.0F);

		this.setAlwaysEdible();
	}

	@Override
    protected void onFoodEaten(ItemStack stackIn, World worldIn, EntityPlayer playerIn)
    {
		playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 610, 0));
    }

	@Override
    public boolean hasEffect(ItemStack stackIn)
    {
    	return true;
    }

}