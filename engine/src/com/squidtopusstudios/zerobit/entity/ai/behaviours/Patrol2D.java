package com.squidtopusstudios.zerobit.entity.ai.behaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;
import com.squidtopusstudios.zerobit.util.Utils;

/**
 * Sidescroller version of Wander with pausing
 */
public class Patrol2D extends SteeringBehavior<Vector2> {

    private float targetPosition;
    private boolean stopped = false;
    private float minStopTime = 3f;
    private float maxStopTime = 8f;
    private float stopTime = 0;
    private float currStopTime = 0;
    private SteeringEntity entity;


    public Patrol2D(Steerable<Vector2> owner) {
        super(owner);
        this.entity = (SteeringEntity)owner;
        targetPosition = (Utils.randRange(0, 2) == 0)? entity.getMinX() : entity.getMaxX();
    }

    @Override
    protected SteeringAcceleration<Vector2> calculateRealSteering(SteeringAcceleration<Vector2> steering) {
        if (!stopped) {
            if ((targetPosition == entity.getMinX() && owner.getPosition().x <= targetPosition + 0.1) ||
                    (targetPosition == entity.getMaxX() && owner.getPosition().x >= targetPosition - 0.1)) {
                stopTime = Utils.randRange(minStopTime, maxStopTime);
                stopped = true;
            }
            return doSteering(steering);
        } else {
            currStopTime += Gdx.graphics.getDeltaTime();
            if (currStopTime >= stopTime) {
                stopped = false;
                currStopTime = 0;
                targetPosition = (targetPosition == entity.getMaxX()) ? entity.getMinX() : entity.getMaxX();
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

    /** Returns the current x position of the wander target. This method is useful for debug purpose. */
    public float getInternalTargetPosition () {
        return targetPosition;
    }
}
