package com.squidtopusstudios.zerobitengine.entity.systems;

import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.DynamicEntity;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;

/**
 * State System for Ashley Entities.
 */
public class StateSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        /*if (!entity.isBox2D()) {
            DynamicEntity dynamicEntity = (DynamicEntity)entity;
            if (!dynamicEntity.getVelocity().isZero()) {
                dynamicEntity.setState(ZeroBit.EntityState.MOVING);
            } else {
                dynamicEntity.setState(ZeroBit.EntityState.IDLE);
            }

            if (dynamicEntity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                if (dynamicEntity.getVelocity().y > 0 && !dynamicEntity.onGround && !dynamicEntity.isCollidingTop()) {
                    dynamicEntity.setState(ZeroBit.EntityState.JUMPING);
                } else if (dynamicEntity.getVelocity().y < 0 && !dynamicEntity.onGround && !dynamicEntity.isCollidingTop()) {
                    dynamicEntity.setState(ZeroBit.EntityState.FALLING);
                }
            }
        }*/
    }
}
