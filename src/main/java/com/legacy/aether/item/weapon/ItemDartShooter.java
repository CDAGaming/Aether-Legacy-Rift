package com.legacy.aether.item.weapon;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.sounds.SoundsAether;

public class ItemDartShooter extends Item
{

	private ItemDart ammo;

	public ItemDartShooter(ItemDart ammo, Rarity rarity)
	{
		super(new Settings().stackSize(1).rarity(rarity).itemGroup(AetherItemGroup.AETHER_WEAPONS));

		this.ammo = ammo;
	}

	protected ItemStack findDartStack(PlayerEntity playerIn)
	{
		if (playerIn.getStackInHand(Hand.OFF).getItem() == this.ammo)
		{
			return playerIn.getStackInHand(Hand.OFF);
		} 
		else if (playerIn.getStackInHand(Hand.MAIN).getItem() == this.ammo)
		{
			return playerIn.getStackInHand(Hand.MAIN);
		} 
		else
		{
			for (int index = 0; index < playerIn.inventory.getInvSize(); ++index)
			{
				ItemStack stack = playerIn.inventory.getInvStack(index);

				if (stack.getItem() == this.ammo)
				{
					return stack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getStackInHand(handIn);
		boolean bypassDartCheck = playerIn.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, heldItem) > 0;

		ItemStack stack = this.findDartStack(playerIn);

		if (!stack.isEmpty() || bypassDartCheck)
		{
			if (stack.isEmpty())
			{
				stack = new ItemStack(this.ammo);
			}

			ProjectileEntity projectile = this.ammo.createDart(worldIn, heldItem, playerIn);

			if (!worldIn.isClient)
			{
				projectile.method_7437(playerIn, playerIn.pitch, playerIn.yaw, 0.0F, 1.0F, 1.0F);

				worldIn.spawnEntity(projectile);

				if (!(playerIn.abilities.creativeMode))
				{
					projectile.pickupType = ProjectileEntity.PickupType.PICKUP;
				}
				if ((playerIn.abilities.creativeMode))
				{
					projectile.pickupType = ProjectileEntity.PickupType.CREATIVE_PICKUP;
				}
			}

			worldIn.playSound(playerIn, playerIn.getPos(), SoundsAether.dart_shooter_shoot, SoundCategory.PLAYER, 1.0F, 1.0F / (playerIn.getRand().nextFloat() * 0.4F + 0.8F));
		}

		return super.use(worldIn, playerIn, handIn);
    }

}