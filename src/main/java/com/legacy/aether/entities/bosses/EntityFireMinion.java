package com.legacy.aether.entities.bosses;

import com.legacy.aether.entities.EntityTypesAether;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.world.World;

public class EntityFireMinion extends EntityMob
{

	public EntityFireMinion(World world)
	{
		super(EntityTypesAether.FIRE_MINION, world);

		this.isImmuneToFire = true;

		this.setSize(1.1F, 1.8F);
	}

	@Override
    protected void initEntityAI()
    {
		super.initEntityAI();

		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.5D, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
	}

	@Override
	public void tick()
	{
		super.tick();

		for (int i = 0; i < 2; i++)
		{
			double d = this.rand.nextFloat() - 0.5F;
			double d1 = this.rand.nextFloat();
			double d2 = this.rand.nextFloat() - 0.5F;
			double d3 = this.posX + d * d1;
			double d4 = (this.getBoundingBox().minY + d1) + 0.1D;
			double d5 = this.posZ + d2 * d1;

			this.world.addParticle(Particles.SPLASH, d3, d4, d5, 0.0D, -0.075000002980232239D, 0.0D);
		}
	}

}