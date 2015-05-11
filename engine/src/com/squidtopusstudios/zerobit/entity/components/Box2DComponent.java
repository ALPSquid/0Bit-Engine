package com.squidtopusstudios.zerobit.entity.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;

/**
 * Holds Box2D data
 */
public class Box2DComponent extends ZBComponent {

    public Body body;


    @Override
    public void reset() {
        ((Box2DUserData)body.getUserData()).collidingSensor = "";
        ((Box2DUserData)body.getUserData()).footContacts = 0;
    }
}
