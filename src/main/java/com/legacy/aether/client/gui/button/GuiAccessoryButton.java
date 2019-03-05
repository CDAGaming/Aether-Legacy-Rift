package com.legacy.aether.client.gui.button;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.ingame.PlayerInventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.container.GuiAccessories;
import com.mojang.blaze3d.platform.GlStateManager;

public class GuiAccessoryButton extends ButtonWidget
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/inventory/button/cloud.png");

	private static final Identifier HOVERED_TEXTURE = Aether.locate("textures/gui/inventory/button/cloud_hover.png");

	private boolean hovered;

	private Screen screen;

	public GuiAccessoryButton(Screen screen, int xIn, int yIn)
	{
		super(xIn, yIn, 12, 12, "");

		this.screen = screen;
	}

	public GuiAccessoryButton setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;

		return this;
	}

	@Override
	public void onHoveredChanged(int x, int y, boolean hovered)
	{
		this.hovered = hovered;
	}

	@Override
	public void onPressed(double mouseX, double mouseY)
	{
		MinecraftClient mc = MinecraftClient.getInstance();

		if (mc.currentScreen instanceof GuiAccessories)
		{
			mc.openScreen(new PlayerInventoryScreen(mc.player));
		}
		else
		{
			mc.player.networkHandler.sendPacket(new CustomPayloadC2SPacket(Aether.locate("open_accessories"), new PacketByteBuf(Unpooled.buffer())));
		}
	}

	@Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
    	if (this.visible)
    	{
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            MinecraftClient.getInstance().getTextureManager().bindTexture(this.hovered ? HOVERED_TEXTURE : TEXTURE);
            GlStateManager.enableBlend();

            drawTexturedRect(this.x - 1, this.y, 0, 0, 14, 14, 14, 14);

            GlStateManager.popMatrix();
    	}
    }

}