package com.legacy.aether.item.weapon;

import com.legacy.aether.item.AetherItemGroup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class ItemDart extends Item
{

	public ItemDart(Rarity rarity)
	{
		super(new Settings().rarity(rarity).itemGroup(AetherItemGroup.AETHER_WEAPONS));
	}

	public ProjectileEntity createDart(World world, ItemStack stack, LivingEntity entity)
	{
		return null;
	}
}