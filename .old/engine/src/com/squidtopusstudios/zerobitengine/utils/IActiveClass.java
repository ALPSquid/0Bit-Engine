package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.utils.Disposable;

/**
 * Base interface for active (need updating or need to dispose resources) classes
 */
public interface IActiveClass extends Disposable {

    /**
     * Update registered objects
     */
    public void update();
}
