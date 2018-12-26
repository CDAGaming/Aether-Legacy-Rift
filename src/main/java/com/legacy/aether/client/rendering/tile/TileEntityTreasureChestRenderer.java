package com.legacy.aether.client.rendering.tile;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelChest;
import net.minecraft.client.renderer.entity.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import com.legacy.aether.Aether;
import com.legacy.aether.tileentity.TileEntityTreasureChest;

public class TileEntityTreasureChestRenderer extends TileEntityRenderer<TileEntityTreasureChest>
{

	private static final ResourceLocation TEXTURE_DOUBLE = Aether.locate("textures/tile_entities/treasure_chest_large.png");

	private static final ResourceLocation TEXTURE_SINGLE = Aether.locate("textures/tile_entities/treasure_chest.png");

	private final ModelChest chestModel = new ModelChest();

	private final ModelChest largeChestModel = new ModelLargeChest();

    public TileEntityTreasureChestRenderer()
    {

    }

    @Override
    public void render(TileEntityTreasureChest tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage)
    {
		if (tileEntityIn == null)
		{
			TileEntityRendererDispatcher.instance.render(new TileEntityTreasureChest(), 0.0D, 0.0D, 0.0D, 0.0F);

			return;
		}

        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        IBlockState iblockstate = tileEntityIn.hasWorld() ? tileEntityIn.getBlockState() : (IBlockState)Blocks.CHEST.getDefaultState().with(BlockChest.FACING, EnumFacing.SOUTH);
        ChestType chesttype = iblockstate.has(BlockChest.TYPE) ? (ChestType)iblockstate.get(BlockChest.TYPE) : ChestType.SINGLE;

        if (chesttype != ChestType.LEFT)
        {
            boolean flag = chesttype != ChestType.SINGLE;
            ModelChest modelchest = this.getChestModel(destroyStage, flag);

            if (destroyStage >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(flag ? 8.0F : 4.0F, 4.0F, 1.0F);
                GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            }
            else
            {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float f = ((EnumFacing)iblockstate.get(BlockChest.FACING)).getHorizontalAngle();

            if ((double)Math.abs(f) > 1.0E-5D)
            {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            float angle = ((IChestLid)tileEntityIn).getLidAngle(partialTicks);
            angle = 1.0F - angle;
            angle = 1.0F - angle * angle * angle;
            modelchest.getLid().rotateAngleX = -(angle * ((float)Math.PI / 2F));

            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            if (destroyStage >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }

    private ModelChest getChestModel(int destroyStage, boolean isDouble)
    {
        ResourceLocation resourcelocation;

        if (destroyStage >= 0)
        {
            resourcelocation = DESTROY_STAGES[destroyStage];
        }
        else
        {
            resourcelocation = isDouble ? TEXTURE_DOUBLE : TEXTURE_SINGLE;
        }

        this.bindTexture(resourcelocation);

        return isDouble ? this.largeChestModel : this.chestModel;
    }

}