package com.squidtopusstudios.zerobit.worlds;

import com.squidtopusstudios.zerobit.entity.systems.*;
import com.squidtopusstudios.zerobit.entity.systems.ai.BehaviourSystem;

/**
 * A pre-configured Box2D World
 */
public abstract class B2DWorld extends ZBWorld {

    private float width;
    private float height;


    /**
     * @param width Width of the view in meters
     * @param height Height of the view in meters
     */
    public B2DWorld(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void load(Runnable callBack) {
        entities.getEngine().addSystem(new MessagingSystem());
        entities.getEngine().addSystem(new InputSystem());
        entities.getEngine().addSystem(new CameraSystem(width, height));
        entities.getEngine().addSystem(new MovementSystem());
        entities.getEngine().addSystem(new BehaviourSystem());
        entities.getEngine().addSystem(new StatSystem());
        entities.getEngine().addSystem(new AnimationSystem());
        entities.getEngine().addSystem(new ParallaxSystem());
        //entities.getEngine().addSystem(new DaySystem());
        entities.getEngine().addSystem(new VisualSystem());
        entities.getEngine().addSystem(new RenderingSystem());
        entities.getEngine().addSystem(new Box2DSystem());

        entities.getEngine().getSystem(RenderingSystem.class).setCamera(entities.getEngine().getSystem(CameraSystem.class).getCamera());
        entities.getEngine().getSystem(Box2DSystem.class).setCamera(entities.getEngine().getSystem(CameraSystem.class).getCamera());
        entities.getEngine().getSystem(ParallaxSystem.class).setCamera(entities.getEngine().getSystem(CameraSystem.class).getCamera());
        //entities.getEngine().getSystem(DaySystem.class).setB2dSystem(entities.getEngine().getSystem(Box2DSystem.class));
    }
}
