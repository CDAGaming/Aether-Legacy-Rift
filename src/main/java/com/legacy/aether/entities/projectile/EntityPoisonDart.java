package com.legacy.aether.entities.projectile;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.legacy.aether.Aether;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.movement.AetherPoisonMovement;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.IEntityPlayerAether;

public class EntityPoisonDart extends EntityDart
{

	public EntityLivingBase victim;

	public AetherPoisonMovement poison;

    public EntityPoisonDart(World worldIn)
    {
        super(EntityTypesAether.POISON_DART, worldIn);
    }

	public EntityPoisonDart(World worldIn, double x, double y, double z)
	{
		super(EntityTypesAether.POISON_DART, worldIn, x, y, z);
	}

    public EntityPoisonDart(EntityLivingBase entity, World world) 
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

    public EntityPoisonDart(EntityType<? extends EntityDart> entityTypeIn, EntityLivingBase entity, World world) 
    {
		super(entityTypeIn, entity, world);
	}

	@Override
	protected void registerData()
	{
		super.registerData();

		this.setDamage(0.0D);
	}

	@Override
    public void tick()
    {
        super.tick();

        if (this.victim != null)
        {
        	if (this.victim.removed || this.poison.poisonTime == 0)
        	{
        		this.remove();

        		return;
        	}

        	if (this.shootingEntity != null)
        	{
                if (this.world instanceof WorldServer)
                {
                	((WorldServer)this.world).spawnParticle(new ItemParticleData(Particles.ITEM, new ItemStack(Items.ROSE_RED)), this.victim.posX, this.victim.getBoundingBox().minY + this.victim.height * 0.8D, this.victim.posZ, 2, 0.0D, 0.0D, 0.0D, 0.0625D);
                }
        	}

        	this.removed = false;
        	this.poison.tick();
        	this.setInvisible(true);
        	this.setPosition(this.victim.posX, this.victim.posY, this.victim.posZ);
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
    	if (this.victim == null)
    	{
    		super.onCollideWithPlayer(entityIn);
    	}
    }

    @Override
    protected void arrowHit(EntityLivingBase living)
    {
    	super.arrowHit(living);

    	this.victim = living;
    	this.poison = new AetherPoisonMovement(this.victim);

    	if (living instanceof EntityPlayerMP)
    	{
    		EntityPlayerMP ent = (EntityPlayerMP)living;

            if (!this.world.isRemote)
            {
            	((IEntityPlayerAether)ent).getPlayerAether().applyPoison(500);

            	PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());

            	buffer.writeInt(500);

            	ent.connection.sendPacket(new SPacketCustomPayload(Aether.locate("apply_poison"), buffer));
            }
    	}
    	else
    	{
        	this.poison.afflictPoison(500);
    	}

    	this.removed = false;
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
	protected ItemStack getArrowStack() 
	{
		return new ItemStack(ItemsAether.poison_dart);
	}

}