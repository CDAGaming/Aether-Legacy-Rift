package com.legacy.aether.client.gui.container;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.client.gui.ingame.PlayerInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.button.GuiAccessoryButton;
import com.legacy.aether.container.ContainerAccessories;
import com.mojang.blaze3d.platform.GlStateManager;

public class GuiAccessories extends ContainerScreen<ContainerAccessories>
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/inventory/accessories.png");

	public GuiAccessories(int syncId, PlayerEntity playerAetherIn)
	{
		super(new ContainerAccessories(syncId, playerAetherIn), playerAetherIn.inventory, new TranslatableTextComponent("container.crafting", new Object[0]));
	}

	@Override
	public void initialize(MinecraftClient client, int width, int height)
	{
		super.initialize(client, width, height);

		client.player.container = this.container;

		this.addButton(new GuiAccessoryButton(this, this.left + 8, this.top + 65));
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground();
    	super.render(mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

	@Override
    protected void drawForeground(int mouseX, int mouseY)
    {
        this.fontRenderer.draw(this.name.getFormattedText(), 115, 8, 4210752);
    }

	@Override
	protected void drawBackground(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.client.getTextureManager().bindTexture(TEXTURE);

		this.drawTexturedRect(this.left, this.top, 0, 0, 176, 166);

		PlayerInventoryScreen.drawEntity(this.left + 35, this.top + 75, 30, (float)(this.left + 51) - (float)mouseX, (float)(this.top + 75 - 50) - (float)mouseY, this.client.player);
	}

}