package com.legacy.aether.client.rendering.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.legacy.aether.Aether;
import com.legacy.aether.entities.projectile.EntityDart;
import com.legacy.aether.entities.projectile.EntityEnchantedDart;
import com.legacy.aether.entities.projectile.EntityGoldenDart;
import com.legacy.aether.entities.projectile.EntityPoisonNeedle;

public class DartRenderer extends Render<EntityDart>
{

	public DartRenderer(RenderManager renderManager) 
    {
		super(renderManager);

		this.shadowSize = 0.0F;
	}

	@Override
    public void doRender(EntityDart dart, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(dart, d, d1, d2, f, f1);

		if (dart.isInvisible())
		{
			return;
		}

        this.bindEntityTexture(dart);

        GlStateManager.pushMatrix();
        GlStateManager.translated(d, d1, d2);
        GlStateManager.rotatef(dart.prevRotationYaw + (dart.rotationYaw - dart.prevRotationYaw) * f1 - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(dart.prevRotationPitch + (dart.rotationPitch - dart.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder renderWorld = tessellator.getBuffer();

        byte i = 1;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + i * 10) / 32.0F;
        float f5 = (float)(5 + i * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + i * 10) / 32.0F;
        float f9 = (float)(10 + i * 10) / 32.0F;

        GlStateManager.enableRescaleNormal();

        float f11 = (float)dart.arrowShake - f1;

        if (f11 > 0.0F)
        {
        	GlStateManager.rotatef(-MathHelper.sin(f11 * 3.0F) * f11, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scalef(0.05625F, 0.05625F, 0.05625F);
        GlStateManager.translatef(-4.0F, 0.0F, 0.0F);
        GlStateManager.normal3f(0.05625F, 0.0F, 0.0F);

        renderWorld.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderWorld.pos(-7.0D, -2.0D, -2.0D).tex((double)f6, (double)f8).endVertex();
        renderWorld.pos(-7.0D, -2.0D, 2.0D).tex((double)f7, (double)f8).endVertex();
        renderWorld.pos(-7.0D, 2.0D, 2.0D).tex((double)f7, (double)f9).endVertex();
        renderWorld.pos(-7.0D, 2.0D, -2.0D).tex((double)f6, (double)f9).endVertex();
        tessellator.draw();

        GlStateManager.normal3f(-0.05625F, 0.0F, 0.0F);

        renderWorld.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderWorld.pos(-7.0D, 2.0D, -2.0D).tex((double)f6, (double)f8).endVertex();
        renderWorld.pos(-7.0D, 2.0D, 2.0D).tex((double)f7, (double)f8).endVertex();
        renderWorld.pos(-7.0D, -2.0D, 2.0D).tex((double)f7, (double)f9).endVertex();
        renderWorld.pos(-7.0D, -2.0D, -2.0D).tex((double)f6, (double)f9).endVertex();
        tessellator.draw();

        for (int var23 = 0; var23 < 5; ++var23)
        {
        	GlStateManager.rotatef(72.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.normal3f(0.0F, 0.0F, 0.05625F);

            renderWorld.begin(7, DefaultVertexFormats.POSITION_TEX);
            renderWorld.pos(-8.0D, -2.0D, 0.0D).tex((double)f2, (double)f4).endVertex();
            renderWorld.pos(8.0D, -2.0D, 0.0D).tex((double)f3, (double)f4).endVertex();
            renderWorld.pos(8.0D, 2.0D, 0.0D).tex((double)f3, (double)f5).endVertex();
            renderWorld.pos(-8.0D, 2.0D, 0.0D).tex((double)f2, (double)f5).endVertex();
            tessellator.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

	@Override
    protected ResourceLocation getEntityTexture(EntityDart entity)
    {
    	String base = entity instanceof EntityGoldenDart ? "golden" : entity instanceof EntityEnchantedDart ? "enchanted" : "poison";
        return Aether.locate("textures/entity/projectile/dart/" + base + (entity instanceof EntityPoisonNeedle ? "_needle" : "_dart") + ".png");
    }

}