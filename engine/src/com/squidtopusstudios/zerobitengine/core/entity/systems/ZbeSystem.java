package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Disposable;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Extended base functionality of Ashley System
 */
public class ZbeSystem extends IteratingSystem implements Disposable {
    protected boolean paused = false;


    public ZbeSystem() {
        super(ZeroBit.managers.entityManager().getZbeFamily());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {

    }

    @Override
    public boolean checkProcessing() {
        return !paused;
    }

    public void paused(boolean pause) {
        this.paused = pause;
    }

    @Override
    public void dispose() {

    }
}
