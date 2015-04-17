package com.squidtopusstudios.zerobitengine.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.squidtopusstudios.zerobitengine.entity.components.*;

import java.util.HashMap;

/**
 * Manages Ashley components
 */
public class ComponentMappers {
    public static final ComponentMapper<ZbeEntityComponent> metadata = ComponentMapper.getFor(ZbeEntityComponent.class);
    public static final ComponentMapper<ResourceComponent> resource = ComponentMapper.getFor(ResourceComponent.class);
    public static final ComponentMapper<SpriteAnimationComponent> animation = ComponentMapper.getFor(SpriteAnimationComponent.class);
    public static final ComponentMapper<BoundsComponent> bounds = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<SimplePositionComponent> position = ComponentMapper.getFor(SimplePositionComponent.class);
    public static final ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);
    public static final ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);

}