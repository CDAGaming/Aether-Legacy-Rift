package com.legacy.aether.client.rendering;

import com.legacy.aether.client.rendering.entity.*;
import com.legacy.aether.entities.block.EntityFloatingBlock;
import com.legacy.aether.entities.hostile.EntityAechorPlant;
import com.legacy.aether.entities.hostile.EntityChestMimic;
import com.legacy.aether.entities.hostile.EntityCockatrice;
import com.legacy.aether.entities.passive.EntityAerbunny;
import com.legacy.aether.entities.passive.EntityFlyingCow;
import com.legacy.aether.entities.passive.EntityMoa;
import com.legacy.aether.entities.passive.EntityPhyg;
import com.legacy.aether.entities.projectile.EntityDart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.dimdev.rift.listener.client.EntityRendererAdder;

import java.util.Map;

public class AetherEntityRenders implements EntityRendererAdder {

    @Override
    public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) {
        entityRenderMap.put(EntityDart.class, new DartRenderer(renderManager));
        entityRenderMap.put(EntityAerbunny.class, new AerbunnyRenderer(renderManager));
        entityRenderMap.put(EntityMoa.class, new MoaRenderer(renderManager));
        entityRenderMap.put(EntityAechorPlant.class, new AechorPlantRenderer(renderManager));
        entityRenderMap.put(EntityFlyingCow.class, new FlyingCowRenderer(renderManager));
        entityRenderMap.put(EntityPhyg.class, new PhygRenderer(renderManager));
        entityRenderMap.put(EntityCockatrice.class, new CockatriceRenderer(renderManager));
        entityRenderMap.put(EntityChestMimic.class, new ChestMimicRenderer(renderManager));
        entityRenderMap.put(EntityFloatingBlock.class, new FloatingBlockRenderer(renderManager));
    }

}
