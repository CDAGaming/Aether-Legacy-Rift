package com.legacy.aether.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public interface AetherSPacketCustomPayload
{

    public Identifier getChannelName();

    public PacketByteBuf getData();

}