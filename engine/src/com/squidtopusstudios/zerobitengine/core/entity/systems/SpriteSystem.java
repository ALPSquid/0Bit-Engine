package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;

import javax.xml.soap.Text;

/**
 * Animation and static sprite system for Ashley Entities
 */
public class SpriteSystem extends ZbeSystem {

    /**
     * Set current entity Texture (un-necessary due to moving to the Sprite class)
     * @param entity Entity to set texture for
     * @param texture Texture to set
     */
    /*private void setTexture(ZbeEntityBase entity, Texture texture) {
        //setTextureRegion(entity, new TextureRegion(texture));
    }*/

    /**
     * Set current entity TextureRegion (un-necessary due to moving to the Sprite class)
     * @param entity Entity to set texture for
     * @param textureRegion Texture to set
     */
    /*private void setTextureRegion(ZbeEntityBase entity, TextureRegion textureRegion) {
        ResourceComponent resourceComponent = ComponentMappers.resource.get(entity);
        resourceComponent.textureRegion = textureRegion;
    }*/

    //(un-necessary due to moving to the Sprite class)
    /*private TextureRegion getSprite(ZbeEntityBase entity) {
        return ComponentMappers.resource.get(entity).textureRegion;
    }*/

    /**
     * Flip the currently assigned texture along the x and/or y axis
     * @param entity {@link ZbeEntity} instance to flip
     * @param x whether to flip horizontally
     * @param y whether to flip vertically
     */
    public void flipSprite(ZbeEntityBase entity, boolean x, boolean y) {
        //ComponentMappers.resource.get(entity).textureRegion.flip(true, false);
        ComponentMappers.resource.get(entity).sprite.flip(x, y);
    }

    /**
     * Flips the currently assigned texture horizontally
     * @param entity {@link ZbeEntity} instance to flip
     */
    public void flipSprite(ZbeEntityBase entity) {
        flipSprite(entity, true, false);
    }
}
