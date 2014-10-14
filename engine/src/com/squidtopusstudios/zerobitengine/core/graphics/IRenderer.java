package com.squidtopusstudios.zerobitengine.core.graphics;

import com.squidtopusstudios.zerobitengine.utils.IActiveClass;

/**
 * Base interface for Rendering classes
 */
public interface IRenderer extends IActiveClass {

    /**
     * Called on game start
     */
    public void create();

    public void resize(int width, int height);
}
