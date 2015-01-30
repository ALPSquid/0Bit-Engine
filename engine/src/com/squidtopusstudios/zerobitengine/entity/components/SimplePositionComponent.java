package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Simple positions component for Ashley Entities
 */
public class SimplePositionComponent extends Component {
    public Vector2 position = new Vector2(0, 0);
    public Vector2 prevPosition = position;
}
