package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Resource component for Ashley entities. Handles textures and animations
 */
public class ResourceComponent extends Component implements Disposable {

    public TextureRegion textureRegion;

    /**
     * Component to set current entity TextureRegion
     * @param textureRegion TextureRegion to set
     * @return This ResourceComponent instance
     */
    public ResourceComponent setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        return this;
    }

    @Override
    public void dispose() {

    }
}
