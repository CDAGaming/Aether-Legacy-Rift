package com.legacy.aether.client.gui.button;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.container.GuiAccessories;

public class GuiAccessoryButton extends GuiButton
{

	private static final ResourceLocation TEXTURE = Aether.locate("textures/gui/inventory/button/cloud.png");

	private static final ResourceLocation HOVERED_TEXTURE = Aether.locate("textures/gui/inventory/button/cloud_hover.png");

	private GuiScreen screen;

	public GuiAccessoryButton(GuiScreen screen, int xIn, int yIn)
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
	public void mousePressed(double mouseX, double mouseY)
	{
		if (this.screen instanceof GuiAccessories)
		{
			Minecraft.getInstance().displayGuiScreen(new GuiInventory(Minecraft.getInstance().player));
		}
		else
		{
			Minecraft.getInstance().player.connection.sendPacket(new CPacketCustomPayload(Aether.locate("open_accessory_gui"), new PacketBuffer(Unpooled.buffer())));
		}
	}

	@Override
    public void drawButton(int mouseX, int mouseY, float partialTicks)
    {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

    	if (this.visible)
    	{
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.pushMatrix();
            int i = this.getHoverState(this.hovered);
            Minecraft.getInstance().getTextureManager().bindTexture(i == 2 ? HOVERED_TEXTURE : TEXTURE);
            GlStateManager.enableBlend();

            drawModalRectWithCustomSizedTexture(this.x - 1, this.y, 0, 0, 14, 14, 14, 14);

            GlStateManager.popMatrix();
    	}
    }

}