package com.legacy.aether.client.gui.container;

import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.button.GuiAccessoryButton;
import com.legacy.aether.inventory.container.ContainerAccessories;
import com.legacy.aether.player.IEntityPlayerAether;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiAccessories extends GuiContainer {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/gui/inventory/accessories.png");

    public GuiAccessories(IEntityPlayerAether playerAetherIn) {
        super(new ContainerAccessories(playerAetherIn));

        this.allowUserInput = true;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        Minecraft.getMinecraft().player.connection.sendPacket(new CPacketCustomPayload(Aether.locate("close_accessory_gui"), new PacketBuffer(Unpooled.buffer())));
    }

    @Override
    public void initGui() {
        super.initGui();

        this.addButton(new GuiAccessoryButton(this, this.guiLeft + 8, this.guiTop + 65));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(I18n.format("container.crafting"), 115, 8, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        this.drawTexturedModalRect(this.width / 2 - 88, this.height / 2 - 166 / 2, 0, 0, 176, 166);

        GuiInventory.drawEntityOnScreen(this.guiLeft + 35, this.guiTop + 75, 30, (float) (this.guiLeft + 51) - (float) mouseX, (float) (this.guiTop + 75 - 50) - (float) mouseY, this.mc.player);
    }

}