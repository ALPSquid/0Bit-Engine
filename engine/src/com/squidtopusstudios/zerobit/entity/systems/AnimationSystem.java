package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.entity.EntityStates;
import com.squidtopusstudios.zerobit.entity.components.*;

/**
 * Manages Entity Animations
 */
public class AnimationSystem extends IteratingSystem {

    private ComponentMapper<VisualComponent> vism = ComponentMapper.getFor(VisualComponent.class);
    private ComponentMapper<AnimationComponent> animm = ComponentMapper.getFor(AnimationComponent.class);
    private ComponentMapper<SpriterComponent> sprtm = ComponentMapper.getFor(SpriterComponent.class);
    private ComponentMapper<StateComponent> stm = ComponentMapper.getFor(StateComponent.class);
    private VisualComponent visc;
    private AnimationComponent animc;
    private SpriterComponent sprtc;
    private StateComponent stc;


    public AnimationSystem() {
        super(Family.all(VisualComponent.class).one(AnimationComponent.class, SpriterComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        visc = vism.get(entity);
        animc = animm.get(entity);
        sprtc = sprtm.get(entity);
        stc = stm.get(entity);

        // TODO: Implement as an enum component and just query that for names e.g. setAnim(Animations.JUMP)
        // TODO: Add bitwise operators for checking combined states
        String animName = null;
        switch (stc.state) {
            case EntityStates.ATTACKING_MELEE:
            case EntityStates.ATTACKING_RANGED:
                animName = null;
                //sprtc.setFirstAnim("attack", 0f);
                //sprtc.player.setBaseAnimation("idle");
                //sprtc.player.getSecondPlayer().setAnimation("idle");
                sprtc.player.getFirstPlayer().setAnimation("attack");
                //sprtc.player.baseBoneName = "bone_006";
                sprtc.player.setWeight(0f);
                break;
            case EntityStates.JUMPING:
                animName = "jump";
                break;
            case EntityStates.IDLE:
                animName = "idle";
                break;
            case EntityStates.MOVING:
                animName = "run";
                break;
        }
        if (!stc.onGround) {
            animName = "jump";
        }

        // Set the animation
        if (animc != null) {
            animc.currentAnim = animc.animations.get(animName);
            visc.sprite.setRegion(animc.currentAnim.getKeyFrame(animc.stateTime += deltaTime));
        }
        if (sprtc != null && animName != null) {
            sprtc.setSecondAnim(animName, 1f);
        }
    }
}
