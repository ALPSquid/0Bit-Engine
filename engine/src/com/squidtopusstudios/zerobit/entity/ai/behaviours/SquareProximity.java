package com.squidtopusstudios.zerobit.entity.ai.behaviours;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;
import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;

/**
 * Checks for overlapping fixtures.
 * Based onhttps://github.com/libgdx/gdx-ai/blob/master/tests/src/com/badlogic/gdx/ai/tests/steer/box2d/Box2dSquareAABBProximity.java
 */
public class SquareProximity implements Proximity<Vector2>, QueryCallback {

    public class AABB {
        float lowerX;
        float lowerY;
        float upperX;
        float upperY;
    }

    protected SteeringEntity owner;
    protected World world;
    protected float detectionRadius;
    protected com.squidtopusstudios.zerobit.entity.ai.controllers.ProximityCallback behaviourCallback = null;

    private int neighbourCount = 0;
    private final AABB aabb = new AABB();


    public SquareProximity(SteeringEntity owner, World world, float detectionRadius) {
        this.owner = owner;
        this.world = world;
        this.detectionRadius = detectionRadius;
    }

    @Override
    public int findNeighbors(ProximityCallback<Vector2> behaviourCallback) {
        neighbourCount = 0;
        //this.behaviourCallback = behaviourCallback;
        prepareAABB(aabb);
        world.QueryAABB(this, aabb.lowerX, aabb.lowerY, aabb.upperX, aabb.upperY);
        this.behaviourCallback = null;
        return neighbourCount;
    }

    public int findNeighborBodies(com.squidtopusstudios.zerobit.entity.ai.controllers.ProximityCallback behaviourCallback) {
        neighbourCount = 0;
        this.behaviourCallback = behaviourCallback;
        prepareAABB(aabb);
        world.QueryAABB(this, aabb.lowerX, aabb.lowerY, aabb.upperX, aabb.upperY);
        this.behaviourCallback = null;
        return neighbourCount;
    }

    @Override
    public boolean reportFixture(Fixture fixture) {
        //SteeringEntity steerable = getSteerable(fixture);
        /*if (steerable != null && steerable != owner && accept(steerable)) {
            if (behaviourCallback.reportNeighbor(steerable)) {
                neighbourCount++;
            }
        }*/
        Body body = getBody(fixture);
        if (body != null && body != owner.getBody() && accept(body)) {
            if (behaviourCallback.reportNeighbourBody(body)) {
                neighbourCount++;
            }
        }
        return true;
    }

    protected boolean accept(Body body) {
        return true;
    }

    protected void prepareAABB(AABB aabb) {
        Vector2 position = owner.getPosition();
        aabb.lowerX = position.x - detectionRadius;
        aabb.lowerY = position.y - detectionRadius;
        aabb.upperX = position.x + detectionRadius;
        aabb.upperY = position.y + detectionRadius;
    }

    @SuppressWarnings("unchecked")
    protected SteeringEntity getSteerable(Fixture fixture) {
        if (fixture.getBody().getUserData() != null) {
            return ((Box2DUserData) fixture.getBody().getUserData()).steeringEntity;
        }
        return null;
    }

    protected Body getBody(Fixture fixture) {
        if (fixture.getBody().getUserData() != null) {
            return fixture.getBody();
        }
        return null;
    }

    @Override
    public Steerable<Vector2> getOwner() {
        return owner;
    }

    public AABB getAabb() {
        return aabb;
    }

    @Override
    public void setOwner(Steerable<Vector2> owner) {
        this.owner = (SteeringEntity)owner;
    }

    public float getDetectionRadius() {
        return detectionRadius;
    }

    public void setDetectionRadius(float detectionRadius) {
        this.detectionRadius = detectionRadius;
    }
}
