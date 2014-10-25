package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.*;
import com.squidtopusstudios.zerobitengine.WorldB2D;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

import java.util.HashMap;
import java.util.Map;

/**
 * Box2D Component for {@link com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityB2D}
 */
public class Box2DComponent extends Component {

    public boolean collidable = true;

    public Body body = ((WorldB2D)ZeroBit.getWorld()).getB2DWorld().createBody(new BodyDef());
    public Map<String, Fixture> fixtures = new HashMap<String, Fixture>();

    public float friction = 0.8f;
    public float restitution = 0.3f;
    public float density = 0.7f;
    public float angle = 0;
    public BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
}
