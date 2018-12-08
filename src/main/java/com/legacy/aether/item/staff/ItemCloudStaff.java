package com.legacy.aether.item.staff;

import com.legacy.aether.entities.passive.EntityMiniCloud;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCloudStaff extends Item
{

	public ItemCloudStaff() 
	{
		super(new Properties().maxStackSize(1).defaultMaxDamage(60).group(ItemGroup.TOOLS));
	}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack heldItem = playerIn.getHeldItem(handIn);
		PlayerAether playerAether = ((IEntityPlayerAether)playerIn).getPlayerAether();

    	if (worldIn.isRemote)
    	{
    		return super.onItemRightClick(worldIn, playerIn, handIn);
    	}

    	if (playerAether.clouds.isEmpty())
    	{
			EntityMiniCloud leftCloud = new EntityMiniCloud(worldIn, playerIn, 0);
			EntityMiniCloud rightCloud = new EntityMiniCloud(worldIn, playerIn, 1);

			playerAether.clouds.add(leftCloud);
			playerAether.clouds.add(rightCloud);

			worldIn.spawnEntity(leftCloud);
			worldIn.spawnEntity(rightCloud);

    		heldItem.damageItem(1, playerIn);

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
    	}

		return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}