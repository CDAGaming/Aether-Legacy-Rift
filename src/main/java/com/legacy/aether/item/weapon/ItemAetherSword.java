package com.legacy.aether.item.weapon;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSword;

import com.legacy.aether.item.util.AetherTier;

public class ItemAetherSword extends ItemSword
{

	private final AetherTier material;

	public ItemAetherSword(AetherTier material, int damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.COMBAT));

		this.material = material;
	}

	public ItemAetherSword(AetherTier material, EnumRarity rarity, int damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.COMBAT).rarity(rarity));

		this.material = material;
	}

}