package com.legacy.aether.entities.passive;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1361;
import net.minecraft.class_1374;
import net.minecraft.class_1376;
import net.minecraft.class_1394;
import net.minecraft.class_3917;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.container.Container;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import com.google.common.collect.Maps;
import com.legacy.aether.entities.ai.EatAetherGrassGoal;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.world.storage.loot.AetherLootTableList;

public class EntitySheepuff extends AnimalEntity
{

	private static final TrackedData<Boolean> PUFFY = DataTracker.registerData(EntitySheepuff.class, TrackedDataHandlerRegistry.BOOLEAN);

	private static final TrackedData<Byte> COLOR = DataTracker.registerData(EntitySheepuff.class, TrackedDataHandlerRegistry.BYTE);;

	private final CraftingInventory dyeCraftingInventory = new CraftingInventory(new Container(-1) {
		@Override
		public boolean canUse(PlayerEntity playerIn)
		{
			return false;
		}

		@Override
		public class_3917<?> method_17358() 
		{
			throw new UnsupportedOperationException("This menu can't be created in normal way");
		}}, 2, 1);

	private static final Map<DyeColor, float[]> COLORS = Maps.newEnumMap((Map<DyeColor, float[]>) Arrays.stream(DyeColor.values()).collect(Collectors.toMap((dyeColor_1) -> dyeColor_1, EntitySheepuff::method_6630)));

	private static final Map<DyeColor, ItemProvider> DROPS;

	private EatAetherGrassGoal eatGrassGoal;

	private int sheepTimer;

	private static float[] method_6630(DyeColor color)
	{
		if (color == DyeColor.WHITE)
		{
			return new float[] { 0.9019608F, 0.9019608F, 0.9019608F };
		}
		else
		{
			float[] floats_1 = color.getColorComponents();

			return new float[] { floats_1[0] * 0.75F, floats_1[1] * 0.75F, floats_1[2] * 0.75F };
		}
	}

	@Environment(EnvType.CLIENT)
	public static float[] getRgbColor(DyeColor dyeColor_1)
	{
		return (float[]) COLORS.get(dyeColor_1);
	}

	public EntitySheepuff(World world)
	{
		super(EntityType.SHEEP, world);

		this.setSize(0.9F, 1.3F);
	}

	@Override
	protected void method_5959()
	{
		this.eatGrassGoal = new EatAetherGrassGoal(this);
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new class_1374(this, 1.25D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3,new TemptGoal(this, 1.1D, Ingredient.ofItems(ItemsAether.blueberry), false));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.add(5, this.eatGrassGoal);
		this.goalSelector.add(6, new class_1394(this, 1.0D));
		this.goalSelector.add(7, new class_1361(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new class_1376(this));
	}

	@Override
	protected void mobTick()
	{
		this.sheepTimer = this.eatGrassGoal.getTimer();

		super.mobTick();
	}

	@Override
	public void updateMovement()
	{
		if (this.world.isClient)
		{
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.updateMovement();
	}

	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();

		this.dataTracker.startTracking(COLOR, (byte) 0);
		this.dataTracker.startTracking(PUFFY, false);
	}

	@Override
	public Identifier getLootTableId()
	{
		if (this.isSheared())
		{
			return this.getType().getLootTableId();
		}
		else
		{
            switch (this.getColor())
            {
                case WHITE:
                default:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_WHITE;
                case ORANGE:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_ORANGE;
                case MAGENTA:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_MAGENTA;
                case LIGHT_BLUE:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_LIGHT_BLUE;
                case YELLOW:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_YELLOW;
                case LIME:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_LIME;
                case PINK:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_PINK;
                case GRAY:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_GRAY;
                case LIGHT_GRAY:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_LIGHT_GRAY;
                case CYAN:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_CYAN;
                case PURPLE:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_PURPLE;
                case BLUE:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_BLUE;
                case BROWN:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_BROWN;
                case GREEN:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_GREEN;
                case RED:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_RED;
                case BLACK:
                    return AetherLootTableList.ENTITIES_SHEEPUFF_BLACK;
            }
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void method_5711(byte id) //handleStatusUpdate
	{
		if (id == 10)
		{
			this.sheepTimer = 40;
		}
		else
		{
			super.method_5711(id);
		}

	}

	@Environment(EnvType.CLIENT)
	public float getHeadRotationPointY(float float_1)
	{
		if (this.sheepTimer <= 0)
		{
			return 0.0F;
		}
		else if (this.sheepTimer >= 4 && this.sheepTimer <= 36)
		{
			return 1.0F;
		}
		else
		{
			return this.sheepTimer < 4 ? ((float) this.sheepTimer - float_1) / 4.0F : -((float) (this.sheepTimer - 40) - float_1) / 4.0F;
		}
	}

	@Environment(EnvType.CLIENT)
	public float getHeadRotationAngleX(float float_1)
	{
		if (this.sheepTimer > 4 && this.sheepTimer <= 36)
		{
			float float_2 = ((float) (this.sheepTimer - 4) - float_1) / 32.0F;

			return 0.62831855F + 0.21991149F * MathHelper.sin(float_2 * 28.7F);
		}
		else
		{
			return this.sheepTimer > 0 ? 0.62831855F : this.pitch * 0.017453292F;
		}
	}

	@Override
	public boolean interactMob(PlayerEntity playerIn, Hand handIn)
	{
		ItemStack heldItem = playerIn.getStackInHand(handIn);

		if (heldItem.getItem() == Items.SHEARS && !this.isSheared() && !this.isChild())
		{
			this.dropItems();

			heldItem.applyDamage(1, playerIn);
		}

		return super.interactMob(playerIn, handIn);
	}

	public void dropItems()
	{
		if (!this.world.isClient)
		{
			if (this.isPuffed())
			{
				this.setPuffed(false);
			}
			else
			{
				this.setSheared(true);
			}

			int int_1 = 1 + this.random.nextInt(3);

			for (int int_2 = 0; int_2 < int_1; ++int_2)
			{
				ItemEntity item = this.dropItem(DROPS.get(this.getColor()), 1);

				if (item != null)
				{
					item.velocityY += (double) (this.random.nextFloat() * 0.05F);
					item.velocityX += (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
					item.velocityZ += (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
				}
			}
		}

		this.playSoundAtEntity(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);

		compound.putBoolean("sheared", this.isSheared());
		compound.putByte("color", (byte) this.getColor().getId());
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);

		this.setSheared(compound.getBoolean("sheared"));
		this.setColor(DyeColor.byId(compound.getByte("color")));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_SHEEP_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_SHEEP_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_SHEEP_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state)
	{
		this.playSoundAtEntity(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
	}

	public DyeColor getColor()
	{
		return DyeColor.byId((Byte) this.dataTracker.get(COLOR) & 15);
	}

	public void setColor(DyeColor dye)
	{
		byte byte_1 = (Byte) this.dataTracker.get(COLOR);

		this.dataTracker.set(COLOR, (byte) (byte_1 & 240 | dye.getId() & 15));
	}

	public boolean isSheared()
	{
		return ((Byte) this.dataTracker.get(COLOR) & 16) != 0;
	}

	public void setSheared(boolean sheared)
	{
		byte byte_1 = (Byte) this.dataTracker.get(COLOR);

		if (sheared)
		{
			this.dataTracker.set(COLOR, (byte) (byte_1 | 16));
		}
		else
		{
			this.dataTracker.set(COLOR, (byte) (byte_1 & -17));
		}

	}

	public boolean isPuffed()
	{
		return this.dataTracker.get(PUFFY);
	}

	public void setPuffed(boolean puffy)
	{
		this.dataTracker.set(PUFFY, puffy);
	}

	public static DyeColor generateDefaultColor(Random random)
	{
		int int_1 = random.nextInt(100);

		if (int_1 < 5)
		{
			return DyeColor.BLACK;
		}
		else if (int_1 < 10)
		{
			return DyeColor.GRAY;
		}
		else if (int_1 < 15)
		{
			return DyeColor.LIGHT_GRAY;
		}
		else if (int_1 < 18)
		{
			return DyeColor.BROWN;
		}
		else
		{
			return random.nextInt(500) == 0 ? DyeColor.PINK : DyeColor.WHITE;
		}
	}

	public EntitySheepuff createChild(PassiveEntity entity)
	{
		EntitySheepuff sheepEntity_1 = (EntitySheepuff) entity;
		EntitySheepuff sheepEntity_2 = new EntitySheepuff(this.world);

		sheepEntity_2.setColor(this.getChildColor(this, sheepEntity_1));

		return sheepEntity_2;
	}

	@Override
	public void method_5983()
	{
		if (!this.isSheared())
		{
			this.setPuffed(true);
		}
		else
		{
			this.setSheared(false);
		}

		if (this.isChild())
		{
			this.method_5615(60);
		}

	}

	@Override
	public EntityData prepareEntityData(IWorld world, LocalDifficulty difficulty, SpawnType spawnType, EntityData entityData, CompoundTag compound)
	{
		entityData = super.prepareEntityData(world, difficulty, spawnType, entityData, compound);

		this.setColor(generateDefaultColor(world.getRandom()));

		return entityData;
	}

	private DyeColor getChildColor(AnimalEntity entity, AnimalEntity mate)
	{
		DyeColor parentColor = ((EntitySheepuff) entity).getColor();
		DyeColor mateColor = ((EntitySheepuff) mate).getColor();

		this.dyeCraftingInventory.setInvStack(0, new ItemStack(DyeItem.fromColor(parentColor)));
		this.dyeCraftingInventory.setInvStack(1, new ItemStack(DyeItem.fromColor(mateColor)));

		ItemStack colorStack = entity.world.getRecipeManager().craft(this.dyeCraftingInventory, ((EntitySheepuff) entity).world);
		Item colorItem = colorStack.getItem();

		DyeColor childColor;

		if (colorItem instanceof DyeItem)
		{
			childColor = ((DyeItem) colorItem).getColor();
		}
		else
		{
			childColor = this.world.random.nextBoolean() ? parentColor : mateColor;
		}

		return childColor;
	}

	@Override
	public float getEyeHeight()
	{
		return 0.95F * this.height;
	}

	static {
		DROPS = (Map<DyeColor, ItemProvider>) SystemUtil.consume(Maps.<DyeColor, ItemProvider>newEnumMap(DyeColor.class), (enumMap_1) -> {
			enumMap_1.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
			enumMap_1.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
			enumMap_1.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
			enumMap_1.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
			enumMap_1.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
			enumMap_1.put(DyeColor.LIME, Blocks.LIME_WOOL);
			enumMap_1.put(DyeColor.PINK, Blocks.PINK_WOOL);
			enumMap_1.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
			enumMap_1.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
			enumMap_1.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
			enumMap_1.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
			enumMap_1.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
			enumMap_1.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
			enumMap_1.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
			enumMap_1.put(DyeColor.RED, Blocks.RED_WOOL);
			enumMap_1.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
		});
	}

}