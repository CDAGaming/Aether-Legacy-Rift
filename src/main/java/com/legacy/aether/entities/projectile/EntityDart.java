package com.legacy.aether.entities.projectile;

import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public abstract class EntityDart extends ProjectileEntity
{

	private int ticksInAir;

	public EntityDart(EntityType<? extends ProjectileEntity> entityType, double x, double y, double z, World world)
	{
		super(entityType, x, y, z, world);

		this.setUnaffectedByGravity(true);
	}

	public EntityDart(EntityType<? extends ProjectileEntity> entityType, LivingEntity owner, World world)
	{
		super(entityType, owner, world);

		this.setUnaffectedByGravity(true);
	}

	public EntityDart(EntityType<? extends ProjectileEntity> entityType, World world)
	{
		super(entityType, world);

		this.setUnaffectedByGravity(true);
	}

	@Override
	public void update()
	{
		if (!this.world.isClient)
		{
			if (!this.onGround)
			{
				++this.ticksInAir;
			}

			if (this.ticksInAir == 500)
			{
				this.invalidate();

				return;
			}
		}

		super.update();
	}

	@Override
	public Packet<?> createSpawnPacket()
	{
		return new EntitySpawnS2CPacket(this, 1 + (this.getOwner() == null ? this.getEntityId() : this.getOwner().getEntityId()));
	}

}