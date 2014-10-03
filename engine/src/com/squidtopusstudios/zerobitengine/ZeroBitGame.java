package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.squidtopusstudios.zerobitengine.core.Managers;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Main game and engine class. Your main class should extend this.
 */
public class ZeroBitGame extends Game {

    private boolean initialised = false;
    protected World world;

    /**
     * Called when the class is initialised. Make sure to call super.create() when extending this class
     */
    @Override
    public void create() {
        ZeroBit.setLogLevel(ZeroBit.LOG_DEBUG);
        ZeroBit.logger.logDebug("Initialising");
        initialised = true;
        ZeroBit.width = Gdx.graphics.getWidth();
        ZeroBit.height = Gdx.graphics.getHeight();
        ZeroBit.setGame(this);
        ZeroBit.initManagers();
    }

    /**
     * Effectively the game loop
     */
    @Override
    public void render() {
        super.render();
        ZeroBit.managers.update(Gdx.graphics.getDeltaTime());
    }

    /**
     * Called at game end
     */
    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Exiting Game");
        ZeroBit.managers.dispose();
    }

    public boolean isInitialised() {
        return initialised;
    }

}
