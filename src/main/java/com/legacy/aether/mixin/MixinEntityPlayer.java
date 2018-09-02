package com.legacy.aether.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.player.IPlayerAether;
import com.legacy.aether.player.PlayerAether;
import com.mojang.authlib.GameProfile;

@Mixin(value = EntityPlayer.class)
public abstract class MixinEntityPlayer implements IPlayerAether
{

	private PlayerAether playerAether;

	public PlayerAether getPlayerAether()
	{
		return this.playerAether;
	}

	public Entity getInstance()
	{
		return (EntityPlayer) (Object) this;
	}

    @Inject(method = "<init>", at = @At("RETURN"))
	public void playerAetherInit(World world, GameProfile profile, CallbackInfo ci)
	{
		this.playerAether = new PlayerAether((EntityPlayer) (Object) this);
	}

    @Inject(method = "tick", at = @At("RETURN"))
	public void playerUpdate(CallbackInfo ci)
	{
		this.playerAether.tick();
	}

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
	public void writeToPNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		NBTTagCompound aetherTag = new NBTTagCompound();

		this.playerAether.writeToNBT(aetherTag);

		compound.setTag("aetherData", aetherTag);
	}

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
	public void readFromPNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		if (compound.hasKey("aetherData"))
		{
			NBTTagCompound aetherTag = compound.getCompoundTag("aetherData");

			this.playerAether.readFromNBT(aetherTag);
		}
	}

    @Inject(method = "damageArmor", at = @At("RETURN"))
	public void damageAccessories(float damage, CallbackInfo ci)
	{
		this.playerAether.damageAccessories(damage);
	}

    public void setJumping(boolean isJumping)
    {
    	this.playerAether.setJumping(isJumping);
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

}