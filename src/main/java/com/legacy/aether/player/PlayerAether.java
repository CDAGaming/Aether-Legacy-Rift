package com.legacy.aether.player;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.inventory.InventoryAccessories;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.perks.AetherDonationPerks;
import com.legacy.aether.util.AetherTeleportation;
import com.legacy.aether.world.TeleporterAether;

public class PlayerAether implements IPlayerAether
{

	private EntityPlayer player;

	private int shardsUsed;

	private boolean isJumping;

	public float prevPortalAnimTime, portalAnimTime;

	public int timeInPortal, portalCooldown;

	public boolean hasTeleported = false, inPortal = false;

	private AttributeModifier aetherHealth;

	private InventoryAccessories accessories;

	public AetherDonationPerks donationPerks;

	public PlayerAether(EntityPlayer player)
	{
		this.player = player;
		this.donationPerks = new AetherDonationPerks();
		this.accessories = new InventoryAccessories((IEntityPlayerAether) player);
	}

	public void tick()
	{
		if (this.getPlayer().world.isRemote)
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
			if (this.inPortal && this.portalCooldown <= 0)
			{
				int limit = this.getPlayer().getMaxInPortalTime();

				if (this.timeInPortal++ >= limit)
				{
					this.timeInPortal = limit;
					this.portalCooldown = this.getPlayer().getPortalCooldown();
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

                if (this.portalCooldown > 0)
                {
                    --this.portalCooldown;
                }
			}
		}
	}

	/*
	 * The teleporter which sends the player to the Aether/Overworld
	 */
	private void teleportPlayer(boolean shouldSpawnPortal) 
	{
		if (!this.player.world.isRemote)
		{
			MinecraftServer server = this.player.getServer();
			int dimensionToTravel = this.player.dimension == 12 ? 0 : 12;

			if (server != null && server.getPlayerList() != null)
			{
				AetherTeleportation.travelDimension((EntityPlayerMP) this.player, dimensionToTravel, new TeleporterAether(true, server.getWorld(dimensionToTravel)));
			}
		}
	}

	/*
	 * Checks how many of the specific item the player is wearing (If any)
	 */
	public int getAccessoryCount(Item item)
	{
		int count = 0;

		for (int index = 0; index < 8; index++)
		{
			if (this.getAccessories().getStackInSlot(index).getItem() == item)
			{
				count++;
			}
		}

		return count;
	}

	/*
	 * Checks if the player is wearing the specified item as an accessory
	 */
	public boolean wearingAccessory(Item item)
	{
		for (int index = 0; index < 8; index++)
		{
			if (this.getAccessories().getStackInSlot(index).getItem() == item)
			{
				return true;
			}
		}

		return false;
	}

	/*
	 * Checks if the player is wearing the specified item as armor
	 */
	public boolean wearingArmor(Item item)
	{
		for (int index = 0; index < 4; index++)
		{
			if (this.getPlayer().inventory.armorInventory.get(index).getItem() == item)
			{
				return true;
			}
		}

		return false;
	}

	/*
	 * Checks of the player is wearing a full set of Zanite
	 */
	public boolean isWearingZaniteSet()
	{
		return wearingArmor(ItemsAether.zanite_helmet) && wearingArmor(ItemsAether.zanite_chestplate) && wearingArmor(ItemsAether.zanite_leggings) && wearingArmor(ItemsAether.zanite_boots) && wearingAccessory(ItemsAether.zanite_gloves);
	}

	/*
	 * Checks of the player is wearing a full set of Gravitite
	 */
	public boolean isWearingGravititeSet()
	{
		return wearingArmor(ItemsAether.gravitite_helmet) && wearingArmor(ItemsAether.gravitite_chestplate) && wearingArmor(ItemsAether.gravitite_leggings) && wearingArmor(ItemsAether.gravitite_boots) && wearingAccessory(ItemsAether.gravitite_gloves);
	}

	/*
	 * Checks of the player is wearing a full set of Neptune
	 */
	public boolean isWearingNeptuneSet()
	{
		return wearingArmor(ItemsAether.neptune_helmet) && wearingArmor(ItemsAether.neptune_chestplate) && wearingArmor(ItemsAether.neptune_leggings) && wearingArmor(ItemsAether.neptune_boots) && wearingAccessory(ItemsAether.neptune_gloves);
	}

	/*
	 * Checks of the player is wearing a full set of Phoenix
	 */
	public boolean isWearingPhoenixSet()
	{
		return wearingArmor(ItemsAether.phoenix_helmet) && wearingArmor(ItemsAether.phoenix_chestplate) && wearingArmor(ItemsAether.phoenix_leggings) && wearingArmor(ItemsAether.phoenix_boots) && wearingAccessory(ItemsAether.phoenix_gloves);
	}

	/*
	 * Checks of the player is wearing a full set of Valkyrie
	 */
	public boolean isWearingValkyrieSet()
	{
		return wearingArmor(ItemsAether.valkyrie_helmet) && wearingArmor(ItemsAether.valkyrie_chestplate) && wearingArmor(ItemsAether.valkyrie_leggings) && wearingArmor(ItemsAether.valkyrie_boots) && wearingAccessory(ItemsAether.valkyrie_gloves);
	}

	/*
	 * Checks of the player is wearing a full set of Obsidian
	 */
	public boolean isWearingObsidianSet()
	{
		return wearingArmor(ItemsAether.obsidian_helmet) && wearingArmor(ItemsAether.obsidian_chestplate) && wearingArmor(ItemsAether.obsidian_leggings) && wearingArmor(ItemsAether.obsidian_boots) && wearingAccessory(ItemsAether.obsidian_gloves);
	}

	public void increaseMaxHP()
	{
		UUID uuid = UUID.fromString("df6eabe7-6947-4a56-9099-002f90370706");

		++this.shardsUsed;

		this.aetherHealth = new AttributeModifier(uuid, "Aether Health Modifier", (this.shardsUsed * 2.0F), 0);

		if (this.getPlayer().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(uuid) != null)
		{
			this.getPlayer().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(this.aetherHealth);
		}

		this.getPlayer().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(this.aetherHealth);
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("shardsUsed", this.shardsUsed);

		compound.setTag("accessories", ItemStackHelper.saveAllItems(new NBTTagCompound(), this.accessories.stacks));
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		this.shardsUsed = compound.getInteger("shardsUsed");

		ItemStackHelper.loadAllItems(compound.getCompoundTag("accessories"), this.accessories.stacks);
	}

	public void copyFrom(PlayerAether that, boolean keepEverything)
	{
		NBTTagCompound compound = new NBTTagCompound();

		that.writeToNBT(compound);
		this.readFromNBT(compound);

		this.portalCooldown = that.portalCooldown;
	}

	public void damageAccessories(float damage)
	{
		for (int i = 0; i < this.accessories.getSizeInventory(); ++i)
		{
			ItemStack stack = this.accessories.stacks.get(i);

			if (!stack.isEmpty())
			{
				stack.damageItem((int) damage, this.getPlayer());
			}
		}
	}

	public void applyCure(int cureAmount)
	{
		
	}

	public void applyPoison(int poisonAmount)
	{
		
	}

	public void setInPortal()
	{
		this.inPortal = true;
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

	public InventoryAccessories getAccessories()
	{
		return this.accessories;
	}

	public EntityPlayer getPlayer()
	{
		return this.player;
	}

	@Override
	public Entity getEntity() 
	{
		return this.player;
	}

}