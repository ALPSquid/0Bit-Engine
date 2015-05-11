package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.squidtopusstudios.zerobit.entity.components.ParallaxComponent;
import com.squidtopusstudios.zerobit.entity.components.TransformComponent;

/**
 * Moves entities according to their parallax intensity anchored on the camera
 */
public class ParallaxSystem extends IteratingSystem {

    private ComponentMapper<ParallaxComponent> pxm = ComponentMapper.getFor(ParallaxComponent.class);
    private ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    private ParallaxComponent pxc;
    private TransformComponent transc;

    private OrthographicCamera camera;
    private Vector3 prevCameraPos = new Vector3();


    public ParallaxSystem() {
        super(Family.all(ParallaxComponent.class, TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        prevCameraPos.set(camera.position);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        pxc = pxm.get(entity);
        transc = transm.get(entity);
        transc.x += (prevCameraPos.x - camera.position.x) * pxc.intensity;
    }

    /**
     * @param camera camera to use for the Box2D debug renderer and lights
     */
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
        prevCameraPos.set(camera.position);
    }
}
