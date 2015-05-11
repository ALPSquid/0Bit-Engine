package com.squidtopusstudios.zerobit.entity.components.ai;

import com.squidtopusstudios.zerobit.entity.components.ZBComponent;
import com.squidtopusstudios.zerobit.entity.ai.controllers.AIController;

/**
 * Component for AI entities that holds behaviour data
 */
public class BehaviourComponent extends ZBComponent {

    public AIController controller;

    @Override
    public void reset() {
        controller.reset();
    }
}
