package com.squidtopusstudios.zerobit.util.comparators;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.squidtopusstudios.zerobit.entity.components.TransformComponent;

import java.util.Comparator;

/**
 * Compares two Entities by z-index, returning the biggest (one in-front)
 */
public class ZIndexComparator implements Comparator<Entity> {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    @Override
    public int compare(Entity entity1, Entity entity2) {
        TransformComponent tc1 = tm.get(entity1);
        TransformComponent tc2 = tm.get(entity2);

        return tc1.z - tc2.z;
    }
}
