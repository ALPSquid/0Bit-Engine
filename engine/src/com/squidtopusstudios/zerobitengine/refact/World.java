package com.squidtopusstudios.zerobitengine.refact;


import com.squidtopusstudios.zerobitengine.refact.entity.EntityManager;

/**
 * Base class for 0Bit World classes
 */
public abstract class World {

    private EntityManager entityManager;
    private int pixelsPerUnit = 1;


    /**
     * @param pixelsPerUnit the number of pixels per world unit. Default is 1:1.<br/>
     * If using Box2D, this is considered to be pixels per meter
     */
    public World(int pixelsPerUnit) {
        setPixelsPerUnit(pixelsPerUnit);
        entityManager = new EntityManager();
    }

    /** Called each frame. Make sure you call super().update() if overriding */
    public void update(float delta) {
        entityManager.update(delta);
    }

    /**
     * Called when the parent {@link com.squidtopusstudios.zerobitengine.refact.screens.ZbeScreen} is set as the active screen. Reset any objects here
     */
    public abstract void show();

    /**
     * Call when the world is no longer required
     */
    public void dispose() {
        entityManager.dispose();
    }

    /**
     * Get the instance of {@link EntityManager} for this World.
     * @return {@link com.squidtopusstudios.zerobitengine.EntityManager} instance
     */
    public EntityManager getEntities() {
        return entityManager;
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

    /** pixels to units conversions specific to this World */
    public float pixelsToUnits(float pixels) {
        return pixels / getPixelsPerUnit();
    }

    /** units to pixels conversions specific to this World */
    public float unitsToPixels(float units) {
        return units * getPixelsPerUnit();
    }

}
