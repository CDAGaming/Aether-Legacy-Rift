package com.legacy.aether.item.tool;

import com.legacy.aether.item.util.AetherTier;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpade;

public class ItemAetherShovel extends ItemSpade implements IAetherTool
{

	private final AetherTier material;

	public ItemAetherShovel(AetherTier material, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherShovel(AetherTier material, EnumRarity rarity, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public AetherTier getMaterial() 
	{
		return this.material;
	}

}