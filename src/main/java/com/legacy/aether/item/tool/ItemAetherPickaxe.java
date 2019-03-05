package com.legacy.aether.item.tool;

import net.minecraft.item.PickaxeItem;
import net.minecraft.util.Rarity;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.util.AetherTier;

public class ItemAetherPickaxe extends PickaxeItem implements IAetherTool
{

	private AetherTier material;

	public ItemAetherPickaxe(AetherTier material, int damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(AetherItemGroup.AETHER_TOOLS));

		this.material = material;
	}

	public ItemAetherPickaxe(AetherTier material, Rarity rarity, int damageVsEntity, float attackSpeed)
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