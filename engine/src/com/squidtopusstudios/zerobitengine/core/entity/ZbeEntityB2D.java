package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.squidtopusstudios.zerobitengine.WorldB2D;
import com.squidtopusstudios.zerobitengine.core.entity.components.*;
import com.squidtopusstudios.zerobitengine.utils.Utils;

import java.util.Map;

/**
 * Box2D version of {@link ZbeEntity}. Use instead of {@link ZbeEntity} if you want to use Box2D.
 * Note that when working with Box2D, all the following parameters and return values are in world units. You can convert between using the methods from world (getWorld())
 * Entity base methods, like sprite manipulation, are in pixels.
 */
public class ZbeEntityB2D extends ZbeEntityBase {

    public ZbeEntityB2D(String type) {
        super(type);
        add(new Box2DComponent());
        isBox2D = true;
    }

    /**
     * Set the Box2D type
     * @param type BodyType to set
     * @return current instance of ZbeEntityB2D
     */
    public ZbeEntityB2D setBodyType(BodyDef.BodyType type) {
        getComponent(Box2DComponent.class).body.setType(type);
        return this;
    }

    /** @return the entity's BodyType **/
    public BodyDef.BodyType getBodyType() {
        return getComponent(Box2DComponent.class).body.getType();
    }

    /**
     * Set the Vector2 position of the entity's body in units
     * Also sets the entity's bounds which are used for sprite positioning in Box2D entities
     * @return current ZbeEntityB2D instance
     */
    @Override
    public ZbeEntityB2D setPosition(float x, float y) {
        getComponent(Box2DComponent.class).body.setTransform(x, y, 0);
        setBoundsPosition(getWorld().unitsToPixels(x), getWorld().unitsToPixels(y));
        return this;
    }

    /** Set the bounds dimensions in units. This should encapsulate your fixtures and is used for sprite positioning */
    @Override
    public ZbeEntityB2D setBounds(float width, float height) {
        getBounds().setSize(getWorld().unitsToPixels(width), getWorld().unitsToPixels(height));
        if (getSpriteDimensions().width == -1) {
            setSpriteDimensions((int)width, (int)height);
        }
        return this;
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

    /** @return entity's body position in units **/
    @Override
    public Vector2 getPosition() {
        return getComponent(Box2DComponent.class).body.getPosition();
    }

    /**
     * Set the position (in units) of the bounds used for sprite positioning
     * @return current instance of ZbeEntityB2D
     */
    public ZbeEntityB2D setBoundsPosition(float x, float y) {
        getBounds().setPosition(getWorld().unitsToPixels(x), getWorld().unitsToPixels(y));
        return this;
    }

    /** Set the bounds offset to allow for easier positioning around all fixtures **/
    public ZbeEntityB2D setBoundsOffset(float x, float y) {
        getComponent(Box2DComponent.class).offset = new Vector2(x, y);
        return this;
    }

    public Vector2 getBoundsOffset() {
        return getComponent(Box2DComponent.class).offset;
    }

    /** see {@link ZbeEntityBase#setSpriteDimensions(int, int)} (in pixels) **/
    public ZbeEntityBase setSpriteDimensions(int width, int height) {
        super.setSpriteDimensions(width, height);
        //setBounds(width, height);
        return this;
    }

    /** If false, collisions won't be detected. */
    @Override
    public ZbeEntityB2D enableCollisions(boolean collidable) {
        getComponent(Box2DComponent.class).collidable = collidable;
        for (Fixture fixture : getComponent(Box2DComponent.class).body.getFixtureList()) {
            fixture.setSensor(!collidable);
        }
        return this;
    }

    @Override
    public boolean collisionsEnabled() {
        return getComponent(PhysicsComponent.class).collidable;
    }

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
     * Creates this entity as a Box2D rectangle with fixture "base"
     * @param width full width in units
     * @param height full height in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asBox(float width, float height) {
        setBody(getWorld().getB2DWorld().createBody(new BodyDef()));
        setBodyType(getBodyType());

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width / 2, height / 2);
        createFixture("base", poly, getDefaultDensity());
        poly.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            setBounds(width, height);
        }
        return this;
    }

    /**
     * Creates this entity as a Box2D circle with fixture "base"
     * @param radius radius in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asCircle(float radius) {
        setBody(getWorld().getB2DWorld().createBody(new BodyDef()));
        setBodyType(getBodyType());

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        createFixture("base", circle, getDefaultDensity());
        circle.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            setBounds(radius * 2, radius * 2);
        }
        return this;
    }

    public ZbeEntityB2D asPolygon(float... vertices) {
        setBody(getWorld().getB2DWorld().createBody(new BodyDef()));
        setBodyType(getBodyType());

        PolygonShape poly = new PolygonShape();
        poly.set(Utils.normalize(vertices));
        createFixture("base", poly, getDefaultDensity());
        poly.dispose();

        if (getWidth() <= 0 && getHeight() <= 0) {
            // Calculate the bounds by finding the difference between the left most vertex and the right most vertex
            // and the difference between the upper most vertex and the lower most vertex
            float[][] coords = Utils.splitXY(vertices);
            float[] minMaxX = Utils.minMax(coords[0]);
            float[] minMaxY = Utils.minMax(coords[1]);
            setBounds(minMaxX[1] - minMaxX[0], minMaxY[1] - minMaxY[0]);
            //setBoundsOffset(getWorld().pixelsToUnits(getWidth()/2) + offsetX,
            //        getWorld().pixelsToUnits(getHeight()/2) + offsetY);
        }
        return this;
    }

    /**
     * For platformers:
     * Creates this entity with a base fixture set of a box and circle foot sensor
     * Box is added to the fixture list with name 'base_box'
     * foot sensor is added to the fixture list with name 'base_sensor'
     * @param width full width in units
     * @param height full height in units
     */
    public ZbeEntityB2D asSimpleActor(float width, float height) {
        setBody(getWorld().getB2DWorld().createBody(new BodyDef()));
        setBodyType(BodyDef.BodyType.DynamicBody);

        float boxHeight =  height - width/2;
        float boxOffset = (width/2)/2;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, boxHeight / 2, new Vector2(0, boxOffset), 0);
        createFixture("base_box", poly, getDefaultDensity());
        poly.dispose();

        CircleShape circle = new CircleShape();
        circle.setRadius(width/2);
        circle.setPosition(new Vector2(0, -boxHeight/2 + boxOffset));
        createFixture("base_sensor", circle, 0);
        circle.dispose();

        setBounds(width, height);

        return this;
    }

    /**
     * Creates this entity as a Box2D edge with fixture "base"
     * @param x1 start x coordinate in units
     * @param y1 start y coordinate in units
     * @param x2 end x coordinate in units
     * @param y2 end y coordinate in units
     * @return current ZbeEntityB2D instance
     */
    public ZbeEntityB2D asEdge(float x1, float y1, float x2, float y2) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = getBodyType();
        setBody(getWorld().getB2DWorld().createBody(bodyDef));

        EdgeShape edge = new EdgeShape();
        edge.set(x1, y1, x2, y2);
        createFixture("base", edge, getDefaultDensity());
        edge.dispose();
        return this;
    }

    // ---- Box2D specific Getters and Setters ----
    /** @return Body for this entity **/
    public Body getBody() {
        return getComponent(Box2DComponent.class).body;
    }
    /** @return current ZbeEntityB2D instance **/
    public ZbeEntityB2D setBody(Body body) {
        getComponent(Box2DComponent.class).body = body;
        return this;
    }

    /**
     * Creates and adds a fixture to this entity's body and adds the fixture
     * to a map of named fixtures for this entity for easy access
     * @param name unique name for the fixture
     * @return the current ZbeEntityB2D instance
     */
    public Fixture createFixture(String name, FixtureDef fixtureDef) {
        Fixture f = getBody().createFixture(fixtureDef);
        getComponent(Box2DComponent.class).fixtures.put(name, f);
        return f;
    }
    /**
     * See {@link #createFixture(String, FixtureDef)}
     * @param shape Shape to create a fixture from
     * @return the current ZbeEntityB2D instance
     */
    public Fixture createFixture(String name, Shape shape, float density) {
        Fixture f = getBody().createFixture(shape, density);
        getComponent(Box2DComponent.class).fixtures.put(name, f);
        return f;
    }

    /** @return default density, used with as<Shape>() **/
    public float getDefaultDensity() {
        return getComponent(Box2DComponent.class).density;
    }

    /** Set the friction for the given fixture **/
    public void setFriction(String fixture, float friction) {
        getFixture(fixture).setFriction(friction);
    }

    /** Set the friction for all of this entity's fixtures **/
    public void setFriction(float friction) {
        for (Fixture fixture : getFixtures().values()) {
            fixture.setFriction(friction);
        }
    }

    /** @return Map of fixtures for this entity's body **/
    public Map<String, Fixture> getFixtures() {
        return getComponent(Box2DComponent.class).fixtures;
    }
    /** @return Fixture from this entity's body **/
    public Fixture getFixture(String fixtureName) {
        return getFixtures().get(fixtureName);
    }

    @Override
    protected WorldB2D getWorld() {
        return (WorldB2D)super.getWorld();
    }
}
