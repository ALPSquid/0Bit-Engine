package com.squidtopusstudios.zerobitengine;

import com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager;
import com.squidtopusstudios.zerobitengine.utils.IActiveClass;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Main World class. You should extend this to create your world.
 */
public class World implements IActiveClass {
    /**
     * Override this;
     */
    public void create() {

    }

    /**
     * Managed by the effective game loop. When overriding, make sure to call super.update();
     */
    @Override
    public void update(float deltaTime) {

    }

    /**
     * Override this;
     */
    public void reset() {

    }

    /**
     * Override this;
     */
    @Override
    public void dispose() {
        Logger.getInstance().logInfo("Disposing");
    }

    /**
     * Get instance of EntityManager
     * @return EntityManager instance
     */
    public EntityManager entities() {
        return EntityManager.getInstance();
    }
}
