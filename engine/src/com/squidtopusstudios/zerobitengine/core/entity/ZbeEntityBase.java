package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteAnimationSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;
import com.squidtopusstudios.zerobitengine.core.graphics.ZbeAnimation;

import java.util.Arrays;

/**
 * Base class for 0bit Entities
 */
public abstract class ZbeEntityBase extends Entity {

    protected Color debugColour = Color.RED;
    protected boolean isBox2D;


    public ZbeEntityBase(String type) {
        add(new ZbeEntityComponent());
        add(new ResourceComponent());
        add(new SpriteAnimationComponent());
        add(new BoundsComponent());
        add(new SimplePositionComponent());
        //add(new CollisionComponent());
        add(new LogicComponent());
        add(new StateComponent());
        setType(type);
    }

    public boolean isBox2D() {
        return isBox2D;
    }


    /**
     * Set the entity type/group (used for collision detection)
     * @param type Entity type/group
     * @return the instance of ZbeEntityBase
     */
    private ZbeEntityBase setType(String type) {
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
    public ZbeEntityBase setSprite(Texture texture) {
        ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).setTexture(this, texture);
        return this;
    }

    /**
     * Set the TextureRegion component
     * @param textureRegion TextureRegion to set
     * @return current ZbeEntity instance
     */
    public ZbeEntityBase setSprite(TextureRegion textureRegion) {
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
     * Set the alignment of the sprite within the entity bounds. Default {@link ZeroBit}.Align.CENTER.
     * @param align alignment value, {@link ZeroBit}.Align
     * @return current ZbeEntity instance
     */
    public ZbeEntityBase setSpriteAlign(ZeroBit.Align align) {
        getComponent(ResourceComponent.class).align = align;
        return this;
    }

    /**
     * Get the current sprite alignment
     * @return the currently set sprite {@link ZeroBit}.Align property
     */
    public ZeroBit.Align getSpriteAlign() {
        return getComponent(ResourceComponent.class).align;
    }

    /**
     * Set the sprite offset in pixels of the source image
     * @param x x offset in pixels of the source image
     * @param y y offset in pixels of the source image
     * @return current ZbeEntity instance
     */
    public ZbeEntityBase setSpriteOffset(float x, float y) {
        getComponent(ResourceComponent.class).spriteOffset.set(x, y);
        return this;
    }

    /**
     * @return the sprite offset
     */
    public Vector2 getSpriteOffset() {
        return getComponent(ResourceComponent.class).spriteOffset;
    }

    /**
     * Flip the currently assigned texture along the x and/or y axis
     * @param x whether to flip horizontally
     * @param y whether to flip vertically
     */
    public void flipSprite(boolean x, boolean y) {
        ZeroBit.managers.entityManager().getSystem(SpriteSystem.class).flipSprite(this, x, y);
    }
    /**
     * Flips the currently assigned texture horizontally
     */
    public void flipSprite() {
        flipSprite(true, false);
    }

    public ZbeEntityBase setDefaultFrameDuration(float frameDuration) {
        getComponent(SpriteAnimationComponent.class).frameDuration = frameDuration;
        return this;
    }

    /**
     * Creates and registers an array of frames from a sprite sheet. Retrieve with getAnimFrames(name);
     * Designed to be used with createAnimation() for sprite sheets with multiple animations.
     * @param framesName name for the frames
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created array of frames
     */
    public TextureRegion[] createAnimationFrames(String framesName, Texture spriteSheet, int columns, int rows) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimationFrames(this, framesName, spriteSheet, columns, rows);
    }

    /**
     * * Creates and registers a new animation from the provided frames. The animation can be retrieved by name using getAnimation(name);
     * @param animName name to register the animation under
     * @param frames frames to create an animation from
     * @return the created animation
     */
    public ZbeAnimation createAnimation(String animName, TextureRegion... frames) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimation(this, animName, frames);
    }

    /**
     * Creates and registers a new animation from a sprite sheet for the particular animation. The animation can be retrieved by name using getAnimation(name);
     * Note that animation frames are automatically created and registered with the name 'animName_frames'
     * @param animName name to register the animation under. The animation frames are registered with the name 'animName_frames'
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created animation
     */
    public ZbeAnimation createAnimation(String animName, Texture spriteSheet, int columns, int rows) {
        return ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class)
                .createAnimation(this, animName, spriteSheet, columns, rows);
    }

    /**
     * Gets a previously registered animation
     * @param animName name of the registered Animation
     * @return the Animation instance
     */
    public ZbeAnimation getAnimation(String animName) {
        return getComponent(SpriteAnimationComponent.class).getAnimation(animName);
    }

    /**
     * Gets a previously registered set of animation frames
     * @param framesName name of the registered frames
     * @return the array of frames
     */
    public TextureRegion[] getAnimationFrames(String framesName) {
        return getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName);
    }

    /**
     * Gets a part of a previously registered set of animation frames as defined by startFrame and endFrame
     * @param framesName name of the registered frames
     * @param startFrame start frame inclusive (from 0)
     * @param endFrame end frame inclusive (from 0)
     * @return the array of frames
     */
    public TextureRegion[] getAnimationFrames(String framesName, int startFrame, int endFrame) {
        if (startFrame == endFrame) {
            return new TextureRegion[]{getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName)[startFrame]};
        }
        endFrame++;
        return Arrays.copyOfRange(getComponent(SpriteAnimationComponent.class).getAnimationFrames(framesName), startFrame, endFrame);
    }

    /**
     * Set the animation to play
     * @param name name of the registered animation to set
     */
    public ZbeEntityBase setAnimation(String name) {
        ZeroBit.managers.entityManager().getSystem(SpriteAnimationSystem.class).setAnimation(this, name);
        return this;
    }

    /**
     * @return the currently set animation
     */
    public ZbeAnimation getCurrentAnimation() {
        return getComponent(SpriteAnimationComponent.class).currentAnimation;
    }

    /**
     * Set custom dimensions for the entity's sprite. Useful for using bounds smaller/larger than the set sprite.
     * Default is the entity's bounds dimensions.
     * @param width desired sprite width in pixels
     * @param height desired sprite height in pixels
     * @return current ZbeEntityBase instance
     */
    public ZbeEntityBase setSpriteDimensions(int width, int height) {
        getComponent(ResourceComponent.class).spriteDimensions.width = width;
        getComponent(ResourceComponent.class).spriteDimensions.height = height;
        return this;
    }

    /**
     * Get the custom dimensions of the entity's sprite.
     * @return entity's SpriteDimensions
     */
    public ResourceComponent.SpriteDimensions getSpriteDimensions() {
        return getComponent(ResourceComponent.class).spriteDimensions;
    }

    /**
     * Set the rectangle bounds of the entity
     * @return current ZbeEntity instance
     */
    public ZbeEntityBase setBounds(float width, float height) {
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

        /** Override in subclass **/
    public ZbeEntityBase setPosition(float x, float y) {
        return this;
    }

    /** Override in subclass **/
    public Vector2 getPosition() {
        return new Vector2(0,0);
    }

    public float getY() {
        return getPosition().y;
    }
    public float getX() {
        return getPosition().x;
    }

    /** Override in subclass **/
    public ZbeEntityBase enableCollisions(boolean collidable) {
        return this;
    }

    /** Override in subclass **/
    public boolean collisionsEnabled() {
        return false;
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

    public ZbeEntityBase setState(String state) {
        getComponent(StateComponent.class).state = state;
        return this;
    }

    public String getState() {
        return getComponent(StateComponent.class).state;
    }
}