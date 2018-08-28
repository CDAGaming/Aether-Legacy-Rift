package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSpawnObject;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.entities.block.EntityFloatingBlock;

@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry 
{

    @Shadow @Final private Entity trackedEntity;

    @Inject(method = "createSpawnPacket", at = @At("INVOKE"), cancellable = true)
	private void createAetherSpawnPacket(CallbackInfoReturnable<Packet<?>> info)
	{
		if (this.trackedEntity instanceof EntityFloatingBlock)
		{
			EntityFloatingBlock floatingBlock = (EntityFloatingBlock) this.trackedEntity;
			info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 583, Block.getStateId(floatingBlock.getBlockstate())));
		}
	}

}