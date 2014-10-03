package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;

/**
 * Meta System for Ashley Entities
 */
public class MetaSystem extends ZbeSystem {


    public void setName(Entity entity, String name) {
        ComponentMappers.metadata.get(entity).name = name;
    }

    public String getName(Entity entity) {
        return ComponentMappers.metadata.get(entity).name;
    }

}
