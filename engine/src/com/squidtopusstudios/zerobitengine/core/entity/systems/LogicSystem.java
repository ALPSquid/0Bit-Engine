package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;

/**
 * Logic System for Ashley Entities. Register your ZbeEntityLogic class with this using ZbeEntity.setLogic(ZbeEntityLogic)
 */
public class LogicSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntityBase entity, float deltaTime) {
        entity.update(deltaTime);
    }
}
