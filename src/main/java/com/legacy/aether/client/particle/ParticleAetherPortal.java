package com.legacy.aether.client.particle;

import com.legacy.aether.particle.AetherParticleType;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4002;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ParticleAetherPortal extends SpriteBillboardParticle
{

	private final double startX;

	private final double startY;

	private final double startZ;

	private ParticleAetherPortal(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
	{
		super(world, x, y, z);
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.startX = this.posX;
		this.startY = this.posY;
		this.startZ = this.posZ;
		this.field_17867 = 0.1F * (this.random.nextFloat() * 0.2F + 0.5F);
		float float_1 = this.random.nextFloat() * 0.6F + 0.4F;
		this.colorRed = float_1 * 0.9F;
		this.colorGreen = float_1 * 0.3F;
		this.colorBlue = float_1;
		this.maxAge = (int) (Math.random() * 10.0D) + 40;
	}

	@Override
	public ParticleTextureSheet getTextureSheet()
	{
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double velocityX, double velocityY, double velocityZ)
	{
		this.setBoundingBox(this.getBoundingBox().offset(velocityX, velocityY, velocityZ));
		this.repositionFromBoundingBox();
	}

	@Override
	public float method_18132(float tickDelta)
	{
		float float_2 = ((float) this.age + tickDelta) / (float) this.maxAge;

		float_2 = 1.0F - float_2;
		float_2 *= float_2;
		float_2 = 1.0F - float_2;

		return this.field_17867 * float_2;
	}

	@Override
	public int getColorMultiplier(float tickDelta)
	{
		int int_1 = super.getColorMultiplier(tickDelta);
		float float_2 = (float) this.age / (float) this.maxAge;

		float_2 *= float_2;
		float_2 *= float_2;

		int int_2 = int_1 & 255;
		int int_3 = int_1 >> 16 & 255;

		int_3 += (int) (float_2 * 15.0F * 16.0F);

		if (int_3 > 240)
		{
			int_3 = 240;
		}

		return int_2 | int_3 << 16;
	}

	@Override
	public void update()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.age++ >= this.maxAge)
		{
			this.markDead();
		}
		else
		{
			float float_1 = (float) this.age / (float) this.maxAge;
			float float_2 = float_1;
			float_1 = -float_1 + float_1 * float_1 * 2.0F;
			float_1 = 1.0F - float_1;

			this.posX = this.startX + this.velocityX * (double) float_1;
			this.posY = this.startY + this.velocityY * (double) float_1 + (double) (1.0F - float_2);
			this.posZ = this.startZ + this.velocityZ * (double) float_1;
		}
	}

	@Environment(EnvType.CLIENT)
	public static class Factory implements ParticleFactory<AetherParticleType>
	{

		private final class_4002 spriteObject;

		public Factory(class_4002 spriteObject)
		{
			this.spriteObject = spriteObject;
		}

		@Override
		public Particle createParticle(AetherParticleType particleType, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
		{
			ParticleAetherPortal particle = new ParticleAetherPortal(world, x, y, z, velocityX, velocityY, velocityZ);

			particle.method_18140(this.spriteObject);

			return particle;
		}
	}

}