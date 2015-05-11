package com.squidtopusstudios.zerobit.entity.components;

/**
 * Component for Parallax Settings
 */
public class ParallaxComponent extends ZBComponent {

    public float intensity = 0;

    public ParallaxComponent(float intensity) {
        this.intensity = intensity;
    }
    public ParallaxComponent() {
        this(0);
    }

    @Override
    public void reset() {

    }
}
