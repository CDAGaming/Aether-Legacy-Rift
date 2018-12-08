package com.legacy.aether.entities.passive;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import com.google.common.collect.Maps;
import com.legacy.aether.entities.EntityTypesAether;
import com.legacy.aether.entities.ai.EntityAIEatAetherGrass;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.sounds.SoundsAether;
import com.legacy.aether.world.storage.loot.AetherLootTableList;

public class EntitySheepuff extends EntityAetherAnimal
{

	public static final DataParameter<Byte> FLEECE_COLOR = EntityDataManager.<Byte>createKey(EntitySheepuff.class, DataSerializers.BYTE);

	public static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntitySheepuff.class, DataSerializers.BOOLEAN);

	public static final DataParameter<Boolean> PUFFY = EntityDataManager.<Boolean>createKey(EntitySheepuff.class, DataSerializers.BOOLEAN);

    private static final Map<EnumDyeColor, IItemProvider> WOOL_BY_COLOR = Util.make(Maps.<EnumDyeColor, IItemProvider>newEnumMap(EnumDyeColor.class), (p_203402_0_) ->
    {
        p_203402_0_.put(EnumDyeColor.WHITE, Blocks.WHITE_WOOL);
        p_203402_0_.put(EnumDyeColor.ORANGE, Blocks.ORANGE_WOOL);
        p_203402_0_.put(EnumDyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
        p_203402_0_.put(EnumDyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
        p_203402_0_.put(EnumDyeColor.YELLOW, Blocks.YELLOW_WOOL);
        p_203402_0_.put(EnumDyeColor.LIME, Blocks.LIME_WOOL);
        p_203402_0_.put(EnumDyeColor.PINK, Blocks.PINK_WOOL);
        p_203402_0_.put(EnumDyeColor.GRAY, Blocks.GRAY_WOOL);
        p_203402_0_.put(EnumDyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
        p_203402_0_.put(EnumDyeColor.CYAN, Blocks.CYAN_WOOL);
        p_203402_0_.put(EnumDyeColor.PURPLE, Blocks.PURPLE_WOOL);
        p_203402_0_.put(EnumDyeColor.BLUE, Blocks.BLUE_WOOL);
        p_203402_0_.put(EnumDyeColor.BROWN, Blocks.BROWN_WOOL);
        p_203402_0_.put(EnumDyeColor.GREEN, Blocks.GREEN_WOOL);
        p_203402_0_.put(EnumDyeColor.RED, Blocks.RED_WOOL);
        p_203402_0_.put(EnumDyeColor.BLACK, Blocks.BLACK_WOOL);
    });

	private EntityAIEatAetherGrass entityAIEatGrass;

	private int sheepTimer, amountEaten;

    public EntitySheepuff(World world)
    {
        super(EntityTypesAether.SHEEPUFF, world);

		this.amountEaten = 0;

        this.setSize(0.9F, 1.3F);
		this.setFleeceColor(getRandomFleeceColor(rand));
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Ingredient.fromItems(ItemsAether.blueberry), false));
        this.tasks.addTask(5, this.entityAIEatGrass = new EntityAIEatAetherGrass(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D); 
    }

    @Override
	protected void registerData()
    {
        super.registerData();

        this.dataManager.register(FLEECE_COLOR, new Byte((byte)0));
        this.dataManager.register(SHEARED, false);
        this.dataManager.register(PUFFY, false);
    }

	@Override
    protected ResourceLocation getLootTable()
    {
        if (this.getSheared())
        {
            return LootTableList.ENTITIES_SHEEP;
        }
        else
        {
            switch (this.getFleeceColor())
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
    protected void updateAITasks()
    {
        this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public void livingTick()
    {
        if (this.world.isRemote)
        {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }

        super.livingTick();
    }

    @Override
    public void handleStatusUpdate(byte id)
    {
        if (id == 10)
        {
            this.sheepTimer = 40;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    public float getHeadRotationPointY(float p_70894_1_)
    {
        return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F));
    }

    public float getHeadRotationAngleX(float p_70890_1_)
    {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
        {
            float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        }
        else
        {
            return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
        }
    }

    @Override
    public void eatGrassBonus()
    {
    	++this.amountEaten;
    }

	@Override
	protected void playStepSound(BlockPos pos, IBlockState state)
	{
		this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_SHEEP_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if(itemstack != null && itemstack.getItem() == Items.SHEARS && !this.getSheared())
        {
            if(!this.world.isRemote)
            {
				if(this.getPuffed())
				{
					this.setPuffed(false);
				}
				else
				{
					this.setSheared(true);
				}

				int i = 2 + this.rand.nextInt(3);

				for(int j = 0; j < i; j++)
				{
					EntityItem entityitem = this.entityDropItem(WOOL_BY_COLOR.get(this.getFleeceColor()), 1);

					entityitem.motionY += this.rand.nextFloat() * 0.05F;
					entityitem.motionX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
					entityitem.motionZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
				}
            }

            itemstack.damageItem(1, player);
        }

        if (itemstack != null && itemstack.getItem() instanceof ItemDye && !this.getSheared())
        {
        	EnumDyeColor color = ((ItemDye)itemstack.getItem()).getDyeColor();

            if (this.getFleeceColor() != color)
            {
                if (this.getPuffed() && itemstack.getCount() >= 2)
                {
                    this.setFleeceColor(color);
                    itemstack.shrink(2);
                }
                else if (!this.getPuffed())
                {
                    this.setFleeceColor(color);
                    itemstack.shrink(1);
                }
            }
        }
        
        return super.processInteract(player, hand);
    }

    @Override
    protected void jump()
    {
		if(this.getPuffed())
		{
			this.motionY = 1.8D;
			this.motionX += this.rand.nextGaussian() * 0.5D;
			this.motionZ += this.rand.nextGaussian() * 0.5D;
		}
		else
		{
			this.motionY = 0.41999998688697815D;
		}
    }

    @Override
	public void tick()
	{
		super.tick();

		if(this.getPuffed())
		{
			this.fallDistance = 0;

			if(this.motionY < -0.05D)
			{
				this.motionY = -0.05D;
			}
		}

		if(this.amountEaten == 5 && !this.getSheared() && !this.getPuffed())
		{
			this.setPuffed(true);
			this.amountEaten = 0;
		}

		if(this.amountEaten == 10 && this.getSheared() && !this.getPuffed())
		{
			this.setSheared(false);
			this.amountEaten = 0;
		}
	}

    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);

        compound.putBoolean("Sheared", this.getSheared());
        compound.putBoolean("Puffed", this.getPuffed());
        compound.putByte("Color", (byte)this.getFleeceColor().getId());
    }

    @Override
    public void readAdditional(NBTTagCompound compound)
    {
        super.readAdditional(compound);

        this.setSheared(compound.getBoolean("Sheared"));
        this.setPuffed(compound.getBoolean("Puffed"));
        this.setFleeceColor(EnumDyeColor.byId(compound.getByte("Color")));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundsAether.sheepuff_say;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundsAether.sheepuff_hurt;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundsAether.sheepuff_death;
    }

	public EnumDyeColor getFleeceColor()
    {
        return EnumDyeColor.byId(this.dataManager.get(FLEECE_COLOR) & 15);
    }

    public void setFleeceColor(EnumDyeColor i)
    {
        byte byte0 = this.dataManager.get(FLEECE_COLOR);
        this.dataManager.set(FLEECE_COLOR, Byte.valueOf((byte)(byte0 & 240 | i.getId() & 15)));
    }

    public boolean getSheared()
    {
        return this.dataManager.get(SHEARED);
    }

    public void setSheared(boolean flag)
    {
        this.dataManager.set(SHEARED, flag);
    }

	public boolean getPuffed()
    {
        return this.dataManager.get(PUFFY);
    }

	public void setPuffed(boolean flag)
    {
        this.dataManager.set(PUFFY, flag);
    }

	public static EnumDyeColor getRandomFleeceColor(Random random)
    {
        int i = random.nextInt(100);

        if(i < 5) { return EnumDyeColor.LIGHT_BLUE; }
        if(i < 10) { return EnumDyeColor.CYAN; }
        if(i < 15) { return EnumDyeColor.LIME; }
        if(i < 18) { return EnumDyeColor.PINK; }

        return random.nextInt(500) != 0 ? EnumDyeColor.WHITE : EnumDyeColor.PURPLE;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return new EntitySheepuff(world);
	}

}