package com.legacy.aether.client.gui.button;

import com.mojang.blaze3d.platform.GlStateManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ingame.InventoryGui;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.packet.CustomPayloadClientPacket;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.container.GuiAccessories;
import net.minecraft.util.PacketByteBuf;

public class GuiAccessoryButton extends ButtonWidget
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/inventory/button/cloud.png");

	private static final Identifier HOVERED_TEXTURE = Aether.locate("textures/gui/inventory/button/cloud_hover.png");

	private Gui screen;

	public GuiAccessoryButton(Gui screen, int xIn, int yIn)
	{
		super(18067, xIn, yIn, 12, 12, "");

		this.screen = screen;
	}

	public GuiAccessoryButton setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;

		return this;
	}

	@Override
	public void onPressed(double mouseX, double mouseY)
	{
		if (this.screen instanceof GuiAccessories)
		{
			MinecraftClient.getInstance().openGui(new InventoryGui(MinecraftClient.getInstance().player));
		}
		else
		{
			MinecraftClient.getInstance().player.networkHandler.sendPacket(new CustomPayloadClientPacket(Aether.locate("open_accessory_gui"), new PacketByteBuf(Unpooled.buffer())));
		}
	}

	@Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

    	if (this.visible)
    	{
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            int i = this.getHoverState(this.hovered);
            MinecraftClient.getInstance().getTextureManager().bindTexture(i == 2 ? HOVERED_TEXTURE : TEXTURE);
            GlStateManager.enableBlend();

            drawTexturedRect(this.x - 1, this.y, 0, 0, 14, 14, 14, 14);

            // TODO: UP UP - 1.14
            GlStateManager.popMatrix();
    	}
    }

}