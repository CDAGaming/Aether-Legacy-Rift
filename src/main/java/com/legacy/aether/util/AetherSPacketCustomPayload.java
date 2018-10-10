package com.legacy.aether.util;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public interface AetherSPacketCustomPayload
{

    public ResourceLocation getChannelName();

    public PacketBuffer getData();

}