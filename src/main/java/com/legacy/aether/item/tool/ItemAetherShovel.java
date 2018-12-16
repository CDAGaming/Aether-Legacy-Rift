package com.legacy.aether.item.tool;

import com.legacy.aether.item.util.AetherTier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.Rarity;

public class ItemAetherShovel extends ShovelItem implements IAetherTool
{

	private final AetherTier material;

	public ItemAetherShovel(AetherTier material, float damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherShovel(AetherTier material, Rarity rarity, float damageVsEntity, float attackSpeed)
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public AetherTier getMaterial() 
	{
		return this.material;
	}

}