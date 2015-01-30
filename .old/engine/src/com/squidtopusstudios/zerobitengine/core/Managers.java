package com.squidtopusstudios.zerobitengine.core;

import com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager;
import com.squidtopusstudios.zerobitengine.core.subsystems.RenderManager;
import com.squidtopusstudios.zerobitengine.core.subsystems.ResourceManager;
import com.squidtopusstudios.zerobitengine.core.subsystems.ScreenManager;
import com.squidtopusstudios.zerobitengine.utils.IActiveClass;

/**
 * Singleton - Provides easy access to subsystem managers (A manager for managers... so meta)
 */
public class Managers implements IActiveClass {

    private static Managers managersInstance;


    private Managers() {}

    public static Managers getInstance() {
        if (managersInstance == null) {
            managersInstance = new Managers();
            managersInstance.initManagers();
        }
        return managersInstance;
    }

    /**
     * Called on instance creation to stop slow downs during play as {@link com.squidtopusstudios.zerobitengine.ZeroBitGame} calls this.getInstance() on creation
     */
    private void initManagers() {
        resourceManager();
        renderManager();
        screenManager();
    }

    public EntityManager entityManager() {
        return EntityManager.getInstance(ZeroBit.getWorld());
    }

    public RenderManager renderManager() {
        return RenderManager.getInstance();
    }

    public ResourceManager resourceManager() {
        return ResourceManager.getInstance();
    }

    public ScreenManager screenManager() {
        return ScreenManager.getInstance();
    }

    @Override
    public void update() {
        if (ZeroBit.isWorldSet()) {
            entityManager().update();
        }
        screenManager().update();
        resourceManager().update();
        renderManager().update();
    }

    public void resize(int width, int height) {
        renderManager().resize(width, height);
    }

    @Override
    public void dispose() {
        if (entityManager() != null) {
            entityManager().dispose();
        }
        renderManager().dispose();
        screenManager().dispose();
        resourceManager().dispose();
    }
}
