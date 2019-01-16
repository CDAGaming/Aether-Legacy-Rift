package com.legacy.aether.client.gui.container;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.client.gui.ingame.PlayerInventoryGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.button.GuiAccessoryButton;
import com.legacy.aether.container.ContainerAccessories;
import com.mojang.blaze3d.platform.GlStateManager;

public class GuiAccessories extends ContainerGui<ContainerAccessories>
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/inventory/accessories.png");

	public GuiAccessories(int syncId, PlayerEntity playerAetherIn)
	{
		super(new ContainerAccessories(syncId, playerAetherIn), playerAetherIn.inventory, new TranslatableTextComponent("container.crafting", new Object[0]));
		//this.allowUserInput = true; // TODO: 1.14
	}

	@Override
	public void initialize(MinecraftClient client, int width, int height)
	{
		super.initialize(client, width, height);

		client.player.container = this.container;

		this.addButton(new GuiAccessoryButton(this, this.left + 8, this.top + 65));
	}

	@Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground();
    	super.draw(mouseX, mouseY, partialTicks);
        this.drawMousoverTooltip(mouseX, mouseY);
    }

	@Override
    protected void drawForeground(int mouseX, int mouseY)
    {
        this.fontRenderer.draw(this.field_17411.getFormattedText(), 115, 8, 4210752);
    }

	@Override
	protected void drawBackground(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.client.getTextureManager().bindTexture(TEXTURE);

		this.drawTexturedRect(this.width / 2 - 88, this.height / 2 - 166 / 2, 0, 0, 176, 166);

		PlayerInventoryGui.drawEntity(this.left + 35, this.top + 75, 30, (float)(this.left + 51) - (float)mouseX, (float)(this.top + 75 - 50) - (float)mouseY, this.client.player);
	}

}