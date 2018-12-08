package com.legacy.aether.client.particle;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleEvilWhirly extends AetherParticle
{

    float smokeParticleScale;

    public ParticleEvilWhirly(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46347_8_, double p_i46347_10_, double p_i46347_12_)
    {
        this(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i46347_8_, p_i46347_10_, p_i46347_12_, 1.0F);
    }

    public ParticleEvilWhirly(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46348_8_, double p_i46348_10_, double p_i46348_12_, float p_i46348_14_)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.motionX *= (double)0.1F;
        this.motionY *= (double)0.1F;
        this.motionZ *= (double)0.1F;
        this.motionX += p_i46348_8_;
        this.motionY += p_i46348_10_;
        this.motionZ += p_i46348_12_;
        float f = (float)(Math.random() * (double)0.3F);
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.particleScale *= 0.75F;
        this.particleScale *= p_i46348_14_;
        this.smokeParticleScale = this.particleScale;
        this.maxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.maxAge = (int)((float)this.maxAge * p_i46348_14_);
        this.maxAge = Math.max(this.maxAge, 1);
    }

    @Override
    public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = ((float)this.age + partialTicks) / (float)this.maxAge * 32.0F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        this.particleScale = this.smokeParticleScale * f;
        super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public void tick()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge)
        {
            this.setExpired();
        }

        this.setParticleTextureIndex(7 - this.age * 8 / this.maxAge);
        this.motionY += 0.004D;
        this.move(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= (double)0.96F;
        this.motionY *= (double)0.96F;
        this.motionZ *= (double)0.96F;

        if (this.onGround)
        {
            this.motionX *= (double)0.7F;
            this.motionZ *= (double)0.7F;
        }
    }

}
