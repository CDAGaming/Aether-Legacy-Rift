package com.legacy.aether.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.dimension.DimensionType;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.api.player.util.AccessoryInventory;
import com.legacy.aether.api.player.util.AetherAbility;
import com.legacy.aether.api.player.util.PlayerReach;
import com.legacy.aether.entities.util.AetherPoisonMovement;
import com.legacy.aether.inventory.AccessoriesInventory;
import com.legacy.aether.item.tool.IAetherTool;
import com.legacy.aether.item.util.AetherTier;
import com.legacy.aether.player.abilities.FloatAbility;
import com.legacy.aether.player.abilities.JumpBoostAbility;
import com.legacy.aether.player.abilities.StepHeightAbility;
import com.legacy.aether.player.perks.AetherDonationPerks;
import com.legacy.aether.util.AetherTeleportation;
import com.legacy.aether.world.TeleporterAether;
import com.legacy.aether.world.WorldAether;

public class PlayerAether implements IPlayerAether
{

	private PlayerEntity player;

	private int shardsUsed;

	private boolean isJumping;

	public float prevPortalAnimTime, portalAnimTime;

	public int timeInPortal;

	public boolean hasTeleported = false, inPortal = false;

	public final ArrayList<AetherAbility> abilities = new ArrayList<AetherAbility>();

	public final ArrayList<Entity> clouds = new ArrayList<Entity>(2);

	private EntityAttributeModifier aetherHealth;

	private final AccessoryInventory accessories;

	private AetherPoisonMovement poisonMovement;

	public AetherDonationPerks donationPerks;

	private boolean hadReachTool;

	public PlayerAether(PlayerEntity player)
	{
		this.player = player;
		this.donationPerks = new AetherDonationPerks();
		this.poisonMovement = new AetherPoisonMovement(player);
		this.accessories = new AccessoriesInventory(this);

		this.abilities.addAll(Arrays.asList(new FloatAbility(this), new JumpBoostAbility(this), new StepHeightAbility(this)));
	}

	public void tick()
	{
		for (int i = 0; i < this.abilities.size(); ++i)
		{
			AetherAbility ability = this.abilities.get(i);

			if (ability.shouldExecute())
			{
				ability.update();
			}
		}

		for (int i = 0; i < this.clouds.size(); ++i) 
		{
			Entity entity = this.clouds.get(i);

			if (entity.invalid)
			{
				this.clouds.remove(i);
			}
		}

		this.updateReach();
		//this.poisonMovement.tick();

		if (this.getPlayer().dimension == WorldAether.THE_AETHER && this.getPlayer().y <= -10.0F)
		{
			this.teleportPlayer(false);
		}

		if (this.getPlayer().world.isClient)
		{
			this.prevPortalAnimTime = this.portalAnimTime;

			if (this.inPortal)
			{
				this.portalAnimTime += 0.0125F;
				this.inPortal = false;
			}
			else
			{
				if (this.portalAnimTime > 0.0F)
				{
					this.portalAnimTime -= 0.05F;
				}

				if (this.portalAnimTime < 0.0F)
				{
					this.portalAnimTime = 0.0F;
				}
			}
		}
		else
		{
			if (this.inPortal && this.player.portalCooldown <= 0)
			{
				int limit = 80;

				if (this.timeInPortal++ >= limit)
				{
					this.timeInPortal = limit;
					this.player.portalCooldown = this.player.getDefaultPortalCooldown();
					this.teleportPlayer(true);
				}

				this.inPortal = false;
			}
			else
			{
                if (this.timeInPortal > 0)
                {
                    this.timeInPortal -= 4;
                }

                if (this.timeInPortal < 0)
                {
                    this.timeInPortal = 0;
                }

                if (this.player.portalCooldown > 0)
                {
                    --this.player.portalCooldown;
                }
			}
		}
	}

	public boolean disableFallDamage()
	{
		boolean check = false;

		for (int i = 0; i < this.abilities.size(); ++i)
		{
			if (check)
			{
				break;
			}

			AetherAbility ability = this.abilities.get(i);

			check = ability.shouldExecute() && ability.disableFallDamage();
		}

		return check;
	}

	private void updateReach()
	{
		ItemStack mainHeldItem = this.getPlayer().getStackInHand(Hand.MAIN);
		ItemStack offHeldItem = this.getPlayer().getStackInHand(Hand.OFF);
		boolean isMainReachTool = mainHeldItem.getItem() instanceof IAetherTool && ((IAetherTool)mainHeldItem.getItem()).getMaterial() == AetherTier.Valkyrie;
		boolean isOffReachTool = offHeldItem.getItem() instanceof IAetherTool && ((IAetherTool)offHeldItem.getItem()).getMaterial() == AetherTier.Valkyrie;

		if (isMainReachTool || isOffReachTool)
		{
			if (this.getPlayer().world.isClient)
			{
				((PlayerReach)net.minecraft.client.MinecraftClient.getInstance().interactionManager).setReachDistance(10.0F, 10.0F);
			}
			else
			{
				((PlayerReach)((net.minecraft.server.network.ServerPlayerEntity)this.getPlayer()).interactionManager).setReachDistance(10.0F, 10.0F);
			}

			this.hadReachTool = true;
		}
		else if (!isMainReachTool && !isOffReachTool && this.hadReachTool)
		{
			if (this.getPlayer().world.isClient)
			{
				((PlayerReach)net.minecraft.client.MinecraftClient.getInstance().interactionManager).setReachDistance(4.5F, 5.0F);
			}
			else
			{
				((PlayerReach)((net.minecraft.server.network.ServerPlayerEntity)this.getPlayer()).interactionManager).setReachDistance(4.5F, 5.0F);
			}

			this.hadReachTool = false;
		}
	}

	/*
	 * The teleporter which sends the player to the Aether/Overworld
	 */
	private void teleportPlayer(boolean shouldSpawnPortal) 
	{
		if (!this.player.world.isClient)
		{
			MinecraftServer server = this.player.getServer();
			DimensionType dimensionToTravel = this.player.dimension == WorldAether.THE_AETHER ? DimensionType.OVERWORLD : WorldAether.THE_AETHER;

			if (server != null)
			{
				AetherTeleportation.instance().teleportPlayer((ServerPlayerEntity) this.player, dimensionToTravel, new TeleporterAether(shouldSpawnPortal, server.getWorld(dimensionToTravel)));
			}
		}
	}

	@Override
	public void increaseHealth(int amount)
	{
		UUID uuid = UUID.fromString("df6eabe7-6947-4a56-9099-002f90370706");

		this.shardsUsed += amount;

		this.aetherHealth = new EntityAttributeModifier(uuid, "Aether Health Modifier", (this.shardsUsed * 2.0), EntityAttributeModifier.Operation.ADDITION);

		if (this.getPlayer().getAttributeInstance(EntityAttributes.MAX_HEALTH).getModifier(uuid) != null)
		{
			this.getPlayer().getAttributeInstance(EntityAttributes.MAX_HEALTH).removeModifier(this.aetherHealth);
		}

		this.getPlayer().getAttributeInstance(EntityAttributes.MAX_HEALTH).addModifier(this.aetherHealth);
	}

	public void writeToNBT(CompoundTag compound)
	{
		compound.putInt("shardsUsed", this.shardsUsed);

		compound.put("accessories", this.accessories.serialize(new CompoundTag()));
	}

	public void readFromNBT(CompoundTag compound)
	{
		this.shardsUsed = compound.getInt("shardsUsed");

		this.accessories.deserialize(compound.getCompound("accessories"));
	}

	public void copyFrom(PlayerAether that, boolean keepEverything)
	{
		CompoundTag compound = new CompoundTag();

		that.writeToNBT(compound);
		this.readFromNBT(compound);
	}

	public void damageAccessories(float damage)
	{
		/*
		for (int i = 0; i < this.accessories.getInvSize(); ++i)
		{
			ItemStack stack = this.accessories.stacks.get(i);

			if (!stack.isEmpty())
			{
				stack.applyDamage((int) damage, this.getPlayer());
			}
		}*/
	}

	public void inflictCure(int ticks)
	{
		this.poisonMovement.applyCure(ticks);
	}

	public void inflictPoison(int ticks)
	{
		this.poisonMovement.inflictPoison(ticks);
	}

	@Override
	public void setInPortal()
	{
		if (this.player.portalCooldown > 0)
		{
			this.player.portalCooldown = this.player.getDefaultPortalCooldown();
		}
		else
		{
			this.inPortal = true;
		}
	}

	public void setJumping(boolean isJumping)
	{
		this.isJumping = isJumping;
	}

	public boolean isJumping()
	{
		return this.isJumping;
	}

	public int getShardsUsed()
	{
		return this.shardsUsed;
	}

	@Override
	public AccessoryInventory getAccessoryInventory()
	{
		return this.accessories;
	}

	@Override
	public PlayerEntity getPlayer()
	{
		return this.player;
	}

}