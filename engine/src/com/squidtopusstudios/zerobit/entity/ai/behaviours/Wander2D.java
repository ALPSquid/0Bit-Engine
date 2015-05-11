package com.squidtopusstudios.zerobit.entity.ai.behaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobit.util.Utils;

/**
 * Sidescroller version of Wander with pausing
 */
public class Wander2D extends SteeringBehavior<Vector2> {

    private int wanderOrientation;
    private float targetPosition;
    private boolean stopped = false;
    private float minStopTime = 1f;
    private float maxStopTime = 4;
    private float stopTime = 0;
    private float currStopTime = 0;


    public Wander2D(Steerable<Vector2> owner) {
        super(owner);

        targetPosition = owner.getPosition().x;
    }

    @Override
    protected SteeringAcceleration<Vector2> calculateRealSteering(SteeringAcceleration<Vector2> steering) {
        targetPosition = owner.getPosition().x + ((wanderOrientation == 1)? 0.5f : -0.5f);
        if (!stopped) {
            if (Utils.randRange(0f, 1f) < 0.01f) {
                stopTime = Utils.randRange(minStopTime, maxStopTime);
                stopped = true;
            }
            return doSteering(steering);
        }
        else {
            currStopTime += Gdx.graphics.getDeltaTime();
            if (currStopTime >= stopTime) {
                stopped = false;
                currStopTime = 0;
                wanderOrientation = Utils.randRange(0, 1);
            }
            steering.setZero();
        }

        return steering;
    }

    protected SteeringAcceleration<Vector2> doSteering(SteeringAcceleration<Vector2> steering) {
        float maxLinearAcceleration = getActualLimiter().getMaxLinearAcceleration();
        steering.linear.set(targetPosition, 0).sub(owner.getPosition()).nor().scl(maxLinearAcceleration);
        steering.angular = 0;

        return steering;
    }

    /**
     * Min time the steerable should pause for in seconds
     */
    public void setMinStopTime(float seconds) {
        minStopTime = seconds;
    }

    /**
     * Max time the steerable should pause for in seconds
     */
    public void setMaxStopTime(float seconds) {
        maxStopTime = seconds;
    }

    /** Returns the current orientation of the wander target. */
    public float getWanderOrientation () {
        return wanderOrientation;
    }

    /** Returns the current x position of the wander target. This method is useful for debug purpose. */
    public float getInternalTargetPosition () {
        return targetPosition;
    }
}
