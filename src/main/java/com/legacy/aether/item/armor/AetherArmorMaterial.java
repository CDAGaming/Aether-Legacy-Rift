package com.legacy.aether.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;

public class AetherArmorMaterial implements IArmorMaterial
{

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;

    private final int maxDamageFactor;

    private final int[] damageReductionAmountArray;

    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadBase<Ingredient> ingredientLoader;

    public AetherArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, IItemProvider repairMaterial)
    {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.ingredientLoader = new LazyLoadBase<>(() -> Ingredient.fromItems(repairMaterial));
    }

	@Override
	public int func_200896_a(EntityEquipmentSlot slot) 
	{
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int func_200902_b(EntityEquipmentSlot slot)
	{
        return this.damageReductionAmountArray[slot.getIndex()];
	}

	@Override
	public Ingredient func_200898_c() 
	{
		return this.ingredientLoader.getValue();
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return this.soundEvent;
	}

	@Override
	public String getName() 
	{
		return this.name;
	}

	@Override
	public float getToughness() 
	{
		return this.toughness;
	}

	@Override
	public int getEnchantability() 
	{
		return this.enchantability;
	}

}