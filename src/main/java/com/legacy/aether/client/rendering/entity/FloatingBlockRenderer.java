package com.legacy.aether.client.rendering.entity;

import java.util.Random;

import com.legacy.aether.entities.block.EntityFloatingBlock;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import com.sun.prism.TextureMap;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.RenderTypeBlock;
import net.minecraft.class_856;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexBuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.Texture;
import net.minecraft.client.texture.TextureCache;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FloatingBlockRenderer extends EntityRenderer<EntityFloatingBlock>
{

	public FloatingBlockRenderer(EntityRenderDispatcher renderManagerIn)
	{
		super(renderManagerIn);

		//this.shadowSize = 0.5F;
	}

    public boolean method_3933(EntityFloatingBlock livingEntity, class_856 camera, double camX, double camY, double camZ)
    {
    	return super.method_3933(livingEntity, camera, camX, camY, camZ);
    }

	@Override
    public void method_3936(EntityFloatingBlock entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        BlockState iblockstate = entity.getBlockstate();

        if (iblockstate.getRenderType() == RenderTypeBlock.MODEL)
        {
            World world = entity.getWorldObj();

            if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != RenderTypeBlock.NONE)
            {
                this.bindTexture(TextureManager.field_5285); // TODO: VERIFY for LOCATION_BLOCKS_TEXTURE

                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();

                Tessellator tessellator = Tessellator.getInstance();
                VertexBuffer bufferbuilder = tessellator.getVertexBuffer();

                if (this.field_4674) // TODO: Verify renderOutlines
                {
                    GlStateManager.enableColorMaterial();
                    //GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                }

                bufferbuilder.begin(7, VertexFormats.POSITION); // TODO: Verify for BLOCK VertexFormat
                BlockPos blockpos = new BlockPos(entity.x, entity.getBoundingBox().maxY, entity.z);
                GlStateManager.translatef((float)(x - (double)blockpos.getX() - 0.5D), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5D));
                BlockRenderManager blockrendererdispatcher = MinecraftClient.getInstance().getBlockRenderManager();
                blockrendererdispatcher.getRenderer().tesselate(world, blockrendererdispatcher.getModel(iblockstate), iblockstate, blockpos, bufferbuilder, false, new Random(), iblockstate.getPosRandom(entity.getOrigin()));
                tessellator.draw();

                if (this.field_4674)
                {
                    //GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                }

                GlStateManager.enableLighting();
                GlStateManager.popMatrix();

                super.method_3936(entity, x, y, z, entityYaw, partialTicks);
            }
        }
    }

	@Override
	protected Identifier getTexture(EntityFloatingBlock entityIn)
	{
        return TextureManager.field_5285;
	}

}