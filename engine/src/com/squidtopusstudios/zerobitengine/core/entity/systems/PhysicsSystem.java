package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * Physics System for Ashley Entities
 */
public class PhysicsSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.isDynamic()) {
            if (entity.getCollidedEntities().isEmpty()) {
                entity.getVelocity().add(0, -ZeroBit.getWorld().getGravity() * deltaTime);
                //entity.getPosition().add(0, entity.getVelocity().y);
                entity.setPosition(entity.getPosition().x, entity.getPosition().y + entity.getVelocity().y);
            }
        }
    }

}
