package com.legacy.aether.mixin;

import net.minecraft.client.network.packet.MapUpdateClientPacket;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.dimension.DimensionType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.util.MapDimensionData;

@Mixin(MapUpdateClientPacket.class)
public class MixinUpdateClientPacket implements MapDimensionData
{

	private DimensionType dimension;

	@Inject(method = "read", at = @At("RETURN"))
	public void readDimensionData(PacketByteBuf buf, CallbackInfo ci)
	{
		this.dimension = DimensionType.byRawId(buf.readInt());
	}

	@Inject(method = "write", at = @At("RETURN"))
	public void writeDimensionData(PacketByteBuf buf, CallbackInfo ci)
	{
		buf.writeInt(this.dimension.getRawId());
	}

	@Override
	public void setDimension(DimensionType dimension)
	{
		this.dimension = dimension;
	}

	@Override
	public DimensionType getDimension()
	{
		return this.dimension;
	}

}