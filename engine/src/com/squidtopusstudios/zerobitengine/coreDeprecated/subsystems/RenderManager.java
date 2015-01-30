package com.squidtopusstudios.zerobitengine.coreDeprecated.subsystems;

import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.graphics.DebugOverlay;
import com.squidtopusstudios.zerobitengine.graphics.DefaultRenderer;

/**
 * Manages rendering (surprise surprise)
 */
public class RenderManager {

    private static RenderManager renderManagerInstance;
    private DefaultRenderer renderer;
    private DebugOverlay debugOverlay;


    private RenderManager() {}

    public static RenderManager getInstance() {
        if (renderManagerInstance == null) {
            renderManagerInstance = new RenderManager();
            renderManagerInstance.setRenderer(new DefaultRenderer());
            renderManagerInstance.renderer.create();
            renderManagerInstance.debugOverlay = new DebugOverlay();
            renderManagerInstance.debugOverlay.create();
        }
        return renderManagerInstance;
    }

    /**
     * Change the game renderer. An instance of {@link DefaultRenderer} is set by default (only use this if you're using
     * a custom renderer).
     * @param renderer Current game renderer
     */
    public <T extends DefaultRenderer> void setRenderer(T renderer) {
        this.renderer = renderer;
    }

    public DefaultRenderer getRenderer() {
        return renderer;
    }

    public DebugOverlay getDebugOverlay() {
        return debugOverlay;
    }

    public void update() {
        if (ZeroBit.isWorldSet()) {
            renderer.update();
        }
        if (ZeroBit.showDebugOverlay) {
            debugOverlay.update();
        }
    }

    public void resize(int width, int height) {
        renderer.resize(width, height);
        debugOverlay.resize(width, height);
    }

    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        renderer.dispose();
        debugOverlay.dispose();
    }
}
