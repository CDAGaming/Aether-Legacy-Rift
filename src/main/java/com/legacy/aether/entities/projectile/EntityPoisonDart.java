package com.legacy.aether.entities.projectile;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticle;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.CustomPayloadServerPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.legacy.aether.Aether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.movement.AetherPoisonMovement;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.IEntityPlayerAether;

public class EntityPoisonDart extends EntityDart
{

	public LivingEntity victim;

	public AetherPoisonMovement poison;

    public EntityPoisonDart(World worldIn)
    {
        super(EntityTypesAether.POISON_DART, worldIn);
    }

	public EntityPoisonDart(World worldIn, double x, double y, double z)
	{
		super(EntityTypesAether.POISON_DART, worldIn, x, y, z);
	}

    public EntityPoisonDart(LivingEntity entity, World world)
    {
		super(EntityTypesAether.POISON_DART, entity, world);
	}

    public EntityPoisonDart(EntityType<? extends EntityDart> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

	public EntityPoisonDart(EntityType<? extends EntityDart> entityTypeIn, World worldIn, double x, double y, double z)
	{
		super(entityTypeIn, worldIn, x, y, z);
	}

    public EntityPoisonDart(EntityType<? extends EntityDart> entityTypeIn, LivingEntity entity, World world)
    {
		super(entityTypeIn, entity, world);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.setDamage(0.0D);
	}

	@Override
    public void update()
    {
        super.update();

        if (this.victim != null)
        {
        	if (this.victim.invalid || this.poison.poisonTime == 0)
        	{
        		this.invalidate();

        		return;
        	}

        	if (this.shootingEntity != null)
        	{
                if (this.world instanceof ServerWorld)
                {
                	((ServerWorld)this.world).method_8406(new ItemStackParticle(ParticleTypes.ITEM, new ItemStack(Items.RED_DYE)), this.victim.posX, this.victim.getBoundingBox().minY + this.victim.height * 0.8D, this.victim.posZ, 2, 0.0D, 0.0D, 0.0D, 0.0625D);
                }
        	}

        	this.invalid = false;
        	this.poison.tick();
        	this.setInvisible(true);
        	this.setPosition(this.victim.posX, this.victim.posY, this.victim.posZ);
        }
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity entityIn)
    {
    	if (this.victim == null)
    	{
    		super.onCollideWithPlayer(entityIn);
    	}
    }

    @Override
    protected void onHit(LivingEntity living)
    {
    	super.onHit(living);

    	this.victim = living;
    	this.poison = new AetherPoisonMovement(this.victim);

    	if (living instanceof ServerPlayerEntity)
    	{
    		ServerPlayerEntity ent = (ServerPlayerEntity) living;

            if (!this.world.isRemote)
            {
            	((IEntityPlayerAether)ent).getPlayerAether().applyPoison(500);

            	PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());

            	buffer.writeInt(500);

            	ent.networkHandler.sendPacket(new CustomPayloadServerPacket(Aether.locate("apply_poison"), buffer));
            }
    	}
    	else
    	{
        	this.poison.afflictPoison(500);
    	}

    	this.invalid = false;
    }

    @Override
    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
    	if (this.victim != null)
    	{
    		return null;
    	}

    	return super.findEntityOnPath(start, end);
    }

	@Override
	protected ItemStack asItemStack()
	{
		return new ItemStack(ItemsAether.poison_dart);
	}

}