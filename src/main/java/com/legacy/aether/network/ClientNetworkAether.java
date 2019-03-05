package com.legacy.aether.network;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;

public class ClientNetworkAether
{

	public static void initializePacketHandler()
	{
		register("poison", (contextIn, byteBuf) -> onPlayerPoisoned(contextIn, byteBuf));
		register("cure", (contextIn, byteBuf) -> onPlayerCured(contextIn, byteBuf));
	}

	private static void register(String name, PacketConsumer consumer)
	{
		ClientSidePacketRegistry.INSTANCE.register(Aether.locate(name), consumer);
	}

	public static void onPlayerPoisoned(PacketContext contextIn, PacketByteBuf byteBuf)
	{
		IPlayerAether playerAether = AetherAPI.get(contextIn.getPlayer());
		int poisonTicks = byteBuf.readInt();

		playerAether.inflictPoison(poisonTicks);
	}

	public static void onPlayerCured(PacketContext contextIn, PacketByteBuf byteBuf)
	{
		IPlayerAether playerAether = AetherAPI.get(contextIn.getPlayer());
		int poisonTicks = byteBuf.readInt();

		playerAether.inflictCure(poisonTicks);
	}

}