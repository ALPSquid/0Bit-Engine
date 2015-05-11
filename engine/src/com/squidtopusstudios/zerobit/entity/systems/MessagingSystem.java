package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.entity.components.MessagingComponent;
import com.squidtopusstudios.zerobit.util.observers.Observer;

/**
 * Dispatches {@link MessagingComponent} messages
 */
public class MessagingSystem extends IteratingSystem {

    private ComponentMapper<MessagingComponent> msgm = ComponentMapper.getFor(MessagingComponent.class);
    private MessagingComponent msgc;


    public MessagingSystem() {
        super(Family.all(MessagingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        msgc = msgm.get(entity);
        for (Observer observer : msgc.observers) {
            if (!msgc.data.isEmpty()) observer.update(msgc, msgc.data);
        }
        msgc.data.clear();
    }


}
