package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;


/**
 * Physics properties for Ashley Entity
 */
public class PhysicsComponent extends Component {
    public boolean isDynamic = false;
    public Vector2 velocity = new Vector2(0,0);
}
