package com.squidtopusstudios.zerobitengine.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.squidtopusstudios.zerobitengine.World;

/**
 * Single component to provide metadata and identify all engine created Ashley Entities
 */
public class ZbeEntityComponent extends Component {
    public String type = "none";
    public Color debugColour = Color.RED;
    public boolean isBox2D;
}
