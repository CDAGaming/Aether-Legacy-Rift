package com.legacy.aether.item.tool;

import com.legacy.aether.item.util.AetherTier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.Rarity;

public class ItemAetherPickaxe extends PickaxeItem implements IAetherTool
{

	private AetherTier material;

	public ItemAetherPickaxe(AetherTier material, int damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Settings().itemGroup(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherPickaxe(AetherTier material, Rarity rarity, int damageVsEntity, float attackSpeed)
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