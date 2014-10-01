package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.squidtopusstudios.zerobitengine.utils.IManager;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Manages rendering (surprise surprise)
 */
public class RenderManager implements IManager {

    private static RenderManager renderManagerInstance;


    private RenderManager() {}

    public static RenderManager getInstance() {
        if (renderManagerInstance == null) {
            renderManagerInstance = new RenderManager();
        }
        return renderManagerInstance;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        Logger.getInstance().logInfo("Disposing");
    }
}
