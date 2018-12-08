package com.legacy.aether.entities.projectile.crystal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.legacy.aether.api.player.util.IAetherBoss;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.bosses.EntityFireMinion;

public class EntityCrystal extends EntityFlying
{

    public float[] sinage = new float[3];

    public double smotionX;

    public double smotionY;

    public double smotionZ;

    public boolean wasHit;

    private EnumCrystalType type;

    public EntityCrystal(World world) 
    {
        super(EntityTypesAether.CRYSTAL, world);

        double base = 0.2F;

        this.smotionX = (base + (double) this.rand.nextFloat() * 0.15D) * (this.rand.nextInt(2) == 0 ? 1.0D : -1.0D);
        this.smotionY = (base + (double) this.rand.nextFloat() * 0.15D) * (this.rand.nextInt(2) == 0 ? 1.0D : -1.0D);
        this.smotionZ = (base + (double) this.rand.nextFloat() * 0.15D) * (this.rand.nextInt(2) == 0 ? 1.0D : -1.0D);

        this.isImmuneToFire = true;
        this.type = EnumCrystalType.get(this.rand.nextInt(2));

        for (int var2 = 0; var2 < this.sinage.length; ++var2)
        {
            this.sinage[var2] = this.rand.nextFloat() * 6.0F;
        }

        this.setSize(0.9F, 0.9F);
    }

    public EntityCrystal(World world, double x, double y, double z, EnumCrystalType type)
    {
        this(world);

        this.type = type;

        if (this.type == EnumCrystalType.ICE)
        {
            this.smotionX /= 3.0D;
            this.smotionY = 0.0D;
            this.smotionZ /= 3.0D;
        }

        this.setPosition(x, y, z);
    }

    public EntityCrystal(World world, double x, double y, double z, EntityLivingBase target)
    {
        this(world, x, y, z, EnumCrystalType.THUNDER);

        this.setAttackTarget(target);
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.type != EnumCrystalType.THUNDER && !this.world.isRemote)
        {
            this.motionX = this.smotionX;
            this.motionY = this.smotionY;
            this.motionZ = this.smotionZ;
        }

        if (this.type == EnumCrystalType.THUNDER) 
        {
            if (!this.world.isRemote)
            {
                if (this.getAttackTarget() == null || (this.getAttackTarget() != null && !this.getAttackTarget().isAlive())) 
                {
                    this.remove();
                }

                this.faceEntity(this.getAttackTarget(), 10F, 10F);
                this.moveTowardsTarget(this.getAttackTarget(), 0.02D);
            }
        }
        else if (this.collided && !this.world.isRemote)
        {
            if (this.wasHit())
            {
                this.explode();
                this.expire();
                this.remove();
            }

            int var1 = MathHelper.floor(this.posX);
            int var2 = MathHelper.floor(this.getBoundingBox().minY);
            int var3 = MathHelper.floor(this.posZ);
            BlockPos pos = new BlockPos(var1, var2, var3);

            if (this.smotionX > 0.0D && !this.world.isAirBlock(pos.east())) 
            {
                this.motionX = this.smotionX = -this.smotionX;
            }
            else if (this.smotionX < 0.0D && !this.world.isAirBlock(pos.west())) 
            {
                this.motionX = this.smotionX = -this.smotionX;
            }

            if (this.smotionY > 0.0D && !this.world.isAirBlock(pos.up()))
            {
                this.motionY = this.smotionY = -this.smotionY;
            }
            else if (this.smotionY < 0.0D && !this.world.isAirBlock(pos.down()))
            {
                this.motionY = this.smotionY = -this.smotionY;
            }

            if (this.smotionZ > 0.0D && !this.world.isAirBlock(pos.south())) 
            {
                this.motionZ = this.smotionZ = -this.smotionZ;
            }
            else if (this.smotionZ < 0.0D && !this.world.isAirBlock(pos.north()))
            {
                this.motionZ = this.smotionZ = -this.smotionZ;
            }
        }

        if (this.ticksExisted >= this.maxTicksAlive())
        {
            if (this.type == EnumCrystalType.THUNDER)
            {
                this.world.addWeatherEffect(new EntityLightningBolt(this.world, this.posX, this.posY, this.posZ, false));
            }

            this.expire();
            this.remove();
        }

        for (int var1 = 0; var1 < this.sinage.length; ++var1) {
            this.sinage[var1] += 0.3F + (float) var1 * 0.13F;

            if (this.sinage[var1] > ((float) Math.PI * 2F)) {
                this.sinage[var1] -= ((float) Math.PI * 2F);
            }
        }
    }

    @Override
    public void applyEntityCollision(Entity entity)
    {
        super.applyEntityCollision(entity);

        if (entity instanceof EntityCrystal && this.world.getDifficulty() == EnumDifficulty.HARD)
        {
            EntityCrystal crystal = (EntityCrystal) entity;

            if (this.type != crystal.type)
            {
                this.explode();
                this.expire();
                this.remove();
                crystal.explode();
                crystal.expire();
                crystal.remove();
            }
        }
        else if (entity instanceof EntityLivingBase)
        {
            if (this.type == EnumCrystalType.FIRE && !(entity instanceof IAetherBoss) && !(entity instanceof EntityFireMinion))
            {
                if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F))
                {
                    this.explode();
                    this.expire();
                    this.remove();
                    entity.setFire(100);
                }
            }
            else if (this.type == EnumCrystalType.ICE && this.wasHit())
            {
                if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F))
                {
                    this.explode();
                    this.expire();
                    this.remove();
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 10));
                }
            }
            else if (this.type == EnumCrystalType.CLOUD && !(entity instanceof IAetherBoss))
            {
                this.explode();
                this.expire();
                this.remove();
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
            }
            else if (this.type == EnumCrystalType.THUNDER && entity == this.getAttackTarget())
            {
                if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F))
                {
                    this.moveTowardsTarget(entity, -0.3D);
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
        if (source.getTrueSource() != null)
        {
            if (this.type == EnumCrystalType.THUNDER)
            {
                this.moveTowardsTarget(source.getTrueSource(), -0.15D - ((double) damage / 8D));

                return super.attackEntityFrom(source, damage);
            }

            Vec3d var3 = source.getTrueSource().getLookVec();

            if (var3 != null) {
                this.smotionX = var3.x;
                this.smotionY = var3.y;
                this.smotionZ = var3.y;
            }

            this.wasHit = true;

            return true;
        }

        return false;
    }

    public void moveTowardsTarget(Entity target, double speed)
    {
        if (this.world.isRemote)
        {
            return;
        }

        double angle1 = this.rotationYaw / (180F / Math.PI);

        this.motionX -= Math.sin(angle1) * speed;
        this.motionZ += Math.cos(angle1) * speed;

        double a = target.posY - 0.75F;

        if (a < this.getBoundingBox().minY - 0.5F)
        {
            this.motionY -= (speed / 2);
        }
        else if (a > this.getBoundingBox().minY + 0.5F)
        {
            this.motionY += (speed / 2);
        }
        else
        {
            this.motionY += (a - this.getBoundingBox().minY) * (speed / 2);
        }

        if (this.onGround)
        {
            this.onGround = false;
            this.motionY = 0.1F;
        }
    }

    public boolean wasHit()
    {
        return this.wasHit;
    }

    public int maxTicksAlive()
    {
        return this.type == EnumCrystalType.THUNDER ? 200 : 300;
    }

    private void expire()
    {
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, this.type.getDeathSound(), this.getSoundCategory(), 2.0F, this.rand.nextFloat() - this.rand.nextFloat() * 0.2F + 1.2F);

        if (this.world.isRemote)
        {
            return;
        }

        ((WorldServer) this.world).spawnParticle(this.type.getDeathParticle(), this.posX, this.getBoundingBox().minY + this.height * 0.8D, this.posZ, 16, 0.25D, 0.25D, 0.25D, 0.0D);
    }

    private void explode()
    {
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, this.type.getExplosionSound(), this.getSoundCategory(), 2.0F, this.rand.nextFloat() - this.rand.nextFloat() * 0.2F + 1.2F);

        if (this.world.isRemote)
        {
            return;
        }

        double motionMultiplier = 0.5F;

        if (this.type == EnumCrystalType.ICE || this.type == EnumCrystalType.CLOUD)
        {
            motionMultiplier *= 0.5D;
        }

        ((WorldServer) this.world).spawnParticle(this.type.getExplosionParticle(), this.posX, this.posY, this.posZ, 40, 0.0D, 0.0D, 0.0D, motionMultiplier);
    }

    public EnumCrystalType getCrystalType()
    {
        return this.type;
    }

}