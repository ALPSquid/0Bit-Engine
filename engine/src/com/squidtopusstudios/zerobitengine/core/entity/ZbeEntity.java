package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.squidtopusstudios.zerobitengine.core.Managers;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;

/**
 * Wrapper for adding Ashley Components and provides 'standard' Entity functionality
 */
public class ZbeEntity extends Entity {

    /**
     * Converts Texture to TextureRegion and set the region component
     * @param texture Texture to set
     */
    public void setTexture(Texture texture) {
        TextureRegion textureRegion = new TextureRegion();
        textureRegion.setTexture(texture);
        setTextureRegion(textureRegion);
    }

    /**
     * Set TextureRegion component
     * @param textureRegion TextureRegion to set
     */
    public void setTextureRegion(TextureRegion textureRegion) {
        this.add(new ResourceComponent().setTextureRegion(textureRegion));
    }

    public void dispose() {

    }
}
