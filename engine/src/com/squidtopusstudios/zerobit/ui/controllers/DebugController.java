package com.squidtopusstudios.zerobit.ui.controllers;

import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.screens.GameScreen;

/**
 * Controller for the DebugUI
 */
public class DebugController extends UIController {


    public DebugController(GameScreen screen) {
        super(screen);
        this.screen = screen;
    }

    /**
     * @return number of entities in the current world
     */
    public int getEntities() {
        if (((GameScreen)screen).getWorld() != null) {
            return ((GameScreen) screen).getWorld().getEntities().getEntityCount();
        } else {
            return 0;
        }
    }

    public boolean debugEnabled() {
        return ZeroBit.debugEnabled;
    }

    public void toggleDebug() {
         ZeroBit.debugEnabled = !ZeroBit.debugEnabled;
    }
}
