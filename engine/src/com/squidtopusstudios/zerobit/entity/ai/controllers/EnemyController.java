package com.squidtopusstudios.zerobit.entity.ai.controllers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;
import com.squidtopusstudios.zerobit.entity.EntityStates;
import com.squidtopusstudios.zerobit.entity.ai.SteeringEntity;
import com.squidtopusstudios.zerobit.entity.ai.behaviours.Patrol2D;
import com.squidtopusstudios.zerobit.entity.ai.behaviours.RadiusProximity;
import com.squidtopusstudios.zerobit.entity.components.Box2DComponent;


/**
 * Controller for basic Enemies
 */
public class EnemyController extends AIController implements ProximityCallback {

    private SteeringEntity steerable;
    private RadiusProximity proximityBehaviour;
    private float defaultSpeed;
    private float patrolSpeed;
    private float chaseSpeed;


    public EnemyController(Entity entity, float patrolMinX, float patrolMaxX) {
        super(entity);
        steerable = new SteeringEntity(entity);
        steerable.setBoundaries(patrolMinX, patrolMaxX);
        steerable.setSteeringBehavior(new Patrol2D(steerable));

        defaultSpeed = steerable.getMaxLinearSpeed();
        patrolSpeed = defaultSpeed / 1.3f;
        chaseSpeed = defaultSpeed / 1.1f;
        steerable.setMaxLinearSpeed(patrolSpeed);

        ((Box2DUserData)entity.getComponent(Box2DComponent.class).body.getUserData()).steeringEntity = steerable;

        proximityBehaviour = new RadiusProximity(steerable, steerable.getBody().getWorld(), 6);
    }

    @Override
    public void update(float deltaTime) {
        steerable.update(deltaTime);
        proximityBehaviour.findNeighborBodies(this);
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        /*renderer.circle(((Patrol2D) steerable.getSteeringBehavior()).getInternalTargetPosition(),
                steerable.getPosition().y, 0.2f);*/
        renderer.circle(steerable.getPosition().x, steerable.getPosition().y, proximityBehaviour.getDetectionRadius());
        //renderer.rect(steerable.getPosition().x - proximityBehaviour.getDetectionRadius(), steerable.getPosition().y-proximityBehaviour.getDetectionRadius(), proximityBehaviour.getDetectionRadius()*2, proximityBehaviour.getDetectionRadius()*2);
    }

    @Override
    public void reset() {
        proximityBehaviour = new RadiusProximity(steerable, steerable.getBody().getWorld(), 6);
        steerable.setSteeringBehavior(new Patrol2D(steerable));
        steerable.setMaxLinearSpeed(patrolSpeed);
    }

    public SteeringEntity getSteerable() {
        return steerable;
    }

    @Override
    public boolean reportNeighbourBody(Body neighbor) {
        if (((Box2DUserData)neighbor.getUserData()).id.equals("")) { //TODO: Check from target array
            if (!(steerable.getSteeringBehavior() instanceof Seek)) {
                steerable.setSteeringBehavior(new Seek<Vector2>(steerable, new SteeringEntity(neighbor, 1f)));
                steerable.setMaxLinearSpeed(chaseSpeed);
            }
            else if (((SteeringEntity)((Seek)steerable.getSteeringBehavior()).getTarget()).getBody() != neighbor) {
                steerable.setSteeringBehavior(new Seek<Vector2>(steerable, new SteeringEntity(neighbor, 1f)));
                steerable.setMaxLinearSpeed(chaseSpeed);
            }

            // Attack!
            ((Box2DUserData)steerable.getBody().getUserData()).stateComponent.state = EntityStates.ATTACKING_RANGED;

            return true;
        }
        return false;
    }
}
