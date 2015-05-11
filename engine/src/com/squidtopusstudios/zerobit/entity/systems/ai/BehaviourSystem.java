package com.squidtopusstudios.zerobit.entity.systems.ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.entity.components.ai.BehaviourComponent;

/**
 * Manages AI behaviours
 */
public class BehaviourSystem extends IteratingSystem {

    private ComponentMapper<BehaviourComponent> bvm = ComponentMapper.getFor(BehaviourComponent.class);
    private BehaviourComponent bvc;

    public BehaviourSystem() {
        super(Family.all(BehaviourComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bvc = bvm.get(entity);
        bvc.controller.update(deltaTime);
    }
}
