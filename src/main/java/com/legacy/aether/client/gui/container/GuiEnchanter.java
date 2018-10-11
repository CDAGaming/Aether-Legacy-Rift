package com.legacy.aether.client.gui.container;

import com.legacy.aether.Aether;
import com.legacy.aether.inventory.container.ContainerEnchanter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiEnchanter extends GuiContainer {

    private static final ResourceLocation TEXTURE = Aether.locate("textures/gui/enchanter.png");

    private IInventory enchanter;

    public GuiEnchanter(InventoryPlayer inventoryIn, IInventory enchanterIn) {
        super(new ContainerEnchanter(inventoryIn, enchanterIn));

        this.enchanter = enchanterIn;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = this.enchanter.getName().getFormattedText();

        this.fontRenderer.drawString(name, this.xSize / 2f - this.fontRenderer.getStringWidth(name) / 2f, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float arg0, int arg1, int arg2) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int i1;

        if (this.enchanter.getField(1) < 0) {
            i1 = this.getTimeRemaining(12);

            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.getProgressScaled(24);

        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }

    private int getProgressScaled(int i) {
        if (this.enchanter.getField(2) == 0) {
            return 0;
        }

        return (this.enchanter.getField(0) * i) / this.enchanter.getField(2);
    }

    private int getTimeRemaining(int i) {
        return (this.enchanter.getField(1) * i) / 500;
    }

}