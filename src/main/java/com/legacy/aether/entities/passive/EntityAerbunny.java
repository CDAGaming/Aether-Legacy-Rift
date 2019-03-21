package com.legacy.aether.entities.passive;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;
import com.legacy.aether.world.storage.loot.AetherLootTableList;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1394;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAerbunny extends EntityAetherAnimal
{

	public static final TrackedData<Byte> PUFF = DataTracker.registerData(EntityAerbunny.class, TrackedDataHandlerRegistry.BYTE);

	public int puffiness;

	private int jumpTicks;

	private int jumps;

	public EntityAerbunny(World world)
	{
		super(EntityTypesAether.AERBUNNY, world);

		this.ignoreCameraFrustum = true;
	}

	@Override
	protected void initGoals()
	{
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new class_1394(this, 2D, 6));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(ItemsAether.blueberry), false));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
		this.goalSelector.add(5, new LookAroundGoal(this));
		//this.goalSelector.add(6, new EntityAIBunnyHop(this));
	}

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(5.0D);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.dataTracker.startTracking(PUFF, (byte) 0);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean shouldRenderAtDistance(double par1)
	{
		return true;
	}

	@Override
	public double getMountedHeightOffset()
	{
		return 0.4D;
	}

	@Override
	public void doJump(boolean jump)
	{
		if (jump)
		{
			this.method_5990();
			this.setPuffiness(11);
			--this.jumps;
		}

		super.doJump(jump);
	}

	@Override
	public void method_5990()
	{
		if (this.world.isClient)
		{
			for (int i = 0; i < 5; ++i)
			{
				double double_1 = this.random.nextGaussian() * 0.02D;
				double double_2 = this.random.nextGaussian() * 0.02D;
				double double_3 = this.random.nextGaussian() * 0.02D;

				this.world.addParticle(ParticleTypes.POOF, this.x + (double)(this.random.nextFloat() * this.getWidth() * 2.0F) - (double)this.getWidth() - double_1 * 10.0D, this.y + (double)(this.random.nextFloat() * this.getHeight()) - double_2 * 10.0D, this.z + (double)(this.random.nextFloat() * this.getWidth() * 2.0F) - (double)this.getWidth() - double_3 * 10.0D, double_1, double_2, double_3);
			}
		}
		else
		{
			this.world.summonParticle(this, (byte) 20);
		}
	}

	//@Override public boolean canRiderInteract() { return true; }
	
	public int getPuffiness()
	{
		return (int) this.dataTracker.get(PUFF);
	}

	public void setPuffiness(int i)
	{
		this.dataTracker.set(PUFF, (byte) i);
	}

	@Override
	public void update()
	{
		super.update();

		this.setPuffiness(this.getPuffiness() - 1);

		if (this.getPuffiness() < 0)
		{
			this.setPuffiness(0);
		}
	}

	@Override
	public void updateMovement()
	{
		if (!this.onGround)
		{
			this.jumps = 1;
			this.jumpTicks = 2;
		}
		else if (this.jumpTicks > 0)
		{
			--this.jumpTicks;
		}

		if (this.jumpTicks <= 0 && this.jumps > 0)
		{
			this.doJump(true);
			this.jumps = 1;
			this.jumpTicks = 2;
			this.setVelocity(new Vec3d(this.getVelocity().x, 0.42D, this.getVelocity().z));
		}

		if (this.getVelocity().y < -0.1D)
		{
			this.setVelocity(new Vec3d(this.getVelocity().x, -0.1D, this.getVelocity().z));
		}

		if (this.getRiddenEntity() != null && this.getRiddenEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) this.getRiddenEntity();

			this.getNavigation().stop();

			this.setRotation(player.yaw, player.pitch);

			player.fallDistance = 0.0F;

			if (!player.onGround && !player.isFallFlying())
			{
				if (!player.abilities.flying)
				{
					this.setVelocity(this.getVelocity().add(0.0D, 0.05D, 0.0D));
				}

				player.fallDistance = 0.0F;

				if (player.getVelocity().y < -0.22499999403953552D)
				{
					if (AetherAPI.get(player).isJumping())
					{
						this.setVelocity(new Vec3d(this.getVelocity().x, 0.125D, this.getVelocity().z));

						this.setPuffiness(11);
						this.method_5990();
					}
				}
			}
		}

		super.updateMovement();
	}

	@Override
	public void handleFallDamage(float distance, float damageMultiplier)
	{

	}

	@Override
	public boolean isInsideWall()
	{
		return false;
	}

	@Override
	public boolean canClimb()
	{
		return false;
	}

	@Override
    public boolean interactMob(PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		if (!stack.isEmpty())
		{
			return super.interactMob(player, hand);
		}
		else
		{
			this.world.playSound(this.x, this.y, this.z, SoundsAether.aerbunny_lift, SoundCategory.NEUTRAL, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F, false);

			if (this.getRiddenEntity() != null)
			{
				this.stopRiding();
			}
			else
			{
				this.startRiding(player);
			}

			return true;
		}
	}

	@Override
	public boolean damage(DamageSource source, float damage)
	{
		return this.getRiddenEntity() != null && source.getAttacker() == this.getRiddenEntity() ? false : super.damage(source, damage);
	}

	@Override
	protected boolean method_5658()
	{
		return this.onGround;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundsAether.aerbunny_hurt;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.aerbunny_death;
	}

	@Override
	public PassiveEntity createChild(PassiveEntity entityageable)
	{
		return new EntityAerbunny(this.world);
	}

	@Override
	public Identifier getLootTableId()
	{
		return AetherLootTableList.ENTITIES_AERBUNNY;
	}

}