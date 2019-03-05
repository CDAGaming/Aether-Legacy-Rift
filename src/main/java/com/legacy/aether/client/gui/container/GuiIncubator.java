package com.legacy.aether.client.gui.container;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.AetherBlockEntity;
import com.legacy.aether.blocks.entity.IncubatorBlockEntity;
import com.legacy.aether.container.ContainerIncubator;
import com.mojang.blaze3d.platform.GlStateManager;

public class GuiIncubator extends ContainerScreen<ContainerIncubator>
{

	private AetherBlockEntity incubator;

	private static final Identifier TEXTURE_INCUBATOR = Aether.locate("textures/gui/incubator.png");

	public GuiIncubator(int syncId, PlayerInventory inventoryIn, AetherBlockEntity incubatorIn)
	{
		super(new ContainerIncubator(syncId, inventoryIn, incubatorIn), inventoryIn, new TranslatableTextComponent("container.inventory", new Object[0]));

		this.incubator = (AetherBlockEntity) incubatorIn;
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
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

	@Override
	protected void drawForeground(int par1, int par2)
	{
		String incubatorName = this.incubator.getName().getFormattedText();
		IncubatorBlockEntity entity = ((IncubatorBlockEntity)this.incubator);

		if (entity.ownerName != null)
		{
			incubatorName = ((IncubatorBlockEntity)this.incubator).ownerName + "'s " + incubatorName;
		}

		this.fontRenderer.draw(incubatorName, this.screenWidth / 2 - this.fontRenderer.getStringWidth(incubatorName) / 2, 6, 4210752);
		this.fontRenderer.draw(this.name.getFormattedText(), 8, this.screenHeight - 96 + 2, 0x404040);
	}

	@Override
	protected void drawBackground(float f, int ia, int ib)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.client.getTextureManager().bindTexture(TEXTURE_INCUBATOR);

		int j = (this.width - this.screenWidth) / 2;
		int k = (this.height - this.screenHeight) / 2;

		this.drawTexturedRect(j, k, 0, 0, this.screenWidth, this.screenHeight);

		if (this.incubator.get(1) > 0)
		{
			int l = this.getTimeRemaining(12);

			this.drawTexturedRect(j + 74, (k + 47) - l, 176, 12 - l, 14, l + 2);
		}

		int i1 = this.getProgressScaled(54);

		this.drawTexturedRect(j + 103, k + 70 - i1, 179, 70 - i1, 10, i1);
	}

	public int getProgressScaled(int i)
	{
		return (this.incubator.get(1) * i) / 5700;
	}

	public int getTimeRemaining(int i)
	{
		return (this.incubator.get(0) * i) / 500;
	}

}