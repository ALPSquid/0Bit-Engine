package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;


/**
 * Physics properties for Ashley Entity
 */
public class PhysicsComponent extends Component {
    public ZeroBit.PHYSICS_TYPE physicsType = ZeroBit.PHYSICS_TYPE.NONE;
    public boolean collidable = false;
    public Vector2 velocity = new Vector2(0,0);
    public Vector2 maxVelocity = new Vector2(100,100);
    public boolean applyGravity = false;
}
