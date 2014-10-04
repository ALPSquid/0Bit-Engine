package com.squidtopusstudios.zerobitengine;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager;
import com.squidtopusstudios.zerobitengine.utils.IActiveClass;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Main {@link World} class. You should extend this to create your world.
 */
public class World implements IActiveClass {
    private float gravity = 9f;

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
        ZeroBit.logger.logDebug("Disposing");
    }

    /**
     * Get instance of {@link EntityManager} for this World instance.
     * @return {@link EntityManager} instance
     */
    public EntityManager entities() {
        return EntityManager.getInstance(this);
    }

    /**
     * @param gravity Set the value of gravity for this world
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGravity() {
        return gravity;
    }
}
