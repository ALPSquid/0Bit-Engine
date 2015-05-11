package com.squidtopusstudios.zerobit.entity.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.squidtopusstudios.zerobit.entity.components.Box2DComponent;
import com.squidtopusstudios.zerobit.entity.components.MovementComponent;

/**
 * Steerable Entity
 */
public class SteeringEntity implements Steerable<Vector2> {

    private Body body;
    private MovementComponent mvc = null;
    private Float minX = null;
    private Float maxX = null;
    private float boundingRadius;
    private boolean tagged = false;
    private Body targetEntity = null;

    protected SteeringBehavior<Vector2> steeringBehavior;
    private final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());


    public SteeringEntity(Entity entity, float boundingRadius) {
        this.boundingRadius = boundingRadius;
        body = ComponentMapper.getFor(Box2DComponent.class).get(entity).body;
        mvc = ComponentMapper.getFor(MovementComponent.class).get(entity);
    }
    public SteeringEntity(Entity entity) {
        this(entity, 1f);
    }
    public SteeringEntity(Body body, float boundingRadius) {
        this.body = body;
        this.boundingRadius = boundingRadius;
    }

    public void update(float deltaTime) {
        if (steeringBehavior != null && mvc != null) {
            boolean applySteering = true;
            steeringBehavior.calculateSteering(steeringOutput);

            mvc.right = false;
            mvc.left = false;
            if (!(getSteeringBehavior() instanceof Seek)) {
                // TODO: Move to behaviour and track back to patrol area
                if (minX != null && steeringOutput.linear.x < 0 && getPosition().x - 0.1 < minX) applySteering = false;
                else if (maxX != null && steeringOutput.linear.x > 0 && getPosition().x + 0.1 > maxX)
                    applySteering = false;
            }
            if (applySteering) {
                applySteering(steeringOutput, deltaTime);
            } else {
                stop();
            }
        }
    }

    protected void applySteering(SteeringAcceleration<Vector2> steering, float deltaTime) {
        // Update position and velocity
        if(!steeringOutput.linear.isZero()) {
            Vector2 force = steeringOutput.linear;
            if (force.x > 0) mvc.right = true;
            else if (force.x < 0) mvc.left = true;
        }
    }

    public void stop() {
        mvc.right = false;
        mvc.left = false;
        mvc.stop = true;
        steeringOutput.setZero();
    }

    public SteeringBehavior<Vector2> getSteeringBehavior() {
        return steeringBehavior;
    }

    public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }

    public SteeringAcceleration<Vector2> getSteeringOutput() {
        return steeringOutput;
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    @Override
    public float getOrientation() {
        return body.getAngle();
    }

    @Override
    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    @Override
    public Vector2 newVector() {
        return new Vector2();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float)Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = (float)Math.cos(angle);
        return outVector;
    }

    /**
     * Set entity to chase
     * @param target Steerable<Vector2> to chase
     */
    public void setTargetEntity(Body target) {
        targetEntity = target;
    }
    public Body getTargetEntity() {
        return targetEntity;
    }

    // --- Limiters
    /**
     * Set the maximum boundary for this NPC
     * @param maxX maximum X position of this NPC
     */
    public void setMaxX(Float maxX) {
        this.maxX = maxX;
    }

    /**
     * Set the minimum boundary for this NPC
     * @param minX minimum X position of this NPC
     */
    public void setMinX(Float minX) {
        this.minX = minX;
    }

    /**
     * Set the boundaries for this NPC
     * @param minX minimum X position of this NPC
     * @param maxX maximum X position of this NPC
     */
    public void setBoundaries(Float minX, Float maxX) {
        setMinX(minX);
        setMaxX(maxX);
    }

    public float getMinX() {
        return minX;
    }
    public float getMaxX() {
        return maxX;
    }

    @Override
    public float getMaxLinearSpeed() {
        return mvc.speed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        mvc.speed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return mvc.speed;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {

    }

    @Override
    public float getMaxAngularSpeed() {
        return mvc.speed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {

    }

    @Override
    public float getMaxAngularAcceleration() {
        return mvc.speed;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {

    }
}
