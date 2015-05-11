package com.squidtopusstudios.zerobit.ui.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * Scene2D sprite animation actor
 */
public class SpriteAnimationActor extends Actor {

    private float stateTime = 0;
    private Map<String, Animation> animations = new HashMap<String, Animation>();
    private Animation currentAnim;
    private TextureRegion currentFrame;


    public SpriteAnimationActor() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentFrame = currentAnim.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(),
                currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
    }

    /**
     * @param animation to play
     */
    public void setAnimation(Animation animation) {
        currentAnim = animation;
    }

    /**
     * @return map of animations that this actor has
     */
    public Map<String, Animation> getAnimations() {
        return animations;
    }
}
