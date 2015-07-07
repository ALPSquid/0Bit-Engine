package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;
import com.squidtopusstudios.zerobit.entity.EntityStates;
import com.squidtopusstudios.zerobit.entity.components.Box2DComponent;
import com.squidtopusstudios.zerobit.entity.components.MovementComponent;
import com.squidtopusstudios.zerobit.entity.components.StateComponent;

/**
 * Handles movement via flags. TODO: Support non-Box2D objects and design state system
 */
public class MovementSystem extends IteratingSystem {

    protected ComponentMapper<MovementComponent> mvm = ComponentMapper.getFor(MovementComponent.class);
    protected ComponentMapper<Box2DComponent> b2dm = ComponentMapper.getFor(Box2DComponent.class);
    protected ComponentMapper<StateComponent> stm = ComponentMapper.getFor(StateComponent.class);
    protected MovementComponent mvc;
    protected Box2DComponent b2dc;
    protected StateComponent stc;


    public MovementSystem() {
        super(Family.all(MovementComponent.class, Box2DComponent.class, StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        mvc = mvm.get(entity);
        b2dc = b2dm.get(entity);
        stc = stm.get(entity);

        if (mvc.stop) {
            resetSpeed(true, false);
            mvc.stop = false;
            //stc.state = (stc.state != EntityStates.IDLE)? stc.state & EntityStates.IDLE : EntityStates.IDLE;
            stc.state = EntityStates.IDLE;
        }

        if (mvc.right) {
            if (b2dc.body.getLinearVelocity().x < mvc.speed) {
                b2dc.body.applyLinearImpulse(mvc.speed / 2, ((stc.onGround)? -1f : 0),
                        b2dc.body.getWorldCenter().x, b2dc.body.getWorldCenter().y, true);
            }

            //stc.state = (stc.state != EntityStates.MOVING)? stc.state & EntityStates.MOVING : EntityStates.MOVING;
            stc.state = EntityStates.MOVING;
        }
        else if (mvc.left) {
            if (b2dc.body.getLinearVelocity().x > -mvc.speed) {
                b2dc.body.applyLinearImpulse(-mvc.speed / 2, ((stc.onGround)? -1f : 0),
                        b2dc.body.getWorldCenter().x, b2dc.body.getWorldCenter().y, true);
            }
            //stc.state = (stc.state != EntityStates.MOVING)? stc.state & EntityStates.MOVING : EntityStates.MOVING;
            stc.state = EntityStates.MOVING;
        }

        if (mvc.up) {
            if (b2dc.body.getLinearVelocity().y < mvc.speed) {
                b2dc.body.applyLinearImpulse(0, mvc.speed / 2,
                        b2dc.body.getWorldCenter().x, b2dc.body.getWorldCenter().y, true);
            }
            //stc.state = (stc.state != EntityStates.MOVING)? stc.state & EntityStates.MOVING : EntityStates.MOVING;
            stc.state = EntityStates.MOVING;
        }
        else if (mvc.down) {
            if (b2dc.body.getLinearVelocity().y > -mvc.speed) {
                b2dc.body.applyLinearImpulse(0, -mvc.speed / 2,
                        b2dc.body.getWorldCenter().x, b2dc.body.getWorldCenter().y, true);
            }
            //stc.state = (stc.state != EntityStates.MOVING)? stc.state & EntityStates.MOVING : EntityStates.MOVING;
            stc.state = EntityStates.MOVING;
        }

        if (mvc.jump) {
            if (stc.onGround) b2dc.body.applyLinearImpulse(0, mvc.jumpForce, b2dc.body.getWorldCenter().x, b2dc.body.getWorldCenter().y, true);
            mvc.jump = false;
        }
    }

    protected void resetSpeed(boolean x, boolean y) {
        b2dc.body.setLinearVelocity(
                (x)? 0 : b2dc.body.getLinearVelocity().x,
                (y)? 0 : b2dc.body.getLinearVelocity().y
        );
    }

    protected void resetSpeed() {
        resetSpeed(true, true);
    }

}
