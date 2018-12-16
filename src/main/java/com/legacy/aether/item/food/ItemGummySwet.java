package com.legacy.aether.item.food;

import com.legacy.aether.item.ItemsAether;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemGummySwet extends Item
{

	public ItemGummySwet()
	{
		super(new Settings().itemGroup(ItemGroup.FOOD).rarity(ItemsAether.AETHER_LOOT));
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getStackInHand(handIn);

		if (playerIn.shouldHeal())
		{
			if (!playerIn.isCreative())
			{
				heldItem.shrink(1);
			}

			playerIn.heal(playerIn.getHealthMaximum());

    		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

}