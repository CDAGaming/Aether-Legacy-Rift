package com.legacy.aether.item.food;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemLifeShard extends Item
{

	public ItemLifeShard() 
	{
		super(new Settings().stackSize(1).itemGroup(ItemGroup.MISC).rarity(ItemsAether.AETHER_LOOT));
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		PlayerAether playerAether = ((IEntityPlayerAether)playerIn).getPlayerAether();
		ItemStack heldItem = playerIn.getStackInHand(handIn);

		if (worldIn.isRemote)
		{
			return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
		}

		if (playerAether.getShardsUsed() < 10)
		{
			playerAether.increaseMaxHP();
			heldItem.subtractAmount(1);

			return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

}