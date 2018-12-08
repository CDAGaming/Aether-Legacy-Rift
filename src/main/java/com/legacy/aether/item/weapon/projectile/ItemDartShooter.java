package com.legacy.aether.item.weapon.projectile;

import com.legacy.aether.entities.projectile.EntityDart;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemDartShooter extends Item
{

	private ItemDart dart;

	public ItemDartShooter(ItemDart dart)
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.COMBAT));

		this.dart = dart;
	}

	@Override
    public EnumRarity getRarity(ItemStack stack)
    {
    	return stack.getItem() == ItemsAether.enchanted_dart_shooter ? EnumRarity.RARE : super.getRarity(stack);
    }

	private ItemStack consumeItem(EntityPlayer player)
	{
		IInventory inv = player.inventory;

		if (!player.abilities.isCreativeMode)
		{
			return new ItemStack(this.dart);
		}

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);

			if (stack.isEmpty())
			{
				continue;
			}

			if (stack.getItem() == this.dart)
			{
				stack.shrink(1);

				if (stack.getCount() == 0)
				{
					stack = ItemStack.EMPTY;
				}

				inv.setInventorySlotContents(i, stack);

				return new ItemStack(this.dart);
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack dartStack = this.consumeItem(playerIn);

		if (!dartStack.isEmpty())
		{
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsAether.dart_shooter_shoot, SoundCategory.PLAYERS, 1.0F, 1.0F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));

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

				if (!(playerIn.abilities.isCreativeMode))
				{
					dart.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
				}
				if ((playerIn.abilities.isCreativeMode))
				{
					dart.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
				}
			}

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}