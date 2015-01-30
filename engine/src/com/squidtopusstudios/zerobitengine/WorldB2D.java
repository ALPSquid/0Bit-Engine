package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.math.Vector2;


/**
 * Box2D version of World. Use instead of {@link World} if you want to use Box2D.
 * This is basically a manager for the actual Box2D World class to allow unified functionality between this and
 * the standard 0Bit {@link World} class.
 */
public abstract class WorldB2D extends World {

    private com.badlogic.gdx.physics.box2d.World b2World;
    public float physicsTimeStep = 1/45f;
    public int maxFrameSkip = 5;
    public int velocityIterations = 8;
    public int positionIterations = 3;
    private float accumulator = 0;


    public WorldB2D(int pixelsPerUnit) {
        super(pixelsPerUnit);
        worldType = ZeroBit.WorldType.BOX2D;
    }

    @Override
    public void create() {
        b2World = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -getGravity() * getPixelsPerUnit()), true);
    }

    @Override
    public void update() {
        /*float frameTime = Math.min(Gdx.graphics.getDeltaTime(), 0.25f);
        accumulator += frameTime;
        int loops = 0;
        while (accumulator >= physicsTimeStep && loops < maxFrameSkip) {
            b2World.step(physicsTimeStep, velocityIterations, positionIterations);
            accumulator -= physicsTimeStep;
            loops++;
        }*/
        b2World.step(ZeroBit.getDelta(), velocityIterations, positionIterations);
    }

    /**
     * @return the Box2D World instance
     */
    public com.badlogic.gdx.physics.box2d.World getB2DWorld() {
        return b2World;
    }

    /**
     * @param gravity Set the value of gravity for this world in world units (meters)
     */
    @Override
    public void setGravity(float gravity) {
        super.setGravity(gravity);
        b2World.setGravity(new Vector2(0, -gravity));
    }
    public void setGravity(Vector2 gravity) {
        super.setGravity(gravity.y);
        b2World.setGravity(new Vector2(gravity.x, gravity.y));
    }
    public Vector2 getB2DGravity() {
        return b2World.getGravity();
    }

    /**
     * Convert x & y coordinates in pixels to x & y coordinates in world units
     * @return Vector2 coordinates in pixels
     */
    public Vector2 pixelsToPosition(float x, float y) {
        return new Vector2((x / getPixelsPerUnit()) - ((ZeroBit.targetWidth/getPixelsPerUnit()) / 2), (y / getPixelsPerUnit()));
    }

    @Override
    public void dispose() {
        b2World.dispose();
    }
}
