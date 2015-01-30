package com.squidtopusstudios.zerobitengine;


import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Main {@link World} class. You should extend this to create your world.
 */
public class World extends WorldBase {

    public World(int pixelsPerUnit) {
        super(pixelsPerUnit);
        worldType = ZeroBit.WorldType.DEFAULT;
    }
}
