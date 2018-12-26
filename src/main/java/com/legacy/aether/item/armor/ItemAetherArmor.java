package com.legacy.aether.item.armor;

import net.minecraft.client.render.entity.ArmorEntityRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class ItemAetherArmor extends ArmorItem
{

	private String armorName = "";

	private int color;

	public ItemAetherArmor(AetherArmorType typeIn, EquipmentSlot slotIn)
	{
		super(typeIn.getMaterial(), slotIn, new Settings().itemGroup(ItemGroup.COMBAT));

		this.color = typeIn.getColor();
	}

	public ItemAetherArmor(AetherArmorType typeIn, Rarity rarityIn, EquipmentSlot slotIn)
	{
		super(typeIn.getMaterial(), slotIn, new Settings().itemGroup(ItemGroup.COMBAT).rarity(rarityIn));

		this.color = typeIn.getColor();
	}

	public ItemAetherArmor(String nameIn, AetherArmorType typeIn, EquipmentSlot slotIn)
	{
		this(typeIn, slotIn);

		this.armorName = nameIn;
	}

	public ItemAetherArmor(String nameIn, AetherArmorType typeIn, Rarity rarityIn, EquipmentSlot slotIn)
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