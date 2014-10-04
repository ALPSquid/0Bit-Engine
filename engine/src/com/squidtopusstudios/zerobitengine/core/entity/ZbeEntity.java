package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MetaSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.ZbeSystem;

import java.awt.*;
import java.util.*;

/**
 * Wrapper for adding Ashley Components and systems
 */
public class ZbeEntity extends Entity {

    public ZbeEntity(String name) {
        add(new ZbeEntityComponent());
        add(new ResourceComponent());
        add(new BoundsComponent());
        add(new SimplePositionComponent());
        add(new PhysicsComponent());
        add(new CollisionComponent());
        ZeroBit.managers.entityManager().getSystem(MetaSystem.class).setName(this, name);
    }

    /**
     * Internal and simple way of getting Ashley components.
     * This allows you to add your own components (using ZbeEntity.add(component)) without
     * having to create your own ZbeEntity class
     * @param component component class to get e.g. ResourceComponent.class
     * @return the instance of the component belonging to this entity
     */
    public <T extends Component> T getComponent(Class<T> component) {
        return ComponentMapper.getFor(component).get(this);
    }

    /**
     * Set the Texture component
     * @param texture Texture to set
     * @return current ZbeEntity instance
     */
    public ZbeEntity setSprite(Texture texture) {
        ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).setTexture(this, texture);
        return this;
    }

    /**
     * Set the TextureRegion component
     * @param textureRegion TextureRegion to set
     * @return current ZbeEntity instance
     */
    public ZbeEntity setSprite(TextureRegion textureRegion) {
        ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).setTextureRegion(this, textureRegion);
        return this;
    }

    /**
     * Get the currently set TextureRegion component
     * @return Currently set TextureRegion
     */
    public TextureRegion getSprite() {
        return ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).getSprite(this);
    }

    /**
     * @return the entity's name/ID
     */
    public String getName() {
        return ZeroBit.managers.entityManager().getSystem(MetaSystem.class).getName(this);
    }

    /**
     * Set the rectangle bounds of the entity
     * @return current ZbeEntity instance
     */
    public ZbeEntity setBounds(float width, float height) {
        getComponent(BoundsComponent.class).bounds.setSize(width, height);
        return this;
    }

    /**
     * @return rectangle bounds
     */
    public Rectangle getBounds() {
        return getComponent(BoundsComponent.class).bounds;
    }

    public float getWidth() {
        return getBounds().width;
    }

    public float getHeight() {
        return getBounds().height;
    }

    /**
     * Set the Vector2 position of the entity
     * @return current ZbeEntity instance
     */
    public ZbeEntity setPosition(float x, float y) {
        getComponent(SimplePositionComponent.class).position.set(x, y);
        getBounds().setPosition(x, y);
        return this;
    }

    /**
     * @return Vector2 position of the entity
     */
    public Vector2 getPosition() {
        return getComponent(SimplePositionComponent.class).position;
    }

    /**
     * Toggle dynamic attribute of the entity. If true, physics will be applied.
     */
    public void setDynamic(boolean isDynamic) {
        getComponent(PhysicsComponent.class).isDynamic = isDynamic;
    }

    public boolean isDynamic() {
        return getComponent(PhysicsComponent.class).isDynamic;
    }

    public Vector2 getVelocity() {
        return getComponent(PhysicsComponent.class).velocity;
    }

    public java.util.List<String> getCollidedEntities() {
        return getComponent(CollisionComponent.class).collidedEntities;
    }
}
