package com.legacy.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(PlantBlock.class)
public class MixinBlockBush 
{

	@Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
	protected void aetherlegacy_canPlantOnTop(BlockState stateIn, BlockView readerIn, BlockPos posIn, CallbackInfoReturnable<Boolean> ci)
	{
		if (!ci.getReturnValue())
		{
			Block block = stateIn.getBlock();
			boolean canBePlacedOn = block == BlocksAether.aether_grass || block == BlocksAether.aether_dirt || block == BlocksAether.aether_farmland;

			ci.setReturnValue(canBePlacedOn);
		}
	}

}