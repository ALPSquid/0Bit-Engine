package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;


/**
 * Box2D version of World. Use instead of {@link World} if you want to use Box2D.
 * This is basically a manager for the actual Box2D World class to allow unified functionality between this and
 * the standard 0Bit {@link World} class.
 */
public class WorldB2D extends WorldBase {

    private com.badlogic.gdx.physics.box2d.World b2World;
    public float physicsTimeStep = 1/45f;
    public int maxFrameSkip = 5;
    public int velocityIterations = 4;
    public int physicsIterations = 4;
    private float accumulator = 0;


    public WorldB2D(int pixelsPerUnit) {
        super(pixelsPerUnit);
        worldType = ZeroBit.WorldType.BOX2D;
    }

    @Override
    public void create() {
        b2World = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -getGravity()*getPixelsPerUnit()), true);
    }

    @Override
    public void update() {
        float frameTime = Math.min(ZeroBit.getDelta(), 0.25f);
        accumulator += frameTime;
        int loops = 0;
        while (accumulator >= physicsTimeStep && loops < maxFrameSkip) {
            b2World.step(ZeroBit.getDelta(), velocityIterations, physicsIterations);
            accumulator -= physicsTimeStep;
            loops++;
        }
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

    @Override
    public void dispose() {
        super.dispose();
        b2World.dispose();
    }
}