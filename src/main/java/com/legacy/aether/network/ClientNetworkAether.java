package com.legacy.aether.network;

import java.util.UUID;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.networking.CustomPayloadPacketRegistry;
import net.fabricmc.fabric.networking.PacketContext;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.EntityTracker;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.entities.block.EntityFloatingBlock;

public class ClientNetworkAether
{

	public static void initializePacketHandler()
	{
		register("poison", (contextIn, byteBuf) -> onPlayerPoisoned(contextIn, byteBuf));
		register("cure", (contextIn, byteBuf) -> onPlayerCured(contextIn, byteBuf));
		register("spawns", (contextIn, byteBuf) -> onEntitySpawned(contextIn, byteBuf));
	}

	private static void register(String name, BiConsumer<PacketContext, PacketByteBuf> consumer)
	{
		CustomPayloadPacketRegistry.CLIENT.register(Aether.locate(name), consumer);
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

	public static void onEntitySpawned(PacketContext contextIn, PacketByteBuf byteBuf)
	{
		MinecraftClient client = MinecraftClient.getInstance();
		int typeId = byteBuf.readInt();
		int entityId = byteBuf.readInt();
		UUID uuid = byteBuf.readUuid();
		double x = byteBuf.readDouble();
		double y = byteBuf.readDouble();
		double z = byteBuf.readDouble();
		int velocityX = byteBuf.readInt();
		int velocityY = byteBuf.readInt();
		int velocityZ = byteBuf.readInt();
		int pitch = byteBuf.readInt();
		int yaw = byteBuf.readInt();
		Entity entity = null;

		if (typeId == 0)
		{
			entity = new EntityFloatingBlock(client.world, x, y, z, Block.getStateFromRawId(byteBuf.readInt()));
		}

		if (entity != null)
		{
			EntityTracker.method_14070(entity, x, y, z);

			entity.pitch = (float)(pitch * 360) / 256.0F;
			entity.yaw = (float)(yaw * 360) / 256.0F;

			entity.setEntityId(entityId);
			entity.setUuid(uuid);

			client.world.method_2942(entityId, entity);
		}
	}

}
