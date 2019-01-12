package com.legacy.aether.item.util;

import net.minecraft.item.ItemProvider;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public class AetherItemTier implements ToolMaterial
{

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final float damageVsEntity;
    private final int enchantability;

    private final Lazy<Ingredient> ingredientLoader;

	public AetherItemTier(int harvestLevel, int maxUses, float efficiencyOnProperMaterial, float damageVsEntity, int enchantability, ItemProvider repairMaterial)
	{
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiencyOnProperMaterial = efficiencyOnProperMaterial;
        this.damageVsEntity = damageVsEntity;
        this.enchantability = enchantability;

        this.ingredientLoader = new Lazy<>(() -> Ingredient.ofItems(repairMaterial));
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return this.ingredientLoader.get();
	}

	@Override
	public int getMiningLevel()
	{
		return this.harvestLevel;
	}

	@Override
	public int getDurability()
	{
		return this.maxUses;
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public float getBlockBreakingSpeed()
	{
		return this.efficiencyOnProperMaterial;
	}

	@Override
	public float getAttackDamage()
	{
		return this.damageVsEntity;
	}

}