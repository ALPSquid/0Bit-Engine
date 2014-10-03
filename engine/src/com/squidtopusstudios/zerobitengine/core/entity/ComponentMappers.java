package com.squidtopusstudios.zerobitengine.core.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.squidtopusstudios.zerobitengine.core.entity.components.BoundsComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.ResourceComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.ZbeEntityComponent;

/**
 * Manages Ashley components
 */
public class ComponentMappers {
    public static final ComponentMapper<ZbeEntityComponent> metadata = ComponentMapper.getFor(ZbeEntityComponent.class);
    public static final ComponentMapper<ResourceComponent> resource = ComponentMapper.getFor(ResourceComponent.class);
    public static final ComponentMapper<BoundsComponent> bounds = ComponentMapper.getFor(BoundsComponent.class);
}
