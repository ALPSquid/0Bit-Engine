package com.squidtopusstudios.zerobit.entity.components;

import com.squidtopusstudios.zerobit.entity.EntityStates;

/**
 * Holds state data
 */
public class StateComponent extends ZBComponent {

    public int state = EntityStates.IDLE;
    public boolean onGround = false;

    @Override
    public void reset() {

    }
}
