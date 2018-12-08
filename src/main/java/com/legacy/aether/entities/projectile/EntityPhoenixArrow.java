package com.legacy.aether.entities.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityPhoenixArrow extends EntityTippedArrow implements IProjectile
{

	private boolean isSpectral = false;

    private int duration = 200;

	public EntityPhoenixArrow(World worldIn) 
	{
		super(worldIn);
	}

    public EntityPhoenixArrow(World worldIn, double x, double y, double z)
    {
    	super(worldIn, x, y, z);
    }

	public EntityPhoenixArrow(World worldIn, EntityLivingBase shooter, boolean isSpectral)
	{
		super(worldIn, shooter);

		this.isSpectral = isSpectral;
	}

	@Override
    public void tick()
    {
        super.tick();

        if (this.world.isRemote)
        {
            if (this.inGround)
            {
                if (this.timeInGround % 5 == 0)
                {
                    this.spawnPotionParticles(1);
                }
            }
            else
            {
            	if (this.isSpectral)
            	{
                    this.world.addParticle(Particles.ENTITY_EFFECT, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
            	}

                this.spawnPotionParticles(2);
            }
        }
    }

    private void spawnPotionParticles(int particleCount)
    {
        for (int j = 0; j < particleCount; ++j)
        {
        	this.world.addParticle(Particles.FLAME, this.posX + (this.rand.nextGaussian() / 5D), this.posY + (this.rand.nextGaussian() / 5D), this.posZ + (this.rand.nextGaussian() / 3D), 0.0D, 0.0D, 0.0D);
        }
    }

	@Override
	protected ItemStack getArrowStack() 
	{
		return this.isSpectral ? new ItemStack(Items.SPECTRAL_ARROW) : super.getArrowStack();
	}

    @Override
    protected void arrowHit(EntityLivingBase living)
    {
        super.arrowHit(living);

        living.setFire(10);
        
        if (this.isSpectral)
        {
            PotionEffect potioneffect = new PotionEffect(MobEffects.GLOWING, this.duration, 0);
            living.addPotionEffect(potioneffect);
        }
    }

    @Override
    public void readAdditional(NBTTagCompound compound)
    {
        super.readAdditional(compound);

        if (compound.contains("Duration"))
        {
            this.duration = compound.getInt("Duration");
        }

        if (compound.contains("Spectral"))
        {
        	this.isSpectral = compound.getBoolean("Spectral");
        }
    }

    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);

        compound.putInt("Duration", this.duration);
        compound.putBoolean("Spectral", this.isSpectral);
    }

}