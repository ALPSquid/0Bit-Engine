package com.squidtopusstudios.zerobit.entity.components;

import com.badlogic.ashley.core.Component;

/**
 * Component that can reset itself
 */
public abstract class ZBComponent extends Component {

    public abstract void reset();
}
