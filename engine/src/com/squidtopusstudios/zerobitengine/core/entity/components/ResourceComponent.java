package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Resource component for Ashley entities
 */
public class ResourceComponent extends Component {
    /*public class SpriteDimensions {
        public float width = -1;
        public float height = -1;
    }
    public SpriteDimensions spriteDimensions = new SpriteDimensions();*/
    public Sprite sprite = new Sprite();
    public boolean autoRotate = true;
    public boolean autoOrigin = true;
    //public TextureRegion textureRegion;
    public ZeroBit.Align align = ZeroBit.Align.CENTER;
    public Vector2 spriteOffset = new Vector2(0, 0);
}
