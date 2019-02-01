package com.legacy.aether.mixin.client;

import net.minecraft.client.gui.ingame.AbstractPlayerInventoryScreen;
import net.minecraft.client.gui.ingame.PlayerInventoryScreen;
import net.minecraft.container.PlayerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.legacy.aether.client.gui.button.GuiAccessoryButton;

@Mixin(PlayerInventoryScreen.class)
public abstract class MixinGuiInventory extends AbstractPlayerInventoryScreen<PlayerContainer>
{
    public MixinGuiInventory(PlayerEntity player)
    {
        super(player.containerPlayer, player.inventory, new TranslatableTextComponent("container.crafting", new Object[0]));

        //this.allowUserInput = true;
    }

    @Inject(method = "onInitialized", at = @At("RETURN"))
	protected void initAccessoryButton(CallbackInfo ci)
	{
		//GuiInventory instance = (GuiInventory) (Object) this;

		this.addButton(new GuiAccessoryButton(this, this.left + 26, this.top + 65));
	}

}
