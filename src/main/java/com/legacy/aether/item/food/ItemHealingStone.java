package com.legacy.aether.item.food;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class ItemHealingStone extends ItemAetherFood
{

	public ItemHealingStone() 
	{
		super(new Settings().itemGroup(ItemGroup.FOOD).rarity(Rarity.RARE), 1, 0.0F);

		this.setAlwaysEdible();
	}

	@Override
    protected void onConsumed(ItemStack stackIn, World worldIn, PlayerEntity playerIn)
    {
		playerIn.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 610, 0));
    }

	@Override
    public boolean hasEffect(ItemStack stackIn)
    {
    	return true;
    }

}