package com.squidtopusstudios.zerobit.entity.components;


/**
 * Holds flags for movement
 */
public class MovementComponent extends ZBComponent {

    public float speed = 4;
    public float jumpForce = 6;

    public boolean stop = false;
    public boolean left = false;
    public boolean right = false;
    public boolean up = false;
    public boolean down = false;
    public boolean jump = false;

    @Override
    public void reset() {
        stop = false;
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
