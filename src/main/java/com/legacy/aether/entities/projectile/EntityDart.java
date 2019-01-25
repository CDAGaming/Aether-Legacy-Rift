package com.legacy.aether.entities.projectile;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDart extends ProjectileEntity
{

	private int ticksInAir;

	public EntityDart(EntityType<?> entityType, double x, double y, double z, World world)
	{
		super(entityType, x, y, z, world);

		this.setSize(0.5F, 0.5F);
		this.setUnaffectedByGravity(true);
	}

	public EntityDart(EntityType<?> entityType, LivingEntity owner, World world)
	{
		super(entityType, owner, world);

		this.setSize(0.5F, 0.5F);
		this.setUnaffectedByGravity(true);
	}

	public EntityDart(EntityType<?> entityType, World world)
	{
		super(entityType, world);

		this.setSize(0.5F, 0.5F);
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

	public abstract int getSpawnID();

	public static PacketByteBuf write(EntityDart dart) 
	{
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

		buf.writeInt(dart.getSpawnID());
		buf.writeVarInt(dart.getEntityId());
		buf.writeUuid(dart.getUuid());
		buf.writeDouble(dart.x);
		buf.writeDouble(dart.y);
		buf.writeDouble(dart.z);
		buf.writeInt((int)(MathHelper.clamp(dart.velocityX, -3.9D, 3.9D) * 8000.0D));
		buf.writeInt((int)(MathHelper.clamp(dart.velocityY, -3.9D, 3.9D) * 8000.0D));
		buf.writeInt((int)(MathHelper.clamp(dart.velocityZ, -3.9D, 3.9D) * 8000.0D));
		buf.writeInt(MathHelper.floor(dart.pitch * 256.0F / 360.0F));
		buf.writeInt(MathHelper.floor(dart.yaw * 256.0F / 360.0F));

		//Extra data
		buf.writeInt(dart.getOwner() != null ? dart.getOwner().getEntityId() : 0);

		return buf;
	}

}