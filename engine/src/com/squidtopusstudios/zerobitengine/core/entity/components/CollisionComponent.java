package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Collision data for Ashley Entities
 */
public class CollisionComponent extends Component {
    public List<String> collidedEntities = new ArrayList<String>();
}
