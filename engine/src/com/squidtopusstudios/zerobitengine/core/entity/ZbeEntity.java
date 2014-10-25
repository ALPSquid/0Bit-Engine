package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.core.entity.systems.CollisionSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MovementSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Standard Entity with optional rectangle collision detection and basic physics.
 * Note that all parameters and return values, except moveBy() which is in units, are in pixels. You can convert between using the methods from world (getWorld())
 * This may well change in the future to use units for everything like the Box2D entity
 */
public class ZbeEntity extends ZbeEntityBase {

    public boolean onGround = false;

    public ZbeEntity(String type) {
        super(type);
        add(new PhysicsComponent());
        add(new CollisionComponent());
        isBox2D = false;
    }

    /**
     * Set the Vector2 position of the entity in pixels
     * @return current ZbeEntity instance
     */
    @Override
    public ZbeEntity setPosition(float x, float y) {
        getComponent(SimplePositionComponent.class).position.set(x, y);
        getBounds().setPosition(x, y);
        return this;
    }

    /**
     * @return Vector2 position of the entity
     */
    @Override
    public Vector2 getPosition() {
        return getComponent(SimplePositionComponent.class).position;
    }

    /**
     * If false(default) collisions won't be detected
     */
    @Override
    public ZbeEntity enableCollisions(boolean collidable) {
        getComponent(PhysicsComponent.class).collidable = collidable;
        return this;
    }

    public boolean collisionsEnabled() {
        return getComponent(PhysicsComponent.class).collidable;
    }

    /**
     * Check if source entity is colliding with any entity of type entityType
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @param x x position of the source entity's bounds
     * @param y y position of the source entity's bounds
     * @return the collided entity
     */
    public ZbeEntity collides(ZbeEntity entity, String entityType, float x, float y) {
        return ZeroBit.managers.entityManager().getSystem(CollisionSystem.class).collides(entity, entityType, x, y);
    }
    /**
     * Check if source entity is colliding with any entity of type entityType. Uses the entity's position for x and y
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @return the collided entity
     */
    public ZbeEntity collides(ZbeEntity entity, String entityType) {
        return collides(entity, entityType, entity.getX(), entity.getY());
    }

    /**
     * @return true if the entity is currently colliding with something
     */
    public boolean isColliding() {
        return isCollidingLeft() || isCollidingRight() || isCollidingTop() || isCollidingBottom();
    }

    public  boolean isCollidingLeft() {
        return getComponent(CollisionComponent.class).collidedLeft;
    }
    public  boolean isCollidingRight() {
        return getComponent(CollisionComponent.class).collidedRight;
    }
    public boolean isCollidingTop() {
        return getComponent(CollisionComponent.class).collidedTop;
    }
    public boolean isCollidingBottom() {
        return getComponent(CollisionComponent.class).collidedBottom;
    }


    /**
     * Set the entity types you want this entity to be unable to pass through
     * @param solidTypes Solid types to make this entity unable to pass through
     */
    public ZbeEntity setSolidTypes(String... solidTypes) {
        getComponent(CollisionComponent.class).solidTypes = Arrays.asList(solidTypes);
        return this;
    }
    public List<String> getSolidTypes() {
        return getComponent(CollisionComponent.class).solidTypes;
    }

    /**
     * Set the physics type of the entity. By default it's set to NONE
     */
    public ZbeEntity setPhysicsType(ZeroBit.PhysicsType type) {
        getComponent(PhysicsComponent.class).physicsType = type;
        return this;
    }

    public ZeroBit.PhysicsType getPhysicsType() {
        return getComponent(PhysicsComponent.class).physicsType;
    }

    public Vector2 getVelocity() {
        return getComponent(PhysicsComponent.class).velocity;
    }

    public void setVelocity(float x, float y) {
        if (x > getMaxVelocity().x) {
            x = getMaxVelocity().x;
        }
        if (y > getMaxVelocity().y) {
            y = getMaxVelocity().y;
        }
        getVelocity().set(x, y);
    }

    public void setXVelocity(float x) {
        setVelocity(x, getVelocity().y);
    }

    public void setYVelocity(float y) {
        setVelocity(getVelocity().x, y);
    }

    public void increaseVelocity(float xIncrease, float yIncrease) {
        getVelocity().set(getVelocity().x + xIncrease, getVelocity().y + yIncrease);
    }

    public void setMaxVelocity(float x, float y) {
        getMaxVelocity().set(x, y);
    }

    protected Vector2 getMaxVelocity() {
        return getComponent(PhysicsComponent.class).maxVelocity;
    }

    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * Interpolation is set to true by default, meaning x and y are considered to be the amount of movement in 1 second
     */
    private void moveBy(float x, float y) {
        ZeroBit.managers.entityManager().getSystem(MovementSystem.class).moveBy(this, x, y, !ZeroBit.isFixedTimeStep());
    }
    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * @param interpolate whether to interpolate the movement.
     *                    If true, x and y are considered to be the amount of movement in 1 second
     */
    private void moveBy(float x, float y, boolean interpolate) {
        ZeroBit.managers.entityManager().getSystem(MovementSystem.class).moveBy(this, x, y, interpolate);
    }
}
