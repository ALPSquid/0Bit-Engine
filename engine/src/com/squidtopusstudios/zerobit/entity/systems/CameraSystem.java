package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.squidtopusstudios.zerobit.entity.components.Box2DComponent;


/**
 * Renders entity sprites
 */
public class CameraSystem extends EntitySystem {

    protected Box2DComponent b2dc;

    protected OrthographicCamera camera;
    protected Entity followEntity;

    protected Float minX;
    protected Float maxX;
    protected Float minY;
    protected Float maxY;
    protected float followX;
    protected float followY;
    protected float followZ = 0;


    /**
     * @param camWidth Camera frustum width in meters
     * @param camHeight Camera frustum height in meters
     */
    public CameraSystem(float camWidth, float camHeight) {
        super();
        camera = new OrthographicCamera(camWidth, camHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void update(float deltaTime) {
        /*if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.position.x -= 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.position.y += 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.position.y -= 0.2;*/

        if (followEntity != null) {
            // TODO: Use TransformComponent if entity doesn't have a Box2DComponent
            followX = b2dc.body.getPosition().x;
            followY = b2dc.body.getPosition().y;

            if (minX != null) if (followX < minX) followX = minX;
            if (maxX != null) if (followX > maxX) followX = maxX;

            if (minY != null) if (followY < minY) followY = minY;
            if (maxY != null) if (followY > maxY) followY = maxY;

            camera.position.set(followX, followY, followZ);
        }
        camera.update();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    /**
     * Sets the camera to follow this entity
     * @param entity Entity for the camera to follow
     */
    public void follow(Entity entity) {
        followEntity = entity;
        b2dc = entity.getComponent(Box2DComponent.class);
    }

    /**
     * Sets the camera to follow this entity while keeping within the bounds set
     * @param entity Entity for the camera to follow
     * @param minX minimum X position of the left edge of the camera, null for unlimited
     * @param maxX maximum X position of the right edge of the camera, null for unlimited
     * @param minY minimum Y position of the bottom edge of the camera, null for unlimited
     * @param maxY maximum Y position of the top edge of the camera, null for unlimited
     */
    public void follow(Entity entity, Float minX, Float maxX, Float minY, Float maxY) {
        follow(entity);
        if (minX != null) this.minX = minX + camera.viewportWidth / 2f;
        if (maxX != null) this.maxX = maxX - camera.viewportWidth / 2f;
        if (minY != null) this.minY = minY + camera.viewportHeight / 2f;
        if (maxY != null) this.maxY = maxY - camera.viewportHeight / 2f;
    }

    public void stopFollow() {
        minX = null;
        maxX = null;
        minY = null;
        maxY = null;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
