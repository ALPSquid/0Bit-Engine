package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * Physics System for Ashley Entities
 */
public class PhysicsSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER)) {
            entity.moveBy(0, -ZeroBit.getWorld().getGravity());
        }
    }

}
