package com.legacy.aether.item.armor;

import net.minecraft.class_1741;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemContainer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class AetherArmorMaterial implements class_1741
{

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;

    private final int maxDamageFactor;

    private final int[] damageReductionAmountArray;

    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> ingredientLoader;

    public AetherArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, ItemContainer repairMaterial)
    {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.ingredientLoader = new LazyLoadBase<>(() -> Ingredient.ofItems(repairMaterial));
    }

	@Override
	public int getDurability(EquipmentSlot slot)
	{
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlot slot)
	{
        return this.damageReductionAmountArray[slot.getIndex()];
	}

	@Override
	public Ingredient method_7695()
	{
		return this.ingredientLoader.getValue();
	}

	@Override
	public SoundEvent method_7698()
	{
		return this.soundEvent;
	}

	@Override
	public String method_7694()
	{
		return this.name;
	}

	@Override
	public float method_7700()
	{
		return this.toughness;
	}

	@Override
	public int method_7699()
	{
		return this.enchantability;
	}

}