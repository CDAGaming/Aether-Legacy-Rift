package com.legacy.aether.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemPigSlayer extends ItemAetherSword
{

	public ItemPigSlayer()
	{
		super(AetherTier.Legendary, ItemsAether.AETHER_LOOT, 3, -2.4F);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
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
				entityliving.attackEntityFrom(DamageSource.causeMobDamage(entityliving1), 9999.0F);
			}

			for (int j = 0; j < 20; ++j)
			{
				double d = entityliving.world.rand.nextGaussian() * 0.02D;
				double d1 = entityliving.world.rand.nextGaussian() * 0.02D;
				double d2 = entityliving.world.rand.nextGaussian() * 0.02D;
				double d3 = 5.0D;

				entityliving.world.addParticle(Particles.FLAME, (entityliving.posX + (double)(entityliving.world.rand.nextFloat() * entityliving.width * 2.0F)) - (double)entityliving.width - d * d3, (entityliving.posY + (double)(entityliving.world.rand.nextFloat() * entityliving.height)) - d1 * d3, (entityliving.posZ + (double)(entityliving.world.rand.nextFloat() * entityliving.width * 2.0F)) - (double)entityliving.width - d2 * d3, d, d1, d2);
			}

			entityliving.remove();
		}

		return super.hitEntity(itemstack, entityliving, entityliving1);
	}

}