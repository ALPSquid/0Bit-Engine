package com.squidtopusstudios.zerobitengine.refact.entity.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Manages the Box2D simulation. Add to your World's engine if you want to use Box2D
 */
public class Box2DSystem extends EntitySystem {

    public float physicsTimeStep = 1/45f;
    public int maxFrameSkip = 3;
    public int velocityIterations = 8;
    public int positionIterations = 3;
    private float accumulator = 0;
    private int loops;
    public World b2World;


    public Box2DSystem(Vector2 gravity, boolean doSleep) {
        b2World = new World(gravity, doSleep);
    }

    public Box2DSystem(Vector2 gravity) {
        this(gravity, true);
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;
        loops = 0;
        while (accumulator > physicsTimeStep && loops < maxFrameSkip) {
            b2World.step(physicsTimeStep, velocityIterations, positionIterations);
            accumulator -= physicsTimeStep;
            loops++;
        }
    }
}