package com.legacy.aether.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.api.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity implements IEntityPlayerAether
{

	private PlayerAether playerAether;

	protected MixinPlayerEntity(EntityType<?> entityType_1, World world_1)
	{
		super(entityType_1, world_1);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void playerInit(CallbackInfo ci)
	{
		this.playerAether = new PlayerAether((PlayerEntity) (Object) this);
	}

	public PlayerAether getPlayerAether()
	{
		return this.playerAether;
	}

	public Entity getInstance()
	{
		return (PlayerEntity) (Object) this;
	}

	@Inject(method = "update", at = @At("RETURN"))
	public void playerUpdate(CallbackInfo ci)
	{
		this.playerAether.setJumping(this.field_6282);

		this.playerAether.tick();
	}

	@Inject(method = "handleFallDamage", at = @At("HEAD"))
	public void handleAetherFallDamage(float fallDamage, float multiplier, CallbackInfo ci)
	{
		if (this.playerAether.disableFallDamage())
		{
			fallDamage = 0.0F;
			multiplier = 0.0F;
		}
	}

	@Inject(method = "writeCustomDataToTag", at = @At("RETURN"))
	public void writePlayerNBT(CompoundTag compound, CallbackInfo ci)
	{
		CompoundTag aetherTag = new CompoundTag();

		this.playerAether.writeToNBT(aetherTag);

		compound.put("aetherData", aetherTag);
	}

	@Inject(method = "readCustomDataFromTag", at = @At("RETURN"))
	public void readPlayerNBT(CompoundTag compound, CallbackInfo ci)
	{
		CompoundTag aetherTag = compound.getCompound("aetherData");

		this.playerAether.readFromNBT(aetherTag);
	}

	public void handleFallDamage(float fallDamage, float multiplier)
	{
		if (!((PlayerEntity) (Object) this).abilities.allowFlying)
		{
			if (fallDamage >= 2.0F)
			{
				((PlayerEntity) (Object) this).method_7339(Stats.FALL_ONE_CM, (int) Math.round((double) fallDamage * 100.0D));
			}

			super.handleFallDamage(fallDamage, this.playerAether.disableFallDamage() ? 0.0F : multiplier);
		}
	}

}