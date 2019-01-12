package com.legacy.aether.item.food;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.item.ItemsAether;

public class ItemLifeShard extends Item
{

	public ItemLifeShard() 
	{
		super(new Settings().stackSize(1).itemGroup(ItemGroup.MISC).rarity(ItemsAether.AETHER_LOOT));
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		IPlayerAether playerAether = AetherAPI.get(playerIn);
		ItemStack heldItem = playerIn.getStackInHand(handIn);

		if (worldIn.isClient)
		{
			return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
		}

		if (playerAether.getShardsUsed() < 10)
		{
			playerAether.increaseHealth(1);
			heldItem.subtractAmount(1);

			return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

}