package com.legacy.aether.item.tool;

import com.legacy.aether.item.util.AetherTier;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPickaxe;

public class ItemAetherPickaxe extends ItemPickaxe implements IAetherTool
{

	private AetherTier material;

	public ItemAetherPickaxe(AetherTier material, int damageVsEntity, float attackSpeed) 
	{
		super(material.getDefaultTier(), damageVsEntity, attackSpeed, new Properties().group(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherPickaxe(AetherTier material, EnumRarity rarity, int damageVsEntity, float attackSpeed) 
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