package com.squidtopusstudios.zerobitengine.refact.entity.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Convenience super class for Systems that should process Entities with certain components. Extends Ashley's IteratingSystem
 */
public abstract class ZbeIteratingSystem extends IteratingSystem {


    public ZbeIteratingSystem(Family family) {
        super(family);
    }

    @SafeVarargs
    public ZbeIteratingSystem(Class<? extends Component>... componentTypes) {
        this(Family.all(componentTypes).get());
    }
}
