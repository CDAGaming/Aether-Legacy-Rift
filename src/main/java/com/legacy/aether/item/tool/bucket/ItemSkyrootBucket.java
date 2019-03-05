package com.legacy.aether.item.tool.bucket;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import com.legacy.aether.item.AetherItemGroup;
import com.legacy.aether.item.ItemsAether;

public class ItemSkyrootBucket extends Item
{

    private final Fluid containedBlock;

	public ItemSkyrootBucket()
	{
		super(new Settings().stackSize(16).itemGroup(AetherItemGroup.AETHER_MISC));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Item containerIn)
	{
		super(new Settings().stackSize(1).itemGroup(AetherItemGroup.AETHER_MISC).recipeRemainder(containerIn));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Fluid containedFluidIn, Item containerIn)
	{
		super(new Settings().stackSize(1).itemGroup(AetherItemGroup.AETHER_MISC).recipeRemainder(containerIn));

		this.containedBlock = containedFluidIn;
	}

	@Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getStackInHand(handIn);
        HitResult raytraceresult = getHitResult(worldIn, playerIn, this.containedBlock == Fluids.EMPTY ? RayTraceContext.FluidHandling.SOURCE_ONLY : RayTraceContext.FluidHandling.NONE);

        if (itemstack.getItem() != ItemsAether.skyroot_water_bucket && itemstack.getItem() != ItemsAether.skyroot_bucket)
        {
        	playerIn.setCurrentHand(handIn);

            return new TypedActionResult<ItemStack>(ActionResult.PASS, itemstack);
        }

        if (raytraceresult == null)
        {
            return new TypedActionResult<ItemStack>(ActionResult.PASS, itemstack);
        }
        else if (raytraceresult.getType() == HitResult.Type.BLOCK)
        {
        	BlockHitResult class_3965_1 = (BlockHitResult) raytraceresult;
            BlockPos blockpos = class_3965_1.getBlockPos();

            if (worldIn.canPlayerModifyAt(playerIn, blockpos) && playerIn.canPlaceBlock(blockpos, class_3965_1.getSide(), itemstack))
            {
                if (this.containedBlock == Fluids.EMPTY)
                {
                    BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if (iblockstate1.getBlock() instanceof FluidDrainable) {
                        Fluid fluid = ((FluidDrainable) iblockstate1.getBlock()).tryDrainFluid(worldIn, blockpos, iblockstate1);

                        if (fluid != Fluids.EMPTY) {
                            playerIn.incrementStat(Stats.USED.getOrCreateStat(this));
                            playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                            ItemStack itemstack1 = this.fillBucket(itemstack, playerIn, ItemsAether.skyroot_water_bucket);

                            if (!worldIn.isClient) {
                            	Criterions.FILLED_BUCKET.method_8932((ServerPlayerEntity) playerIn, new ItemStack(ItemsAether.skyroot_water_bucket));
                            }

                            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemstack1);
                        }
                    }

                    return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemstack);
                }
                else
                {
                    BlockState iblockstate = worldIn.getBlockState(blockpos);
                    BlockPos blockpos1 = iblockstate.getBlock() instanceof FluidFillable ? blockpos : class_3965_1.getBlockPos().offset(class_3965_1.getSide());

                    this.placeLiquid(playerIn, worldIn, blockpos1, class_3965_1);

					if (playerIn instanceof ServerPlayerEntity)
					{
						Criterions.PLACED_BLOCK.handle((ServerPlayerEntity)playerIn, blockpos1, itemstack);
					}

					playerIn.incrementStat(Stats.USED.getOrCreateStat(this));
					return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, this.emptyBucket(itemstack, playerIn));
                }
            }
            else
            {
                return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemstack);
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
		IPlayerAether player = AetherAPI.get(entityplayer);

        if (entityplayer instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity entityplayermp = (ServerPlayerEntity) entityplayer;
            Criterions.CONSUME_ITEM.handle(entityplayermp, itemstack);
            entityplayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }

		if (!entityplayer.abilities.creativeMode)
		{
			itemstack.subtractAmount(1);
		}

		if (itemstack.getItem() == ItemsAether.skyroot_poison_bucket)
		{
			player.inflictPoison(500);
		}
		else if (itemstack.getItem() == ItemsAether.skyroot_remedy_bucket)
		{
			player.inflictCure(200);
		}
		else if (itemstack.getItem() == ItemsAether.skyroot_milk_bucket)
		{
			if (!world.isClient)
			{
				entityplayer.clearPotionEffects();
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

    public boolean placeLiquid(PlayerEntity playerIn, World worldIn, BlockPos posIn, BlockHitResult hitResult)
    {
		if (((BlockAetherPortal) BlocksAether.aether_portal).method_10352(worldIn, posIn))
		{
			this.playEmptySound(playerIn, worldIn, posIn);

			return true;
		}

        if (!(this.containedBlock instanceof BaseFluid))
        {
            return false;
        }
        else
        {
            BlockState iblockstate = worldIn.getBlockState(posIn);
            Material material = iblockstate.getMaterial();
            boolean flag = !material.method_15799();
            boolean flag1 = material.isReplaceable();

            if (worldIn.isAir(posIn) || flag || flag1 || iblockstate.getBlock() instanceof FluidFillable && ((FluidFillable)iblockstate.getBlock()).canFillWithFluid(worldIn, posIn, iblockstate, this.containedBlock))
            {
                if (worldIn.dimension.isNether())
                {
                    int i = posIn.getX();
                    int j = posIn.getY();
                    int k = posIn.getZ();
                    worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCK, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else if (iblockstate.getBlock() instanceof FluidFillable)
                {
                    if (((FluidFillable)iblockstate.getBlock()).tryFillWithFluid(worldIn, posIn, iblockstate, ((BaseFluid)this.containedBlock).getState(false)))
                    {
                        this.playEmptySound(playerIn, worldIn, posIn);
                    }
                }
                else
                {
                    if (!worldIn.isClient && (flag || flag1) && !material.isLiquid())
                    {
                        worldIn.breakBlock(posIn, true);
                    }

                    this.playEmptySound(playerIn, worldIn, posIn);
                    worldIn.setBlockState(posIn, this.containedBlock.getDefaultState().getBlockState(), 11);
                }

                return true;
            }
            else
            {
                return hitResult == null ? false : this.placeLiquid(playerIn, worldIn, hitResult.getBlockPos().offset(hitResult.getSide()), null);
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