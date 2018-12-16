package com.legacy.aether.item.weapon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
    public boolean onEntityDamaged(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
    	if (!(attacker instanceof PlayerEntity))
    	{
    		return super.onEntityDamaged(stack, target, attacker);
    	}

		PlayerEntity player = (PlayerEntity) attacker;

		if(player.getHealth() < player.getHealthMaximum())
		{
			player.heal(1.0F);
		}

        return super.onEntityDamaged(stack, target, attacker);
    }

}