package com.squidtopusstudios.zerobitengine.entity.systems;

import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.DynamicEntity;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Collision detection for Ashley Entities
 */
public class CollisionSystem extends ZbeSystem {

    private Rectangle predictionBounds;
    private List<List<DynamicEntity>> testEntities = new ArrayList<List<DynamicEntity>>();


    /**
     * Check if source entity is colliding with any entity of type entityType
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @param x x position of the source entity's bounds
     * @param y y position of the source entity's bounds
     * @return the collided entity
     */
    public DynamicEntity collides(DynamicEntity entity, String entityType, float x, float y) {
        /*predictionBounds = entity.getBounds();
        predictionBounds.x = x;
        predictionBounds.y = y;
        if (ZeroBit.managers.entityManager().getEntitiesByType(entityType) != null) {
            for (DynamicEntity testEntity : ZeroBit.managers.entityManager().getEntitiesByTypeAs(entityType, DynamicEntity.class)) {
                if (predictionBounds.overlaps(testEntity.getBounds())) {
                    return testEntity;
                }
            }
        }*/
        return null;
    }
    /**
     * Check if source entity is colliding with any entity of type entityType. Uses the entity's position for x and y
     * @param entity entity to check collision for
     * //@param entityType entity type to check collisions with
     * @return the collided entity
     */
    /*public DynamicEntity collides(DynamicEntity entity, String entityType) {
        return collides(entity, entityType, entity.getX(), entity.getY());
    }*/

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
    }


}
