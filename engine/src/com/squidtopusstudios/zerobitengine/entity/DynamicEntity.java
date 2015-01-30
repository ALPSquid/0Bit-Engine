package com.squidtopusstudios.zerobitengine.entity;

import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.components.CollisionComponent;
import com.squidtopusstudios.zerobitengine.entity.components.PhysicsComponent;
import com.squidtopusstudios.zerobitengine.entity.components.SimplePositionComponent;
import com.squidtopusstudios.zerobitengine.entity.systems.CollisionSystem;
import com.squidtopusstudios.zerobitengine.entity.systems.MovementSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Standard Entity with optional rectangle collision detection and basic physics.
 * Note that all parameters and return values, except moveBy() which is in units, are in pixels. You can convert between using the methods from world (getWorld())
 * This may well change in the future to use units for everything like the Box2D entity
 */
public class DynamicEntity extends ZbeEntity {

    public boolean onGround = false;

    public DynamicEntity(String type) {
        super(type);
        add(new PhysicsComponent());
        add(new CollisionComponent());
        //isBox2D = false;
    }

    @Override
    public void create() {

    }

    /**
     * Set the Vector2 position of the entity in pixels
     * @return current ZbeEntity instance
     */
    /*@Override
    public DynamicEntity setPosition(float x, float y) {
        //getComponent(SimplePositionComponent.class).position.set(x, y);
        getBounds().setPosition(x, y);
        return this;
    }*/

    /**
     * @return Vector2 position of the entity
     */
    /*@Override
    public Vector2 getPosition() {
        return getComponent(SimplePositionComponent.class).position;
    }*/

    /**
     * If false(default) collisions won't be detected
     */
    /*@Override
    public DynamicEntity enableCollisions(boolean collidable) {
        getComponent(PhysicsComponent.class).collidable = collidable;
        return this;
    }*/

    /*public boolean collisionsEnabled() {
        return getComponent(PhysicsComponent.class).collidable;
    }*/

    @Override
    public void dispose() {

    }

    /**
     * Check if source entity is colliding with any entity of type entityType
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @param x x position of the source entity's bounds
     * @param y y position of the source entity's bounds
     * @return the collided entity
     */
    /*public DynamicEntity collides(DynamicEntity entity, String entityType, float x, float y) {
        return ZeroBit.managers.entityManager().getSystem(CollisionSystem.class).collides(entity, entityType, x, y);
    }*/
    /**
     * Check if source entity is colliding with any entity of type entityType. Uses the entity's position for x and y
     * @param entity entity to check collision for
     * @param entityType entity type to check collisions with
     * @return the collided entity
     */
    /*public DynamicEntity collides(DynamicEntity entity, String entityType) {
        return collides(entity, entityType, entity.getX(), entity.getY());
    }*/

    /**
     * @return true if the entity is currently colliding with something
     */
    public boolean isColliding() {
        return isCollidingLeft() || isCollidingRight() || isCollidingTop() || isCollidingBottom();
    }

    public  boolean isCollidingLeft() {
        return getCollisionData().collidedLeft;
    }
    public  boolean isCollidingRight() {
        return getCollisionData().collidedRight;
    }
    public boolean isCollidingTop() {
        return getCollisionData().collidedTop;
    }
    public boolean isCollidingBottom() {
        return getCollisionData().collidedBottom;
    }


    /**
     * Set the entity types you want this entity to be unable to pass through
     * @param solidTypes Solid types to make this entity unable to pass through
     */
    public DynamicEntity setSolidTypes(String... solidTypes) {
        getCollisionData().solidTypes = Arrays.asList(solidTypes);
        return this;
    }
    public List<String> getSolidTypes() {
        return getCollisionData().solidTypes;
    }

    /**
     * Set the physics type of the entity. By default it's set to NONE
     */
    /*public DynamicEntity setPhysicsType(ZeroBit.PhysicsType type) {
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
    }*/

    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * Interpolation is set to true by default, meaning x and y are considered to be the amount of movement in 1 second
     */
    /*private void moveBy(float x, float y) {
        ZeroBit.managers.entityManager().getSystem(MovementSystem.class).moveBy(this, x, y, !ZeroBit.isFixedTimeStep());
    }*/
    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * @param interpolate whether to interpolate the movement.
     *                    If true, x and y are considered to be the amount of movement in 1 second
     */
    /*private void moveBy(float x, float y, boolean interpolate) {
        ZeroBit.managers.entityManager().getSystem(MovementSystem.class).moveBy(this, x, y, interpolate);
    }*/
}
