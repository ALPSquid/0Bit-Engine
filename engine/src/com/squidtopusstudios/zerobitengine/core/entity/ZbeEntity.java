package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.BoundsComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.ZbeEntityComponent;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MetaSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;

/**
 * Wrapper for adding Ashley Components and systems
 */
public class ZbeEntity extends Entity {

    public ZbeEntity(String name) {
        add(new ZbeEntityComponent());
        add(new ResourceComponent());
        add(new BoundsComponent());
        ZeroBit.managers.entityManager().getSystem(MetaSystem.class).setName(this, name);
    }

    /**
     * Set the Texture component
     * @param texture Texture to set
     */
    public ZbeEntity setSprite(Texture texture) {
        SystemMappers.spriteSystem().setTexture(this, texture);
        return this;
    }

    /**
     * Set the TextureRegion component
     * @param textureRegion TextureRegion to set
     */
    public ZbeEntity setSprite(TextureRegion textureRegion) {
        SystemMappers.spriteSystem().setTextureRegion(this, textureRegion);
        return this;
    }

    public TextureRegion getSprite() {
        return SystemMappers.spriteSystem().getSprite(this);
    }

    public String getName() {
        return SystemMappers.metaSystem().getName(this);
    }

    public ZbeEntity setBounds(int width, int height) {
        ComponentMappers.bounds.get(this).bounds.setSize(width, height);
        return this;
    }

    public Rectangle getBounds() {
        return ComponentMappers.bounds.get(this).bounds;
    }
}
