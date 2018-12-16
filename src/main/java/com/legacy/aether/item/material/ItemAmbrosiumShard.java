package com.legacy.aether.item.material;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.legacy.aether.blocks.BlocksAether;

public class ItemAmbrosiumShard extends Item
{

	public ItemAmbrosiumShard()
	{
		super(new Settings().itemGroup(ItemGroup.MISC));
	}

	@Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        if (context.getWorld().getBlockState(context.getPos()).getBlock() == BlocksAether.aether_grass)
        {
        	if (!context.getPlayer().isCreative())
        	{
        		context.getItemStack().shrink(1);
        	}

        	context.getWorld().setBlockState(context.getPos(), BlocksAether.enchanted_aether_grass.getDefaultState());

        	return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
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

			playerIn.heal(2.0F);

    		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
		}

		return new TypedActionResult<ItemStack>(ActionResult.PASS, heldItem);
    }

}