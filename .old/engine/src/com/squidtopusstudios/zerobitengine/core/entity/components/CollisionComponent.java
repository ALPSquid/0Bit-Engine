package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;

import java.util.*;

/**
 * Collision data for Ashley Entities
 */
public class CollisionComponent extends Component {
    public List<String> solidTypes = new ArrayList<String>();
    public boolean collidedLeft = false;
    public boolean collidedRight = false;
    public boolean collidedTop = false;
    public boolean collidedBottom = false;
}
