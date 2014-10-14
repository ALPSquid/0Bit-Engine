package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.core.entity.systems.CollisionSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MovementSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;
import com.squidtopusstudios.zerobitengine.utils.ZbeEntityLogic;

import java.util.Arrays;
import java.util.List;

/**
 * Wrapper for adding Ashley Components and systems
 */
public class ZbeEntity extends Entity {

    private Color debugColour = Color.RED;
    public boolean onGround = false;

    public ZbeEntity() {
        add(new ZbeEntityComponent());
        add(new ResourceComponent());
        add(new BoundsComponent());
        add(new SimplePositionComponent());
        add(new PhysicsComponent());
        add(new CollisionComponent());
        add(new LogicComponent());
        add(new StateComponent());
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
     * The entity's update loop, put your entity's logic here
     * @param deltaTime elapsed time between the last frame and this frame
     */
    public void update(float deltaTime) {}

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
     * Set custom dimensions for the entity's sprite. Useful for using bounds smaller/larger than the set sprite.
     * Default is the entity's bounds dimensions.
     * @param width desired sprite width in pixels
     * @param height desired sprite height in pixels
     * @return current ZbeEntity instance
     */
    public ZbeEntity setSpriteDimensions(int width, int height) {
        getComponent(ResourceComponent.class).spriteDimensions.width = width;
        getComponent(ResourceComponent.class).spriteDimensions.height = height;
        return this;
    }

    /**
     * Get the custom dimensions of the entity's sprite.
     * @return entity's SpriteDimensions
     */
    public ResourceComponent.SpriteDimensions getSpriteDimenstions() {
        return getComponent(ResourceComponent.class).spriteDimensions;
    }

    /**
     * Set the entity type/group (used for collision detection)
     * @param type Entity type/group
     * @return the instance of {@link com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity}
     */
    public ZbeEntity setType(String type) {
        getComponent(ZbeEntityComponent.class).type = type;
        return this;
    }

    /**
     * @return the entity's type
     */
    public String getType() {
        return getComponent(ZbeEntityComponent.class).type;
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
     * Set the Vector2 position of the entity in world units
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

    public float getY() {
        return getPosition().y;
    }
    public float getX() {
        return getPosition().x;
    }

    /**
     * If false(default) collisions won't be detected
     */
    public ZbeEntity enableCollisions(boolean collidable) {
        getComponent(PhysicsComponent.class).collidable = collidable;
        return this;
    }

    public boolean collisionsEnabled() {
        return getComponent(PhysicsComponent.class).collidable;
    }

    /*/**
     * Set which entity types you want the engine to automatically detect. These can be obtained through ZbeEntity.getCollidedEntities().
     * Use ZeroBit.MANAGED_ENTITIES.ALL or ZeroBit.MANAGED_ENTITIES.NONE for umbrella management or set your own types.
     * Alternatively manage collisions yourself using ZbeEntity.collides(String entityType).
     * @param entityTypes Entity Type to check collisions with
     */
    /*public ZbeEntity manageCollisionsWith(String... entityTypes) {
        getComponent(CollisionComponent.class).managedEntityTypes = Arrays.asList(entityTypes);
        return this;
    }

    public List<String> getManagedCollisionEntities() {
        return getComponent(CollisionComponent.class).managedEntityTypes;
    }*/

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
    public ZbeEntity setPhysicsType(ZeroBit.PHYSICS_TYPE type) {
        getComponent(PhysicsComponent.class).physicsType = type;
        return this;
    }

    public ZeroBit.PHYSICS_TYPE getPhysicsType() {
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

    private Vector2 getMaxVelocity() {
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


    /**
     * Set the debug colour fro this entity.
     * You can use Utils.Colour.fromRGBA() to convert RGBA values into GL float values
     */
    public void setDebugColour(Color colour) {
        debugColour = colour;
    }

    public Color getDebugColour() {
        return debugColour;
    }

    public ZbeEntity setState(String state) {
        getComponent(StateComponent.class).state = state;
        return this;
    }

    public String getState() {
        return getComponent(StateComponent.class).state;
    }

}
