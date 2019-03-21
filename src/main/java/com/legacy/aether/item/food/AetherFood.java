package com.legacy.aether.item.food;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodItemSetting;

public class AetherFood
{

	public static final FoodItemSetting DEFAULT = new FoodItemSetting.Builder().hunger(2).saturationModifier(1.5F).build();

	public static final FoodItemSetting ENCHANTED_BLUEBERRY = new FoodItemSetting.Builder().hunger(8).saturationModifier(10.0F).build();

	public static final FoodItemSetting WHITE_APPLE = new FoodItemSetting.Builder().saturationModifier(10.0F).alwaysEdible().eatenFast().build();

	public static final FoodItemSetting HEALING_STONE = new FoodItemSetting.Builder().saturationModifier(2.5F).alwaysEdible().eatenFast().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 610, 0), 1.0F).build();

}