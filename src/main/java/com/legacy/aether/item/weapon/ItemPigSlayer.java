package com.legacy.aether.item.weapon;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;
import net.minecraft.particle.ParticleTypes;

public class ItemPigSlayer extends ItemAetherSword
{

	public ItemPigSlayer()
	{
		super(AetherTier.Legendary, ItemsAether.AETHER_LOOT, 3, -2.4F);
	}

	@Override
	public boolean onEntityDamaged(ItemStack itemstack, LivingEntity entityliving, LivingEntity entityliving1)
	{
		if (entityliving == null || entityliving1 == null)
		{
			return false;
		}

		String s = entityliving.getType().getTranslationKey().toLowerCase();

		if (s != null && (s.toLowerCase().contains("pig") || s.toLowerCase().contains("phyg")))
		{
			if (entityliving.getHealth() >= 0.0F)
			{
				entityliving.hurtTime = 0;
				entityliving.setHealth(1.0F);
				entityliving.damage(DamageSource.mob(entityliving1), 9999.0F);
			}

			for (int j = 0; j < 20; ++j)
			{
				double d = entityliving.world.random.nextGaussian() * 0.02D;
				double d1 = entityliving.world.random.nextGaussian() * 0.02D;
				double d2 = entityliving.world.random.nextGaussian() * 0.02D;
				double d3 = 5.0D;

				// TODO: Either this of 8496, idk /shrug - 1.14
				entityliving.world.method_8406(ParticleTypes.FLAME, (entityliving.getPos().getX() + (double)(entityliving.world.random.nextFloat() * entityliving.width * 2.0F)) - (double)entityliving.width - d * d3, (entityliving.getPos().getY() + (double)(entityliving.world.random.nextFloat() * entityliving.height)) - d1 * d3, (entityliving.getPos().getZ() + (double)(entityliving.world.random.nextFloat() * entityliving.width * 2.0F)) - (double)entityliving.width - d2 * d3, d, d1, d2);
			}

			entityliving.invalidate();
		}

		return super.onEntityDamaged(itemstack, entityliving, entityliving1);
	}

}