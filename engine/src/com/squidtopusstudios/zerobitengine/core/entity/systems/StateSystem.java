package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * State System for Ashley Entities.
 */
public class StateSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (!entity.getVelocity().isZero()) {
            entity.setState(ZeroBit.ENTITY_STATE.MOVING);
        }
        else {
            entity.setState(ZeroBit.ENTITY_STATE.IDLE);
        }

        if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER)) {
            if (entity.getVelocity().y > 0 && !entity.onGround && !entity.isCollidingTop()) {
                entity.setState(ZeroBit.ENTITY_STATE.JUMPING);
            }
            else if (entity.getVelocity().y < 0 && !entity.onGround && !entity.isCollidingTop()) {
                entity.setState(ZeroBit.ENTITY_STATE.FALLING);
            }
        }
    }
}
