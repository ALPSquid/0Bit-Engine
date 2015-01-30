package com.squidtopusstudios.zerobitengine;

import com.squidtopusstudios.zerobitengine.utils.ActiveInterface;

/**
 * Base class for 0Bit World classes
 */
public abstract class World implements ActiveInterface {

    protected ZeroBit.WorldType worldType = ZeroBit.WorldType.DEFAULT;
    private EntityManager entityManager;
    /** Default world gravity, ignore if you're not using gravity */
    private float gravity = 9f;
    private int pixelsPerUnit = 1;

    /** @param pixelsPerUnit Used for positioning and scaling including movement calculations */
    public World(int pixelsPerUnit) {
        setPixelsPerUnit(pixelsPerUnit);
        entityManager = new EntityManager();
    }

    public abstract void create();

    /**
     * Called when the screen is reset
     */
    public abstract void reset();

    /**
     * Get the instance of {@link EntityManager} for this World.
     * @return {@link com.squidtopusstudios.zerobitengine.EntityManager} instance
     */
    public EntityManager entities() {
        return entityManager;
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

    /** pixels to units conversions specific to this World */
    public float pixelsToUnits(float pixels) {
        return pixels / getPixelsPerUnit();
    }

    /** units to pixels conversions specific to this World */
    public float unitsToPixels(float units) {
        return units * getPixelsPerUnit();
    }
}
