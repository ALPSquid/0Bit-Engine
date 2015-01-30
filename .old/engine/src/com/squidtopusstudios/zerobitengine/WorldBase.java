package com.squidtopusstudios.zerobitengine;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager;
import com.squidtopusstudios.zerobitengine.utils.IActiveClass;

/**
 * Base class for 0Bit World classes
 */
public abstract class WorldBase implements IActiveClass {

    protected ZeroBit.WorldType worldType = ZeroBit.WorldType.DEFAULT;
    private float gravity = 9f;
    private int pixelsPerUnit = 1;

    public WorldBase(int pixelsPerUnit) {
        setPixelsPerUnit(pixelsPerUnit);
    }

    /**
    * Override this;
    */
    public void create() {

    }

    /**
     * Managed by the effective game loop. When overriding, make sure to call super.update();
     */
    @Override
    public void update() {

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
     * Get instance of {@link com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager} for this World instance.
     * @return {@link com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager} instance
     */
    public EntityManager entities() {
        return EntityManager.getInstance(this);
    }

    /**
     * @param gravity Set the value of gravity for this world in world units
     */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGravity() {
        return gravity;
    }

    /**
     * Set how many pixels 1 world unit should be. Default is 1:1.
     * If using Box2D, this is considered to be pixels per meter
     * @param pixelsPerUnit the number of pixels per world unit
     */
    public void setPixelsPerUnit(int pixelsPerUnit) {
        this.pixelsPerUnit = pixelsPerUnit;
    }

    public int getPixelsPerUnit() {
        return pixelsPerUnit;
    }

    public ZeroBit.WorldType getWorldType() {
        return worldType;
    }


    public float pixelsToUnits(float pixels) {
        return pixels / getPixelsPerUnit();
    }

    public float unitsToPixels(float units) {
        return units * getPixelsPerUnit();
    }
}
