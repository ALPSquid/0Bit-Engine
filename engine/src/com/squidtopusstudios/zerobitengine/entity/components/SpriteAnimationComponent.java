package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.graphics.ZbeAnimation;

import java.util.HashMap;
import java.util.Map;

/**
 * Sprite Animation component for Ashley entities
 */
public class SpriteAnimationComponent extends Component {

    public ZbeAnimation currentAnimation;
    public float stateTime;
    public float frameDuration = 0.05f;
    public Map<String, ZbeAnimation> animations = new HashMap<String, ZbeAnimation>();
    public Map<String, TextureRegion[]> animationFrames = new HashMap<String, TextureRegion[]>();


    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime);
    }

    /**
     * Gets a previously registered animation
     * @param name name of the registered Animation
     * @return the Animation instance
     */
    public ZbeAnimation getAnimation(String name) {
        return animations.get(name);
    }

    /**
     * Gets a previously registered set of animation frames
     * @param name name of the registered frames
     * @return the array of frames
     */
    public TextureRegion[] getAnimationFrames(String name) {
        return animationFrames.get(name);
    }
}
