package com.legacy.aether.item.weapon;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
    public boolean onEntityDamaged(ItemStack itemstack, LivingEntity entityliving, LivingEntity entityliving1)
    {
        if (entityliving.deathCounter > 0)
        {
            return true;
        }
        else
        {
            if ((new Random()).nextBoolean() && entityliving1 != null && entityliving1 instanceof PlayerEntity && !entityliving1.world.isRemote && entityliving.hurtTime > 0)
            {
                entityliving.dropItem(ItemsAether.candy_cane, 1);
            }

            itemstack.applyDamage(1, entityliving1);
            return true;
        }
    }

}