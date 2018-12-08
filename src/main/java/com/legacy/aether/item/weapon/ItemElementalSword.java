package com.legacy.aether.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.util.AetherTier;

public class ItemElementalSword extends ItemAetherSword
{

	public ItemElementalSword() 
	{
		super(AetherTier.Elemental, ItemsAether.AETHER_LOOT, 4, -2.4F);
	}

	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityliving, EntityLivingBase entityliving1)
    {
		if (this == ItemsAether.flaming_sword)
		{
			entityliving.setFire(30);
		}
		else if (this == ItemsAether.lightning_sword)
		{
			EntityLightningBolt lightning = new EntityLightningBolt(entityliving1.world, entityliving.posX, entityliving.posY, entityliving.posZ, false);

			entityliving1.world.addWeatherEffect(lightning);
		}
		else if (this == ItemsAether.holy_sword && entityliving.isEntityUndead())
		{
			entityliving.attackEntityFrom(DamageSource.causeMobDamage(entityliving1), 20);

			stack.damageItem(10, entityliving1);
		}

		return super.hitEntity(stack, entityliving, entityliving1);
    }

}