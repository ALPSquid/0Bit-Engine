package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.CollisionComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.PhysicsComponent;

/**
 * handles movement for Ashley Entities
 */
public class MovementSystem extends ZbeSystem {

    private ZbeEntity solidEntity;

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        ComponentMappers.position.get(entity).prevPosition = entity.getPosition();
        if (ComponentMappers.physics.get(entity).applyGravity) {
            entity.increaseVelocity(0, -ZeroBit.getWorld().getGravity() * deltaTime);
        }
        moveBy(entity, entity.getVelocity().x, entity.getVelocity().y, true);
    }

    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param entity the target entity to move
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * @param interpolate whether to interpolate the movement.
     *                    If true, x and y are considered to be the amount of movement in 1 second
     */
    public void moveBy(ZbeEntity entity, float x, float y, boolean interpolate) {
        x *= ZeroBit.getWorld().getPixelsPerUnit();
        y *= ZeroBit.getWorld().getPixelsPerUnit();

        if (interpolate) {
            x *= Gdx.graphics.getDeltaTime();
            y *= Gdx.graphics.getDeltaTime();
        }
        if (entity.collisionsEnabled()) {
            entity.getComponent(CollisionComponent.class).collidedRight = false;
            entity.getComponent(CollisionComponent.class).collidedLeft = false;
            entity.getComponent(CollisionComponent.class).collidedTop = false;
            entity.getComponent(CollisionComponent.class).collidedBottom = false;
            // Move x
            for (String solidType : entity.getSolidTypes()) {
                solidEntity = entity.collides(entity, solidType, entity.getX() + x, entity.getY());
                if (solidEntity != null) {
                    if ( x > 0) {
                        // Moving right
                        x = solidEntity.getX() - (entity.getX() + entity.getWidth());
                        entity.getComponent(CollisionComponent.class).collidedRight = true;
                    } else if (x < 0) {
                        // Moving left
                        x = (solidEntity.getX() + solidEntity.getWidth()) - entity.getX();
                        entity.getComponent(CollisionComponent.class).collidedLeft = true;
                    }
                    break;
                }
            }
            for (String solidType : entity.getSolidTypes()) {
                // move Y
                if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER)) {
                    // fix rounding errors with a gravity bias
                    if (y == 0) {
                        y = -0.01f;
                    }
                }
                solidEntity = entity.collides(entity, solidType, entity.getX(), entity.getY() + y);
                if (solidEntity != null) {
                    if (y > 0) {
                        // Moving up
                        y = solidEntity.getY() - (entity.getY() + entity.getHeight());
                        entity.getComponent(CollisionComponent.class).collidedTop = true;

                        if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER)) {
                            entity.setYVelocity(0);
                        }
                    } else if (y < 0) {
                        // Moving down
                        y = (solidEntity.getY() + solidEntity.getHeight()) - entity.getY();
                        entity.getComponent(CollisionComponent.class).collidedBottom = true;

                        if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER)) {
                            entity.onGround = true;
                            entity.setYVelocity(0);
                        }
                    }
                    break;
                }
            }
            if (entity.getPhysicsType().equals(ZeroBit.PHYSICS_TYPE.PLATFORMER) && !entity.isCollidingBottom()) {
                entity.onGround = false;
            }
        }
        entity.setPosition(entity.getX() + x, entity.getY() + y);
    }
}
