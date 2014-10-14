package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * State data for Ashley Entity
 */
public class StateComponent extends Component {
    public String state = ZeroBit.ENTITY_STATE.IDLE;
    public final boolean onGround = false;
}
