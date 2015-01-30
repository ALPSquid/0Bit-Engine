package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.squidtopusstudios.zerobitengine.WorldB2D;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.utils.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Box2D version of {@link ZbeEntity}. Use instead of {@link ZbeEntity} if you want to use Box2D.
 * Note that when working with Box2D, all the following parameters and return values are in world units. You can convert between using the methods from world (getWorld())
 * Entity base methods, like sprite manipulation, are in pixels.
 */
public class ZbeEntityB2D extends ZbeEntityBase {

    public PolygonRegion polygonRegion; //TODO Rube: Test and remove


    public ZbeEntityB2D(String type) {
        super(type);
        add(new Box2DComponent());
        isBox2D = true;
    }

    /**
     * Set the Box2D type for the Body
     * @param bodyName name of the body
     * @param type BodyType to set
     * @return current instance of ZbeEntityB2D
     */
    public ZbeEntityB2D setBodyType(String bodyName, BodyDef.BodyType type) {
        getBody(bodyName).setType(type);
        return this;
    }
    /** see {@link #setBodyType(String, BodyDef.BodyType)} using first added Body **/
    public ZbeEntityB2D setBodyType(BodyDef.BodyType type) {
        getBody().setType(type);
        return this;
    }

    /**
     * Set the Vector2 position of the first added body
     * Also sets the entity's bounds which are used for sprite positioning in Box2D entities
     * @return current ZbeEntityB2D instance
     */
    @Override
    public ZbeEntityB2D setPosition(float x, float y) {
        //getBody().setTransform(x, y, 0);
        for (Body body : getBodies().values()) {
            body.setTransform(x, y, body.getAngle());
        }
        setBoundsPosition(getWorld().unitsToPixels(x), getWorld().unitsToPixels(y));
        return this;
    }
    /** see {@link #setPosition(float, float)}**/
    public ZbeEntityB2D setPosition(String bodyName, float x, float y) {
        getBody(bodyName).setTransform(x, y, 0);
        setBoundsPosition(getWorld().unitsToPixels(x), getWorld().unitsToPixels(y));
        return this;
    }

    /** @return position in units of the first added body **/
    @Override
    public Vector2 getPosition() {
        return getBody().getPosition();
    }
    /** see {@link #getPosition()}**/
    public Vector2 getPosition(String bodyName) {
        return getBody(bodyName).getPosition();
    }

    /**
     * Set the bounds dimensions in units. This should encapsulate your fixtures and is used for sprite positioning
     * @param resizeSprite whether to resize the sprite to match the new bounds dimensions
     **/
    @Override
    public ZbeEntityB2D setBounds(float width, float height, boolean resizeSprite) {
        getBounds().setSize(getWorld().unitsToPixels(width), getWorld().unitsToPixels(height));
        if (resizeSprite) {
            getSprite().setSize(getWorld().unitsToPixels(width), getWorld().unitsToPixels(height));
        }
        return this;
    }
    /** Set the bounds dimensions in units. This should encapsulate your fixtures and is used for sprite positioning
     * The Sprite WILL automatically be resized to match */
    @Override
    public ZbeEntityB2D setBounds(float width, float height) {
        setBounds(width, height, true);
        return this;
    }

    /**
     * Sets the origin for bounds positioning.
     * Use :
     *  // TODO: centering for polygons that have coordinates below 0
     *  BoundsOrigin.BOTTOM_LEFT for standard polygons (all vert coordinates >= 0)
     *  BoundsOrigin.CENTER for standard shapes (boxes, circles etc.)
     * Note: the green circle when using the debug renderer is the origin of the respective body
     * @return current ZbeEntityB2D instance for chaining
     */
    public ZbeEntityB2D setBoundsOrigin(ZeroBit.BoundsOrigin origin) {
        getComponent(BoundsComponent.class).boundsOrigin = origin;
        return this;
    }

    /** @return the origin around which the bounds are positioned **/
    public ZeroBit.BoundsOrigin getBoundsOrigin() {
        return getComponent(BoundsComponent.class).boundsOrigin;
    }


    /**
     * Sets the body to center the bounding box on
     * @param bodyName the body to center the bounding box on
     * @return current ZbeEntityB2D instance for chaining
     */
    public ZbeEntityB2D setBoundsAnchor(String bodyName) {
        getComponent(BoundsComponent.class).boundsAnchor = bodyName;
        return this;
    }

    /** @return the body the bounding box is centered on. Default is the parent body **/
    public Body getBoundsAnchor() {
        if (getComponent(BoundsComponent.class).boundsAnchor == null) {
            return getBody();
        }
        return getBody(getComponent(BoundsComponent.class).boundsAnchor);
    }

    /** The idea is to automatically size and position the bounding box but to do so requires a lot of calculations
     * on each fixture shape not to mention the fact that you can't actually get the vertices of a PolygonShape.
     * Will just have to be manual for now
     */
    private ZbeEntityB2D adjustBounds() {
        for (Fixture fixture : getFixtures().values()) {
            switch (fixture.getType()) {
                case Polygon:
                    break;
                case Circle:
                    break;
                case Edge:
                    break;
                case Chain:
                    break;
            }
        }
        return this;
    }

    /**
     * Set the position (in units) of the bounds used for sprite positioning
     * @return current instance of ZbeEntityB2D
     */
    public ZbeEntityB2D setBoundsPosition(float x, float y) {
        getBounds().setPosition(getWorld().unitsToPixels(x), getWorld().unitsToPixels(y));
        return this;
    }

    /** Set the bounds offset to allow for easier positioning around all fixtures.
     *  Warning: If an offset is set when rotation is enabled, the bounds will not stay centered while rotating! **/
    public ZbeEntityB2D setBoundsOffset(float x, float y) {
        getComponent(Box2DComponent.class).offset = new Vector2(x, y);
        return this;
    }

    public Vector2 getBoundsOffset() {
        return getComponent(Box2DComponent.class).offset;
    }

    /** If false, collisions won't be detected. */
    @Override
    public ZbeEntityB2D enableCollisions(boolean collidable) {
        getComponent(Box2DComponent.class).collidable = collidable;
        for (Body body : getBodies().values()) {
            for (Fixture fixture : body.getFixtureList()) {
                fixture.setSensor(!collidable);
            }
        }
        return this;
    }

    @Override
    public boolean collisionsEnabled() {
        return getComponent(PhysicsComponent.class).collidable;
    }

    public Vector2 getVelocity(String bodyName) {
        return getBody(bodyName).getLinearVelocity();
    }
    /** get the velocity of the first body added **/
    public Vector2 getVelocity() {
        return getBody().getLinearVelocity();
    }

    public void setVelocity(float x, float y) {
        // TODO
    }

    public void increaseVelocity(float xIncrease, float yIncrease) {
        // TODO
    }

    public void setMaxVelocity(float x, float y) {
        // TODO
    }

    // ---- Box2D predefined Bodies
    /**
     * Creates this entity as a Box2D rectangle with body "base_box" fixture "base_box"
     * @param width full width in units
     * @param height full height in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asBox(float width, float height) {
        BodyDef bodyDef = new BodyDef();
        createBody("base_box", bodyDef);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.density = getDefaultDensity();
        fixtureDef.friction = getDefaultFriction();
        createFixture("base_box", "base_box", fixtureDef);
        poly.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            setBounds(width, height);
        }
        return this;
    }

    /**
     * Creates this entity as a Box2D circle with body "base_circle" and fixture "base_circle"
     * @param radius radius in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asCircle(float radius) {
        BodyDef bodyDef = new BodyDef();
        createBody("base_circle", bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = getDefaultDensity();
        fixtureDef.friction = getDefaultFriction();
        createFixture("base_circle", "base_circle", fixtureDef);
        circle.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            setBounds(radius * 2, radius * 2);
        }
        return this;
    }

    /**
     * Creates this entity as a Box2D polygon with body "base_poly" and fixture "base_poly"
     * @param vertices the vertices to construct a polygon from
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asPolygon(float... vertices) {
        BodyDef bodyDef = new BodyDef();
        createBody("base_poly", bodyDef);

        PolygonShape poly = new PolygonShape();
        poly.set(vertices); //Utils.normalize(vertices)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.density = getDefaultDensity();
        fixtureDef.friction = getDefaultFriction();
        createFixture("base_poly", "base_poly", fixtureDef);
        poly.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            // Calculate the bounds by finding the difference between the left most vertex and the right most vertex
            // and the difference between the upper most vertex and the lower most vertex
            float[][] coords = Utils.splitXY(vertices);
            float[] minMaxX = Utils.minMax(coords[0]);
            float[] minMaxY = Utils.minMax(coords[1]);
            setBounds(minMaxX[1] - minMaxX[0], minMaxY[1] - minMaxY[0]);
            setBoundsOrigin(ZeroBit.BoundsOrigin.BOTTOM_LEFT);
        }
        return this;
    }

    /**
     * For platformers:
     * Creates this entity with the following:
     *      - A box body with name 'base_box'
     *          - With a box fixture 'base_box'
     *      - A circle body with name 'base_motor'
     *          - With a circle fixture 'base_motor'
     *      - A revolute joint with name 'base_motor' connecting the two bodies together
     * @param width full width in units
     * @param height full height in units
     */
    public ZbeEntityB2D asSimpleActor(float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        createBody("base_box", bodyDef);

        float boxHeight =  height - width/2;
        float boxOffset = (width/2)/2;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, boxHeight / 2, new Vector2(0, boxOffset), 0);
        createFixture("base_box", "base_box", poly, getDefaultDensity());
        poly.dispose();

        /*CircleShape circle = new CircleShape();
        circle.setRadius(width/2);
        circle.setPosition(new Vector2(0, -boxHeight/2 + boxOffset));
        createFixture("base_sensor", circle, 0);
        circle.dispose();*/

        bodyDef.position.set(getBody("base_box").getPosition());
        createBody("base_motor", bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(width/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = getDefaultDensity();
        fixtureDef.friction = getDefaultFriction();
        fixtureDef.restitution = 0;
        createFixture("base_motor", "base_motor", fixtureDef);
        circle.dispose();

        RevoluteJointDef motor = new RevoluteJointDef();
        motor.enableMotor = false;
        //motor.maxMotorTorque = 50;
        motor.bodyA = getBody("base_box");
        motor.bodyB = getBody("base_motor");
        motor.collideConnected = false;
        motor.localAnchorA.set(0, -boxHeight/2 + boxOffset);
        motor.localAnchorB.set(0, 0);

        createJoint("base_motor", motor);

        getBody("base_box").setBullet(true);
        getBody("base_motor").setBullet(true);
        getBody("base_box").setSleepingAllowed(false);
        getBody("base_motor").setSleepingAllowed(false);
        getBody("base_box").setFixedRotation(true);

        setParentBody("base_motor");
        setBoundsAnchor("base_box");


        if (getWidth() <= 0 && getHeight() <= 0) {
            setBounds(width, height);
        }

        return this;
    }

    /**
     * Creates this entity as a Box2D edge with body "base" and fixture "base"
     * @param x1 start x coordinate in units
     * @param y1 start y coordinate in units
     * @param x2 end x coordinate in units
     * @param y2 end y coordinate in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asEdge(float x1, float y1, float x2, float y2) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = getDefaultBodyType();
        createBody("base", bodyDef);

        EdgeShape edge = new EdgeShape();
        edge.set(x1, y1, x2, y2);
        createFixture("base", "base", edge, getDefaultDensity());
        edge.dispose();
        return this;
    }

    // ---- Box2D specific Getters and Setters ----
    /** @return the parent body for this entity, if no parent is manually set, the first added body is used **/
    public Body getBody() {
        return getComponent(Box2DComponent.class).bodies.values().iterator().next();
    }

    /** @return the Body by name belonging to this entity**/
    public Body getBody(String bodyName) {
        return getBodies().get(bodyName);
    }
    /** @return Map of Bodies belonging to this entity **/
    public Map<String, Body> getBodies() {
        return getComponent(Box2DComponent.class).bodies;
    }

    /** Sets the parent body for this entity.
     * This is used when calling a method that references a body without supplying a body name
     *      e.g. setPosition(x, y) over setPosition(bodyName, x, y) **/
    public ZbeEntityB2D setParentBody(String bodyName) {
        Map<String, Body> bodies = new LinkedHashMap<String, Body>(getBodies());
        Body parentBody = bodies.get(bodyName);
        bodies.remove(bodyName);

        getBodies().clear();
        getBodies().put(bodyName, parentBody);
        getBodies().putAll(bodies);
        return this;
    }

    /** Gets the name of a registered Body. Make sure the Body your requesting the name of has been registered with the entity first!
     * @return the registered name of the body
     */
    public String getBodyName(Body body) {
        for (Map.Entry<String, Body> entry : getBodies().entrySet()) {
            if (entry.getValue().equals(body)) {
                return entry.getKey();
            }
        }
        ZeroBit.logger.logError("Provided Body could not be found, are you sure you registered it with addBody()/createBody()?");
        return "";
    }

    /** Adds the Body to the Map of Bodies belonging to this entity
     * @param name unique name for the body
     * @return current ZbeEntityB2D instance **/
    public ZbeEntityB2D addBody(String name, Body body) {
        getBodies().put(name, body);
        return this;
    }
    /** Creates and adds a Body to the Map of Bodies belonging to this entity **/
    public Body createBody(String name, BodyDef bodyDef) {
        Body b = getWorld().getB2DWorld().createBody(bodyDef);
        getBodies().put(name, b);
        return b;
    }

    /** @return Map of all fixtures belonging to this entity **/
    public Map<String, Fixture> getFixtures() {
        return getComponent(Box2DComponent.class).fixtures;
    }
    /** @return Fixture belonging to this entity **/
    public Fixture getFixture(String fixtureName) {
        return getFixtures().get(fixtureName);
    }

    /**
     * Creates and adds a fixture to the body and adds the fixture
     * to a map of named fixtures for this entity for easy access
     * @param bodyName name of the body to add the fixture to
     * @param fixtureName unique name for the fixture
     * @return the created fixture
     */
    public Fixture createFixture(String bodyName, String fixtureName, FixtureDef fixtureDef) {
        Fixture f = getBody(bodyName).createFixture(fixtureDef);
        getComponent(Box2DComponent.class).fixtures.put(fixtureName, f);
        return f;
    }
    /**
     * See {@link #createFixture(String bodyName, String fixtureName, FixtureDef fixtureDef)}
     * @param shape Shape to create a fixture from
     * @return the created fixture
     */
    public Fixture createFixture(String bodyName, String fixtureName, Shape shape, float density) {
        Fixture f = getBody(bodyName).createFixture(shape, density);
        getFixtures().put(fixtureName, f);
        return f;
    }

    /** @return map of joints belonging to this entity **/
    public Map<String, Joint> getJoints() {
        return getComponent(Box2DComponent.class).joints;
    }

    /** @return the Joint by name belonging to this entity **/
    public Joint getJoint(String jointName) {
        return getJoints().get(jointName);
    }

    /**
     * Creates a Joint from a JointDef and adds it to the map of joints belonging to this entity
     * @param jointName unique name for the joint
     * @return the created Joint
     */
    public Joint createJoint(String jointName, JointDef jointDef) {
        Joint j = getWorld().getB2DWorld().createJoint(jointDef);
        getJoints().put(jointName, j);
        return j;
    }

    /** @return default density, used with as<Shape>() **/
    public float getDefaultDensity() {
        return getComponent(Box2DComponent.class).defaultDensity;
    }

    /** @return default friction, used with as<Shape>() **/
    public float getDefaultFriction() {
        return getComponent(Box2DComponent.class).defaultFriction;
    }

    /** @return default body type, used with as<Shape>() **/
    public BodyDef.BodyType getDefaultBodyType() {
        return getComponent(Box2DComponent.class).defaultType;
    }

    /** Set the friction for the given fixture **/
    public ZbeEntityB2D setFriction(String fixture, float friction) {
        getFixture(fixture).setFriction(friction);
        return this;
    }

    /** Set the friction for all of this entity's fixtures **/
    public ZbeEntityB2D setFriction(float friction) {
        for (Fixture fixture : getFixtures().values()) {
            fixture.setFriction(friction);
        }
        return this;
    }

    @Override
    protected WorldB2D getWorld() {
        return (WorldB2D)super.getWorld();
    }

}
