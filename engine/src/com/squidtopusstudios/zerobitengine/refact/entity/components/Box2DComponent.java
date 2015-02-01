package com.squidtopusstudios.zerobitengine.refact.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Holds a Box2D body. Add to your World's engine if you want to use Box2D
 */
public class Box2DComponent extends Component {

    public Body body;

}
