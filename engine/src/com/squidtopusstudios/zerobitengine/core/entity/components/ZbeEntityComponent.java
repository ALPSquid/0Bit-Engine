package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Single component to provide metadata and identify all engine created Ashley Entities
 */
public class ZbeEntityComponent extends Component {
    public String type = "null";
}
