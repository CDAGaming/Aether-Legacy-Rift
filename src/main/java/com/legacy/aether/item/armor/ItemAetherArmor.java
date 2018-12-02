package com.legacy.aether.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemGroup;

public class ItemAetherArmor extends ItemArmor
{

	private String armorName = "";

	private int color;

	public ItemAetherArmor(AetherArmorType typeIn, EntityEquipmentSlot slotIn) 
	{
		super(typeIn.getMaterial(), slotIn, new Properties().group(ItemGroup.COMBAT));

		this.color = typeIn.getColor();
	}

	public ItemAetherArmor(AetherArmorType typeIn, EnumRarity rarityIn, EntityEquipmentSlot slotIn) 
	{
		super(typeIn.getMaterial(), slotIn, new Properties().group(ItemGroup.COMBAT).rarity(rarityIn));

		this.color = typeIn.getColor();
	}

	public ItemAetherArmor(String nameIn, AetherArmorType typeIn, EntityEquipmentSlot slotIn) 
	{
		this(typeIn, slotIn);

		this.armorName = nameIn;
	}

	public ItemAetherArmor(String nameIn, AetherArmorType typeIn, EnumRarity rarityIn, EntityEquipmentSlot slotIn) 
	{
		this(typeIn, rarityIn, slotIn);

		this.armorName = nameIn;
	}

	public int getColor()
	{
		return this.color;
	}

	public String getArmorName()
	{
		return this.armorName;
	}

}