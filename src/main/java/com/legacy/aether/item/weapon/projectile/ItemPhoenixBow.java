package com.legacy.aether.item.weapon.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import com.legacy.aether.entities.projectile.EntityPhoenixArrow;
import com.legacy.aether.item.ItemsAether;

public class ItemPhoenixBow extends BowItem
{

	public ItemPhoenixBow()
	{
		super(new Item.Settings().defaultMaxDamage(384).group(ItemGroup.COMBAT));

        this.addPropertyOverride(new Identifier("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) ->
        {
            if (p_210310_2_ == null)
            {
                return 0.0F;
            }
            else
            {
                return p_210310_2_.getActiveItemStack().getItem() != ItemsAether.phoenix_bow ? 0.0F : (float)(p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / 20.0F;
            }
        });
	}

	@Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
    {
        if (entityLiving instanceof PlayerEntity)
        {
            PlayerEntity entityplayer = (PlayerEntity) entityLiving;
            boolean flag = entityplayer.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                int i = this.getUseDuration(stack) - timeLeft;
                float f = getArrowVelocity(i);

                if (!((double)f < 0.1D))
                {
                    boolean flag1 = flag && itemstack.getItem() == Items.ARROW;

                    if (!worldIn.isRemote)
                    {
                        ArrowEntity entityarrow = this.createArrow(worldIn, itemstack.getItem() instanceof SpectralArrowItem, itemstack, entityplayer);
                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setOnFireFor(100);
                        }

                        stack.applyDamage(1, entityplayer);

                        if (flag1 || entityplayer.abilities.creativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                        {
                            entityarrow.pickupType = ArrowEntity.PickupType.CREATIVE_PICKUP;
                        }

                        worldIn.spawnEntity(entityarrow);
                    }

                    worldIn.playSound((PlayerEntity) null, entityplayer.getPos().getX(), entityplayer.getPos().getY(), entityplayer.getPos().getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYER, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1 && !entityplayer.abilities.creativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            entityplayer.inventory.removeOne(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.ITEM_USED.get(this));
                }
            }
        }
    }

    private ArrowEntity createArrow(World worldIn, boolean isSpectral, ItemStack stack, LivingEntity shooter)
    {
        EntityPhoenixArrow entityPhoenixArrow = new EntityPhoenixArrow(worldIn, shooter, isSpectral);

        entityPhoenixArrow.setPotionEffect(stack);

        return entityPhoenixArrow;
    }

    private ItemStack findAmmo(PlayerEntity player)
    {
        if (this.isArrow(player.getStackInHand(Hand.OFF)))
        {
            return player.getStackInHand(Hand.OFF);
        }
        else if (this.isArrow(player.getStackInHand(Hand.MAIN)))
        {
            return player.getStackInHand(Hand.MAIN);
        }
        else
        {
            for (int i = 0; i < player.inventory.getInvSize(); ++i)
            {
                ItemStack itemstack = player.inventory.getInvStack(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

}