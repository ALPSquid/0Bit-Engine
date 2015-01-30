package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.ZeroBit;

/**
 * Resource component for Ashley entities
 */
public class ResourceComponent extends Component {
    public Sprite sprite = new Sprite();
    public boolean autoRotate = true;
    public boolean autoOrigin = true;
    public ZeroBit.Align align = ZeroBit.Align.CENTER;
    public Vector2 spriteOffset = new Vector2(0, 0);
}
