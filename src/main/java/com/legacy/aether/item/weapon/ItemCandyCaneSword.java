package com.legacy.aether.item.weapon;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemCandyCaneSword extends ItemAetherSword
{

	public ItemCandyCaneSword()
	{
		super(AetherTier.Candy, 3, -2.0F);
	}

	@Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
    {
        if (entityliving.deathTime > 0)
        {
            return true;
        }
        else
        {
            if ((new Random()).nextBoolean() && entityliving1 != null && entityliving1 instanceof EntityPlayer && !entityliving1.world.isRemote && entityliving.hurtTime > 0)
            {
                entityliving.entityDropItem(ItemsAether.candy_cane, 1);
            }

            itemstack.damageItem(1, entityliving1);
            return true;
        }
    }

}