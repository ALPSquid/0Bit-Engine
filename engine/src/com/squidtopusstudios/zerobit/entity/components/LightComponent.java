package com.squidtopusstudios.zerobit.entity.components;


import box2dLight.Light;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Component for attaching Box2D lights to entities
 * Note: Currently using PositionalLight.attachToBody instead
 */
public class LightComponent extends ZBComponent {

    private List<Light> lights = new ArrayList<Light>();


    /** Adds a light to be rendered */
    public void addLight(Light light) {
        lights.add(light);
    }

    /** Removes a light */
    public void removeLight(Light light) {
        lights.remove(light);
    }

    /**
     * @return List of added lights
     */
    public List<Light> getLights() {
        return lights;
    }

    @Override
    public void reset() {

    }
}
