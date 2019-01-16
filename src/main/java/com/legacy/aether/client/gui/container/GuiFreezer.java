package com.legacy.aether.client.gui.container;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.AetherBlockEntity;
import com.legacy.aether.container.ContainerFreezer;
import com.mojang.blaze3d.platform.GlStateManager;

public class GuiFreezer extends ContainerGui<ContainerFreezer>
{

	private static final Identifier TEXTURE = Aether.locate("textures/gui/freezer.png");

	private AetherBlockEntity freezer;

	public GuiFreezer(int syncId, PlayerInventory inventoryIn, AetherBlockEntity enchanterIn)
	{
		super(new ContainerFreezer(syncId, inventoryIn, enchanterIn), inventoryIn, new TranslatableTextComponent("container.inventory", new Object[0]));

		this.freezer = enchanterIn;
	}

	@Override
	public void initialize(MinecraftClient client, int width, int height)
	{
		super.initialize(client, width, height);

		client.player.container = this.container;
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
		String name = this.freezer.getName().getFormattedText();

		this.fontRenderer.draw(name, this.containerWidth / 2f - this.fontRenderer.getStringWidth(name) / 2f, 6, 4210752);
		this.fontRenderer.draw(this.name.getFormattedText(), 8, this.containerHeight - 96 + 2, 4210752);
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

		if (this.freezer.get(1) > 0)
		{
			i1 = this.getTimeRemaining(12);

			this.drawTexturedRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.getProgressScaled(24);

		this.drawTexturedRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

	private int getProgressScaled(int i)
	{
		if (this.freezer.get(2) == 0)
		{
			return 0;
		}

		return (this.freezer.get(0) * i) / this.freezer.get(2);
	}

	private int getTimeRemaining(int i)
	{
		return (this.freezer.get(1) * i) / 500;
	}

}