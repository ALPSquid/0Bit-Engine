package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.CollisionComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Collision detection for Ashley Entities
 */
public class CollisionSystem extends ZbeSystem {

    private Rectangle predictionBounds;
    private List<List<ZbeEntity>> testEntities = new ArrayList<List<ZbeEntity>>();


    /**
     * Check if source entity is colliding with any entity of type entityType
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @param x x position of the source entity's bounds
     * @param y y position of the source entity's bounds
     * @return the collided entity
     */
    public ZbeEntity collides(ZbeEntity entity, String entityType, float x, float y) {
        predictionBounds = entity.getBounds();
        predictionBounds.x = x;
        predictionBounds.y = y;
        if (ZeroBit.managers.entityManager().getEntitiesByType(entityType) != null) {
            for (ZbeEntity testEntity : ZeroBit.managers.entityManager().getEntitiesByType(entityType)) {
                if (entity.getBounds().overlaps(testEntity.getBounds())) {
                    return testEntity;
                }
            }
        }
        return null;
    }

    /**
     * Check if source entity is colliding with any entity of type entityType. Uses the entity's position for x and y
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @return the collided entity
     */
    public ZbeEntity collides(ZbeEntity entity, String entityType) {
        return collides(entity, entityType, entity.getX(), entity.getY());
    }


    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        /*testEntities.clear();
        if (entity.collisionsEnabled() && !entity.getManagedCollisionEntities().contains(ZeroBit.MANAGED_ENTITIES.NONE)) {
            // Get a list of managed entities to check
            if (entity.getManagedCollisionEntities().contains(ZeroBit.MANAGED_ENTITIES.ALL)) {
                testEntities.add(Arrays.asList(ZeroBit.managers.entityManager().getEntities()));
            } else {
                for (String type : entity.getManagedCollisionEntities()) {
                    testEntities.add(ZeroBit.managers.entityManager().getEntitiesByType(type));
                }
            }
            entity.getComponent(CollisionComponent.class).collidedXLeft = false;
            entity.getComponent(CollisionComponent.class).collidedXRight = false;
            entity.getComponent(CollisionComponent.class).collidedYTop = false;
            entity.getComponent(CollisionComponent.class).collidedYBottom = false;
            for (List<ZbeEntity> testEntityType : testEntities) {
                for (ZbeEntity testEntity : testEntityType) {
                    if (testEntity != entity) {
                        // Add the entity's velocity to check where it will be in 2 frames times
                        predictionBounds = entity.getBounds();
                        predictionBounds.setPosition(predictionBounds.x + entity.getVelocity().x*2,
                                predictionBounds.y + entity.getVelocity().y*2);
                        if (predictionBounds.overlaps(testEntity.getBounds())) {
                            if (entity.getCollidedEntities().get(testEntity.getType()) == null) {
                                entity.getCollidedEntities().put(testEntity.getType(), new ArrayList<ZbeEntity>());
                            }
                            // Add the entity it's colliding with to the list of collidedEntities
                            entity.getCollidedEntities().get(testEntity.getType()).add(testEntity);
                            // Stop the entity if it collides and is moving
                            if (entity.getSolidTypes().contains(testEntity.getType())) {
                                // y
                                if (entity.getVelocity().y < 0 && entity.getY() > testEntity.getY()) {
                                    entity.getComponent(CollisionComponent.class).collidedYBottom = true;
                                } else if (entity.getVelocity().y > 0 && entity.getY() < testEntity.getY()) {
                                    entity.getComponent(CollisionComponent.class).collidedYTop = true;
                                }
                                // x
                                if (entity.getVelocity().x > 0 && entity.getX() < testEntity.getX()) {
                                    entity.getComponent(CollisionComponent.class).collidedXRight = true;
                                } else if (entity.getVelocity().x < 0 && entity.getX() > testEntity.getX()) {
                                    entity.getComponent(CollisionComponent.class).collidedXLeft = true;
                                }
                            }
                        }
                        else if (entity.getCollidedEntities().get(testEntity.getType()) != null && entity.getCollidedEntities().get(testEntity.getType()).contains(testEntity)) {
                            entity.getCollidedEntities().get(testEntity.getType()).remove(testEntity);
                        }
                    }
                }
            }
        }*/
    }


}
