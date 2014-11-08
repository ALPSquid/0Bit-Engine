package com.squidtopusstudios.zerobitengine.core.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.squidtopusstudios.zerobitengine.WorldB2D;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Box2D Component for {@link com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityB2D}
 */
public class Box2DComponent extends Component {

    public boolean collidable = true;
    public Vector2 offset = new Vector2(0, 0);

    //public Body body = ((WorldB2D)ZeroBit.getWorld()).getB2DWorld().createBody(new BodyDef());
    public Map<String, Body> bodies = new LinkedHashMap<String, Body>();
    public Map<String, Fixture> fixtures = new LinkedHashMap<String, Fixture>();
    public Map<String, Joint> joints = new LinkedHashMap<String, Joint>();
    // TODO support for an image per body

    public float defaultFriction = 0.5f;
    public float defaultRestitution = 0.3f;
    public float defaultDensity = 0.4f;
    public BodyDef.BodyType defaultType = BodyDef.BodyType.StaticBody;
}
