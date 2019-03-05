package com.legacy.aether.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Int2ObjectBiMap;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.accessories.AccessoryType;
import com.legacy.aether.api.accessories.AetherAccessory;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.api.player.util.AccessoryInventory;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.item.accessory.ItemAccessory;

public class AccessoriesInventory implements AccessoryInventory
{

	private final DefaultedList<ItemStack> stackList = DefaultedList.create(8, ItemStack.EMPTY);

	private final List<InventoryListener> listeners = new ArrayList<InventoryListener>();

	private final Int2ObjectBiMap<AccessoryType> types = AccessoryType.createCompleteList();

	private final Int2ObjectBiMap<EntityAttributeModifier> modifiers = new Int2ObjectBiMap<EntityAttributeModifier>(8);

	private final IPlayerAether playerAether;

	public AccessoriesInventory(IPlayerAether playerAether)
	{
		this.playerAether = playerAether;
	}

	@Override
	public void addListener(InventoryListener listener)
	{
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(InventoryListener listener)
	{
		this.listeners.remove(listener);
	}

	@Override
	public void setInvStack(int slot, ItemStack stack)
	{
		if (stack.getItem() instanceof ItemAccessory)
		{
			EntityAttributeModifier modifier = new EntityAttributeModifier(this.types.get(slot).name().toLowerCase() + "_modifier", ((ItemAccessory)stack.getItem()).getDamageMultiplier(), Operation.MULTIPLY_BASE);

			this.playerAether.getPlayer().getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).addModifier(modifier);

			if (this.modifiers.get(slot) != null && this.playerAether.getPlayer().getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).hasModifier(this.modifiers.get(slot)))
			{
				this.playerAether.getPlayer().getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).removeModifier(this.modifiers.get(slot));
			}

			this.modifiers.put(modifier, slot);
		}

		this.stackList.set(slot, stack);
	}

	@Override
	public void damageAccessory(int damage, AccessoryType type)
	{
		ItemStack stack = this.getInvStack(type);

		if (!stack.isEmpty() && !this.playerAether.getPlayer().isCreative())
		{
			stack.applyDamage(damage, this.playerAether.getPlayer());
		}
	}

	@Override
	public void damageWornStack(int damage, ItemStack stack)
	{
		AetherAccessory accessory = AetherAPI.instance().getAccessory(stack);
		ItemStack currentStack = this.getInvStack(accessory.getAccessoryType());

		if (currentStack.isEmpty() && accessory.getExtraType() != null)
		{
			currentStack = this.getInvStack(accessory.getExtraType());
		}

		if (!currentStack.isEmpty() && !this.playerAether.getPlayer().isCreative())
		{
			currentStack.applyDamage(damage, this.playerAether.getPlayer());
		}
	}

	@Override
	public void setInvStack(AccessoryType type, ItemStack stack)
	{
		this.setInvStack(this.types.getId(type), stack);
	}

	@Override
	public ItemStack getInvStack(AccessoryType type)
	{
		return this.getInvStack(this.types.getId(type));
	}

	@Override
	public ItemStack removeInvStack(AccessoryType type)
	{
		return this.removeInvStack(this.types.getId(type));
	}

	@Override
	public boolean setAccessorySlot(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return false;
		}

		AetherAccessory accessory = AetherAPI.instance().getAccessory(stack);

		for (int index = 0; index < this.types.size(); ++index)
		{
			AccessoryType type = this.types.get(index);

			if (!this.getInvStack(index).isEmpty())
			{
				continue;
			}

			if (accessory.getAccessoryType() == type || accessory.getExtraType() == type)
			{
				this.setInvStack(index, stack);

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean wearingAccessory(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return false;
		}

		for (int index = 0; index < this.getInvSize(); index++)
		{
			if (this.getInvStack(index).getItem() == stack.getItem())
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean wearingArmor(ItemStack stack)
	{
		if (stack.isEmpty())
		{
			return false;
		}

		for (int index = 0; index < EquipmentSlot.values().length; index++)
		{
			EquipmentSlot slot = EquipmentSlot.values()[index];

			if (slot.getType() == EquipmentSlot.Type.ARMOR && this.playerAether.getPlayer().getEquippedStack(slot).getItem() == stack.getItem())
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isWearingZaniteSet()
	{
		return this.wearingArmor(new ItemStack(ItemsAether.zanite_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.zanite_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.zanite_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.zanite_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.zanite_gloves));
	}

	@Override
	public boolean isWearingGravititeSet()
	{
		return wearingArmor(new ItemStack(ItemsAether.gravitite_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.gravitite_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.gravitite_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.gravitite_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.gravitite_gloves));
	}

	@Override
	public boolean isWearingNeptuneSet()
	{
		return wearingArmor(new ItemStack(ItemsAether.neptune_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.neptune_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.neptune_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.neptune_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.neptune_gloves));
	}

	@Override
	public boolean isWearingPhoenixSet()
	{
		return wearingArmor(new ItemStack(ItemsAether.phoenix_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.phoenix_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.phoenix_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.phoenix_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.phoenix_gloves));
	}

	@Override
	public boolean isWearingValkyrieSet()
	{
		return wearingArmor(new ItemStack(ItemsAether.valkyrie_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.valkyrie_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.valkyrie_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.valkyrie_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.valkyrie_gloves));
	}

	@Override
	public boolean isWearingObsidianSet()
	{
		return wearingArmor(new ItemStack(ItemsAether.obsidian_helmet)) && this.wearingArmor(new ItemStack(ItemsAether.obsidian_chestplate)) && this.wearingArmor(new ItemStack(ItemsAether.obsidian_leggings)) && this.wearingArmor(new ItemStack(ItemsAether.obsidian_boots)) && this.wearingAccessory(new ItemStack(ItemsAether.obsidian_gloves));
	}

	@Override
	public int getAccessoryCount(ItemStack stack)
	{
		int count = 0;

		if (stack.isEmpty())
		{
			return count;
		}

		for (int index = 0; index < this.getInvSize(); index++)
		{
			if (this.getInvStack(index).getItem() == stack.getItem())
			{
				count++;
			}
		}

		return count;
	}

	@Override
	public void markDirty()
	{
		if (!this.listeners.isEmpty())
		{
			for(int id = 0; id < this.listeners.size(); ++id)
			{
				this.listeners.get(id).onInvChange(this);
			}
		}
	}

	@Override
	public DefaultedList<ItemStack> getInventory()
	{
		return this.stackList;
	}

}