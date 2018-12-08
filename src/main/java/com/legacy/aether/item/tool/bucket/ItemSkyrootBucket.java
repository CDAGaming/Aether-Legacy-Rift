package com.legacy.aether.item.tool.bucket;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.init.Fluids;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
		super(new Properties().maxStackSize(16).group(ItemGroup.TOOLS));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Item containerIn)
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.TOOLS).containerItem(containerIn));

		this.containedBlock = Fluids.EMPTY;
	}

	public ItemSkyrootBucket(Fluid containedFluidIn, Item containerIn)
	{
		super(new Properties().maxStackSize(1).group(ItemGroup.TOOLS).containerItem(containerIn));

		this.containedBlock = containedFluidIn;
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, this.containedBlock == Fluids.EMPTY);

        if (itemstack.getItem() != ItemsAether.skyroot_water_bucket && itemstack.getItem() != ItemsAether.skyroot_bucket)
        {
        	playerIn.setActiveHand(handIn);

            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }

        if (raytraceresult == null)
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }
        else if (raytraceresult.type == RayTraceResult.Type.BLOCK)
        {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, itemstack))
            {
                if (this.containedBlock == Fluids.EMPTY)
                {
                    IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                    if (iblockstate1.getBlock() instanceof IBucketPickupHandler)
                    {
                        Fluid fluid = ((IBucketPickupHandler)iblockstate1.getBlock()).pickupFluid(worldIn, blockpos, iblockstate1);

                        if (fluid != Fluids.EMPTY)
                        {
                            playerIn.addStat(StatList.ITEM_USED.get(this));
                            playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                            ItemStack itemstack1 = this.fillBucket(itemstack, playerIn, ItemsAether.skyroot_water_bucket);

                            if (!worldIn.isRemote)
                            {
                                CriteriaTriggers.FILLED_BUCKET.func_204817_a((EntityPlayerMP)playerIn, new ItemStack(ItemsAether.skyroot_water_bucket));
                            }

                            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack1);
                        }
                    }

                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
                }
                else
                {
                    IBlockState iblockstate = worldIn.getBlockState(blockpos);
                    BlockPos blockpos1 = this.getPlacementPosition(iblockstate, blockpos, raytraceresult);

                    if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1, raytraceresult))
                    {
                        this.onLiquidPlaced(worldIn, itemstack, blockpos1);

                        if (playerIn instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1, itemstack);
                        }

                        playerIn.addStat(StatList.ITEM_USED.get(this));
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, this.emptyBucket(itemstack, playerIn));
                    }
                    else
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
                    }
                }
            }
            else
            {
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
            }
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if (entityLiving instanceof EntityPlayer)
    	{
    		return this.onBucketContentsConsumed(stack, worldIn, (EntityPlayer) entityLiving);
    	}

    	return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

	public ItemStack onBucketContentsConsumed(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		PlayerAether player = ((IEntityPlayerAether)entityplayer).getPlayerAether();

        if (entityplayer instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
            CriteriaTriggers.CONSUME_ITEM.trigger(entityplayermp, itemstack);
            entityplayermp.addStat(StatList.ITEM_USED.get(this));
        }

		if (!entityplayer.abilities.isCreativeMode)
		{
			itemstack.shrink(1);
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
				entityplayer.func_195061_cb();
			}
		}

		return itemstack.isEmpty() ? new ItemStack(ItemsAether.skyroot_bucket) : itemstack;
	}

	@Override
    public int getUseDuration(ItemStack stack)
    {
        return 32;
    }

	@Override
    public EnumAction getUseAction(ItemStack stack)
    {
        if (stack.getItem() != ItemsAether.skyroot_water_bucket && stack.getItem() != ItemsAether.skyroot_bucket)
        {
        	return EnumAction.DRINK;
        }

        return EnumAction.NONE;
    }

    private BlockPos getPlacementPosition(IBlockState p_210768_1_, BlockPos p_210768_2_, RayTraceResult p_210768_3_)
    {
        return p_210768_1_.getBlock() instanceof ILiquidContainer ? p_210768_2_ : p_210768_3_.getBlockPos().offset(p_210768_3_.sideHit);
    }

    protected ItemStack emptyBucket(ItemStack p_203790_1_, EntityPlayer p_203790_2_)
    {
        return !p_203790_2_.abilities.isCreativeMode ? new ItemStack(ItemsAether.skyroot_bucket) : p_203790_1_;
    }

    public void onLiquidPlaced(World worldIn, ItemStack p_203792_2_, BlockPos pos)
    {

    }

    private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, Item fullBucket)
    {
        if (player.abilities.isCreativeMode)
        {
            return emptyBuckets;
        }
        else
        {
            emptyBuckets.shrink(1);

            if (emptyBuckets.isEmpty())
            {
                return new ItemStack(fullBucket);
            }
            else
            {
                if (!player.inventory.addItemStackToInventory(new ItemStack(fullBucket)))
                {
                    player.dropItem(new ItemStack(fullBucket), false);
                }

                return emptyBuckets;
            }
        }
    }

    public boolean tryPlaceContainedLiquid(@Nullable EntityPlayer player, World worldIn, BlockPos posIn, @Nullable RayTraceResult p_180616_4_)
    {
        if (!(this.containedBlock instanceof FlowingFluid))
        {
            return false;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(posIn);
            Material material = iblockstate.getMaterial();
            boolean flag = !material.isSolid();
            boolean flag1 = material.isReplaceable();

            if (worldIn.isAirBlock(posIn) || flag || flag1 || iblockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)iblockstate.getBlock()).canContainFluid(worldIn, posIn, iblockstate, this.containedBlock))
            {
                if (worldIn.dimension.doesWaterVaporize())
                {
                    int i = posIn.getX();
                    int j = posIn.getY();
                    int k = posIn.getZ();
                    worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        worldIn.addParticle(Particles.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else if (iblockstate.getBlock() instanceof ILiquidContainer)
                {
                    if (((ILiquidContainer)iblockstate.getBlock()).receiveFluid(worldIn, posIn, iblockstate, ((FlowingFluid)this.containedBlock).getStillFluidState(false)))
                    {
                        this.playEmptySound(player, worldIn, posIn);
                    }
                }
                else
                {
                    if (!worldIn.isRemote && (flag || flag1) && !material.isLiquid())
                    {
                        worldIn.destroyBlock(posIn, true);
                    }

                    this.playEmptySound(player, worldIn, posIn);
                    worldIn.setBlockState(posIn, this.containedBlock.getDefaultState().getBlockState(), 11);
                }

                return true;
            }
            else
            {
                return p_180616_4_ == null ? false : this.tryPlaceContainedLiquid(player, worldIn, p_180616_4_.getBlockPos().offset(p_180616_4_.sideHit), (RayTraceResult)null);
            }
        }
    }

    protected void playEmptySound(@Nullable EntityPlayer player, IWorld worldIn, BlockPos pos)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

	@Override
    public EnumRarity getRarity(ItemStack stack)
    {
    	return stack.getItem() == ItemsAether.skyroot_remedy_bucket ? EnumRarity.RARE : super.getRarity(stack);
    }

}