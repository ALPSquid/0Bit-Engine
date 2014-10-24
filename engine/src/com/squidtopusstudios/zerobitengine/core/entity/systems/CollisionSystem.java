package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;
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
            for (ZbeEntity testEntity : ZeroBit.managers.entityManager().getEntitiesByTypeAs(entityType, ZbeEntity.class)) {
                if (predictionBounds.overlaps(testEntity.getBounds())) {
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
    public void processEntity(ZbeEntityBase entity, float deltaTime) {
    }


}
