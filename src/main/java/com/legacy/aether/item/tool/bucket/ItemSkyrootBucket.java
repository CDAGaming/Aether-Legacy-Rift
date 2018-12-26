package com.legacy.aether.item.tool.bucket;

import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.CriterionCriterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.class_2263;
import net.minecraft.class_2402;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.player.IEntityPlayerAether;
import com.legacy.aether.player.PlayerAether;

public class ItemSkyrootBucket extends Item
{

    private final Fluid containedBlock;

	public ItemSkyrootBucket()
	{
		super(new Settings().stackSize(16).itemGroup(ItemGroup.TOOLS));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Item containerIn)
	{
		super(new Settings().stackSize(1).itemGroup(ItemGroup.TOOLS).containerItem(containerIn));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Fluid containedFluidIn, Item containerIn)
	{
		super(new Settings().stackSize(1).itemGroup(ItemGroup.TOOLS).containerItem(containerIn));

		this.containedBlock = containedFluidIn;
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getStackInHand(handIn);
        HitResult raytraceresult = this.getHitResult(worldIn, playerIn, this.containedBlock == Fluids.EMPTY);

        if (itemstack.getItem() != ItemsAether.skyroot_water_bucket && itemstack.getItem() != ItemsAether.skyroot_bucket)
        {
        	playerIn.setCurrentHand(handIn);

            return new TypedActionResult<ItemStack>(ActionResult.PASS, itemstack);
        }

        if (raytraceresult == null)
        {
            return new TypedActionResult<ItemStack>(ActionResult.PASS, itemstack);
        }
        else if (raytraceresult.type == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (worldIn.canPlayerModifyAt(playerIn, blockpos) && playerIn.canPlaceBlock(blockpos, raytraceresult.side, itemstack))
            {
                if (this.containedBlock == Fluids.EMPTY)
                {
                    BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if (iblockstate1.getBlock() instanceof class_2263) {
                        Fluid fluid = ((class_2263) iblockstate1.getBlock()).method_9700(worldIn, blockpos, iblockstate1);

                        if (fluid != Fluids.EMPTY) {
                            playerIn.incrementStat(Stats.USED.method_14956(this));
                            playerIn.playSoundAtEntity(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                            ItemStack itemstack1 = this.fillBucket(itemstack, playerIn, ItemsAether.skyroot_water_bucket);

                            if (!worldIn.isRemote) {
                                CriterionCriterions.FILLED_BUCKET.method_8932((ServerPlayerEntity) playerIn, new ItemStack(ItemsAether.skyroot_water_bucket));
                            }

                            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemstack1);
                        }
                    }

                    return new TypedActionResult<ItemStack>(ActionResult.FAILURE, itemstack);
                }
                else
                {
                    BlockState iblockstate = worldIn.getBlockState(blockpos);
                    BlockPos blockpos1 = this.getPlacementPosition(iblockstate, blockpos, raytraceresult);

                    if (playerIn != null)
                    {
                        this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1, raytraceresult);

                        if (playerIn instanceof ServerPlayerEntity)
                        {
                            CriterionCriterions.PLACED_BLOCK.handle((ServerPlayerEntity)playerIn, blockpos1, itemstack);
                        }

                        playerIn.incrementStat(Stats.USED.method_14956(this));
                        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, this.emptyBucket(itemstack, playerIn));
                    }
                    else
                    {
                        return new TypedActionResult<ItemStack>(ActionResult.FAILURE, itemstack);
                    }
                }
            }
            else
            {
                return new TypedActionResult<ItemStack>(ActionResult.FAILURE, itemstack);
            }
        }
        else
        {
            return new TypedActionResult<ItemStack>(ActionResult.PASS, itemstack);
        }
    }

    public ItemStack onItemFinishedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving)
    {
    	if (entityLiving instanceof PlayerEntity)
    	{
    		return this.onBucketContentsConsumed(stack, worldIn, (PlayerEntity) entityLiving);
    	}

    	return super.onItemFinishedUsing(stack, worldIn, entityLiving);
    }

	public ItemStack onBucketContentsConsumed(ItemStack itemstack, World world, PlayerEntity entityplayer)
	{
		PlayerAether player = ((IEntityPlayerAether)entityplayer).getPlayerAether();

        if (entityplayer instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity entityplayermp = (ServerPlayerEntity) entityplayer;
            CriterionCriterions.CONSUME_ITEM.handle(entityplayermp, itemstack);
            entityplayer.incrementStat(Stats.USED.method_14956(this));
        }

		if (!entityplayer.abilities.creativeMode)
		{
			itemstack.subtractAmount(1);
		}

		if (itemstack.getItem() == ItemsAether.skyroot_poison_bucket)
		{
			player.applyPoison(500);
		}
		else if (itemstack.getItem() == ItemsAether.skyroot_remedy_bucket)
		{
			player.applyCure(200);
		}
		else if (itemstack.getItem() == ItemsAether.skyroot_milk_bucket)
		{
			if (!world.isRemote)
			{
				//entityplayer.func_195061_cb();
			}
		}

		return itemstack.isEmpty() ? new ItemStack(ItemsAether.skyroot_bucket) : itemstack;
	}

	@Override
    public int getMaxUseTime(ItemStack stack)
    {
        return 32;
    }

	@Override
    public UseAction getUseAction(ItemStack stack)
    {
        if (stack.getItem() != ItemsAether.skyroot_water_bucket && stack.getItem() != ItemsAether.skyroot_bucket)
        {
        	return UseAction.DRINK;
        }

        return UseAction.NONE;
    }

    private BlockPos getPlacementPosition(BlockState p_210768_1_, BlockPos p_210768_2_, HitResult p_210768_3_)
    {
        return p_210768_3_.getBlockPos().offset(p_210768_3_.side);
    }

    protected ItemStack emptyBucket(ItemStack p_203790_1_, PlayerEntity p_203790_2_)
    {
        return !p_203790_2_.abilities.creativeMode ? new ItemStack(ItemsAether.skyroot_bucket) : p_203790_1_;
    }

    private ItemStack fillBucket(ItemStack emptyBuckets, PlayerEntity player, Item fullBucket)
    {
        if (player.abilities.creativeMode)
        {
            return emptyBuckets;
        }
        else
        {
            emptyBuckets.subtractAmount(1);

            if (emptyBuckets.isEmpty())
            {
                return new ItemStack(fullBucket);
            }
            else
            {
                if (!player.inventory.insertStack(new ItemStack(fullBucket)))
                {
                    player.dropItem(new ItemStack(fullBucket), false);
                }

                return emptyBuckets;
            }
        }
    }

    public boolean tryPlaceContainedLiquid(PlayerEntity player, World worldIn, BlockPos posIn, HitResult p_180616_4_)
    {
        if (!(this.containedBlock instanceof BaseFluid))
        {
            return false;
        }
        else
        {
            BlockState iblockstate = worldIn.getBlockState(posIn);
            Material material = iblockstate.getMaterial();
            boolean flag = !material.method_15799();
            boolean flag1 = material.method_15800();

            if (worldIn.isAir(posIn) || flag || flag1 || iblockstate.getBlock() instanceof class_2402 && ((class_2402)iblockstate.getBlock()).method_10310(worldIn, posIn, iblockstate, this.containedBlock))
            {
                if (worldIn.dimension.method_12465())
                {
                    int i = posIn.getX();
                    int j = posIn.getY();
                    int k = posIn.getZ();
                    worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCK, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        worldIn.method_8406(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else if (iblockstate.getBlock() instanceof class_2402)
                {
                    if (((class_2402)iblockstate.getBlock()).method_10311(worldIn, posIn, iblockstate, ((BaseFluid)this.containedBlock).getState(false)))
                    {
                        this.playEmptySound(player, worldIn, posIn);
                    }
                }
                else
                {
                    if (!worldIn.isRemote && (flag || flag1) && !material.method_15797())
                    {
                        worldIn.breakBlock(posIn, true);
                    }

                    this.playEmptySound(player, worldIn, posIn);
                    worldIn.setBlockState(posIn, this.containedBlock.getDefaultState().getBlockState(), 11);
                }

                return true;
            }
            else
            {
                return p_180616_4_ == null ? false : this.tryPlaceContainedLiquid(player, worldIn, p_180616_4_.getBlockPos().offset(p_180616_4_.side), null);
            }
        }
    }

    protected void playEmptySound(PlayerEntity player, IWorld worldIn, BlockPos pos)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCK, 1.0F, 1.0F);
    }

	@Override
    public Rarity getRarity(ItemStack stack)
    {
    	return stack.getItem() == ItemsAether.skyroot_remedy_bucket ? Rarity.RARE : super.getRarity(stack);
    }

}