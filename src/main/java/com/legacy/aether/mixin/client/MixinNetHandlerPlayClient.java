package com.legacy.aether.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.entities.block.EntityFloatingBlock;

import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.network.play.server.SPacketSpawnObject;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient
{

	@Shadow private WorldClient world;

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
        }
    }

}