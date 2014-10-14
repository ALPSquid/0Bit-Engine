package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Resource component for Ashley entities
 */
public class ResourceComponent extends Component {
    public class SpriteDimensions {
        public float width = -1;
        public float height = -1;
    }
    public SpriteDimensions spriteDimensions = new SpriteDimensions();
    public TextureRegion textureRegion;

}
