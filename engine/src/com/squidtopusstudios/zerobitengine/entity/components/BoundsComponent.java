package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.ZeroBit;

/**
 * Rectangle bounds for Ashley Entity
 */
public class BoundsComponent extends Component {
    public final Rectangle bounds = new Rectangle();
    public ZeroBit.BoundsOrigin boundsOrigin = ZeroBit.BoundsOrigin.CENTER;
    public String boundsAnchor = null;
}
