package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.legacy.aether.api.AetherAPI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(Entity.class)
public class MixinEntity
{

	@ModifyArg(method = "method_5623", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V"), index = 3)
	private float aetherlegacy_setFallDistance(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (entity instanceof PlayerEntity)
		{
			return AetherAPI.get((PlayerEntity) entity).disableFallDamage() ? 0.0F : fallDistance;
		}

		return fallDistance;
	}
}