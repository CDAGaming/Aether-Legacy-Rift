package com.legacy.aether.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.MapRenderer;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.EntitySpawnClientPacket;
import net.minecraft.client.network.packet.MapUpdateClientPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.map.MapState;
import net.minecraft.server.network.EntityTracker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.util.MapDimensionData;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler
{

	@Shadow private ClientWorld world;

	@Shadow private MinecraftClient client;

	@Inject(method = "onEntitySpawn", at = @At("RETURN"))
	public void onAetherEntitySpawn(EntitySpawnClientPacket packet, CallbackInfo ci)
	{
		double d0 = packet.getX();
		double d1 = packet.getY();
		double d2 = packet.getZ();

		Entity aetherEntity = null;

		if (packet.getEntityTypeId() == 583)
		{
			aetherEntity = new EntityFloatingBlock(this.world, d0, d1, d2, Block.getStateFromRawId(packet.getEntityData()));

			packet.setEntityData(0);
		}
		else if (packet.getEntityTypeId() == 584)
		{
			aetherEntity = new EntityGoldenDart(d0, d1, d2, this.world);
		}
		else if (packet.getEntityTypeId() == 585)
		{
			aetherEntity = new EntityEnchantedDart(d0, d1, d2, this.world);
		}
		else if (packet.getEntityTypeId() == 586)
		{
			aetherEntity = new EntityPoisonDart(d0, d1, d2, this.world);
		}
		else if (packet.getEntityTypeId() == 587)
		{
			aetherEntity = new EntityPoisonNeedle(d0, d1, d2, this.world);
		}
		/*else if (packet.getType() == 588)
		{
			aetherEntity = new EntityMiniCloud(this.world, (EntityPlayer) this.world.getEntityByID(packet.getData()), 0);
			packet.setData(0);
		}
		else if (packet.getType() == 589)
		{
			aetherEntity = new EntityMiniCloud(this.world, (EntityPlayer) this.world.getEntityByID(packet.getData()), 1);
			packet.setData(0);
		}
		else if (packet.getType() == 590)
		{
			aetherEntity = new EntityCrystal(this.world, d0, d1, d2, EnumCrystalType.FIRE);
			packet.setData(0);
		}
		else if (packet.getType() == 591)
		{
			aetherEntity = new EntityCrystal(this.world, d0, d1, d2, EnumCrystalType.ICE);
			packet.setData(0);
		}
		else if (packet.getType() == 592)
		{
			aetherEntity = new EntityCrystal(this.world, d0, d1, d2, (EntityPlayer) this.world.getEntityByID(packet.getData()));
			packet.setData(0);
		}
		else if (packet.getType() == 593)
		{
			aetherEntity = new EntityPhoenixArrow(this.world, d0, d1, d2);
		}*/

		if (aetherEntity != null)
		{
			EntityTracker.method_14070(aetherEntity, d0, d1, d2);

			aetherEntity.pitch = (float) (packet.getPitch() * 360) / 256.0F;
			aetherEntity.yaw = (float) (packet.getYaw() * 360) / 256.0F;

			Entity[] aentity = aetherEntity.getParts();

			if (aentity != null)
			{
				int i = packet.getId() - aetherEntity.getEntityId();
				for (Entity entity2 : aentity)
				{
					entity2.setEntityId(entity2.getEntityId() + i);
				}
			}

			aetherEntity.setEntityId(packet.getId());
			aetherEntity.setUuid(packet.getUuid());

			this.world.method_2942(packet.getId(), aetherEntity);

			if (packet.getEntityData() > 0)
			{
				if (packet.getEntityTypeId() > 583 && packet.getEntityTypeId() < 588)
				{
					Entity entity3 = this.world.getEntityById(packet.getEntityData() - 1);

					if (entity3 instanceof LivingEntity && aetherEntity instanceof ProjectileEntity)
					{
						ProjectileEntity projectileEntity_1 = (ProjectileEntity) aetherEntity;

						projectileEntity_1.setOwner(entity3);

						if (entity3 instanceof PlayerEntity)
						{
							projectileEntity_1.pickupType = ProjectileEntity.PickupType.PICKUP;

							if (((PlayerEntity) entity3).abilities.creativeMode)
							{
								projectileEntity_1.pickupType = ProjectileEntity.PickupType.CREATIVE_PICKUP;
							}
						}
					}
				}

				aetherEntity.setVelocityClient((double) packet.getVelocityX() / 8000.0D, (double) packet.getVelocityY() / 8000.0D, (double) packet.getVelocityz() / 8000.0D);
			}
		}
	}

	@Inject(method = "onMapUpdate", at = @At("RETURN"))
	public void onMapUpdateDimension(MapUpdateClientPacket packet, CallbackInfo ci)
	{
		MapRenderer mapRenderer = this.client.gameRenderer.getMapRenderer();
		String string_1 = FilledMapItem.method_17440(packet.getId());
		MapState mapState = this.client.world.method_17891(string_1);//FilledMapItem.method_7997(this.client.world, string_1);

		mapState.dimension = ((MapDimensionData)packet).getDimension();

		mapRenderer.updateTexture(mapState);
	}

}