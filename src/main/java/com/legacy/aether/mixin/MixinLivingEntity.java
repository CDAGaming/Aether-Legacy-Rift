package com.legacy.aether.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.item.accessory.ItemAccessory;

@Mixin(LivingEntity.class)
public class MixinLivingEntity
{

	@Shadow protected boolean field_6282; //isJumping

	/*@ModifyVariable(method = "damage", at = @At(value = "HEAD"), index = 1)
	public float getGloveDamage(DamageSource source, float amount)
	{
		if (source.getAttacker() instanceof PlayerEntity)
		{
			IPlayerAether playerAether = AetherAPI.get((PlayerEntity) source.getAttacker());

			if (playerAether.getPlayer().getMainHandStack().isEmpty())
			{
				for (int index = 0; index < playerAether.getAccessoryInventory().getInvSize(); index++)
				{
					if (!playerAether.getAccessoryInventory().getInvStack(index).isEmpty())
					{
						amount *= ((ItemAccessory)playerAether.getAccessoryInventory().getInventory().get(index).getItem()).getDamageMultiplier();
					}
				}

				playerAether.getAccessoryInventory().damageAccessory(1, AccessoryType.GLOVES);
			}
		}

		return amount;
		//args.set(1, amount);
	}*/

}