package com.squidtopusstudios.zerobitengine.refact.entity.components;

import com.badlogic.ashley.core.Component;

/**
 * Simple Position Component for Entities<br/>
 */
public class PositionComponent extends Component {

    public float x;
    public float y;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
