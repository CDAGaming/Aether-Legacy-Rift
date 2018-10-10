package com.legacy.aether.item.accessory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
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

	private ResourceLocation texture, texture_slim;

	public ItemAccessory(AccessoryType type, EnumRarity rarity, int color)
	{
		super(new Builder().group(ItemGroup.MISC).maxStackSize(1).rarity(rarity));

		this.type = type;
		this.color = color;
		this.texture = Aether.locate("textures/armor/accessory_base.png");
		this.texture_slim = Aether.locate("textures/armor/accessory_base_slim.png");
	}

	public ItemAccessory(String material, AccessoryType type, EnumRarity rarity, int color)
	{
		this(type, rarity, color);

    	this.texture = Aether.locate("textures/armor/accessory_" + material + ".png");
		this.texture_slim = Aether.locate("textures/armor/accessory_" + material + "_slim.png");
	}

	public ItemAccessory(AccessoryType type)
	{
		this(type, EnumRarity.COMMON, 0xDDDDDD);
	}

	public ItemAccessory(AccessoryType type, EnumRarity rarity)
	{
		this(type, rarity, 0xDDDDDD);
	}

	public ItemAccessory(AccessoryType type, int color)
	{
		this(type, EnumRarity.COMMON, color);
	}

	public ItemAccessory(String material, AccessoryType type)
	{
		this(material, type, EnumRarity.COMMON, 0xDDDDDD);
	}

	public ItemAccessory(String material, AccessoryType type, EnumRarity rarity)
	{
		this(material, type, rarity, 0xDDDDDD);
	}

	public ItemAccessory(String material, AccessoryType type, int color)
	{
		this(material, type, EnumRarity.COMMON, color);
	}

	public void register()
	{
		AetherAPI.instance().register(new Accessory(this.type, this));
	}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack heldItem = playerIn.getHeldItem(handIn);

        if (heldItem != ItemStack.EMPTY)
        {
        	if (((IEntityPlayerAether)playerIn).getPlayerAether().getAccessories().isAccessoryValidForSlot(this.type, heldItem))
        	{
            	heldItem.shrink(1);

                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
        	}
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, heldItem);
    }

    public ResourceLocation getAccessoryTexture()
    {
    	return this.getAccessoryTexture(false);
    }

    public ResourceLocation getAccessoryTexture(boolean isSlim)
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