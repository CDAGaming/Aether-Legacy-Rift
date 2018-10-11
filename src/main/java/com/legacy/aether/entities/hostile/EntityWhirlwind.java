package com.legacy.aether.entities.hostile;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.particle.AetherParticle;
import com.legacy.aether.client.particle.ParticleEvilWhirly;
import com.legacy.aether.client.particle.ParticlePassiveWhirly;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.player.perks.AetherRankings;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityWhirlwind extends EntityMob {

    public static final DataParameter<Boolean> IS_EVIL = EntityDataManager.<Boolean>createKey(EntityWhirlwind.class, DataSerializers.BOOLEAN);

    public static final DataParameter<Integer> COLOR_DATA = EntityDataManager.createKey(EntityWhirlwind.class, DataSerializers.VARINT);

    public ArrayList<Object> particles = new ArrayList<Object>();

    public int lifeLeft;

    public int actionTimer;

    public float movementAngle;
    public float movementCurve;

    public EntityWhirlwind(World world) {
        super(EntityTypesAether.WHIRLWIND, world);

        this.setSize(0.6F, 0.8F);

        this.movementAngle = this.rand.nextFloat() * 360F;
        this.movementCurve = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;

        this.lifeLeft = this.rand.nextInt(512) + 512;

        if (this.rand.nextInt(10) == 0) {
            this.lifeLeft /= 2;
            this.setEvil(true);
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return this.world.getBlockState(pos.down()).getBlock() == BlocksAether.aether_grass ? 10.0F : this.world.getBrightness(pos) - 0.5F;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((this.rand.nextDouble() * 0.025D) + 0.025D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IS_EVIL, false);
        this.dataManager.register(COLOR_DATA, Item.getIdFromItem(ItemDye.getItem(EnumDyeColor.WHITE)));
    }

    public Integer getColorData() {
        Item item = Item.getItemById(this.dataManager.get(COLOR_DATA));

        if (item instanceof ItemDye) {
            return ((ItemDye) item).getDyeColor().func_196060_f();
        }

        return EnumDyeColor.WHITE.func_196060_f();
    }

    public void setColorData(Integer data) {
        this.dataManager.set(COLOR_DATA, data);
    }

    public boolean isEvil() {
        return this.dataManager.get(IS_EVIL);
    }

    public void setEvil(boolean isEvil) {
        this.dataManager.set(IS_EVIL, isEvil);
    }

    @Override
    public void onLivingUpdate() {
        EntityPlayer closestPlayer = this.findClosestPlayer();

        if (this.isEvil()) {
            if (closestPlayer != null && closestPlayer.onGround) {
                this.setAttackTarget(closestPlayer);
            }
        }

        if (this.getAttackTarget() == null) {
            this.motionX = Math.cos(0.01745329F * this.movementAngle) * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
            this.motionZ = -Math.sin(0.01745329F * this.movementAngle) * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
            this.movementAngle += this.movementCurve;
        } else {
            super.onLivingUpdate();
        }

        if (this.lifeLeft-- <= 0 || handleWaterMovement()) {
            this.setDead();
        }

        if (!this.world.isRemote) {
            if (closestPlayer != null) {
                this.actionTimer++;
            }

            if (this.actionTimer >= 128) {
                if (this.isEvil()) {
                    EntityCreeper entitycreeper = new EntityCreeper(this.world);

                    entitycreeper.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ, this.rand.nextFloat() * 360F, 0.0F);
                    entitycreeper.motionX = (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                    entitycreeper.motionZ = (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;

                    this.world.spawnEntity(entitycreeper);
                    this.actionTimer = 0;
                    this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.HOSTILE, 0.5F, 1.0F);
                } else if (this.rand.nextInt(4) == 0) {
                    this.entityDropItem(this.getRandomDrop(), 1);
                    this.actionTimer = 0;
                    this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.HOSTILE, 0.5F, 1.0F);
                }
            }
        } else {
            this.updateParticles();
        }

        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(2.5D, 2.5D, 2.5D));

        for (int l = 0; l < list.size(); l++) {
            Entity entity = (Entity) list.get(l);

            double d9 = (float) entity.posX;
            double d11 = (float) entity.posY - entity.getYOffset() * 0.6F;
            double d13 = (float) entity.posZ;
            double d15 = this.getDistance(entity);
            double d17 = d11 - this.posY;

            if (d15 <= 1.5D + d17) {
                entity.motionY = 0.15000000596046448D;
                entity.fallDistance = 0.0F;

                if (d17 > 1.5D) {
                    entity.motionY = -0.44999998807907104D + d17 * 0.34999999403953552D;
                    d15 += d17 * 1.5D;
                } else {
                    entity.motionY = 0.125D;
                }

                double d19 = Math.atan2(this.posX - d9, this.posZ - d13) / 0.01745329424738884D;
                d19 += 160D;
                entity.motionX = -Math.cos(0.01745329424738884D * d19) * (d15 + 0.25D) * 0.10000000149011612D;
                entity.motionZ = Math.sin(0.01745329424738884D * d19) * (d15 + 0.25D) * 0.10000000149011612D;

                if (entity instanceof EntityWhirlwind) {
                    entity.setDead();

                    if (!this.isEvil()) {
                        this.lifeLeft /= 2;
                        this.setEvil(true);
                        this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.HOSTILE, this.rand.nextFloat() - this.rand.nextFloat() * 0.2F + 1.2F, 1.0F);
                    }
                }
            } else {
                double d20 = Math.atan2(this.posX - d9, this.posZ - d13) / 0.01745329424738884D;
                entity.motionX += Math.sin(0.01745329424738884D * d20) * 0.0099999997764825821D;
                entity.motionZ += Math.cos(0.01745329424738884D * d20) * 0.0099999997764825821D;
            }

            if (!this.world.isAirBlock(this.getPosition())) {
                this.lifeLeft -= 50;
            }

            if (this.world.getGameRules().getBoolean("mobGriefing")) {
                int i2 = (MathHelper.floor(this.posX) - 1) + this.rand.nextInt(3);
                int j2 = MathHelper.floor(this.posY) + this.rand.nextInt(5);
                int k2 = (MathHelper.floor(this.posZ) - 1) + this.rand.nextInt(3);

                if (this.world.getBlockState(new BlockPos.MutableBlockPos().setPos(i2, j2, k2)).getBlock() instanceof BlockLeaves) {
                    this.world.setBlockState(new BlockPos(i2, j2, k2), Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);

        if (heldItem.getItem() instanceof ItemDye && AetherRankings.isRankedPlayer(player.getUniqueID())) {
            this.setColorData(Item.getIdFromItem(heldItem.getItem()));

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean canTriggerWalking() {
        return false;
    }

    public void updateParticles() {
        if (!this.isEvil()) {
            Integer color = this.getColorData();

            for (int k = 0; k < 2; k++) {
                double d1 = (float) this.posX + rand.nextFloat() * 0.25F;
                double d4 = (float) posY + height + 0.125F;
                double d7 = (float) this.posZ + rand.nextFloat() * 0.25F;
                float f = rand.nextFloat() * 360F;

                AetherParticle particle = new ParticlePassiveWhirly(this.world, -Math.sin(0.01745329F * f) * 0.75D, d4 - 0.25D, Math.cos(0.01745329F * f) * 0.75D, d1, 0.125D, d7);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
                this.particles.add(particle);

                particle.setRBGColorF((((color >> 16) & 0xFF) / 255F), (((color >> 8) & 0xFF) / 255F), ((color & 0xFF) / 255F));
                particle.setPosition(this.posX, this.posY, this.posZ);
            }
        } else {
            for (int k = 0; k < 3; k++) {
                double d2 = (float) posX + rand.nextFloat() * 0.25F;
                double d5 = (float) posY + height + 0.125F;
                double d8 = (float) posZ + rand.nextFloat() * 0.25F;
                float f1 = rand.nextFloat() * 360F;
                AetherParticle particle = new ParticleEvilWhirly(this.world, -Math.sin(0.01745329F * f1) * 0.75D, d5 - 0.25D, Math.cos(0.01745329F * f1) * 0.75D, d2, 0.125D, d8, 3.5F);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle);
                this.particles.add(particle);

                particle.setPosition(this.posX, this.posY, this.posZ);
            }
        }

        if (this.particles.size() > 0) {
            for (int i1 = 0; i1 < this.particles.size(); i1++) {
                AetherParticle particle = (AetherParticle) this.particles.get(i1);

                if (!particle.isAlive()) {
                    this.particles.remove(particle);
                } else {
                    double d10 = particle.getX();
                    double d12 = particle.getBoundingBox().minY;
                    double d14 = particle.getZ();
                    double d16 = this.getDistanceToParticle(particle);
                    double d18 = d12 - this.posY;
                    particle.setMotionY(0.11500000208616257D);
                    double d21 = Math.atan2(this.posX - d10, this.posZ - d14) / 0.01745329424738884D;
                    d21 += 160D;
                    particle.setMotionX(-Math.cos(0.01745329424738884D * d21) * (d16 * 2.5D - d18) * 0.10000000149011612D);
                    particle.setMotionZ(Math.sin(0.01745329424738884D * d21) * (d16 * 2.5D - d18) * 0.10000000149011612D);
                }
            }
        }
    }

    public float getDistanceToParticle(AetherParticle particle) {
        float f = (float) (this.posX - particle.getX());
        float f1 = (float) (this.posY - particle.getY());
        float f2 = (float) (this.posZ - particle.getZ());

        return MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public Item getRandomDrop() {
        int i = this.rand.nextInt(100) + 1;

        if (i == 100) {
            return Items.DIAMOND;
        }

        if (i >= 96) {
            return Items.IRON_INGOT;
        }

        if (i >= 91) {
            return Items.GOLD_INGOT;
        }

        if (i >= 82) {
            return Items.COAL;
        }

        if (i >= 80) {
            return Item.getItemFromBlock(Blocks.PUMPKIN);
        }

        if (i >= 75) {
            return Item.getItemFromBlock(Blocks.GRAVEL);
        }

        if (i >= 64) {
            return Item.getItemFromBlock(Blocks.CLAY);
        }

        if (i >= 52) {
            return Items.STICK;
        }

        if (i >= 38) {
            return Items.FLINT;
        }

        if (i > 20) {
            return Item.getItemFromBlock(BlocksAether.skyroot_log);
        } else {
            return Item.getItemFromBlock(Blocks.SAND);
        }
    }

    public EntityPlayer findClosestPlayer() {
        return this.world.getClosestPlayerToEntity(this, 16D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);

        nbttagcompound.setFloat("movementAngle", this.movementAngle);
        nbttagcompound.setFloat("movementCurve", this.movementCurve);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        this.movementAngle = nbttagcompound.getFloat("movementAngle");
        this.movementCurve = nbttagcompound.getFloat("movementCurve");
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        return false;
    }

    @Override
    public void applyEntityCollision(Entity entity) {

    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }

    @Override
    public boolean isOnLadder() {
        return collidedHorizontally;
    }

}