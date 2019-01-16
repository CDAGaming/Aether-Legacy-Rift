package com.legacy.aether.container;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.packet.GuiSlotUpdateClientPacket;
import net.minecraft.container.ContainerType;
import net.minecraft.container.CraftingContainer;
import net.minecraft.container.CraftingResultSlot;
import net.minecraft.container.CraftingTableContainer;
import net.minecraft.container.Slot;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.TypedRecipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class CopiedPlayerContainer extends CraftingContainer<CraftingInventory> {

	private static final String[] field_7829 = new String[] { "item/empty_armor_slot_boots", "item/empty_armor_slot_leggings", "item/empty_armor_slot_chestplate", "item/empty_armor_slot_helmet" };
	private static final EquipmentSlot[] EQUIPMENT_SLOT_ORDER;
	private final CraftingInventory invCrafting = new CraftingInventory(this, 2, 2);
	private final CraftingResultInventory invCraftingResult = new CraftingResultInventory();
	public final boolean local;
	private final PlayerEntity owner;

	public CopiedPlayerContainer(int syncId, PlayerInventory playerInventory_1, boolean boolean_1, PlayerEntity playerEntity_1)
	{
		super((ContainerType<?>) null, syncId);

		this.local = boolean_1;
		this.owner = playerEntity_1;
		this.addSlot(new CraftingResultSlot(playerInventory_1.player, this.invCrafting, this.invCraftingResult, 0, 154, 28));

		int int_6;
		int int_5;

		for (int_6 = 0; int_6 < 2; ++int_6)
		{
			for (int_5 = 0; int_5 < 2; ++int_5)
			{
				this.addSlot(new Slot(this.invCrafting, int_5 + int_6 * 2, 98 + int_5 * 18, 18 + int_6 * 18));
			}
		}

		for (int_6 = 0; int_6 < 4; ++int_6)
		{
			final EquipmentSlot equipmentSlot_1 = EQUIPMENT_SLOT_ORDER[int_6];

			this.addSlot(new Slot(playerInventory_1, 39 - int_6, 8, 8 + int_6 * 18) {
				public int getMaxStackAmount() {
					return 1;
				}

				public boolean canInsert(ItemStack itemStack_1)
				{
					return equipmentSlot_1 == MobEntity.getPreferredEquipmentSlot(itemStack_1);
				}

				public boolean canTakeItems(PlayerEntity playerEntity_1)
				{
					ItemStack itemStack_1 = this.getStack();
					return !itemStack_1.isEmpty() && !playerEntity_1.isCreative() && EnchantmentHelper.hasBindingCurse(itemStack_1) ? false
							: super.canTakeItems(playerEntity_1);
				}

				@Environment(EnvType.CLIENT)
				public String getBackgroundSprite() {
					return CopiedPlayerContainer.field_7829[equipmentSlot_1
							.getEntitySlotId()];
				}
			});
		}

		for (int_6 = 0; int_6 < 3; ++int_6) {
			for (int_5 = 0; int_5 < 9; ++int_5) {
				this.addSlot(new Slot(playerInventory_1, int_5 + (int_6 + 1)
						* 9, 8 + int_5 * 18, 84 + int_6 * 18));
			}
		}

		for (int_6 = 0; int_6 < 9; ++int_6) {
			this.addSlot(new Slot(playerInventory_1, int_6, 8 + int_6 * 18, 142));
		}

		this.addSlot(new Slot(playerInventory_1, 40, 77, 62) {
			@Environment(EnvType.CLIENT)
			public String getBackgroundSprite() {
				return "item/empty_armor_slot_shield";
			}
		});
	}

	public void populateRecipeFinder(RecipeFinder recipeFinder_1) {
		this.invCrafting.provideRecipeInputs(recipeFinder_1);
	}

	public void clearCraftingSlots() {
		this.invCraftingResult.clearInv();
		this.invCrafting.clearInv();
	}

	public boolean matches(Recipe<? super CraftingInventory> recipe_1) {
		return recipe_1.matches(this.invCrafting, this.owner.world);
	}

	public void onContentChanged(Inventory inventory_1) {
		method_17399(this.syncId, this.owner.world,
				this.owner, this.invCrafting, this.invCraftingResult);
	}

	protected static void method_17399(int int_1, World world_1,
			PlayerEntity playerEntity_1, CraftingInventory craftingInventory_1,
			CraftingResultInventory craftingResultInventory_1) {
		if (!world_1.isClient) {
			ServerPlayerEntity serverPlayerEntity_1 = (ServerPlayerEntity) playerEntity_1;
			ItemStack itemStack_1 = ItemStack.EMPTY;
			Optional<TypedRecipe> optional_1 = world_1.getServer()
					.getRecipeManager()
					.get(RecipeType.CRAFTING, craftingInventory_1, world_1);
			if (optional_1.isPresent()) {
				TypedRecipe typedRecipe_1 = (TypedRecipe) optional_1.get();
				if (craftingResultInventory_1.shouldCraftRecipe(world_1,
						serverPlayerEntity_1, typedRecipe_1)) {
					itemStack_1 = typedRecipe_1.craft(craftingInventory_1);
				}
			}

			craftingResultInventory_1.setInvStack(0, itemStack_1);
			serverPlayerEntity_1.networkHandler
					.sendPacket(new GuiSlotUpdateClientPacket(int_1, 0,
							itemStack_1));
		}
	}

	public void close(PlayerEntity playerEntity_1) {
		super.close(playerEntity_1);
		this.invCraftingResult.clearInv();
		if (!playerEntity_1.world.isClient) {
			this.method_7607(playerEntity_1, playerEntity_1.world,
					this.invCrafting);
		}
	}

	public boolean canUse(PlayerEntity playerEntity_1) {
		return true;
	}

	public ItemStack transferSlot(PlayerEntity playerEntity_1, int int_1) {
		ItemStack itemStack_1 = ItemStack.EMPTY;
		Slot slot_1 = (Slot) this.slotList.get(int_1);
		if (slot_1 != null && slot_1.hasStack()) {
			ItemStack itemStack_2 = slot_1.getStack();
			itemStack_1 = itemStack_2.copy();
			EquipmentSlot equipmentSlot_1 = MobEntity
					.getPreferredEquipmentSlot(itemStack_1);
			if (int_1 == 0) {
				if (!this.insertItem(itemStack_2, 9, 45, true)) {
					return ItemStack.EMPTY;
				}

				slot_1.onStackChanged(itemStack_2, itemStack_1);
			} else if (int_1 >= 1 && int_1 < 5) {
				if (!this.insertItem(itemStack_2, 9, 45, false)) {
					return ItemStack.EMPTY;
				}
			} else if (int_1 >= 5 && int_1 < 9) {
				if (!this.insertItem(itemStack_2, 9, 45, false)) {
					return ItemStack.EMPTY;
				}
			} else if (equipmentSlot_1.getType() == EquipmentSlot.Type.ARMOR
					&& !((Slot) this.slotList.get(8 - equipmentSlot_1
							.getEntitySlotId())).hasStack()) {
				int int_2 = 8 - equipmentSlot_1.getEntitySlotId();
				if (!this.insertItem(itemStack_2, int_2, int_2 + 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (equipmentSlot_1 == EquipmentSlot.HAND_OFF
					&& !((Slot) this.slotList.get(45)).hasStack()) {
				if (!this.insertItem(itemStack_2, 45, 46, false)) {
					return ItemStack.EMPTY;
				}
			} else if (int_1 >= 9 && int_1 < 36) {
				if (!this.insertItem(itemStack_2, 36, 45, false)) {
					return ItemStack.EMPTY;
				}
			} else if (int_1 >= 36 && int_1 < 45) {
				if (!this.insertItem(itemStack_2, 9, 36, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(itemStack_2, 9, 45, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack_2.isEmpty()) {
				slot_1.setStack(ItemStack.EMPTY);
			} else {
				slot_1.markDirty();
			}

			if (itemStack_2.getAmount() == itemStack_1.getAmount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemStack_3 = slot_1.onTakeItem(playerEntity_1,
					itemStack_2);
			if (int_1 == 0) {
				playerEntity_1.dropItem(itemStack_3, false);
			}
		}

		return itemStack_1;
	}

	public boolean method_7613(ItemStack itemStack_1, Slot slot_1) {
		return slot_1.inventory != this.invCraftingResult
				&& super.method_7613(itemStack_1, slot_1);
	}

	public int getCraftingResultSlotIndex() {
		return 0;
	}

	public int getCraftingWidth() {
		return this.invCrafting.getWidth();
	}

	public int getCraftingHeight() {
		return this.invCrafting.getHeight();
	}

	@Environment(EnvType.CLIENT)
	public int getCraftingSlotCount() {
		return 5;
	}

	static {
		EQUIPMENT_SLOT_ORDER = new EquipmentSlot[] { EquipmentSlot.HEAD,
				EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET };
	}
}