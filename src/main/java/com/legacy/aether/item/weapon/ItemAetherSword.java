package com.legacy.aether.item.weapon;

import com.legacy.aether.item.tool.AetherToolType;
import com.legacy.aether.item.tool.IAetherTool;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSword;

public class ItemAetherSword extends ItemSword implements IAetherTool
{

	private final AetherToolType material;

	public ItemAetherSword(AetherToolType material, IItemTier itemTier, int damageVsEntity, float attackSpeed) 
	{
		super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.COMBAT));

		this.material = material;
	}

	public ItemAetherSword(AetherToolType material, EnumRarity rarity, IItemTier itemTier, int damageVsEntity, float attackSpeed) 
	{
		super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.COMBAT).rarity(rarity));

		this.material = material;
	}

	@Override
	public AetherToolType getMaterial()
	{
		return this.material;
	}

}