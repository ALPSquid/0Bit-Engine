package com.squidtopusstudios.zerobitengine.core;

import com.squidtopusstudios.zerobitengine.core.subsystems.EntityManager;
import com.squidtopusstudios.zerobitengine.core.subsystems.RenderManager;
import com.squidtopusstudios.zerobitengine.core.subsystems.ResourceManager;
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
     * Called on instance creation. Just to stop slow downs as ZeroBitGame calls getInstance() on creation
     */
    private void initManagers() {
        entityManager();
        renderManager();
        resourceManager();
    }

    public EntityManager entityManager() {
        return EntityManager.getInstance();
    }

    public RenderManager renderManager() {
        return RenderManager.getInstance();
    }

    public ResourceManager resourceManager() {
        return ResourceManager.getInstance();
    }

    @Override
    public void update(float deltaTime) {
        entityManager().update(deltaTime);
        renderManager().update(deltaTime);
        resourceManager().update(deltaTime);
    }

    @Override
    public void dispose() {
        entityManager().dispose();
        renderManager().dispose();
        resourceManager().dispose();
    }
}
