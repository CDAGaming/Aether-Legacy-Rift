package com.legacy.aether.client.gui.container;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.ContainerGui;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.container.ContainerEnchanter;

public class GuiEnchanter extends ContainerGui
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/enchanter.png");

	private Inventory enchanter;

	public GuiEnchanter(Inventory inventoryIn, Inventory enchanterIn)
	{
		super(new ContainerEnchanter(inventoryIn, enchanterIn));

		this.enchanter = enchanterIn;
	}

	@Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground();
    	super.draw(mouseX, mouseY, partialTicks);
        this.drawMousoverTooltip(mouseX, mouseY);
    }

	@Override
	protected void drawForeground(int par1, int par2)
	{
		String name = this.enchanter.getName().getFormattedText();

		this.fontRenderer.draw(name, this.containerWidth / 2f - this.fontRenderer.getStringWidth(name) / 2f, 6, 4210752);
		this.fontRenderer.draw(I18n.translate("container.inventory"), 8, this.containerHeight - 96 + 2, 4210752);
	}

	@Override
	protected void drawBackground(float arg0, int arg1, int arg2)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.client.getTextureManager().bindTexture(TEXTURE);

		int k = (this.width - this.containerWidth) / 2;
		int l = (this.height - this.containerHeight) / 2;

		this.drawTexturedRect(k, l, 0, 0, this.containerWidth, this.containerHeight);

		int i1;

		if (this.enchanter.getInvProperty(1) < 0)
		{
			i1 = this.getTimeRemaining(12);

			this.drawTexturedRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.getProgressScaled(24);

		this.drawTexturedRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

	private int getProgressScaled(int i)
	{
		if (this.enchanter.getInvProperty(2) == 0)
		{
			return 0;
		}

		return (this.enchanter.getInvProperty(0) * i) / this.enchanter.getInvProperty(2);
	}

	private int getTimeRemaining(int i)
	{
		return (this.enchanter.getInvProperty(1) * i) / 500;
	}

}