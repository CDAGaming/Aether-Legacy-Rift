package com.legacy.aether.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.portal.BlockAetherPortal;
import com.legacy.aether.world.WorldAether;

@Mixin(BucketItem.class)
public class MixinBucketItem
{

	@Shadow @Final private Fluid fluid;

	@Inject(method = "placeFluid", at = @At("HEAD"), cancellable = true)
	public void placeAetherPortal(PlayerEntity playerIn, World worldIn, BlockPos posIn, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> ci)
	{
		if (this.fluid == Fluids.WATER && ((BlockAetherPortal) BlocksAether.aether_portal).method_10352(worldIn, posIn))
		{
			this.playEmptyingSound(playerIn, worldIn, posIn);

			ci.setReturnValue(true);
			ci.cancel();
		}
		else if (this.fluid == Fluids.LAVA && playerIn.dimension == WorldAether.THE_AETHER)
		{
			this.playEmptyingSound(playerIn, worldIn, posIn);

			worldIn.setBlockState(posIn, BlocksAether.aerogel.getDefaultState(), 11);

			ci.setReturnValue(true);
			ci.cancel();
		}
	}

	@Shadow
	protected void playEmptyingSound(PlayerEntity playerIn, IWorld worldIn, BlockPos posIn)
	{

	}

}