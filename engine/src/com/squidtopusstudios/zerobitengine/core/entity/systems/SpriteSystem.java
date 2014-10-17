package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;

import javax.xml.soap.Text;

/**
 * Animation and static sprite system for Ashley Entities
 */
public class SpriteSystem extends ZbeSystem {

    /**
     * Set current entity TextureRegion
     * @param entity Entity to set texture for
     * @param texture Texture to set
     */
    public void setTexture(ZbeEntity entity, Texture texture) {
        setTextureRegion(entity, new TextureRegion(texture));
    }

    /**
     * Set current entity TextureRegion
     * @param entity Entity to set texture for
     * @param textureRegion Texture to set
     */
    public void setTextureRegion(ZbeEntity entity, TextureRegion textureRegion) {
        ResourceComponent resourceComponent = ComponentMappers.resource.get(entity);
        resourceComponent.textureRegion = textureRegion;
        if (resourceComponent.spriteDimensions.width == -1) {
            resourceComponent.spriteDimensions.width = entity.getWidth();
        }
        if (resourceComponent.spriteDimensions.height == -1) {
            resourceComponent.spriteDimensions.height = entity.getHeight();
        }
    }

    public TextureRegion getSprite(Entity entity) {
        return ComponentMappers.resource.get(entity).textureRegion;
    }

    /**
     * Flip the currently assigned texture along the x and/or y axis
     * @param entity {@link ZbeEntity} instance to flip
     * @param x whether to flip horizontally
     * @param y whether to flip vertically
     */
    public void flipSprite(ZbeEntity entity, boolean x, boolean y) {
        ComponentMappers.resource.get(entity).textureRegion.flip(true, false);
    }

    /**
     * Flips the currently assigned texture horizontally
     * @param entity {@link ZbeEntity} instance to flip
     */
    public void flipSprite(ZbeEntity entity) {
        flipSprite(entity, true, false);
    }
}
