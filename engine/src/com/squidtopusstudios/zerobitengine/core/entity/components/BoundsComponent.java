package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Rectangle bounds for Ashley Entity
 */
public class BoundsComponent extends Component {
    public final Rectangle bounds = new Rectangle();
}
