package com.legacy.aether.client.gui.container;

import com.mojang.blaze3d.platform.GlStateManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.client.gui.ingame.InventoryGui;
import net.minecraft.client.network.packet.CustomPayloadClientPacket;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.button.GuiAccessoryButton;
import com.legacy.aether.inventory.container.ContainerAccessories;
import com.legacy.aether.player.IEntityPlayerAether;

public class GuiAccessories extends ContainerGui
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/inventory/accessories.png");

	public GuiAccessories(PlayerEntity playerAetherIn)
	{
		super(new ContainerAccessories(playerAetherIn));

		//this.allowUserInput = true; // TODO: 1.14
	}

	@Override
	public void initialize(MinecraftClient client, int width, int height)
	{
		super.initialize(client, width, height);

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
        this.fontRenderer.draw(I18n.translate("container.crafting"), 115, 8, 4210752);
    }

	@Override
	protected void drawBackground(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.client.getTextureManager().bindTexture(TEXTURE);

		this.drawTexturedRect(this.width / 2 - 88, this.height / 2 - 166 / 2, 0, 0, 176, 166);

		InventoryGui.drawEntity(this.left + 35, this.top + 75, 30, (float)(this.left + 51) - (float)mouseX, (float)(this.top + 75 - 50) - (float)mouseY, this.client.player);
	}

}