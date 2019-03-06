package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.LivingEntity;

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