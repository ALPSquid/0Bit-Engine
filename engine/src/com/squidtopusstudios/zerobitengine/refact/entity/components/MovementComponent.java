package com.squidtopusstudios.zerobitengine.refact.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Keeps track of velocity and acceleration
 */
public class MovementComponent extends Component {

    public Vector2 acceleration = new Vector2(0,0);
    public Vector2 velocity = new Vector2(0,0);
}
