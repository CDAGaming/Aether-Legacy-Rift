package com.legacy.aether.network;

import java.util.UUID;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.networking.CustomPayloadPacketRegistry;
import net.fabricmc.fabric.networking.PacketContext;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.network.EntityTracker;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;

public class ClientNetworkAether
{

	public static void initializePacketHandler()
	{
		register("poison", (contextIn, byteBuf) -> onPlayerPoisoned(contextIn, byteBuf));
		register("cure", (contextIn, byteBuf) -> onPlayerCured(contextIn, byteBuf));
		//register("spawns", (contextIn, byteBuf) -> onEntitySpawned(contextIn, byteBuf));
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
		int entityId = byteBuf.readVarInt();
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
		else if (typeId == 1)
		{
			entity = new EntityGoldenDart(x, y, z, client.world);
		}
		else if (typeId == 2)
		{
			entity = new EntityEnchantedDart(x, y, z, client.world);
		}
		else if (typeId == 3)
		{
			entity = new EntityPoisonDart(x, y, z, client.world);
		}
		else if (typeId == 4)
		{
			entity = new EntityPoisonNeedle(x, y, z, client.world);
		}

		if (entity != null)
		{
			EntityTracker.method_14070(entity, x, y, z);

			entity.pitch = (float)(pitch * 360) / 256.0F;
			entity.yaw = (float)(yaw * 360) / 256.0F;

			entity.setEntityId(entityId);
			entity.setUuid(uuid);

			client.world.method_2942(entityId, entity);

			if (entity instanceof ProjectileEntity)
			{
				int ownerId = byteBuf.readInt();

				if (ownerId != 0)
				{
					Entity owner = client.world.getEntityById(ownerId);

					if (owner instanceof LivingEntity)
					{
						ProjectileEntity projectile = (ProjectileEntity) entity;

						projectile.setOwner(owner);

						if (owner instanceof PlayerEntity)
						{
							projectile.pickupType = ProjectileEntity.PickupType.PICKUP;

							if (((PlayerEntity) owner).abilities.creativeMode)
							{
								projectile.pickupType = ProjectileEntity.PickupType.CREATIVE_PICKUP;
							}
						}
					}
				}

				entity.setVelocityClient((double) velocityX / 8000.0D, (double) velocityY / 8000.0D, (double) velocityZ / 8000.0D);
			}
		}
	}

}
