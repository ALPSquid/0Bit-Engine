package com.squidtopusstudios.zerobitengine.refact;


import com.badlogic.gdx.Game;
import com.squidtopusstudios.zerobitengine.refact.screens.ScreenManager;

/**
 * Main game and engine class. Your main class should extend this.
 */
public abstract class ZbeGame extends Game {

    private ScreenManager screenManager;

    public ZbeGame() {}

    /**
     * Make sure you call super.create()!
     */
    @Override
    public void create() {
        screenManager = new ScreenManager(this);
    }

    /**
     * Effectively the game loop
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * @return the game's ScreenManager instance for adding and changing screens
     */
    public ScreenManager getScreens() {
        return screenManager;
    }
}
