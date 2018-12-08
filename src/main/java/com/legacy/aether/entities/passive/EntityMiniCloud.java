package com.legacy.aether.entities.passive;

import java.util.UUID;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.sounds.SoundsAether;

public class EntityMiniCloud extends EntityFlying implements IEntityOwnable
{

	private UUID ownerUUID;

    public int shotTimer, lifeSpan;

    public boolean hasOwner, direction, hasSpawned = false;

    public double targetX, targetY, targetZ;

    public EntityMiniCloud(World worldObj)
    {
        super(EntityTypesAether.MINI_CLOUD, worldObj);

        this.entityCollisionReduction = 1.75F;
    }

    public EntityMiniCloud(World worldObj, EntityPlayer owner, int direction)
    {
        this(worldObj);

        this.noClip = true;
        this.lifeSpan = 3600;
        this.hasOwner = true;
        this.direction = direction == 0;
        this.ownerUUID = owner.getUniqueID();
        this.rotationPitch = this.getOwner().rotationPitch;
        this.rotationYaw = this.getOwner().rotationYaw;

        this.setSize(0.5F, 0.45F);
        this.setPosition(this.targetX, this.targetY, this.targetZ);
    }

    @Override
    public void livingTick()
    {
        super.livingTick();

        --this.lifeSpan;

        if (this.lifeSpan <= 0)
        {
            this.spawnExplosionParticle();
            this.remove();
        }
        else
        {
        	if (!this.hasOwner || this.getOwner() == null)
        	{
                this.remove();
        		return;
        	}

            if (this.hasOwner && this.getOwner() == null)
            {
            	this.hasOwner = false;
                this.remove();
                return;
            }

            if (this.shotTimer > 0)
            {
                --this.shotTimer;
            }

            if (!this.getOwner().removed)
            {
                this.getTargetPos();

                if (this.atShoulder())
                {
                    this.motionX *= 0.65D;
                    this.motionY *= 0.65D;
                    this.motionZ *= 0.65D;

        			this.rotationYaw = this.getOwner().rotationYaw + (this.direction ? 1F : -1F);;
        			this.rotationPitch = this.getOwner().rotationPitch;
        			this.rotationYawHead = this.getOwner().rotationYawHead;

                    if (this.shotTimer <= 0 && this.getOwner().swingProgress > 0.0F)
                    {
                    	/*float var7 = this.rotationYaw - (this.direction ? 1.0F : -1.0F);
                        double var1 = this.posX + Math.sin((double)var7 / -(180D / Math.PI)) * 1.6D;
                        double var3 = this.posY - -1.0D;
                        double var5 = this.posZ + Math.cos((double)var7 / -(180D / Math.PI)) * 1.6D;
                        EntityIceyBall iceCrystal = new EntityIceyBall(this.world, var1, var3, var5, true);
                        Vec3d var9 = this.getLookVec();

                        if (var9 != null)
                        {
                        	iceCrystal.smotionX = var9.x * 1.5D;
                        	iceCrystal.smotionY = var9.y * 1.5D;
                        	iceCrystal.smotionZ = var9.z * 1.5D;
                        }

                        iceCrystal.smacked = true;

                        if (!this.world.isRemote)
                        {
                            this.world.spawnEntityInWorld(iceCrystal);
                        }*/

                        this.world.playSound(this.posX, this.posY, this.posZ, SoundsAether.zephyr_shoot, SoundCategory.NEUTRAL, 0.75F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F, false);

                        this.shotTimer = 40;
                    }
                }
                else
                {
                    this.approachTarget();
                }
            }
            else
            {
                this.spawnExplosionParticle();
                this.remove();
            }
        }
    }

    public int getDirection()
    {
    	return this.direction ? 0 : 1;
    }

    public void getTargetPos()
    {
        if (this.getDistance(this.getOwner()) > 2.0F)
        {
            this.targetX = this.getOwner().posX;
            this.targetY = this.getOwner().posY - 0.10000000149011612D;
            this.targetZ = this.getOwner().posZ;
        }
        else
        {
            double var1 = (double)this.getOwner().rotationYaw;

            if (this.direction)
            {
                var1 -= 90.0D;
            }
            else
            {
                var1 += 90.0D;
            }

            var1 /= -(180D / Math.PI);
            this.targetX = this.getOwner().posX + Math.sin(var1) * 1.05D;
            this.targetY = this.getOwner().posY - 0.10000000149011612D;
            this.targetZ = this.getOwner().posZ + Math.cos(var1) * 1.05D;
        }
    }

    public boolean atShoulder()
    {
        double var1 = this.posX - this.targetX;
        double var3 = this.posY - this.targetY;
        double var5 = this.posZ - this.targetZ;
        return Math.sqrt(var1 * var1 + var3 * var3 + var5 * var5) < 0.3D;
    }

    public void approachTarget()
    {
        double var1 = this.targetX - this.posX;
        double var3 = this.targetY - this.posY;
        double var5 = this.targetZ - this.posZ;
        double var7 = Math.sqrt(var1 * var1 + var3 * var3 + var5 * var5) * 3.25D;
        this.motionX = (this.motionX + var1 / var7) / 2.0D;
        this.motionY = (this.motionY + var3 / var7) / 2.0D;
        this.motionZ = (this.motionZ + var5 / var7) / 2.0D;
        Math.atan2(var1, var5);
    }

    @Override
    public boolean isInRangeToRenderDist(double var1)
    {
        return true;
    }

    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);
        compound.putShort("LifeSpan", (short)this.lifeSpan);
        compound.putShort("ShotTimer", (short)this.shotTimer);
        this.hasOwner = this.getOwner() != null;
        compound.putBoolean("GotPlayer", this.hasOwner);
        compound.putBoolean("direction", this.direction);
    }

    @Override
    public void readAdditional(NBTTagCompound compound)
    {
        super.readAdditional(compound);
        this.lifeSpan = compound.getShort("LifeSpan");
        this.shotTimer = compound.getShort("ShotTimer");
        this.hasOwner = compound.getBoolean("GotPlayer");
        this.direction = compound.getBoolean("direction");
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
    	if (source.getTrueSource() == this.getOwner() || source.getDamageType() == "inWall")
    	{
    		return false;
    	}

        return super.attackEntityFrom(source, damage);
    }

	@Override
	public EntityPlayer getOwner()
	{
        return this.getOwnerId() == null ? null : this.world.getPlayerEntityByUUID(this.getOwnerId());
	}

	@Override
	public UUID getOwnerId()
	{
		return this.ownerUUID;
	}

}