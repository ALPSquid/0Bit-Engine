package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.squidtopusstudios.zerobitengine.ZeroBit;

/**
 * State data for Ashley Entity
 */
public class StateComponent extends Component {
    public String state = ZeroBit.EntityState.IDLE;
    public final boolean onGround = false;
}
