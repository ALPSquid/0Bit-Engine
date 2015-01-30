package com.squidtopusstudios.zerobitengine.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Disposable;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.entity.components.ZbeEntityComponent;

/**
 * Extended base functionality of Ashley System
 */
public abstract class ZbeSystem extends IteratingSystem {
    protected boolean paused = false;


    public ZbeSystem() {
        super(Family.getFor(ZbeEntityComponent.class));
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        processEntity(((ZbeEntity)entity), ZeroBit.getDelta());
    }

    public abstract void processEntity(ZbeEntity entity, float deltaTime);

    @Override
    public boolean checkProcessing() {
        return !paused;
    }

    public void paused(boolean pause) {
        this.paused = pause;
    }
}
