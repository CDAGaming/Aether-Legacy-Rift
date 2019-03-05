package com.legacy.aether.util;

import java.util.Iterator;

import com.legacy.aether.world.TeleporterAether;
import com.legacy.aether.world.WorldAether;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.client.network.packet.EntityPotionEffectS2CPacket;
import net.minecraft.client.network.packet.PlayerAbilitiesS2CPacket;
import net.minecraft.client.network.packet.PlayerRespawnS2CPacket;
import net.minecraft.client.network.packet.WorldEventS2CPacket;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;

public class AetherTeleportation
{

	private static final AetherTeleportation INSTANCE = new AetherTeleportation();

	public void teleportPlayer(ServerPlayerEntity player, DimensionType dimensionType_1, TeleporterAether teleporter)
	{
		DimensionType dimensionType_2 = player.dimension;
		PlayerManager playerManager_1 = player.getServer().getPlayerManager();
		ServerWorld serverWorld_1 = player.getServer().getWorld(dimensionType_2);
		player.dimension = dimensionType_1;
		ServerWorld serverWorld_2 = player.getServer().getWorld(dimensionType_1);
		player.networkHandler.sendPacket(new PlayerRespawnS2CPacket(dimensionType_1, player.world.getDifficulty(), player.world.getLevelProperties().getGeneratorType(), player.interactionManager.getGameMode()));
		playerManager_1.method_14576(player);
		serverWorld_1.method_18770(player);
		player.invalid = false;

		this.teleport(player, serverWorld_1, serverWorld_2, teleporter);

		serverWorld_2.method_18211(player);
		this.handleTravelAdvancements(player, serverWorld_1);
		player.networkHandler.teleportRequest(player.x, player.y, player.z, player.yaw, player.pitch);
		player.interactionManager.setWorld(serverWorld_2);
		player.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(player.abilities));
		playerManager_1.method_14606(player, serverWorld_2);
		playerManager_1.method_14594(player);

		Iterator<StatusEffectInstance> var38 = player.getPotionEffects().iterator();

		while (var38.hasNext())
		{
			StatusEffectInstance statusEffectInstance_1 = (StatusEffectInstance) var38.next();
			player.networkHandler.sendPacket(new EntityPotionEffectS2CPacket(player.getEntityId(), statusEffectInstance_1));
		}

		player.networkHandler.sendPacket(new WorldEventS2CPacket(1032, BlockPos.ORIGIN, 0, false));

		((PlayerTeleportationData) player).afterTeleportation();
	}

	private void teleport(ServerPlayerEntity player, ServerWorld fromWorld, ServerWorld toWorld, TeleporterAether teleporter)
	{
		double double_1 = player.x;
		double double_2 = player.z;

		fromWorld.getProfiler().push("moving");

		if (player.dimension == WorldAether.THE_AETHER)
		{
			double_1 /= 8.0D;
			double_2 /= 8.0D;
		}
		else if (player.dimension == DimensionType.OVERWORLD)
		{
			double_1 *= 8.0D;
			double_2 *= 8.0D;
		}

		player.setPositionAndAngles(double_1, player.y, double_2, player.yaw, player.pitch);

		fromWorld.getProfiler().pop();

		fromWorld.getProfiler().push("placing");

		double double_5 = Math.min(-2.9999872E7D, toWorld.getWorldBorder().getBoundWest() + 16.0D);
		double double_6 = Math.min(-2.9999872E7D, toWorld.getWorldBorder().getBoundNorth() + 16.0D);
		double double_7 = Math.min(2.9999872E7D, toWorld.getWorldBorder().getBoundEast() - 16.0D);
		double double_8 = Math.min(2.9999872E7D, toWorld.getWorldBorder().getBoundSouth() - 16.0D);

		double_1 = MathHelper.clamp(double_1, double_5, double_7);
		double_2 = MathHelper.clamp(double_2, double_6, double_8);

		player.setPositionAndAngles(double_1, player.y, double_2, player.yaw, player.pitch);

		if (!teleporter.portalSpawn)
		{
			player.networkHandler.teleportRequest(player.x, 256, player.z, player.yaw, player.pitch);
			player.networkHandler.syncWithPlayerPosition();
		}
		else if (!teleporter.method_8653(player, player.yaw))
		{
			teleporter.method_8654(player);
			teleporter.method_8653(player, player.yaw);
		}

		fromWorld.getProfiler().pop();

		player.setWorld(toWorld);
	}

	private void handleTravelAdvancements(ServerPlayerEntity player, ServerWorld world)
	{
		DimensionType dimensionType_1 = world.dimension.getType();
		DimensionType dimensionType_2 = player.world.dimension.getType();
		Criterions.CHANGED_DIMENSION.handle(player, dimensionType_1, dimensionType_2);
	}

	public static AetherTeleportation instance()
	{
		return INSTANCE;
	}
}