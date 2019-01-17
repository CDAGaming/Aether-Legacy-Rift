package com.legacy.aether.client.rendering.entity;

import java.util.Random;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.mojang.blaze3d.platform.GlStateManager;

public class FloatingBlockRenderer extends EntityRenderer<EntityFloatingBlock>
{

	public FloatingBlockRenderer(EntityRenderDispatcher renderManagerIn)
	{
		super(renderManagerIn);

		this.field_4672 = 0.5F;
	}

	@Override
    public void render(EntityFloatingBlock entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        BlockState iblockstate = entity.getBlockstate();

        if (iblockstate.getRenderType() == BlockRenderType.MODEL)
        {
            World world = entity.getWorldObj();

            if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != BlockRenderType.INVISIBLE)
            {
                this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);

                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBufferBuilder();

                if (this.renderOutlines)
                {
                    GlStateManager.enableColorMaterial();
                    GlStateManager.setupSolidRenderingTextureCombine(this.getOutlineColor(entity));
                }

                bufferbuilder.begin(7, VertexFormats.POSITION_COLOR_UV_LMAP);
                BlockPos blockpos = new BlockPos(entity.x, entity.getBoundingBox().maxY, entity.z);
                GlStateManager.translatef((float)(x - (double)blockpos.getX() - 0.5D), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5D));
                BlockRenderManager blockrendererdispatcher = MinecraftClient.getInstance().getBlockRenderManager();
                blockrendererdispatcher.getModelRenderer().tesselate(world, blockrendererdispatcher.getModel(iblockstate), iblockstate, blockpos, bufferbuilder, false, new Random(), iblockstate.getRenderingSeed(entity.getOrigin()));
                tessellator.draw();

                if (this.renderOutlines)
                {
                    GlStateManager.tearDownSolidRenderingTextureCombine();
                    GlStateManager.disableColorMaterial();
                }

                GlStateManager.enableLighting();
                GlStateManager.popMatrix();

                super.render(entity, x, y, z, entityYaw, partialTicks);
            }
        }
    }

	@Override
	protected Identifier getTexture(EntityFloatingBlock entityIn)
	{
        return TextureManager.field_5285;
	}

}