package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;

/**
 * Animation and static sprite system for Ashley Entities
 */
public class SpriteSystem extends ZbeSystem {


    @Override
    public void processEntity(Entity entity, float deltaTime) {

    }

    /**
     * Set current entity TextureRegion
     * @param entity Entity to set texture for
     * @param texture Texture to set
     */
    public void setTexture(Entity entity, Texture texture) {
        setTextureRegion(entity, new TextureRegion(texture));
    }

    /**
     * Set current entity TextureRegion
     * @param entity Entity to set texture for
     * @param textureRegion Texture to set
     */
    public void setTextureRegion(Entity entity, TextureRegion textureRegion) {
        ComponentMappers.resource.get(entity).textureRegion = textureRegion;
    }

    public TextureRegion getSprite(Entity entity) {
        return ComponentMappers.resource.get(entity).textureRegion;
    }
}
