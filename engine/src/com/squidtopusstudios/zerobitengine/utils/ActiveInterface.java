package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.utils.Disposable;

/**
 * An interface for classes that need updating and/or disposing
 */
public interface ActiveInterface extends Disposable {

    /**
     * Update registered objects
     */
    public void update();
}
