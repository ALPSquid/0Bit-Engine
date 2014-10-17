package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Disposable;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

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
        processEntity(((ZbeEntity)entity), ZeroBit.getDelta());
    }
    public void processEntity(ZbeEntity entity, float deltaTime) {

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
