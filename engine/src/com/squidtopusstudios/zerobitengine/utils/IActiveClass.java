package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.utils.Disposable;

/**
 * Base interface for Manager classes
 */
public interface IActiveClass extends Disposable {

    /**
     * Update registered objects
     */
    public void update(float deltaTime);
}
