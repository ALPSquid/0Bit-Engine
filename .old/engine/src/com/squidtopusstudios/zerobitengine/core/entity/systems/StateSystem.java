package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;

/**
 * State System for Ashley Entities.
 */
public class StateSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntityBase entity, float deltaTime) {
        if (!entity.isBox2D()) {
            ZbeEntity zbeEntity = (ZbeEntity)entity;
            if (!zbeEntity.getVelocity().isZero()) {
                zbeEntity.setState(ZeroBit.EntityState.MOVING);
            } else {
                zbeEntity.setState(ZeroBit.EntityState.IDLE);
            }

            if (zbeEntity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                if (zbeEntity.getVelocity().y > 0 && !zbeEntity.onGround && !zbeEntity.isCollidingTop()) {
                    zbeEntity.setState(ZeroBit.EntityState.JUMPING);
                } else if (zbeEntity.getVelocity().y < 0 && !zbeEntity.onGround && !zbeEntity.isCollidingTop()) {
                    zbeEntity.setState(ZeroBit.EntityState.FALLING);
                }
            }
        }
    }
}
