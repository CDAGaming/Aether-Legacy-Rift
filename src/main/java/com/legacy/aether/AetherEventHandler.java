package com.legacy.aether;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import com.legacy.aether.item.ItemsAether;
import com.legacy.aether.world.WorldAether;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class AetherEventHandler implements UseItemCallback, UseBlockCallback, UseEntityCallback
{

	private static final AetherEventHandler INSTANCE = new AetherEventHandler();

	public static void register()
	{
		UseItemCallback.EVENT.register(INSTANCE);
		UseBlockCallback.EVENT.register(INSTANCE);
		UseEntityCallback.EVENT.register(INSTANCE);
	}

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand)
	{
		if (player.isSpectator())
		{
			return ActionResult.PASS;
		}

		ItemStack stack = player.getStackInHand(hand);

		if (player.dimension == WorldAether.THE_AETHER && (stack.getItem() == ItemsAether.skyroot_water_bucket || stack.getItem() == Items.WATER_BUCKET || stack.getItem() == Items.LAVA_BUCKET))
		{
			HitResult hitResult = this.getHitResult(world, player, RayTraceContext.FluidHandling.NONE);

			if (hitResult.getType() == HitResult.Type.BLOCK)
			{
				BlockHitResult blockHitResult = (BlockHitResult) hitResult;
				BlockPos pos = blockHitResult.getBlockPos();

				if (world.canPlayerModifyAt(player, pos) && player.canPlaceBlock(pos, blockHitResult.getSide(), stack))
				{
					BlockState state = world.getBlockState(pos);
					BlockPos blockPos_2 = state.getBlock() instanceof FluidFillable ? pos : blockHitResult.getBlockPos().offset(blockHitResult.getSide());

					if (this.placeFluid(player, world, blockPos_2, blockHitResult, stack.getItem() == Items.LAVA_BUCKET ? Fluids.LAVA : Fluids.WATER))
					{
						if (player instanceof ServerPlayerEntity)
						{
							Criterions.PLACED_BLOCK.handle((ServerPlayerEntity) player, blockPos_2, stack);
						}

						player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

						return ActionResult.SUCCESS;
					}
				}
			}

			return ActionResult.PASS;
		}

		return ActionResult.PASS;
	}

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult)
	{
		if (player.isSpectator())
		{
			return ActionResult.PASS;
		}

		ItemStack stack = player.getStackInHand(hand);
		BlockPos pos = hitResult.getBlockPos();
		BlockPos hitPos = pos.offset(hitResult.getSide());

		if (player.dimension == WorldAether.THE_AETHER && stack.getItem() == Items.FLINT_AND_STEEL && FlintAndSteelItem.method_7825(world.getBlockState(hitPos), world, hitPos))
		{
			world.playSound(player, hitPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCK, 1.0F, world.random.nextFloat() * 0.4F + 0.8F);

			for (int int_4 = 0; int_4 < 8; ++int_4)
			{
				world.addParticle(ParticleTypes.LARGE_SMOKE, (double) hitPos.getX() + Math.random(), (double) hitPos.getY() + Math.random(), (double) hitPos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
			}

			return ActionResult.SUCCESS;
		}

		return ActionResult.PASS;
	}

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult)
	{
		if (player.isSpectator())
		{
			return ActionResult.PASS;
		}

		ItemStack stack = player.getStackInHand(hand);

		if (stack.getItem() == ItemsAether.skyroot_bucket && entity instanceof CowEntity && !player.abilities.creativeMode && !((CowEntity) entity).isChild())
		{
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);

			stack.subtractAmount(1);

			if (stack.isEmpty())
			{
				player.setStackInHand(hand, new ItemStack(ItemsAether.skyroot_milk_bucket));
			}
			else if (!player.inventory.insertStack(new ItemStack(ItemsAether.skyroot_milk_bucket)))
			{
				player.dropItem(new ItemStack(ItemsAether.skyroot_milk_bucket), false);
			}

			return ActionResult.SUCCESS;
		}

		return ActionResult.PASS;
	}

	private HitResult getHitResult(World world, PlayerEntity player, RayTraceContext.FluidHandling fluidHandler)
	{
		Vec3d startVector = player.getCameraPosVec(1.0F);

		float float_3 = MathHelper.cos(-player.yaw * 0.017453292F - 3.1415927F);
		float float_4 = MathHelper.sin(-player.yaw * 0.017453292F - 3.1415927F);
		float float_5 = -MathHelper.cos(-player.pitch * 0.017453292F);
		float float_6 = MathHelper.sin(-player.pitch * 0.017453292F);

		float float_7 = float_4 * float_5;
		float float_9 = float_3 * float_5;

		Vec3d endVector = startVector.add((double) float_7 * 5.0D, (double) float_6 * 5.0D, (double) float_9 * 5.0D);

		return world.rayTrace(new RayTraceContext(startVector, endVector, RayTraceContext.ShapeType.OUTLINE, fluidHandler, player));
	}

	private boolean placeFluid(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult, Fluid fluid)
	{
		if (fluid == Fluids.WATER && ((BlockAetherPortal) BlocksAether.aether_portal).method_10352(world, pos))
		{
			this.playEmptyingSound(player, world, pos, fluid);

			return true;
		}
		else if (fluid == Fluids.LAVA && player.dimension == WorldAether.THE_AETHER)
		{
			this.playEmptyingSound(player, world, pos, fluid);

			world.setBlockState(pos, BlocksAether.aerogel.getDefaultState(), 11);

			return true;
		}

		return false;
	}

	private void playEmptyingSound(PlayerEntity player, IWorld world, BlockPos pos, Fluid fluid)
	{
		SoundEvent emptySound = fluid.matches(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;

		world.playSound(player, pos, emptySound, SoundCategory.BLOCK, 1.0F, 1.0F);
	}

}