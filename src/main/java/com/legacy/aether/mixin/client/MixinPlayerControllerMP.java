package com.legacy.aether.mixin.client;

import com.legacy.aether.item.tool.AetherToolType;
import com.legacy.aether.item.tool.IAetherTool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {

    @Shadow
    private GameType currentGameType;

    /**
     * @author Modding Legacy
     */
    @Overwrite
    public float getBlockReachDistance() {
        ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();

        if (stack.getItem() instanceof IAetherTool && ((IAetherTool) stack.getItem()).getMaterial() == AetherToolType.Valkyrie) {
            return this.currentGameType.isCreative() ? 14.5F : 10.0F;
        }

        return this.currentGameType.isCreative() ? 15.0F : 14.5F;
    }

}