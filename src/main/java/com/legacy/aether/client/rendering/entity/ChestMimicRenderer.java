package com.legacy.aether.client.rendering.entity;

import com.legacy.aether.Aether;
import com.legacy.aether.client.model.ChestMimicModel;
import com.legacy.aether.entities.hostile.EntityChestMimic;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import java.util.Calendar;

public class ChestMimicRenderer extends RenderLiving<EntityChestMimic> {

    private static final ResourceLocation TEXTURE_HEAD = Aether.locate("textures/entity/mimic/mimic_head_christmas.png");

    private static final ResourceLocation TEXTURE_LEGS = Aether.locate("textures/entity/mimic/mimic_legs_christmas.png");

    private ChestMimicModel modelbase;

    public ChestMimicRenderer(RenderManager renderManager) {
        super(renderManager, new ChestMimicModel(), 0.0F);

        this.modelbase = (ChestMimicModel) this.getMainModel();
    }

    @Override
    public void doRender(EntityChestMimic mimic, double x, double y, double z, float pitch, float yaw) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(180.0F - pitch, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        this.modelbase.setRotationAngles(0, 0F, 0.0F, 0.0F, 0.0F, 0.0F, mimic);

        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
            this.bindTexture(TEXTURE_HEAD);
            this.modelbase.renderHead(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, mimic);
            this.bindTexture(TEXTURE_LEGS);
            this.modelbase.renderLegs(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, mimic);
        } else {
            this.bindTexture(TEXTURE_HEAD);
            this.modelbase.renderHead(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, mimic);
            this.bindTexture(TEXTURE_LEGS);
            this.modelbase.renderLegs(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, mimic);
        }

        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityChestMimic entity) {
        return null;
    }

}