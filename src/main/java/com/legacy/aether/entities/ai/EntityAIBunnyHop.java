package com.legacy.aether.entities.ai;

import com.legacy.aether.entities.passive.EntityAetherAnimal;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIBunnyHop extends EntityAIBase {

    private EntityAetherAnimal animal;

    public EntityAIBunnyHop(EntityAetherAnimal animal) {
        this.animal = animal;
        this.setMutexBits(8);
    }

    @Override
    public boolean shouldExecute() {
        return this.animal.motionZ > 0.0D || this.animal.motionX > 0.0D || this.animal.onGround;
    }

    @Override
    public void updateTask() {
        if (this.animal.moveForward != 0.0F) {
            this.animal.getJumpHelper().setJumping();
        }
    }

}