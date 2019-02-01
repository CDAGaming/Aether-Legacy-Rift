package com.legacy.aether.entities.projectile;

import io.netty.buffer.Unpooled;
import net.minecraft.client.network.packet.CustomPayloadClientPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.util.AetherPoisonMovement;
import com.legacy.aether.item.ItemsAether;

public class EntityPoisonDart extends EntityDart
{

	private LivingEntity victim;

	private AetherPoisonMovement poison;

	public EntityPoisonDart(EntityType<?> entityType, double x, double y, double z, World world)
	{
		super(entityType, x, y, z, world);

		this.setDamage(0);
	}

	public EntityPoisonDart(EntityType<?> entityType, LivingEntity owner, World world)
	{
		super(entityType, owner, world);

		this.setDamage(0);
	}

	public EntityPoisonDart(EntityType<?> entityType, World world)
	{
		super(entityType, world);

		this.setDamage(0);
	}

	public EntityPoisonDart(double x, double y, double z, World world)
	{
		this(EntityTypesAether.POISON_DART, x, y, z, world);
	}

	public EntityPoisonDart(LivingEntity owner, World world)
	{
		this(EntityTypesAether.POISON_DART, owner, world);
	}

	public EntityPoisonDart(World world)
	{
		this(EntityTypesAether.POISON_DART, world);
	}

	@Override
	public void update()
	{
		super.update();

		if (this.victim != null)
		{
			if (!this.victim.isValid() || this.poison.ticks == 0)
			{
				this.invalidate();

				return;
			}

			if (this.getOwner() != null)
			{
				if (this.getOwner().world instanceof ServerWorld)
				{
					((ServerWorld) this.getOwner().world).method_14199(new ItemStackParticleParameters(ParticleTypes.ITEM, new ItemStack(Items.RED_DYE)), this.victim.x, this.victim.getBoundingBox().minY + this.victim.getHeight() * 0.8D, this.victim.z, 2, 0.0D, 0.0D, 0.0D, 0.0625D);
				}
			}

			this.invalid = false;
			this.poison.onUpdate();
			this.setInvisible(true);
			this.setPosition(this.victim.x, this.victim.y, this.victim.z);
		}
	}

	@Override
	protected void onHit(LivingEntity entityIn)
	{
		super.onHit(entityIn);

		if (entityIn instanceof ServerPlayerEntity)
		{
			AetherAPI.get((PlayerEntity) entityIn).inflictPoison(500);

			PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());

			byteBuf.writeInt(500);

			((ServerPlayerEntity)entityIn).networkHandler.sendPacket(new CustomPayloadClientPacket(Aether.locate("poison"), byteBuf));
		}
		else
		{
			this.victim = entityIn;
			this.poison = new AetherPoisonMovement(this.victim);
			this.poison.inflictPoison(500);
			this.invalid = false;
		}
	}

	@Override
	protected Entity method_7434(Vec3d start, Vec3d end)
	{
		return this.victim == null ? super.method_7434(start, end) : null;
	}

	@Override
	public void onPlayerCollision(PlayerEntity playerIn)
	{
		if (this.victim == null)
		{
			super.onPlayerCollision(playerIn);
		}
	}

	@Override
	protected ItemStack asItemStack()
	{
		return new ItemStack(ItemsAether.poison_dart);
	}

	@Override
	public int getSpawnID()
	{
		return 3;
	}

}