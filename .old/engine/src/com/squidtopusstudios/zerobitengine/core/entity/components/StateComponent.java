package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * State data for Ashley Entity
 */
public class StateComponent extends Component {
    public String state = ZeroBit.EntityState.IDLE;
    public final boolean onGround = false;
}
