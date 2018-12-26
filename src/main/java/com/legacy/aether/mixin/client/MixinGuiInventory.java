package com.legacy.aether.mixin.client;

import net.minecraft.client.gui.ingame.AbstractGuiInventory;
import net.minecraft.client.gui.ingame.InventoryGui;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.client.gui.button.GuiAccessoryButton;

@Mixin(InventoryGui.class)
public abstract class MixinGuiInventory extends AbstractGuiInventory
{
    public MixinGuiInventory(PlayerEntity player)
    {
        super(player.containerPlayer);

        //this.allowUserInput = true;
    }

    @Inject(method = "onInitialized", at = @At("RETURN"))
	protected void initAccessoryButton(CallbackInfo ci)
	{
		//GuiInventory instance = (GuiInventory) (Object) this;

		this.addButton(new GuiAccessoryButton(this, this.left + 26, this.top + 65));
	}

}
