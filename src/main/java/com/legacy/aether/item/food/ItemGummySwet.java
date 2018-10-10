package com.legacy.aether.item.food;

import com.legacy.aether.item.ItemsAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGummySwet extends Item
{

	public ItemGummySwet()
	{
		super(new Builder().group(ItemGroup.FOOD).rarity(ItemsAether.AETHER_LOOT));
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);

		if (playerIn.shouldHeal())
		{
			if (!playerIn.isCreative())
			{
				heldItem.shrink(1);
			}

			playerIn.heal(playerIn.getMaxHealth());

    		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
    }

}