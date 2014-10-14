package com.squidtopusstudios.zerobitengine;

import com.badlogic.gdx.*;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.utils.ZbeInputProcessor;

/**
 * Main game and engine class. Your main class should extend this.
 */
public class ZeroBitGame extends Game {

    private boolean initialised = false;
    private String appVersion;
    private int targetWidth;
    private int targetHeight;
    private boolean fixedTimeStep;

    public ZeroBitGame(String appVersion, int targetWidth, int targetHeight, boolean fixedTimeStep) {
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
        initialised = true;
        ZeroBit.setInputMultiplexer(new InputMultiplexer());
        ZeroBit.setGlobalInputProcessor(new ZbeInputProcessor());
        ZeroBit.setGame(appVersion, this, targetWidth, targetHeight, fixedTimeStep);
        ZeroBit.initManagers();
    }

    /**
     * Effectively the game loop
     */
    @Override
    public void render() {
        super.render();
        ZeroBit.managers.update();
        if (ZeroBit.input().isKeyPressed(Input.Keys.SHIFT_LEFT) && ZeroBit.input().isKeyJustPressed(ZeroBit.debugKey)) {
            ZeroBit.toggleDebugRenderer();
        } else if (!ZeroBit.input().isKeyPressed(Input.Keys.SHIFT_LEFT) && ZeroBit.input().isKeyJustPressed(ZeroBit.debugKey)) {
            ZeroBit.toggleDebugOverlay();
        }
    }

    /**
     * Called at game end
     */
    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Exiting Game");
        ZeroBit.managers.dispose();
    }

    @Override
    public void resize(int width, int height) {
        ZeroBit.managers.resize(width, height);
    }

    public boolean isInitialised() {
        return initialised;
    }

}
