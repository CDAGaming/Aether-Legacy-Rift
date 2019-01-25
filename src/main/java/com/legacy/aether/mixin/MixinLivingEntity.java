package com.legacy.aether.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.item.accessory.ItemAccessory;

@Mixin(LivingEntity.class)
public class MixinLivingEntity
{

	@Shadow protected boolean field_6282; //isJumping

	@Inject(method = "damage", at = @At("HEAD"))
	public void addGloveDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> ci)
	{
		//TODO: Fix!!
		if (source.getAttacker() instanceof PlayerEntity)
		{
			IPlayerAether playerAether = AetherAPI.get((PlayerEntity) source.getAttacker());

			if (playerAether.getPlayer().getMainHandStack().isEmpty())
			{
				for (int index = 0; index < playerAether.getAccessoryInventory().getInvSize(); index++)
				{
					if (playerAether.getAccessoryInventory().getInvStack(index).isEmpty())
					{
						continue;
					}

					amount *= ((ItemAccessory)playerAether.getAccessoryInventory().getInventory().get(index).getItem()).getDamageMultiplier();
				}

				playerAether.getAccessoryInventory().damageAccessory(1, AccessoryType.GLOVES);
			}
		}
	}

}