package com.legacy.aether.entities.hostile;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.passive.EntityAetherAnimal;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.world.storage.loot.AetherLootTableList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityAechorPlant extends EntityAetherAnimal implements IRangedAttackMob {

    public float sinage;

    public int poisonRemaining, size;

    public EntityAechorPlant(World world) {
        super(EntityTypesAether.AECHOR_PLANT, world);

        this.size = this.rand.nextInt(4) + 1;
        this.sinage = this.rand.nextFloat() * 6F;
        this.poisonRemaining = this.rand.nextInt(4) + 2;

        this.setPosition(this.posX, this.posY, this.posZ);
        this.setSize(0.75F + ((float) this.size * 0.125F), 0.5F + ((float) this.size * 0.075F));
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLivingBase>(this, EntityLivingBase.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.hurtTime > 0) {
            this.sinage += 0.9F;
        }

        if (this.getAttackTarget() != null) {
            this.sinage += 0.3F;
        } else {
            this.sinage += 0.1F;
        }

        if (this.sinage > 3.141593F * 2F) {
            this.sinage -= (3.141593F * 2F);
        }

        if (this.world.getBlockState(this.getPosition().down()).getBlock() != BlocksAether.aether_grass) {
            this.setDead();
        }
    }

    public boolean canSpawn(IWorld worldIn) {
        return this.rand.nextInt(400) == 0;
    }

    @Override
    public void setSwingingArms(boolean isSwinging) {

    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase targetIn, float arg1) {
        double x = targetIn.posX - this.posX;
        double z = targetIn.posZ - this.posZ;
        double y = 0.1D + (Math.sqrt((x * x) + (z * z) + 0.1D) * 0.5D) + ((this.posY - targetIn.posY) * 0.25D);

        double distance = 1.5D / Math.sqrt((x * x) + (z * z) + 0.1D);

        EntityPoisonNeedle poisonNeedle = new EntityPoisonNeedle(this, this.world);

        poisonNeedle.shoot(x * distance, y, z * distance, 0.285F + ((float) y * 0.05F), 1.0F);

        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.2F / (this.getRNG().nextFloat() * 0.2F + 0.9F));

        this.world.spawnEntity(poisonNeedle);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);

        if (heldItem.getItem() == ItemsAether.skyroot_bucket && !player.capabilities.isCreativeMode) {
            heldItem.shrink(1);

            if (heldItem.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(ItemsAether.skyroot_poison_bucket));
            } else if (!player.inventory.addItemStackToInventory(new ItemStack(ItemsAether.skyroot_poison_bucket))) {
                player.dropItem(new ItemStack(ItemsAether.skyroot_poison_bucket), false);
            }

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
        if (this.getHealth() <= 0.0F) {
            super.knockBack(entity, strength, xRatio, zRatio);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        compound.setInteger("size", this.size);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.size = compound.getInteger("size");
    }

    @Override
    public EntityAechorPlant createChild(EntityAgeable entityIn) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_BIG_FALL;
    }

    @Override
    public ResourceLocation getLootTable() {
        return AetherLootTableList.ENTITIES_AECHOR_PLANT;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

}