package com.legacy.aether.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.network.Packet;
import net.minecraft.world.BlockView;
import net.minecraft.world.dimension.DimensionType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.util.MapDimensionData;

@Mixin(MapState.class)
public class MixinMapState
{

	@Shadow public DimensionType dimension;

	@Inject(method = "method_100", at = @At("RETURN"), cancellable = true)
	public void addDimensionData(ItemStack itemStack_1, BlockView blockView_1, PlayerEntity playerEntity_1, CallbackInfoReturnable<Packet<?>> ci)
	{
		Packet<?> packetData = ci.getReturnValue();

		if (packetData instanceof MapDimensionData)
		{
			((MapDimensionData)packetData).setDimension(this.dimension);
		}
	}

}