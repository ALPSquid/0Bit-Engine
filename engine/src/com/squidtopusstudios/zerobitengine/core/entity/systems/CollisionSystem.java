package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * Collision detection for Ashley Entities
 */
public class CollisionSystem extends ZbeSystem {

    private Rectangle predictionBounds = new Rectangle();

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.isDynamic()) {
            for (ZbeEntity testEntity : ZeroBit.managers.entityManager().getEntities()) {
                if (testEntity != entity) {
                    predictionBounds = entity.getBounds();
                    predictionBounds.setPosition(predictionBounds.x + entity.getVelocity().x,
                                                 predictionBounds.y + entity.getVelocity().y);
                    if (predictionBounds.overlaps(testEntity.getBounds())) {
                        if (!entity.getCollidedEntities().contains(testEntity.getName())) {
                            entity.getVelocity().set(0, 0);
                        }
                        entity.getCollidedEntities().add(testEntity.getName());
                    }
                    else {
                        entity.getCollidedEntities().remove(testEntity.getName());
                    }
                }
            }
        }
    }
}
