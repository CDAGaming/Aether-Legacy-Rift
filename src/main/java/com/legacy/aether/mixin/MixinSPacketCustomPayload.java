package com.legacy.aether.mixin;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.legacy.aether.util.AetherSPacketCustomPayload;

@Mixin(SPacketCustomPayload.class)
public class MixinSPacketCustomPayload implements AetherSPacketCustomPayload 
{

    @Shadow private ResourceLocation channel;

    @Shadow private PacketBuffer data;

    @Override
    public ResourceLocation getChannelName()
    {
        return channel;
    }

    @Override
    public PacketBuffer getData() 
    {
        return data;
    }

}