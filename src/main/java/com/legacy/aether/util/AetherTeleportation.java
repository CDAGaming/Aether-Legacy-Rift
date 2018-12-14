package com.legacy.aether.util;

import net.minecraft.client.network.packet.PlayerRespawnClientPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;

public class AetherTeleportation 
{

	public static void travelDimension(ServerPlayerEntity player, DimensionType dimensionIn, Teleporter teleporterIn)
	{
		MinecraftServer mcServer = player.getServer();

		if (mcServer == null)
		{
			return;
		}

        int i = player.dimension.getRawId();
        ServerWorld worldserver = mcServer.getWorld(player.dimension);
        player.dimension = dimensionIn;
        ServerWorld worldserver1 = mcServer.getWorld(player.dimension);
        player.networkHandler.sendPacket(new PlayerRespawnClientPacket(player.dimension, player.world.getDifficulty(), player.world.getWorldInfo().getGenerator(), player.interactionManager.getGameType()));
        mcServer.getPlayerList().updatePermissionLevel(player);
        worldserver.removeEntityDangerously(player);
        player.removed = false;
        transferEntityToWorld(player, i, worldserver, worldserver1, teleporterIn);
        mcServer.getPlayerList().preparePlayer(player, worldserver);
        player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.interactionManager.setWorld(worldserver1);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.abilities));
        mcServer.getPlayerList().preparePlayer(player, worldserver1);
        mcServer.getPlayerList().sendInventory(player);

        for (PotionEffect potioneffect : player.getActivePotionEffects())
        {
            player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
        }
	}

    public static void transferEntityToWorld(Entity entityIn, int lastDimension, ServerWorld oldWorldIn, ServerWorld toWorldIn, Teleporter teleporterIn)
    {
        double d0 = entityIn.getPos().getX();
        double d1 = entityIn.getPos().getZ();
        float f = entityIn.getRotationClient().y;
        oldWorldIn.getProfiler().begin("moving");

        if (entityIn.dimension == DimensionType.THE_NETHER)
        {
            d0 = MathHelper.clamp(d0 / 8.0D, toWorldIn.getWorldBorder().minX() + 16.0D, toWorldIn.getWorldBorder().maxX() - 16.0D);
            d1 = MathHelper.clamp(d1 / 8.0D, toWorldIn.getWorldBorder().minZ() + 16.0D, toWorldIn.getWorldBorder().maxZ() - 16.0D);
            entityIn.setLocationAndAngles(d0, entityIn.getPos().getY(), d1, entityIn.rotationYaw, entityIn.rotationPitch);

            if (entityIn.isAlive())
            {
                oldWorldIn.tickEntity(entityIn, false);
            }
        }
        else if (entityIn.dimension == DimensionType.OVERWORLD)
        {
            d0 = MathHelper.clamp(d0 * 8.0D, toWorldIn.getWorldBorder().minX() + 16.0D, toWorldIn.getWorldBorder().maxX() - 16.0D);
            d1 = MathHelper.clamp(d1 * 8.0D, toWorldIn.getWorldBorder().minZ() + 16.0D, toWorldIn.getWorldBorder().maxZ() - 16.0D);
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);

            if (entityIn.isAlive())
            {
                oldWorldIn.tickEntity(entityIn, false);
            }
        }

        if (entityIn.dimension == DimensionType.THE_END)
        {
            BlockPos blockpos;

            if (lastDimension == 1)
            {
                blockpos = toWorldIn.getSpawnPoint();
            }
            else
            {
                blockpos = toWorldIn.getSpawnCoordinate();
            }

            d0 = (double)blockpos.getX();
            entityIn.posY = (double)blockpos.getY();
            d1 = (double)blockpos.getZ();
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, 90.0F, 0.0F);

            if (entityIn.isAlive())
            {
                oldWorldIn.tickEntity(entityIn, false);
            }
        }

        oldWorldIn.profiler.endSection();

        if (lastDimension != 1)
        {
            oldWorldIn.getProfiler().begin("placing");
            d0 = (double)MathHelper.clamp((int)d0, -29999872, 29999872);
            d1 = (double)MathHelper.clamp((int)d1, -29999872, 29999872);

            if (entityIn.isAlive())
            {
                entityIn.setLocationAndAngles(d0, entityIn.getPos().getY(), d1, entityIn.rotationYaw, entityIn.rotationPitch);
                teleporterIn.placeInPortal(entityIn, f);
                toWorldIn.spawnEntity(entityIn);
                toWorldIn.tickEntity(entityIn, false);
            }

            oldWorldIn.getProfiler().end();
        }

        entityIn.setWorld(toWorldIn);
    }

}