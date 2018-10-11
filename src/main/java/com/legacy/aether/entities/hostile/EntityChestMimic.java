package com.legacy.aether.entities.hostile;

import com.legacy.aether.entities.EntityTypesAether;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityChestMimic extends EntityMob {

    public float mouth, legs;

    private float legsDirection = 1;

    public EntityChestMimic(World world) {
        super(EntityTypesAether.CHEST_MIMIC, world);

        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.mouth = (float) ((Math.cos((float) ticksExisted / 10F * 3.14159265F)) + 1F) * 0.6F;
        this.legs *= 0.9F;

        if (this.prevPosX - this.posX != 0 || this.prevPosZ - this.posZ != 0) {
            this.legs += legsDirection * 0.2F;

            if (this.legs > 1.0F) {
                this.legsDirection = -1;
            }

            if (this.legs < -1.0F) {
                this.legsDirection = 1;
            }
        } else {
            this.legs = 0.0F;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (damageSource.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) damageSource.getImmediateSource();

            if (player.getActiveItemStack().getItem() instanceof ItemAxe) {
                damage *= 1.25F;
            }
        }

        return super.attackEntityFrom(damageSource, damage);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_WOOD_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_CHEST_CLOSE;
    }

	/*@Override
	protected void dropFewItems(boolean var1, int var2) 
	{
		dropItem(Item.getItemFromBlock(Blocks.CHEST), 1);
	}*/

}