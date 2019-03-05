package com.legacy.aether.item.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import com.legacy.aether.item.AetherItemGroup;

public class ItemHealingStone extends ItemAetherFood
{

	public ItemHealingStone() 
	{
		super(new Settings().itemGroup(AetherItemGroup.AETHER_FOOD).rarity(Rarity.RARE), 1, 0.0F);

		this.setConsumeQuickly();
		this.setAlwaysConsumable();
	}

	@Override
    protected void onConsumed(ItemStack stackIn, World worldIn, LivingEntity entityIn)
    {
		entityIn.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 610, 0));
    }

	@Override
    public boolean hasEnchantmentGlint(ItemStack stackIn)
    {
    	return true;
    }

}