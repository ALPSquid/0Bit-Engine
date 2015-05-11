package com.squidtopusstudios.zerobit.entity.ai.controllers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;
import com.squidtopusstudios.zerobit.entity.ai.behaviours.Wander2D;

/**
 * Controller for NPCs
 */
public class NPCController extends AIController {

    private SteeringEntity steerable;


    public NPCController(Entity entity) {
        super(entity);
        steerable = new SteeringEntity(entity);
        steerable.setMaxLinearSpeed(steerable.getMaxLinearSpeed()/2.5f);
        steerable.setSteeringBehavior(new Wander2D(steerable));
    }

    @Override
    public void update(float deltaTime) {
        steerable.update(deltaTime);
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        renderer.circle(((Wander2D) steerable.getSteeringBehavior()).getInternalTargetPosition(),
                steerable.getPosition().y, 0.2f);
    }

    @Override
    public void reset() {

    }

    public SteeringEntity getSteerable() {
        return steerable;
    }
}
