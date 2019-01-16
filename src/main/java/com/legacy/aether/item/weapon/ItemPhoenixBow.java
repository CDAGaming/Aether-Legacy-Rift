package com.legacy.aether.item.weapon;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.ItemsAether;

public class ItemPhoenixBow extends BowItem
{

	public ItemPhoenixBow()
	{
		super(new Item.Settings().durability(384).itemGroup(AetherItemGroup.AETHER_WEAPONS));

		this.addProperty(new Identifier("pull"), (itemStack_1, world_1, livingEntity_1) -> {
			if (livingEntity_1 == null)
			{
				return 0.0F;
			} 
			else
			{
				return livingEntity_1.getActiveItem().getItem() != ItemsAether.phoenix_bow ? 0.0F : (float) (itemStack_1.getMaxUseTime() - livingEntity_1 .method_6014()) / 20.0F;
			}});
	}

}