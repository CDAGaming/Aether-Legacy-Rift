package com.legacy.aether.item.food;

import net.minecraft.class_4174;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class AetherFood
{

	public static final class_4174 DEFAULT = create(2, 1.5F, false, false, false);

	public static final class_4174 ENCHANTED_BLUEBERRY = create(8, 10.0F, false, false, false);

	public static final class_4174 WHITE_APPLE = create(0, 10.0F, false, true, true);

	public static final class_4174 HEALING_STONE = create(0, 2.5F, false, true, true, new StatusEffectInstance(StatusEffects.REGENERATION, 610, 0), 1.0F);

	//public static final class_4174

	private static class_4174 create(int hunger, float saturation, boolean wolfFood, boolean alwaysConsumable, boolean quickEating)
	{
		return create(hunger, saturation, wolfFood, alwaysConsumable, quickEating, null, -1.0F);
	}

	private static class_4174 create(int hunger, float saturation, boolean wolfFood, boolean alwaysConsumable, boolean quickEating, StatusEffectInstance statusEffect, float chance)
	{
		class_4174.class_4175 builder = new class_4174.class_4175();

		if (wolfFood)
		{
			builder.method_19236();
		}

		if (alwaysConsumable)
		{
			builder.method_19240();
		}

		if (quickEating)
		{
			builder.method_19241();
		}

		if (chance > 0.0F)
		{
			builder.method_19239(statusEffect, chance);
		}

		return builder.method_19238(hunger).method_19237(saturation).method_19242();
	}

}