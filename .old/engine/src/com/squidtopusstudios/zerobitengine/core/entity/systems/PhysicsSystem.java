package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;

/**
 * Physics System for Ashley Entities
 */
public class PhysicsSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntityBase entity, float deltaTime) {
        if (!entity.isBox2D()) {
            if (((ZbeEntity) entity).getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                //entity.moveBy(0, -ZeroBit.getWorld().getGravity());
                //entity.increaseVelocity(0, -ZeroBit.getWorld().getGravity());
                if (!((ZbeEntity) entity).onGround && ((ZbeEntity) entity).getVelocity().y > -ZeroBit.getWorld().getGravity()) {
                    ComponentMappers.physics.get(entity).applyGravity = true;
                } else {
                    ComponentMappers.physics.get(entity).applyGravity = false;
                }
            }
        }
    }

}
