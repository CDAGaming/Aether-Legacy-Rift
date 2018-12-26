package com.legacy.aether.item.staff;

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

public class ItemCloudStaff extends Item
{

	public ItemCloudStaff() 
	{
		super(new Settings().stackSize(1).durability(60).itemGroup(ItemGroup.TOOLS));
	}

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
    	ItemStack heldItem = playerIn.getStackInHand(handIn);
		PlayerAether playerAether = ((IEntityPlayerAether)playerIn).getPlayerAether();

    	if (worldIn.isRemote)
    	{
    		return super.use(worldIn, playerIn, handIn);
    	}

    	if (playerAether.clouds.isEmpty())
    	{
			//EntityMiniCloud leftCloud = new EntityMiniCloud(worldIn, playerIn, 0);
			//EntityMiniCloud rightCloud = new EntityMiniCloud(worldIn, playerIn, 1);

			//playerAether.clouds.add(leftCloud);
			//playerAether.clouds.add(rightCloud);

			//worldIn.spawnEntity(leftCloud);
			//worldIn.spawnEntity(rightCloud);

    		heldItem.applyDamage(1, playerIn);

            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, heldItem);
    	}

		return super.use(worldIn, playerIn, handIn);
    }

}