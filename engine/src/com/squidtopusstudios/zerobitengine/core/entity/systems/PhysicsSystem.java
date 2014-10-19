package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * Physics System for Ashley Entities
 */
public class PhysicsSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
            //entity.moveBy(0, -ZeroBit.getWorld().getGravity());
            //entity.increaseVelocity(0, -ZeroBit.getWorld().getGravity());
            if (!entity.onGround && entity.getVelocity().y > -ZeroBit.getWorld().getGravity()) {
                ComponentMappers.physics.get(entity).applyGravity = true;
            } else {
                ComponentMappers.physics.get(entity).applyGravity = false;
            }
        }
    }

}
