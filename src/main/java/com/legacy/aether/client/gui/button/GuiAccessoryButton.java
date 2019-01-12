package com.legacy.aether.client.gui.button;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ingame.PlayerInventoryGui;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.server.network.packet.CustomPayloadServerPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.container.GuiAccessories;
import com.mojang.blaze3d.platform.GlStateManager;

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
		MinecraftClient mc = MinecraftClient.getInstance();

		if (mc.currentGui instanceof GuiAccessories)
		{
			mc.openGui(new PlayerInventoryGui(mc.player));
		}
		else
		{
			mc.player.networkHandler.sendPacket(new CustomPayloadServerPacket(Aether.locate("open_accessories"), new PacketByteBuf(Unpooled.buffer())));
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
            int i = this.hovered ? 2 : 1;
            MinecraftClient.getInstance().getTextureManager().bindTexture(i == 2 ? HOVERED_TEXTURE : TEXTURE);
            GlStateManager.enableBlend();

            drawTexturedRect(this.x - 1, this.y, 0, 0, 14, 14, 14, 14);

            GlStateManager.popMatrix();
    	}
    }

}