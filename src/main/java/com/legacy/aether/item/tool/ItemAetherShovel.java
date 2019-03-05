package com.legacy.aether.item.tool;

import net.minecraft.item.ShovelItem;
import net.minecraft.util.Rarity;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.util.AetherTier;

public class ItemAetherShovel extends ShovelItem implements IAetherTool
{

	private final AetherTier material;

	public ItemAetherShovel(AetherTier material, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(AetherItemGroup.AETHER_TOOLS));

		this.material = material;
	}

	public ItemAetherShovel(AetherTier material, Rarity rarity, float damageVsEntity, float attackSpeed)
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(AetherItemGroup.AETHER_TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public AetherTier getMaterial() 
	{
		return this.material;
	}

}