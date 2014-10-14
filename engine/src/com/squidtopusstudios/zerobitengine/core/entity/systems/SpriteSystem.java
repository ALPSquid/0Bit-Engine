package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;

/**
 * Animation and static sprite system for Ashley Entities
 */
public class SpriteSystem extends ZbeSystem {


    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.getVelocity().x < 0 && !ComponentMappers.resource.get(entity).textureRegion.isFlipX()) {
            ComponentMappers.resource.get(entity).textureRegion.flip(true, false);
        }
        else if (entity.getVelocity().x > 0 && ComponentMappers.resource.get(entity).textureRegion.isFlipX()) {
            ComponentMappers.resource.get(entity).textureRegion.flip(true, false);
        }
    }

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
}
