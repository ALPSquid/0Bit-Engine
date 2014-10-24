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
            if (!((ZbeEntity) entity).getVelocity().isZero()) {
                entity.setState(ZeroBit.EntityState.MOVING);
            } else {
                entity.setState(ZeroBit.EntityState.IDLE);
            }

            if (((ZbeEntity) entity).getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                if (((ZbeEntity) entity).getVelocity().y > 0 && !((ZbeEntity) entity).onGround && !((ZbeEntity) entity).isCollidingTop()) {
                    entity.setState(ZeroBit.EntityState.JUMPING);
                } else if (((ZbeEntity) entity).getVelocity().y < 0 && !((ZbeEntity) entity).onGround && !((ZbeEntity) entity).isCollidingTop()) {
                    entity.setState(ZeroBit.EntityState.FALLING);
                }
            }
        }
    }
}
