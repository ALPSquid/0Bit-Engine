package com.squidtopusstudios.zerobit.entity.components;

import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Component for Animations
 */
public class AnimationComponent extends ZBComponent {

    public float stateTime = 0;
    public Map<String, Animation> animations = new HashMap<String, Animation>();
    public Animation currentAnim;

    @Override
    public void reset() {

    }
}
