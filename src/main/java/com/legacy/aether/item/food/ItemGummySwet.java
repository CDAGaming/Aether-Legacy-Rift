package com.legacy.aether.item.food;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.ItemsAether;

public class ItemGummySwet extends Item
{

	public ItemGummySwet()
	{
		super(new Settings().itemGroup(AetherItemGroup.AETHER_FOOD).rarity(ItemsAether.AETHER_LOOT));
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getStackInHand(handIn);

		if (playerIn.canFoodHeal())
		{
			if (!playerIn.isCreative())
			{
				heldItem.subtractAmount(1);
			}

			playerIn.heal(playerIn.getHealthMaximum());

    		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

}