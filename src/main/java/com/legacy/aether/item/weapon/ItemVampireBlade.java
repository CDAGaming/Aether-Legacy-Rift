package com.legacy.aether.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemVampireBlade extends ItemAetherSword
{

	public ItemVampireBlade()
	{
		super(AetherTier.Legendary, ItemsAether.AETHER_LOOT, 3, -2.4F);
	}

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	if (!(attacker instanceof EntityPlayer))
    	{
    		return super.hitEntity(stack, target, attacker);
    	}

		EntityPlayer player = (EntityPlayer)attacker;

		if(player.getHealth() < player.getMaxHealth())
		{
			player.heal(1.0F);
		}

        return super.hitEntity(stack, target, attacker);
    }

}