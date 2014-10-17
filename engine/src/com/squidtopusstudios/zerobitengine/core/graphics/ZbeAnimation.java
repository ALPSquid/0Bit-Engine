package com.squidtopusstudios.zerobitengine.core.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Placeholder: Possibility of adding extra functionality like a frame duration per individual frame etc.
 */
public class ZbeAnimation extends Animation{

    private String name;

    /** Constructor, storing the frame duration and key frames.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames. */
    public ZbeAnimation(String name, float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
        this.name = name;
    }

    /** Constructor, storing the frame duration, key frames and play type.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames.
     * @param playMode the animation playback mode. */
    public ZbeAnimation(String name, float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
        this.name = name;
    }
    /** Constructor, storing the frame duration and key frames.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the {@link TextureRegion}s representing the frames. */
    public ZbeAnimation(String name, float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
