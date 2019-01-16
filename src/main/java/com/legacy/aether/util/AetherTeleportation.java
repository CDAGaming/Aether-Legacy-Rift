package com.legacy.aether.util;

import java.util.Iterator;

import net.minecraft.client.network.packet.EntityPotionEffectClientPacket;
import net.minecraft.client.network.packet.PlayerAbilitiesClientPacket;
import net.minecraft.client.network.packet.PlayerRespawnClientPacket;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.dimension.DimensionType;

import com.legacy.aether.world.WorldAether;

public class AetherTeleportation
{

	private static final AetherTeleportation INSTANCE = new AetherTeleportation();

	public void teleportPlayer(ServerPlayerEntity player, DimensionType dimensionType_1, PortalForcer teleporter)
	{
		PlayerManager playerManager = player.getServer().getPlayerManager();
		ServerWorld serverWorld_1 = player.getServer().getWorld(player.dimension);
		player.dimension = dimensionType_1;
		ServerWorld serverWorld_2 = player.getServer().getWorld(player.dimension);
		player.networkHandler.sendPacket(new PlayerRespawnClientPacket(player.dimension, player.world.getDifficulty(), player.world.getLevelProperties().getGeneratorType(), player.interactionManager.getGameMode()));
		playerManager.method_14576(player);
		serverWorld_1.method_8507(player);
		player.invalid = false;
		this.teleport(player, serverWorld_1, serverWorld_2, teleporter);
		playerManager.method_14612(player, serverWorld_1);
		player.networkHandler.method_14363(player.x, player.y, player.z, player.yaw, player.pitch);
		player.interactionManager.setWorld(serverWorld_2);
		player.networkHandler.sendPacket(new PlayerAbilitiesClientPacket(player.abilities));
		playerManager.method_14606(player, serverWorld_2);
		playerManager.method_14594(player);
		Iterator<StatusEffectInstance> var6 = player.getPotionEffects().iterator();

		while (var6.hasNext())
		{
			StatusEffectInstance statusEffectInstance_1 = var6.next();
			player.networkHandler.sendPacket(new EntityPotionEffectClientPacket(player.getEntityId(), statusEffectInstance_1));
		}

	}

	private void teleport(ServerPlayerEntity player, ServerWorld fromWorld, ServerWorld toWorld, PortalForcer teleporter)
	{
		double double_1 = player.x;
		double double_2 = player.z;
		float float_1 = player.yaw;

		fromWorld.getProfiler().push("moving");

		if (player.dimension == WorldAether.THE_AETHER)
		{
			double_1 = MathHelper.clamp(double_1 / 8.0D, toWorld.getWorldBorder().getBoundWest() + 16.0D, toWorld.getWorldBorder().getBoundEast() - 16.0D);
			double_2 = MathHelper.clamp(double_2 / 8.0D, toWorld.getWorldBorder().getBoundNorth() + 16.0D, toWorld.getWorldBorder().getBoundSouth() - 16.0D);

			player.setPositionAndAngles(double_1, player.y, double_2, player.yaw, player.pitch);

			if (player.isValid())
			{
				fromWorld.method_8553(player, false);
			}
		}
		else if (player.dimension == DimensionType.OVERWORLD)
		{
			double_1 = MathHelper.clamp(double_1 * 8.0D, toWorld.getWorldBorder().getBoundWest() + 16.0D, toWorld.getWorldBorder().getBoundEast() - 16.0D);
			double_2 = MathHelper.clamp(double_2 * 8.0D, toWorld.getWorldBorder().getBoundNorth() + 16.0D, toWorld.getWorldBorder().getBoundSouth() - 16.0D);

			player.setPositionAndAngles(double_1, player.y, double_2, player.yaw, player.pitch);

			if (player.isValid())
			{
				fromWorld.method_8553(player, false);
			}
		}

		fromWorld.getProfiler().pop();

		fromWorld.getProfiler().push("placing");

		double_1 = (double) MathHelper.clamp((int) double_1, -29999872, 29999872);
		double_2 = (double) MathHelper.clamp((int) double_2, -29999872, 29999872);

		if (player.isValid())
		{
			player.setPositionAndAngles(double_1, player.y, double_2, player.yaw, player.pitch);
			teleporter.method_8655(player, float_1);
			toWorld.spawnEntity(player);
			toWorld.method_8553(player, false);
		}

		fromWorld.getProfiler().pop();

		player.setWorld(toWorld);
	}

	public static AetherTeleportation instance()
	{
		return INSTANCE;
	}

}