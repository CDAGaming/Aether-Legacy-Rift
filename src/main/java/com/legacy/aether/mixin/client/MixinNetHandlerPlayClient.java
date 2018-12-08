package com.legacy.aether.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.passive.EntityMiniCloud;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.util.AetherSPacketCustomPayload;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient
{

	@Shadow private WorldClient world;

    @Inject(method = "handleCustomPayload", at = @At("HEAD"), cancellable = true)
	public void processAetherPayload(SPacketCustomPayload packetIn, CallbackInfo ci)
	{
    	AetherSPacketCustomPayload hook = (AetherSPacketCustomPayload) packetIn;
		ResourceLocation location = hook.getChannelName();

		if (location.getNamespace().equals("aether_legacy"))
		{
			EntityPlayer player = Minecraft.getInstance().player;
			String packetName = location.getPath();

			if ("apply_poison".equals(packetName))
			{
				int poisonAmount = hook.getData().readInt();

				((IEntityPlayerAether)player).getPlayerAether().applyPoison(poisonAmount);
			}

			ci.cancel();
		}
	}

    @Inject(method = "handleSpawnObject", at = @At("RETURN"))
    public void handleAetherSpawnObject(SPacketSpawnObject packetIn, CallbackInfo ci)
    {
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        Entity aetherEntity = null;

    	if (packetIn.getType() == 583)
    	{
    		aetherEntity = new EntityFloatingBlock(this.world, d0, d1, d2, Block.getStateById(packetIn.getData()));
    		packetIn.setData(0);
    	}
    	else if (packetIn.getType() == 584)
    	{
    		aetherEntity = new EntityGoldenDart(this.world, d0, d1, d2);
    	}
    	else if (packetIn.getType() == 585)
    	{
    		aetherEntity = new EntityEnchantedDart(this.world, d0, d1, d2);
    	}
    	else if (packetIn.getType() == 586)
    	{
    		aetherEntity = new EntityPoisonNeedle(this.world, d0, d1, d2);
    	}
    	else if (packetIn.getType() == 587)
    	{
    		aetherEntity = new EntityPoisonDart(this.world, d0, d1, d2);
    	}
    	else if (packetIn.getType() == 588)
    	{
    		aetherEntity = new EntityMiniCloud(this.world, (EntityPlayer) this.world.getEntityByID(packetIn.getData()), 0);
    		packetIn.setData(0);
    	}
    	else if (packetIn.getType() == 589)
    	{
    		aetherEntity = new EntityMiniCloud(this.world, (EntityPlayer) this.world.getEntityByID(packetIn.getData()), 1);
    		packetIn.setData(0);
    	}

        if (aetherEntity != null)
        {
            EntityTracker.updateServerPosition(aetherEntity, d0, d1, d2);

            aetherEntity.rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
            aetherEntity.rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;

            Entity[] aentity = aetherEntity.getParts();

            if (aentity != null)
            {
                int i = packetIn.getEntityID() - aetherEntity.getEntityId();

                for (Entity entity2 : aentity)
                {
                    entity2.setEntityId(entity2.getEntityId() + i);
                }
            }

            aetherEntity.setEntityId(packetIn.getEntityID());
            aetherEntity.setUniqueId(packetIn.getUniqueId());
            this.world.addEntityToWorld(packetIn.getEntityID(), aetherEntity);

            if (packetIn.getData() > 0)
            {
            	if (packetIn.getType() > 583 && packetIn.getType() < 588)
            	{
                    Entity entity3 = this.world.getEntityByID(packetIn.getData() - 1);

                    if (entity3 instanceof EntityLivingBase && aetherEntity instanceof EntityArrow)
                    {
                        ((EntityArrow)aetherEntity).shootingEntity = entity3.getUniqueID();
                    }
            	}

                aetherEntity.setVelocity((double)packetIn.getSpeedX() / 8000.0D, (double)packetIn.getSpeedY() / 8000.0D, (double)packetIn.getSpeedZ() / 8000.0D);
            }
        }
    }

}