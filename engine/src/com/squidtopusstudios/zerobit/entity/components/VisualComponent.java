package com.squidtopusstudios.zerobit.entity.components;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * For holding texture and animation data
 */
public class VisualComponent extends ZBComponent {

    public Sprite sprite = new Sprite();
    public boolean flipX = false;
    /** Whether the sprite should be rendered relative to it's center.
     * If this is set, remember to change the sprite origin to it's center so it will rotate correctly
     */
    public boolean centerSprite = false;

    @Override
    public void reset() {

    }
}
