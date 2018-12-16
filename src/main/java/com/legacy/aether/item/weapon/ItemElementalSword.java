package com.legacy.aether.item.weapon;

import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemElementalSword extends ItemAetherSword
{

	public ItemElementalSword() 
	{
		super(AetherTier.Legendary, ItemsAether.AETHER_LOOT, 4, -2.4F);
	}

	@Override
    public boolean onEntityDamaged(ItemStack stack, LivingEntity entityliving, LivingEntity entityliving1)
    {
		if (this == ItemsAether.flaming_sword)
		{
			entityliving.setOnFireFor(30);
		}
		else if (this == ItemsAether.lightning_sword)
		{
			LightningEntity lightning = new LightningEntity(entityliving1.world, entityliving.getPos().getX(), entityliving.getPos().getY(), entityliving.getPos().getZ(), false);

			entityliving1.world.addWeatherEffect(lightning);
		}
		else if (this == ItemsAether.holy_sword && entityliving.isUndead())
		{
			entityliving.damage(DamageSource.mob(entityliving1), 20);

			stack.applyDamage(10, entityliving1);
		}

		return super.onEntityDamaged(stack, entityliving, entityliving1);
    }

}