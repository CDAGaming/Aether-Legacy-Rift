package com.legacy.aether.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.entities.util.EntityMountable;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;
import com.mojang.authlib.GameProfile;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends EntityLivingBase implements IEntityPlayerAether
{

	protected MixinEntityPlayer(EntityType<?> type, World p_i48577_2_)
	{
		super(type, p_i48577_2_);
	}

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

    @Inject(method = "writeAdditional", at = @At("RETURN"))
	public void writeToPNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		NBTTagCompound aetherTag = new NBTTagCompound();

		this.playerAether.writeToNBT(aetherTag);

		compound.put("aetherData", aetherTag);
	}

    @Inject(method = "readAdditional", at = @At("RETURN"))
	public void readFromPNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		if (compound.contains("aetherData"))
		{
			NBTTagCompound aetherTag = compound.getCompound("aetherData");

			this.playerAether.readFromNBT(aetherTag);
		}
	}

    @Inject(method = "damageArmor", at = @At("RETURN"))
	public void damageAccessories(float damage, CallbackInfo ci)
	{
		this.playerAether.damageAccessories(damage);
	}

    @Overwrite
    public void updateRidden()
    {
    	EntityPlayer player = (EntityPlayer) (Object) this;

        if (!player.world.isRemote && !(player.getRidingEntity() instanceof EntityMountable) && player.isSneaking() && player.isPassenger())
        {
        	player.stopRiding();
            player.setSneaking(false);
        }
        else
        {
            double d0 = player.posX;
            double d1 = player.posY;
            double d2 = player.posZ;
            float f = player.rotationYaw;
            float f1 = player.rotationPitch;
            super.updateRidden();
            player.prevCameraYaw = player.cameraYaw;
            player.cameraYaw = 0.0F;
            this.addMountedMovementStat(player.posX - d0, player.posY - d1, player.posZ - d2);

            if (player.getRidingEntity() instanceof EntityPig)
            {
            	player.rotationPitch = f1;
            	player.rotationYaw = f;
            	player.renderYawOffset = ((EntityPig)player.getRidingEntity()).renderYawOffset;
            }
        }
    }

    @Shadow
    private void addMountedMovementStat(double x, double y, double z)
    {
    	
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

}