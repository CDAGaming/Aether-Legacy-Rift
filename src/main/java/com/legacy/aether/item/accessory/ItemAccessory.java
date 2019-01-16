package com.legacy.aether.item.accessory;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.accessories.AetherAccessory;
import com.legacy.aether.item.AetherItemGroup;

public class ItemAccessory extends Item
{

	private int color;

	private AccessoryType type;

	private Identifier texture;

	private Identifier texture_slim;

	private float damageMultiplier = 1.0F;

	public ItemAccessory(AccessoryType type, Rarity rarity, int color)
	{
		super(new Settings().itemGroup(AetherItemGroup.AETHER_ACCESSORIES).durability(5 * type.getDurability()).rarity(rarity));

		this.type = type;
		this.color = color;
		this.texture = Aether.locate("textures/armor/accessory_base.png");
		this.texture_slim = Aether.locate("textures/armor/accessory_base_slim.png");
	}

	public ItemAccessory(String material, AccessoryType type, Rarity rarity, int color)
	{
		this(type, rarity, color);

    	this.texture = Aether.locate("textures/armor/accessory_" + material + ".png");
		this.texture_slim = Aether.locate("textures/armor/accessory_" + material + "_slim.png");
	}

	public ItemAccessory(AccessoryType type)
	{
		this(type, Rarity.COMMON, 0xDDDDDD);
	}

	public ItemAccessory(AccessoryType type, Rarity rarity)
	{
		this(type, rarity, 0xDDDDDD);
	}

	public ItemAccessory(AccessoryType type, int color)
	{
		this(type, Rarity.COMMON, color);
	}

	public ItemAccessory(String material, AccessoryType type)
	{
		this(material, type, Rarity.COMMON, 0xDDDDDD);
	}

	public ItemAccessory(String material, AccessoryType type, Rarity rarity)
	{
		this(material, type, rarity, 0xDDDDDD);
	}

	public ItemAccessory(String material, AccessoryType type, int color)
	{
		this(material, type, Rarity.COMMON, color);
	}

	public ItemAccessory setDamageMultiplier(float multiplier)
	{
		this.damageMultiplier = multiplier;

		return this;
	}

	public Identifier getTexture()
	{
		return this.getTexture(false);
	}

	public Identifier getTexture(boolean isSlim)
	{
		return isSlim ? this.texture_slim : this.texture;
	}

	public AccessoryType getType()
	{
		return this.type;
	}

	public int getColor()
	{
		return this.color;
	}

	public float getDamageMultiplier()
	{
		return this.damageMultiplier;
	}

	public void register()
	{
		AetherAPI.instance().register(new AetherAccessory(this, this.type));
	}

}