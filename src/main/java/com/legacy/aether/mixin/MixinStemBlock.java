package com.legacy.aether.mixin;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;

@Mixin({CropBlock.class, StemBlock.class, AttachedStemBlock.class})
public class MixinStemBlock
{

	@Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
	private void canPlantOnAetherTop(BlockState state, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> ci)
	{
		if (!ci.getReturnValue())
		{
			ci.setReturnValue(state.getBlock() == BlocksAether.aether_farmland);
		}
	}

}