package com.squidtopusstudios.zerobit.entity.ai.behaviours;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;

/**
 * https://github.com/libgdx/gdx-ai/blob/master/tests/src/com/badlogic/gdx/ai/tests/steer/box2d/Box2dRadiusProximity.java
 */
public class RadiusProximity extends SquareProximity {


    public RadiusProximity(SteeringEntity owner, World world, float detectionRadius) {
        super(owner, world, detectionRadius);
    }

    @Override
    public boolean accept(Body body) {
        // The bounding radius of the current body is taken into account
        // by adding it to the radius proximity
        float range = detectionRadius + body.getFixtureList().get(0).getShape().getRadius();

        // Make sure the current body is within the range.
        // Notice we're working in distance-squared space to avoid square root.
        float distanceSquare = body.getPosition().dst2(owner.getPosition());

        return distanceSquare <= range * range;
    }
}
