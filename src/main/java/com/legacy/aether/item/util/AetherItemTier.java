package com.legacy.aether.item.util;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyLoadBase;

public class AetherItemTier implements IItemTier
{

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final float damageVsEntity;
    private final int enchantability;

    private final LazyLoadBase<Ingredient> ingredientLoader;

	public AetherItemTier(int harvestLevel, int maxUses, float efficiencyOnProperMaterial, float damageVsEntity, int enchantability, IItemProvider repairMaterial)
	{
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiencyOnProperMaterial = efficiencyOnProperMaterial;
        this.damageVsEntity = damageVsEntity;
        this.enchantability = enchantability;

        this.ingredientLoader = new LazyLoadBase<>(() -> Ingredient.fromItems(repairMaterial));
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return this.ingredientLoader.getValue();
	}

	@Override
	public int getHarvestLevel()
	{
		return this.harvestLevel;
	}

	@Override
	public int getMaxUses()
	{
		return this.maxUses;
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public float getEfficiency()
	{
		return this.efficiencyOnProperMaterial;
	}

	@Override
	public float getAttackDamage()
	{
		return this.damageVsEntity;
	}

}