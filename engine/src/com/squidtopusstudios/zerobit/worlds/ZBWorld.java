package com.squidtopusstudios.zerobit.worlds;

import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.EntityManager;

/**
 * Holds a Box2D world read from an Overlap2D file. Each level should be a World and set in the GameScreen
 */
public abstract class ZBWorld {

    protected boolean loaded = false;
    protected EntityManager entities;


    public ZBWorld() {

    }

    /**
     * Call externally to load the world
     * @param callBack Optional load complete callback
     */
    public final void doLoad(Runnable callBack) {
        if (!loaded) {
            ZeroBit.logger.logDebug("Loading World");
            entities = new EntityManager();

            // load subclass
            load(callBack);
            loaded = true;
        } else {
            if (callBack != null) callBack.run();
        }
    }
    public final void doLoad() {
        doLoad(null);
    }

    /**
     * Add engine systems and load assets and entities here. Will only run once during the world's life cycle. <br/>
     * Asset.manager.finishloading() is called automatically
     * @param callBack Optional load complete callback
     */
    public abstract void load(final Runnable callBack);

    public void update(float delta) {
        entities.update(delta);
    }

    public void dispose() {
        if (entities != null) entities.dispose();
        loaded = false;
    }

    /** Resets all entity components */
    public void reset() {
        entities.reset();
    }

    public EntityManager getEntities() {
        return entities;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
