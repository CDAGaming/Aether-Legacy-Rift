package com.legacy.aether.mixin.client;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.client.gui.button.GuiAccessoryButton;

@Mixin(value = GuiInventory.class)
public abstract class MixinGuiInventory extends InventoryEffectRenderer
{
    public MixinGuiInventory(EntityPlayer player)
    {
        super(player.inventoryContainer);

        this.allowUserInput = true;
    }

    @Inject(method = "initGui", at = @At("RETURN"))
	protected void initAccessoryButton(CallbackInfo ci)
	{
		//GuiInventory instance = (GuiInventory) (Object) this;

		this.addButton(new GuiAccessoryButton(this, this.guiLeft + 26, this.guiTop + 65));
	}

}
