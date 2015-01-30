package com.squidtopusstudios.zerobitengine.entity.systems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.entity.components.SpriteAnimationComponent;
import com.squidtopusstudios.zerobitengine.graphics.ZbeAnimation;

/**
 * Sprite Animation System for Ashley Entities. Handles the updating of animation frames.
 */
public class SpriteAnimationSystem  extends ZbeSystem {

    SpriteAnimationComponent spriteAnim;
    TextureRegion textureRegion;
    boolean flipX;
    boolean flipY;

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
       /* spriteAnim = ComponentMappers.animation.get(entity);
        if (spriteAnim.currentAnimation != null) {
            // update the current animation
            spriteAnim.stateTime += deltaTime;
            textureRegion = spriteAnim.getCurrentFrame();
            flipX = entity.getSprite().isFlipX();
            flipY = entity.getSprite().isFlipY();
            entity.setTextureRegion(textureRegion);
            if (flipX || flipY) entity.flipSprite(flipX, flipY);
        }*/
    }

    /**
     * Creates and registers an array of frames from a sprite sheet. Retrieve with getAnimFrames(name);
     * Designed to be used with createAnimation() for sprite sheets with multiple animations.
     * @param entity ZbeEntity instance
     * @param framesName name for the frames
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created array of frames
     */
    public TextureRegion[] createAnimationFrames(ZbeEntity entity, String framesName, Texture spriteSheet, int columns, int rows) {
        TextureRegion[][] tmp  = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/columns, spriteSheet.getHeight()/rows);
        TextureRegion[] frames = new TextureRegion[columns * rows];
        int index = 0;
        for (int i=0; i < rows; i++) {
            for (int j=0; j < columns; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        ZeroBit.logger.logDebug("Creating animation frames: "+framesName);
        ComponentMappers.animation.get(entity).animationFrames.put(framesName, frames);
        return frames;
    }

    /**
     * Creates and registers a new animation from the provided frames. The animation can be retrieved by name using getAnimation(name);
     * @param entity ZbeEntity instance
     * @param animName name to register the animation under
     * @param frames frames to create an animation from
     * @return the created animation
     */
    public ZbeAnimation createAnimation(ZbeEntity entity, String animName, TextureRegion... frames) {
        spriteAnim = ComponentMappers.animation.get(entity);
        ZbeAnimation animation = new ZbeAnimation(animName, spriteAnim.frameDuration, frames);
        ZeroBit.logger.logDebug("Creating animation: "+animName);
        spriteAnim.animations.put(animName, animation);
        return animation;
    }

    /**
     * Creates and registers a new animation from a sprite sheet for the particular animation. The animation can be retrieved by name using getAnimation(name);
     * Note that animation frames are automatically created and registered with the name 'animName_frames'
     * @param entity ZbeEntity instance
     * @param animName name to register the animation under. The animation frames are registered with the name 'animName_frames'
     * @param spriteSheet animation sprite sheet. Assumes all frames are of equal size. //TODO explicit sizing
     * @param columns number of columns in the sprite sheet
     * @param rows number of rows in the sprite sheet
     * @return the created animation
     */
    public ZbeAnimation createAnimation(ZbeEntity entity, String animName, Texture spriteSheet, int columns, int rows) {
        TextureRegion[] frames = createAnimationFrames(entity, animName+"_frames", spriteSheet, columns, rows);
        return createAnimation(entity, animName, frames);
    }

    /**
     * Set the currently playing animation
     * @param entity ZbeEntity instance
     * @param name name of the registered animation to set
     */
    public void setAnimation(ZbeEntity entity, String name) {
        /*if (entity.getCurrentAnimation() == null || !entity.getCurrentAnimation().getName().equals(name)) {
            //ZeroBit.logger.logDebug("Setting " + entity.getType() + " animation to " + name);
            //spriteAnim.currentAnimation = spriteAnim.animations.get(name);
            ComponentMappers.animation.get(entity).currentAnimation = entity.getAnimation(name);
            if (entity.getCurrentAnimation() == null) {
                ZeroBit.logger.logError("NullPointer trying to get animation: '"+name+"', did you forget to create it?");
            }
        }*/
    }
}
