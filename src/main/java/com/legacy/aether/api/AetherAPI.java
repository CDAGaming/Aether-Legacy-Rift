package com.legacy.aether.api;

import java.util.*;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import com.legacy.aether.api.accessories.Accessory;
import com.legacy.aether.api.enchantment.Enchantment;
import com.legacy.aether.api.enchantment.EnchantmentFuel;
import com.legacy.aether.api.freezable.Freezable;
import com.legacy.aether.api.freezable.FreezableFuel;
import com.legacy.aether.api.moa.MoaType;
import net.minecraft.util.registry.Registry;

public class AetherAPI
{

	private static final HashMap<Identifier, Accessory> ACCESSORY_REGISTRY = new HashMap<>();
	//new ResourceLocation("empty")

	private static final HashMap<Identifier, Enchantment> ENCHANTMENT_REGISTRY = new HashMap<>();

	private static final HashMap<Identifier, Freezable> FREEZABLE_REGISTRY = new HashMap<>();

	private static final HashMap<Identifier, EnchantmentFuel> ENCHANTMENT_FUEL_REGISTRY = new HashMap<>();

	private static final HashMap<Identifier, FreezableFuel> FREEZABLE_FUEL_REGISTRY = new HashMap<>();

	private static final HashMap<Identifier, MoaType> MOA_REGISTRY = new HashMap<>();

	private static int moaListSize;

	private static final AetherAPI INSTANCE = new AetherAPI();

	public AetherAPI()
	{

	}

	public void register(Identifier registryName, MoaType moa)
	{
		moa.setRegistryName(registryName);

		MOA_REGISTRY.put(registryName, moa);
		++moaListSize;
	}

	public void register(Accessory accessory)
	{
		ACCESSORY_REGISTRY.put(Registry.ITEM.getKey(accessory.getItem()), accessory);
	}

	public void register(Freezable freezable)
	{
		FREEZABLE_REGISTRY.put(Registry.ITEM.getKey(freezable.getInput()), freezable);
	}

	public void register(FreezableFuel fuel)
	{
		FREEZABLE_FUEL_REGISTRY.put(Registry.ITEM.getKey(fuel.getFuel()), fuel);
	}

	public void register(Enchantment enchantment)
	{
		ENCHANTMENT_REGISTRY.put(Registry.ITEM.getKey(enchantment.getInput()), enchantment);
	}

	public void register(EnchantmentFuel fuel)
	{
		ENCHANTMENT_FUEL_REGISTRY.put(Registry.ITEM.getKey(fuel.getFuel()), fuel);
	}

	public Accessory getAccessory(ItemStack stack)
	{
		return ACCESSORY_REGISTRY.get(Registry.ITEM.getKey(stack.getItem()));
	}

	public boolean isAccessory(ItemStack stack)
	{
		return ACCESSORY_REGISTRY.containsKey(Registry.ITEM.getKey(stack.getItem()));
	}

	public Freezable getFreezable(ItemStack stack)
	{
		return FREEZABLE_REGISTRY.get(Registry.ITEM.getKey(stack.getItem()));
	}

	public boolean isFreezable(ItemStack stack)
	{
		return FREEZABLE_REGISTRY.containsKey(Registry.ITEM.getKey(stack.getItem()));
	}

	public FreezableFuel getFreezableFuel(ItemStack stack) 
	{
		return FREEZABLE_FUEL_REGISTRY.get(Registry.ITEM.getKey(stack.getItem()));
	}

	public boolean isFreezerFuel(ItemStack stack)
	{
		return FREEZABLE_FUEL_REGISTRY.containsKey(Registry.ITEM.getKey(stack.getItem()));
	}

	public Enchantment getEnchantment(ItemStack stack)
	{
		return ENCHANTMENT_REGISTRY.get(Registry.ITEM.getKey(stack.getItem()));
	}

	public boolean isEnchantable(ItemStack stack)
	{
		return ENCHANTMENT_REGISTRY.containsKey(Registry.ITEM.getKey(stack.getItem()));
	}

	public EnchantmentFuel getEnchantmentFuel(ItemStack stack) 
	{
		return ENCHANTMENT_FUEL_REGISTRY.get(Registry.ITEM.getKey(stack.getItem()));
	}

	public boolean isEnchantmentFuel(ItemStack stack)
	{
		return ENCHANTMENT_FUEL_REGISTRY.containsKey(Registry.ITEM.getKey(stack.getItem()));
	}

	public MoaType getMoa()
	{
		Random random = new Random();
		MoaType[] rescValues = MOA_REGISTRY.values().toArray(new MoaType[0]);

		return rescValues[random.nextInt(rescValues.length)];
	}

	public MoaType getMoa(Identifier registryName)
	{
		return MOA_REGISTRY.get(registryName);
	}

	public MoaType getMoa(int id)
	{
		MoaType[] rescValues = MOA_REGISTRY.values().toArray(new MoaType[0]);

		return rescValues[id];
	}

	public int getMoaId(MoaType moa)
	{
		boolean isMatch = false;
		int indexNumber = -1;

		for (MoaType moaType : MOA_REGISTRY.values()) {
			indexNumber++;
			if (moa.equals(moaType)) {
				isMatch = true;
				break;
			}
		}
		return isMatch ? indexNumber : 0;
	}

	public int getMoaRegistrySize()
	{
		return moaListSize;
	}

	public static AetherAPI instance()
	{
		return INSTANCE;
	}

}