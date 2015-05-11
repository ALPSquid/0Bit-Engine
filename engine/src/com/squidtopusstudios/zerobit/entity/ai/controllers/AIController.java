package com.squidtopusstudios.zerobit.entity.ai.controllers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * BAse AI Controller implementation
 */
public abstract class AIController {

    protected Entity entity;


    public AIController(Entity entity) {
        this.entity = entity;
    }

    public abstract void update(float deltaTime);
    public abstract void debugRender(ShapeRenderer renderer);
    public abstract void reset();
}