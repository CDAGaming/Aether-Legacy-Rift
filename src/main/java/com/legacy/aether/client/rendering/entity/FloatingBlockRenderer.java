package com.legacy.aether.client.rendering.entity;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FloatingBlockRenderer extends Render<EntityFloatingBlock> {

    public FloatingBlockRenderer(RenderManager renderManagerIn) {
        super(renderManagerIn);

        this.shadowSize = 0.5F;
    }

    public boolean shouldRender(EntityFloatingBlock livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntity, camera, camX, camY, camZ);
    }

    @Override
    public void doRender(EntityFloatingBlock entity, double x, double y, double z, float entityYaw, float partialTicks) {
        IBlockState iblockstate = entity.getBlockstate();

        if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {
            World world = entity.getWorldObj();

            if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
                this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();

                if (this.renderOutlines) {
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                }

                bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
                BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                GlStateManager.translate((float) (x - (double) blockpos.getX() - 0.5D), (float) (y - (double) blockpos.getY()), (float) (z - (double) blockpos.getZ() - 0.5D));
                BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                blockrendererdispatcher.getBlockModelRenderer().func_199324_a(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, new Random(), iblockstate.getPositionRandom(entity.getOrigin()));
                tessellator.draw();

                if (this.renderOutlines) {
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                }

                GlStateManager.enableLighting();
                GlStateManager.popMatrix();

                super.doRender(entity, x, y, z, entityYaw, partialTicks);
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFloatingBlock entityIn) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

}