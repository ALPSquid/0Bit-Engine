package com.squidtopusstudios.zerobitengine.graphics;

import com.squidtopusstudios.zerobitengine.utils.ActiveInterface;

/**
 * Base interface for Rendering classes
 */
public interface Renderer extends ActiveInterface {

    /**
     * Called on game start
     */
    public void create();

    public void resize(int width, int height);
}
