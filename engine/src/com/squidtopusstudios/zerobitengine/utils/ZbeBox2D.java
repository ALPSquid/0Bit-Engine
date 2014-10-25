package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Box2D utils for quick fixture creation. Turns out it doesn't actually work:
 * The methods can't be used as Box2D doesn't take a copy of the shape until Body.createFixture() is called
 * so using this causes, quite rightly, a non existent memory address error
 */
public class ZbeBox2D {

    /**
     * Creates a box FixtureDef for adding to a Body
     * @param width the full width of the box
     * @param height the full height of the box
     * @param density the density of the box
     * @return box FixtureDef
     */
    private static FixtureDef createBox(float width, float height, float density) {
        // These can't be used as Box2D doesn't take a copy of the shape until Body.createFixture() is called
        // so using this causes, quite rightly, a non existent memory address error
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, height/2);

        FixtureDef fd = new FixtureDef();
        fd.density = density;
        fd.shape = poly;
        poly.dispose();
        return fd;
    }

    /**
     * Creates a circle FixtureDef for adding to a Body
     * @param density the density of the circle
     * @return circle FixtureDef
     */
    private static FixtureDef createCircle(float radius, float density) {
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fd = new FixtureDef();
        fd.density = density;
        fd.shape = circle;
        circle.dispose();
        return fd;
    }

    /**
     * Creates an edge FixtureDef for adding to a Body
     * @param x1 start x coordinate
     * @param y1 start y coordinate
     * @param x2 end x coordinate
     * @param y2 end y coordinate
     * @param density the density of the edge
     * @return edge FixtureDef
     */
    private static FixtureDef createEdge(float x1, float y1, float x2, float y2, float density) {
        EdgeShape edge = new EdgeShape();
        edge.set(x1, y1, x2, y2);

        FixtureDef fd = new FixtureDef();
        fd.density = density;
        fd.shape = edge;
        edge.dispose();
        return fd;
    }

    /**
     * Creates a FixtureDef based on a shape and density. You still need to dispose the shape!
     * @return the created FixtureDef
     */
    private static FixtureDef createFixtureDef(Shape shape, float density) {
        FixtureDef fixture = new FixtureDef();
        fixture.density = density;
        fixture.shape = shape;
        return fixture;
    }
}
