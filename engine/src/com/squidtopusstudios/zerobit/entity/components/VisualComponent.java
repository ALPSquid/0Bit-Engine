package com.squidtopusstudios.zerobit.entity.components;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * For holding texture and animation data
 */
public class VisualComponent extends ZBComponent {

    public Sprite sprite = new Sprite();
    public boolean flipX = false;

    @Override
    public void reset() {

    }
}
