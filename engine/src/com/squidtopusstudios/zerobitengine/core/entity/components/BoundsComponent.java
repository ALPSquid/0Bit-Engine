package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Rectangle bounds for Ashley Entity
 */
public class BoundsComponent extends Component {
    public final Rectangle bounds = new Rectangle();
}
