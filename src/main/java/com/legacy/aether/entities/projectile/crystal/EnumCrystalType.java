package com.legacy.aether.entities.projectile.crystal;

import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;

public enum EnumCrystalType {

    FIRE(SoundEvents.BLOCK_FIRE_EXTINGUISH, Particles.LARGE_SMOKE, SoundEvents.ENTITY_GENERIC_EXPLODE, Particles.FLAME), ICE(SoundEvents.BLOCK_GLASS_BREAK, null, SoundEvents.BLOCK_GLASS_BREAK, Particles.CLOUD), THUNDER(), CLOUD(SoundEvents.BLOCK_GLASS_BREAK, null, SoundEvents.BLOCK_GLASS_BREAK, Particles.CLOUD);

    private SoundEvent deathSound = null;

    private IParticleData deathParticle = null;

    private SoundEvent explosionSound = null;

    private IParticleData explosionParticle = null;

    private EnumCrystalType()
    {

    }

    EnumCrystalType(SoundEvent deathSound, IParticleData deathParticle, SoundEvent explosionSound, IParticleData explosionParticle)
    {
        this.deathSound = deathSound;
        this.deathParticle = deathParticle;
        this.explosionSound = explosionSound;
        this.explosionParticle = explosionParticle;
    }

    public SoundEvent getDeathSound()
    {
        return this.deathSound;
    }

    public IParticleData getDeathParticle()
    {
        return this.deathParticle;
    }

    public SoundEvent getExplosionSound()
    {
        return this.explosionSound;
    }

    public IParticleData getExplosionParticle()
    {
        return this.explosionParticle;
    }

    public int getId()
    {
        return this.ordinal();
    }

    public static EnumCrystalType get(int id)
    {
        return values()[id];
    }

}