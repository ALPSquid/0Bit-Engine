package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.LogicComponent;
import com.squidtopusstudios.zerobitengine.core.entity.systems.ZbeSystem;

/**
 * Logic System for Ashley Entities. Register your ZbeEntityLogic class with this using ZbeEntity.setLogic(ZbeEntityLogic)
 */
public class LogicSystem extends ZbeSystem {

    @Override
    public void processEntity(ZbeEntity entity, float deltaTime) {
        if (entity.getComponent(LogicComponent.class).logic != null) {
            entity.getComponent(LogicComponent.class).logic.update(entity, deltaTime);
        }
    }
}
