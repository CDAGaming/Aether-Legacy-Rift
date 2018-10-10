package com.legacy.aether.item.tool;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpade;

public class ItemAetherShovel extends ItemSpade implements IAetherTool
{

	private final AetherToolType material;

	public ItemAetherShovel(AetherToolType material, IItemTier itemTier, float damageVsEntity, float attackSpeed) 
	{
		super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.TOOLS));

		this.material = material;
	}

	public ItemAetherShovel(AetherToolType material, EnumRarity rarity, IItemTier itemTier, float damageVsEntity, float attackSpeed) 
	{
		super(itemTier, damageVsEntity, attackSpeed, new Builder().group(ItemGroup.TOOLS).rarity(rarity));

		this.material = material;
	}

	@Override
	public AetherToolType getMaterial() 
	{
		return this.material;
	}

}