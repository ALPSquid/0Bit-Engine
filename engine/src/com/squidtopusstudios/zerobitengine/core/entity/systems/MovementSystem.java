package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.Gdx;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * handles movement for Ashley Entities
 */
public class MovementSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        /*if (entity.collisionsEnabled()) {
            for (String solidType : entity.getSolidTypes()) {
                if (entity.collides(entity, solidType, entity.getX() + entity.getVelocity().x * deltaTime, entity.getY() + entity.getVelocity().y * deltaTime) != null) {
                    move(entity, -entity.getVelocity().x*deltaTime, -entity.getVelocity().y*deltaTime);
                    entity.setVelocity(0, 0);
                    break;
                }
            }
        }*/
    }

    public void moveBy(ZbeEntity entity, float x, float y) {
        if (entity.collisionsEnabled()) {
            for (String solidType : entity.getSolidTypes()) {
                if (entity.collides(entity, solidType, entity.getX() + x * Gdx.graphics.getDeltaTime(),
                        entity.getY() + y * Gdx.graphics.getDeltaTime()) != null)  {
                    x = 0;
                    y = 0;
                    break;
                }
            }
        }
        entity.setPosition(entity.getX() + x * Gdx.graphics.getDeltaTime(), entity.getY() + y * Gdx.graphics.getDeltaTime());
    }
}
