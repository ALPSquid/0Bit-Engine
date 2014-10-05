package com.squidtopusstudios.zerobitengine.utils;

import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

/**
 * Base Entity logic interface. Implement this and put your entity's logic in update()
 */
public interface ZbeEntityLogic {

    /**
     * Put your logic for the entity here
     * @param entity the entity this system is added to, use it to manipulate the entity.
     *               For example, if you've added your ZbeEntityLogic class to a player entity
     *               you can move the player by calling entity.moveBy(x, y) in this update method.
     */
    public void update(ZbeEntity entity, float deltaTime);
}
