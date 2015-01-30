package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.Game;

/**
 * Main game and engine class. Your main class should extend this.
 */
public class ZerobitGame extends Game {

    private String appVersion;
    private int targetWidth;
    private int targetHeight;
    private boolean fixedTimeStep;




    public ZerobitGame(String appVersion, int targetWidth, int targetHeight, boolean fixedTimeStep) {
        this.appVersion = appVersion;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        this.fixedTimeStep = fixedTimeStep;
    }

    /**
     * Called when the class is initialised. Make sure to call super.create() when extending this class
     */
    @Override
    public void create() {
        ZeroBit.logger.logDebug("Initialising");
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
        ZeroBit.logger.logDebug("Exiting Game");
    }

    @Override
    public void resize(int width, int height) {

    }
}
