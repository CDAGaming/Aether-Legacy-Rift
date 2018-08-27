package com.legacy.aether.mixin;

import org.dimdev.rift.injectedmethods.RiftCPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.player.IPlayerAether;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;

@Mixin(NetHandlerPlayServer.class)
public class MixinNetHandlerPlayServer 
{

    @Inject(method = "processCustomPayload", at = @At("RETURN"))
	public void processAetherPayload(CPacketCustomPayload packetIn, CallbackInfo ci)
	{
    	RiftCPacketCustomPayload hook = (RiftCPacketCustomPayload) packetIn;
		ResourceLocation location = hook.getChannelName();

		if (location.getNamespace().equals("aether_legacy"))
		{
			EntityPlayerMP player = ((NetHandlerPlayServer) (Object) this).player;
			String packetName = location.getPath();

			if ("open_accessory_gui".equals(packetName))
			{
				player.displayGUIChest(((IPlayerAether)player).getPlayerAether().getAccessories());
			}
			else if ("close_accessory_gui".equals(packetName))
			{
				player.closeContainer();
			}
		}
	}

}