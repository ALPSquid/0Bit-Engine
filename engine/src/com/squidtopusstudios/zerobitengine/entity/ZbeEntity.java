package com.squidtopusstudios.zerobitengine.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.systems.SpriteAnimationSystem;
import com.squidtopusstudios.zerobitengine.entity.systems.SpriteSystem;
import com.squidtopusstudios.zerobitengine.graphics.ZbeAnimation;
import com.squidtopusstudios.zerobitengine.entity.components.*;

import java.util.Arrays;

/**
 * Base class for 0bit Entities
 */
public abstract class ZbeEntity extends Entity {

    public ZbeEntity(String type) {
        setType(type);
        add(new ZbeEntityComponent());
        add(new SimplePositionComponent());
        add(new LogicComponent());
        add(new StateComponent());
        add(new ResourceComponent());
        add(new SpriteAnimationComponent());
    }

    /** Called when added to the world **/
    public abstract void create();

    /**
     * Set the entity type/group (used for identification including in collision detection)
     * @param type Entity type/group
     */
    private void setType(String type) {
        getMetaData().type = type;
    }

    /**
     * Internal and simple way of getting Ashley components.
     * This allows you to add your own components (using ZbeEntity.add(component)) without
     * having to create your own ZbeEntity class
     * See com.squidtopusstudios.zerobitengine.entity.components for built in component types
     * @param component component class to get e.g. ResourceComponent.class
     * @return the instance of the component belonging to this entity
     */
    public <T extends Component> T get(Class<T> component) {
        return ComponentMapper.getFor(component).get(this);
    }

    public ZbeEntityComponent getMetaData() {
        return ComponentMappers.metadata.get(this);
    }

    public CollisionComponent getCollisionData() {
        return ComponentMappers.collision.get(this);
    }


    /**
     * Set the Sprite component. Note: If you want to change the texture, use setTexture
     * @param sprite Texture to set
     * @return current ZbeEntity instance
     */
    public ZbeEntity setSprite(Sprite sprite) {
        //ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).setTexture(this, texture);
       // getSprite().set(sprite);
        return this;
    }

    /** @return the Sprite for this entity */
    /*public Sprite getSprite() {
        //return ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).getSprite(this);
        return getComponent(ResourceComponent.class).sprite;
    }*/

    /** Set the texture for the entity **/
    /*public ZbeEntity setTexture(Texture texture) {
        getSprite().setRegion(texture);
        return this;
    }

    *//** Set the texture region for the entity **//*
    public ZbeEntity setTextureRegion(TextureRegion region) {
        getSprite().setRegion(region);
        return this;
    }

    *//**
     * Set the alignment of the sprite within the entity bounds. Default {@link ZeroBit}.Align.CENTER.
     * @param align alignment value, {@link ZeroBit}.Align
     * @return current ZbeEntity instance
     *//*
    public ZbeEntity setSpriteAlign(ZeroBit.Align align) {
        getComponent(ResourceComponent.class).align = align;
        return this;
    }

    *//**
     * Get the current sprite alignment
     * @return the currently set sprite {@link ZeroBit}.Align property
     *//*
    public ZeroBit.Align getSpriteAlign() {
        return getComponent(ResourceComponent.class).align;
    }

    *//**
     * Set the sprite offset in pixels of the source image
     * @param x x offset in pixels of the source image
     * @param y y offset in pixels of the source image
     * @return current ZbeEntity instance
     *//*
    public ZbeEntity setSpriteOffset(float x, float y) {
        getComponent(ResourceComponent.class).spriteOffset.set(x, y);
        return this;
    }

    *//**
     * @return the sprite offset
     *//*
    public Vector2 getSpriteOffset() {
        return getComponent(ResourceComponent.class).spriteOffset;
    }

    *//**
     * Flip the currently assigned texture along the x and/or y axis
     * @param x whether to flip horizontally
     * @param y whether to flip vertically
     *//*
    public void flipSprite(boolean x, boolean y) {
        ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).flipSprite(this, x, y);
    }
    *//**
     * Flips the currently assigned texture horizontally
     *//*
    public void flipSprite() {
        flipSprite(true, false);
    }

    public ZbeEntity setDefaultFrameDuration(float frameDuration) {
        getComponent(SpriteAnimationComponent.class).frameDuration = frameDuration;
        return this;
    }

    *//**
     * Creates and registers an array of frames from a sprite sheet. Retrieve with getAnimFrames(name);
     * Designed to be used with createAnimation() for sprite sheets with multiple animations.
     * @param framesName name for the frames
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created array of frames
     *//*
    public TextureRegion[] createAnimationFrames(String framesName, Texture spriteSheet, int columns, int rows) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimationFrames(this, framesName, spriteSheet, columns, rows);
    }

    *//**
     * * Creates and registers a new animation from the provided frames. The animation can be retrieved by name using getAnimation(name);
     * @param animName name to register the animation under
     * @param frames frames to create an animation from
     * @return the created animation
     *//*
    public ZbeAnimation createAnimation(String animName, TextureRegion... frames) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimation(this, animName, frames);
    }

    *//**
     * Creates and registers a new animation from a sprite sheet for the particular animation. The animation can be retrieved by name using getAnimation(name);
     * Note that animation frames are automatically created and registered with the name 'animName_frames'
     * @param animName name to register the animation under. The animation frames are registered with the name 'animName_frames'
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created animation
     *//*
    public ZbeAnimation createAnimation(String animName, Texture spriteSheet, int columns, int rows) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimation(this, animName, spriteSheet, columns, rows);
    }

    *//**
     * Gets a previously registered animation
     * @param animName name of the registered Animation
     * @return the Animation instance
     *//*
    public ZbeAnimation getAnimation(String animName) {
        return getComponent(SpriteAnimationComponent.class).getAnimation(animName);
    }

    *//**
     * Gets a previously registered set of animation frames
     * @param framesName name of the registered frames
     * @return the array of frames
     *//*
    public TextureRegion[] getAnimationFrames(String framesName) {
        return getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName);
    }

    *//**
     * Gets a part of a previously registered set of animation frames as defined by startFrame and endFrame
     * @param framesName name of the registered frames
     * @param startFrame start frame inclusive (from 0)
     * @param endFrame end frame inclusive (from 0)
     * @return the array of frames
     *//*
    public TextureRegion[] getAnimationFrames(String framesName, int startFrame, int endFrame) {
        if (startFrame == endFrame) {
            return new TextureRegion[]{getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName)[startFrame]};
        }
        endFrame++;
        return Arrays.copyOfRange(getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName), startFrame, endFrame);
    }

    *//**
     * Set the animation to play
     * @param name name of the registered animation to set
     *//*
    public ZbeEntity setAnimation(String name) {
        ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class).setAnimation(this, name);
        return this;
    }

    *//**
     * @return the currently set animation
     *//*
    public ZbeAnimation getCurrentAnimation() {
        return getComponent(SpriteAnimationComponent.class).currentAnimation;
    }

    *//**
     * Set custom dimensions for the entity's sprite. Useful for using bounds smaller/larger than the set sprite.
     * Default is the entity's bounds dimensions.
     * @param width desired sprite width in pixels
     * @param height desired sprite height in pixels
     * @return current ZbeEntityBase instance
     *//*
    public ZbeEntity setSpriteDimensions(int width, int height) {
        //TODO maintain aspect ratio option
        //getComponent(ResourceComponent.class).spriteDimensions.width = width;
        //getComponent(ResourceComponent.class).spriteDimensions.height = height;
        getSprite().setSize(width, height);
        return this;
    }

    *//**
     * Set the rectangle bounds of the entity
     * @param resizeSprite whether to resize the sprite to match the new bounds dimensions
     **//*
    public ZbeEntity setBounds(float width, float height, boolean resizeSprite) {
        getBounds().setSize(width, height);
        if (resizeSprite) getSprite().setSize(width, height);
        return this;
    }
    *//**
     * Set the rectangle bounds of the entity. The Sprite WON'T automatically be resized to match.
     * @return current ZbeEntity instance
     *//*
    public ZbeEntity setBounds(float width, float height) {
        setBounds(width, height, false);
        return this;
    }

    *//**
     * @return rectangle bounds
     *//*
    public Rectangle getBounds() {
        return getComponent(BoundsComponent.class).bounds;
    }

    public float getWidth() {
        return getBounds().width;
    }

    public float getHeight() {
        return getBounds().height;
    }

        *//** Override in subclass **//*
    public ZbeEntity setPosition(float x, float y) {
        return this;
    }

    *//** Override in subclass **//*
    public Vector2 getPosition() {
        return new Vector2(0,0);
    }

    public float getY() {
        return getPosition().y;
    }
    public float getX() {
        return getPosition().x;
    }

    *//** Override in subclass **//*
    public ZbeEntity enableCollisions(boolean collidable) {
        return this;
    }

    *//** Override in subclass **//*
    public boolean collisionsEnabled() {
        return false;
    }

    *//**
     * Set the debug colour fro this entity.
     * You can use Utils.Colour.fromRGBA() to convert RGBA values into GL float values
     *//*
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

    *//** @return whether the engine will handle sprite rotation automatically **//*
    public boolean autoRotate() {
        return getComponent(ResourceComponent.class).autoRotate;
    }
    *//** @param autoRotate whether the engine should handle sprite rotation automatically **//*
    public ZbeEntity setAutoRotate(boolean autoRotate) {
        getComponent(ResourceComponent.class).autoRotate = autoRotate;
        return this;
    }
    *//** @return whether the engine will handle the sprite origin automatically **//*
    public boolean autoOrigin() {
        return getComponent(ResourceComponent.class).autoOrigin;
    }
    *//** @param autoOrigin whether the engine should handle the sprite origin automatically **//*
    public ZbeEntity setAutoOrigin(boolean autoOrigin) {
        getComponent(ResourceComponent.class).autoOrigin = autoOrigin;
        return this;
    }*/

    public abstract void dispose();
}
