package com.legacy.aether.item.weapon;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import com.legacy.aether.item.AetherItemGroup;

public class ItemDartShooter extends Item
{

	private ItemDart ammo;

	public ItemDartShooter(ItemDart ammo, Rarity rarity)
	{
		super(new Settings().rarity(rarity).itemGroup(AetherItemGroup.AETHER_WEAPONS));

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

			//ProjectileEntity projectile = this.ammo.createDart(worldIn, heldItem, playerIn);

			//projectileEntity_1.method_7437 TODO: Set velocity
		}

		return super.use(worldIn, playerIn, handIn);
    }

}