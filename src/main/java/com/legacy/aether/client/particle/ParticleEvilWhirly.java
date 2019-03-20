package com.legacy.aether.client.particle;

import net.minecraft.class_4184;
import net.minecraft.client.render.BufferBuilder;
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
        this.velocityX *= (double)0.1F;
        this.velocityY *= (double)0.1F;
        this.velocityZ *= (double)0.1F;
        this.velocityX += p_i46348_8_;
        this.velocityY += p_i46348_10_;
        this.velocityZ += p_i46348_12_;
        float f = (float)(Math.random() * (double)0.3F);
        this.colorRed = f;
        this.colorGreen = f;
        this.colorBlue = f;
        //this.size *= 0.75F; // TODO : VERIFY
        //this.size *= p_i46348_14_;
        //this.smokeParticleScale = this.size;
        this.maxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.maxAge = (int)((float)this.maxAge * p_i46348_14_);
        this.maxAge = Math.max(this.maxAge, 1);
    }

    @Override
    public void buildGeometry(BufferBuilder worldRendererIn, class_4184 context, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = ((float)this.age + partialTicks) / (float)this.maxAge * 32.0F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        //this.size = this.smokeParticleScale * f;
        super.buildGeometry(worldRendererIn, context, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
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

        //this.setSpriteIndex(7 - this.age * 8 / this.maxAge);
        this.velocityY += 0.004D;
        this.setPos(this.velocityX, this.velocityY, this.velocityZ);

        if (this.posY == this.prevPosY)
        {
            this.velocityX *= 1.1D;
            this.velocityZ *= 1.1D;
        }

        this.velocityX *= (double)0.96F;
        this.velocityY *= (double)0.96F;
        this.velocityZ *= (double)0.96F;

        if (this.onGround)
        {
            this.velocityX *= (double)0.7F;
            this.velocityZ *= (double)0.7F;
        }
    }

}
