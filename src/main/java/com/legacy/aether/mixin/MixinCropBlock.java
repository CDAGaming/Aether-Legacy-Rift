package com.legacy.aether.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;

@Mixin(CropBlock.class)
public class MixinCropBlock
{

	@Inject(method = "method_9830", at = @At("RETURN"), cancellable = true)
	private static void getCropGrowthSpeed(Block block, BlockView view, BlockPos pos, CallbackInfoReturnable<Float> ci)
	{
		float newSpeed = ci.getReturnValue();
		BlockPos blockPos_2 = pos.down();

		for (int rangeX = -1; rangeX <= 1; ++rangeX)
		{
			for (int rangeZ = -1; rangeZ <= 1; ++rangeZ)
			{
				float extraSpeed = 0.0F;
				BlockState blockState_1 = view.getBlockState(blockPos_2.add(rangeX, 0, rangeZ));

				if (blockState_1.getBlock() == BlocksAether.aether_farmland)
				{
					extraSpeed = 1.0F;

					if ((Integer) blockState_1.get(FarmlandBlock.MOISTURE) > 0)
					{
						extraSpeed = 3.0F;
					}
				}

				if (rangeX != 0 || rangeZ != 0)
				{
					extraSpeed /= 4.0F;
				}

				newSpeed += extraSpeed;
			}
		}

		ci.setReturnValue(newSpeed);
	}

}