package com.squidtopusstudios.zerobit.entity;

import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;
import com.squidtopusstudios.zerobit.entity.components.StateComponent;

/**
 * User Data for Box2D bodies. Simulates a component for box2D related collision handling and states
 */
public class Box2DUserData {

    public String id = "";
    public StateComponent stateComponent;
    public int footContacts = 0;
    public SteeringEntity steeringEntity = null;
    public String collidingSensor = "";
}
