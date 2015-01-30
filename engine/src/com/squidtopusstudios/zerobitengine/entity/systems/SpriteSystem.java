package com.squidtopusstudios.zerobitengine.entity.systems;

import com.squidtopusstudios.zerobitengine.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;

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
     * @param entity {@link com.squidtopusstudios.zerobitengine.entity.DynamicEntity} instance to flip
     * @param x whether to flip horizontally
     * @param y whether to flip vertically
     */
    public void flipSprite(ZbeEntity entity, boolean x, boolean y) {
        //ComponentMappers.resource.get(entity).textureRegion.flip(true, false);
        ComponentMappers.resource.get(entity).sprite.flip(x, y);
    }

    /**
     * Flips the currently assigned texture horizontally
     * @param entity {@link com.squidtopusstudios.zerobitengine.entity.DynamicEntity} instance to flip
     */
    public void flipSprite(ZbeEntity entity) {
        flipSprite(entity, true, false);
    }

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {

    }
}
