package com.legacy.aether.item.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemParachute extends Item
{

	public ItemParachute(int maxDamage)
	{
		super(new Properties().maxStackSize(1).defaultMaxDamage(maxDamage).group(ItemGroup.TOOLS));
	}

	public ItemParachute()
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.TOOLS));
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	/*if(EntityParachute.entityHasRoomForCloud(world, entityplayer))
    	{
			if(this == ItemsAether.golden_parachute)
			{
				itemstack.damageItem(1, entityplayer);
			} 
			else 
			{
				itemstack.stackSize--;
			}

			world.spawnEntityInWorld(new EntityParachute(world, entityplayer, this == ItemsAether.golden_parachute));

	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    	}*/

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}