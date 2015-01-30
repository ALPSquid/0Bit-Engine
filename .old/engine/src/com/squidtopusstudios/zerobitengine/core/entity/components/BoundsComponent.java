package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Rectangle bounds for Ashley Entity
 */
public class BoundsComponent extends Component {
    public final Rectangle bounds = new Rectangle();
    public ZeroBit.BoundsOrigin boundsOrigin = ZeroBit.BoundsOrigin.CENTER;
    public String boundsAnchor = null;
}
