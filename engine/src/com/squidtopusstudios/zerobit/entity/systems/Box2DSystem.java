package com.squidtopusstudios.zerobit.entity.systems;

import box2dLight.Light;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;
import com.squidtopusstudios.zerobit.entity.Identifiers;
import com.squidtopusstudios.zerobit.entity.components.Box2DComponent;
import com.squidtopusstudios.zerobit.entity.components.MessagingComponent;
import com.squidtopusstudios.zerobit.entity.components.StateComponent;
import com.squidtopusstudios.zerobit.entity.components.TransformComponent;

import java.util.ArrayList;

/**
 * Manages the Box2D World
 */
public class Box2DSystem extends IteratingSystem implements ContactListener {

    private ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<Box2DComponent> b2dm = ComponentMapper.getFor(Box2DComponent.class);
    private ComponentMapper<MessagingComponent> msgm = ComponentMapper.getFor(MessagingComponent.class);

    private final float physicsTimeStep = 1/45f;
    private final int maxFrameSkip = 3;
    private final int velocityIterations = 6;
    private final int positionIterations = 2;
    private float accumulator = 0;
    private int loops;
    private boolean stepped = false;
    private World b2World;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private ArrayList<Light> lights;
    private boolean night = true;
    public Color ambientColour = new Color();


    public Box2DSystem() {
        super(Family.all(Box2DComponent.class, TransformComponent.class, StateComponent.class).get());
        b2World = new World(new Vector2(0, -14), true);
        b2World.setContactListener(this);
        rayHandler = new RayHandler(b2World, 320, 180);
        lights = new ArrayList<Light>();
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (ZeroBit.debugEnabled) debugRenderer.render(b2World, camera.combined);
        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.render();

        accumulator += deltaTime;
        loops = 0;
        stepped = false;
        while (accumulator > physicsTimeStep && loops < maxFrameSkip) {
            b2World.step(physicsTimeStep, velocityIterations, positionIterations);
            accumulator -= physicsTimeStep;
            loops++;
            stepped = true;
        }
        if (stepped) rayHandler.update();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Box2DComponent b2dc = b2dm.get(entity);
        MessagingComponent msgc = msgm.get(entity);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        debugRenderer.dispose();
        rayHandler.dispose();
        b2World.dispose();
    }

    public void registerLight(Light light) {
        lights.add(light);
    }
    public void removeLight(Light light) {
        lights.remove(light);
    }

    public World getB2World() {
        return b2World;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    /**
     * @param camera camera to use for the Box2D debug renderer and lights
     */
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    /** Updates the ray handler ambient colour from {@link #ambientColour} */
    public void updateAmbientColour() {
        rayHandler.setAmbientLight(ambientColour);
    }


    // Contact Listener Methods
    Fixture bodyA;
    Fixture bodyB;
    Box2DUserData userDataA;
    Box2DUserData userDataB;
    @Override
    public void beginContact(Contact contact) {
        // TODO: Add user created callbacks for handling contacts with userdata and fixtures as parameters
        bodyA = contact.getFixtureA();
        bodyB = contact.getFixtureB();
        userDataA = (Box2DUserData)bodyA.getUserData();
        userDataB = (Box2DUserData)bodyB.getUserData();

        if (userDataA != null && userDataA.id.equals(Identifiers.FOOT_SENSOR) && !bodyB.isSensor()) {
            userDataA.footContacts++;
            if (userDataA.footContacts > 0) userDataA.stateComponent.onGround = true;
        }
        if (userDataB != null && userDataB.id.equals(Identifiers.FOOT_SENSOR) && !bodyA.isSensor()) {
            userDataB.footContacts++;
            if (userDataB.footContacts > 0) userDataB.stateComponent.onGround = true;
        }
    }
    @Override
    public void endContact(Contact contact) {
        bodyA = contact.getFixtureA();
        bodyB = contact.getFixtureB();
        userDataA = (Box2DUserData)bodyA.getUserData();
        userDataB = (Box2DUserData)bodyB.getUserData();

        if (userDataA != null && userDataA.id.equals(Identifiers.FOOT_SENSOR) && !bodyB.isSensor()) {
            userDataA.footContacts--;
            if (userDataA.footContacts < 1) userDataA.stateComponent.onGround = false;
        }
        if (userDataB != null && userDataB.id.equals(Identifiers.FOOT_SENSOR) && !bodyA.isSensor()) {
            userDataB.footContacts--;
            if (userDataB.footContacts < 1) userDataB.stateComponent.onGround = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
