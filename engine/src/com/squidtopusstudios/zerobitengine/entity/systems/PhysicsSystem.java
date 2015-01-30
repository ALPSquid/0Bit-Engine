package com.squidtopusstudios.zerobitengine.entity.systems;

import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.entity.DynamicEntity;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;

/**
 * Physics System for Ashley Entities
 */
public class PhysicsSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        /*if (!entity.isBox2D()) {
            if (((DynamicEntity) entity).getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                //entity.moveBy(0, -ZeroBit.getWorld().getGravity());
                //entity.increaseVelocity(0, -ZeroBit.getWorld().getGravity());
                if (!((DynamicEntity) entity).onGround && ((DynamicEntity) entity).getVelocity().y > -ZeroBit.getWorld().getGravity()) {
                    ComponentMappers.physics.get(entity).applyGravity = true;
                } else {
                    ComponentMappers.physics.get(entity).applyGravity = false;
                }
            }
        }*/
    }

}
