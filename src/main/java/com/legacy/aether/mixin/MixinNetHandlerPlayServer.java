package com.legacy.aether.mixin;

import com.legacy.aether.player.IEntityPlayerAether;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.injectedmethods.RiftCPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayServer.class)
public class MixinNetHandlerPlayServer {

    @Shadow
    public EntityPlayerMP player;

    @Inject(method = "processCustomPayload", at = @At("RETURN"))
    public void processAetherPayload(CPacketCustomPayload packetIn, CallbackInfo ci) {
        RiftCPacketCustomPayload hook = (RiftCPacketCustomPayload) packetIn;
        ResourceLocation location = hook.getChannelName();

        if (location.getNamespace().equals("aether_legacy")) {
            String packetName = location.getPath();

            if ("open_accessory_gui".equals(packetName)) {
                this.player.displayGUIChest(((IEntityPlayerAether) this.player).getPlayerAether().getAccessories());
            } else if ("close_accessory_gui".equals(packetName)) {
                this.player.closeContainer();
            }
        }
    }

}