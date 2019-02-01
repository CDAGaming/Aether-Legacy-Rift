package com.legacy.aether.item.weapon;

import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemElementalSword extends ItemAetherSword
{

	public ItemElementalSword() 
	{
		super(AetherTier.Legendary, ItemsAether.AETHER_LOOT, 4, -2.4F);
	}

	@Override
    public boolean onEntityDamaged(ItemStack stack, LivingEntity victim, LivingEntity attacker)
    {
		if (this == ItemsAether.flaming_sword)
		{
			victim.setOnFireFor(30);
		}
		else if (this == ItemsAether.lightning_sword)
		{
			LightningEntity lightning = new LightningEntity(attacker.world, victim.getPos().getX(), victim.getPos().getY(), victim.getPos().getZ(), false);

			if (attacker instanceof ServerPlayerEntity)
			{
				lightning.method_6961((ServerPlayerEntity) attacker);
			}

			//entityliving1.world.addWeatherEffect(lightning);
		}
		else if (this == ItemsAether.holy_sword && victim.isUndead())
		{
			victim.damage(DamageSource.mob(attacker), 20);

			stack.applyDamage(10, attacker);
		}

		return super.onEntityDamaged(stack, victim, attacker);
    }

}