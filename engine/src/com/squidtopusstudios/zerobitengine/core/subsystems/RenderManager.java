package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.graphics.DefaultRenderer;
import com.squidtopusstudios.zerobitengine.core.graphics.IRenderer;
import com.squidtopusstudios.zerobitengine.utils.IManager;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Manages rendering (surprise surprise)
 */
public class RenderManager implements IManager {

    private static RenderManager renderManagerInstance;
    private IRenderer renderer;


    private RenderManager() {}

    public static RenderManager getInstance() {
        if (renderManagerInstance == null) {
            renderManagerInstance = new RenderManager();
            renderManagerInstance.setRenderer(new DefaultRenderer());
            renderManagerInstance.renderer.create();
        }
        return renderManagerInstance;
    }

    /**
     * Change the game renderer. An instance of {@link DefaultRenderer} is set by default (only use this if you're using
     * a custom renderer).
     * @param renderer Current game renderer
     */
    public void setRenderer(IRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        renderer.update(deltaTime);
    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
    }
}
