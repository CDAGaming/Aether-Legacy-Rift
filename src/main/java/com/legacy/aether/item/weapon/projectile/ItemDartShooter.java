package com.legacy.aether.item.weapon.projectile;

import com.legacy.aether.entities.projectile.EntityDart;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemDartShooter extends Item
{

	private ItemDart dart;

	public ItemDartShooter(ItemDart dart)
	{
		super(new Settings().stackSize(1).itemGroup(ItemGroup.COMBAT));

		this.dart = dart;
	}

	@Override
    public Rarity getRarity(ItemStack stack)
    {
    	return stack.getItem() == ItemsAether.enchanted_dart_shooter ? Rarity.RARE : super.getRarity(stack);
    }

	private ItemStack consumeItem(PlayerEntity player)
	{
		Inventory inv = player.inventory;

		if (!player.abilities.creativeMode)
		{
			return new ItemStack(this.dart);
		}

		for (int i = 0; i < inv.getInvSize(); i++)
		{
			ItemStack stack = inv.getInvStack(i);

			if (stack.isEmpty())
			{
				continue;
			}

			if (stack.getItem() == this.dart)
			{
				stack.shrink(1);

				if (stack.getAmount() == 0)
				{
					stack = ItemStack.EMPTY;
				}

				inv.setInvStack(i, stack);

				return new ItemStack(this.dart);
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack dartStack = this.consumeItem(playerIn);

		if (!dartStack.isEmpty())
		{
			worldIn.playSound((PlayerEntity) null, playerIn.getPos().getX(), playerIn.getPos().getY(), playerIn.getPos().getZ(), SoundsAether.dart_shooter_shoot, SoundCategory.PLAYER, 1.0F, 1.0F / (worldIn.random.nextFloat() * 0.4F + 0.8F));

			EntityDart dart = null;

			if (dartStack.getItem() == ItemsAether.poison_dart)
			{
				dart = new EntityPoisonDart(playerIn, worldIn);
			}
			else if (dartStack.getItem() == ItemsAether.enchanted_dart)
			{
				dart = new EntityEnchantedDart(playerIn, worldIn);
			}
			else if (dartStack.getItem() == ItemsAether.golden_dart)
			{
				dart = new EntityGoldenDart(playerIn, worldIn);
			}

			if (!worldIn.isRemote)
			{
				dart.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);

				worldIn.spawnEntity(dart);

				if (!(playerIn.abilities.creativeMode))
				{
					dart.pickupStatus = ArrowEntity.PickupType.PICKUP;
				}
				if ((playerIn.abilities.creativeMode))
				{
					dart.pickupStatus = ArrowEntity.PickupType.CREATIVE_PICKUP;
				}
			}

			return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerIn.getStackInHand(handIn));
		}

		return super.use(worldIn, playerIn, handIn);
	}

}