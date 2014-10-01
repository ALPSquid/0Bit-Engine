package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.Game;
import com.squidtopusstudios.zerobitengine.core.Managers;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Main game and engine class. Your main class should extend this.
 */
public class ZeroBitGame extends Game {

    private boolean initialised = false;
    protected Managers managers;
    protected World world;

    /**
     * Called when the class is initialised. Make sure to call super when overriding.
     */
    @Override
    public void create() {
        Logger.setLogLevel(Logger.LOG_DEBUG);
        Logger.getInstance().logDebug("Initialising");
        initialised = true;
        this.managers = Managers.getInstance();
        ZeroBit.setGame(this);
    }

    /**
     * Effectively the game loop
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Called at game end
     */
    @Override
    public void dispose() {
        Logger.getInstance().logInfo("Exiting Game");
        world.dispose();
        managers.dispose();
    }

    /**
     * Sets the current World instance
     * @param world World instance to set
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Returns the current World instance or throws an error if one hasn't been set
     * @return Current World instance
     */
    public World getWorld() {
        if (world != null) {
            return world;
        } else {
            Logger.getInstance().logError("World instance has not been set! Use ZeroBitGame.setWorld(world);");
            return null;
        }
    }

    public boolean isInitialised() {
        return initialised;
    }

}
