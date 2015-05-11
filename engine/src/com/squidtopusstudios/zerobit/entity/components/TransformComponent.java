package com.squidtopusstudios.zerobit.entity.components;


/**
 * Position and rotation component for all Entity types. Box2D entities use body positions instead.
 */
public class TransformComponent extends ZBComponent {

    // Original position values used for resetting this component
    public float origX = 0;
    public float origY = 0;
    public float origRotation = 0;

    public float x = 0;
    public float y = 0;
    public int z = 0;
    /** Rotation in degrees */
    public float rotation = 0;


    /** Resets the position and rotation to original loaded values */
    @Override
    public void reset() {
        x = origX;
        y = origY;
        rotation = origRotation;
    }
}
