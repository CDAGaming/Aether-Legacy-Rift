package com.legacy.aether.item.accessory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.Accessory;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.player.IEntityPlayerAether;

public class ItemAccessory extends Item
{

	private int color;

	private AccessoryType type;

	private Identifier texture, texture_slim;

	public ItemAccessory(AccessoryType type, Rarity rarity, int color)
	{
		super(new Settings().itemGroup(ItemGroup.MISC).stackSize(1).rarity(rarity));

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

	public void register()
	{
		AetherAPI.instance().register(new Accessory(this.type, this));
	}

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
    	ItemStack heldItem = playerIn.getStackInHand(handIn);

        if (heldItem != ItemStack.EMPTY)
        {
        	if (((IEntityPlayerAether)playerIn).getPlayerAether().getAccessories().setAccessory(heldItem))
        	{
            	heldItem.shrink(1);

                return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
        	}
        }

        return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

    public Identifier getAccessoryTexture()
    {
    	return this.getAccessoryTexture(false);
    }

    public Identifier getAccessoryTexture(boolean isSlim)
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

}