package com.squidtopusstudios.zerobit.ui.controllers;


import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.screens.GameScreen;
import com.squidtopusstudios.zerobit.screens.ZBScreen;
import com.squidtopusstudios.zerobit.ui.views.UIView;

/**
 * Example/super class controller for any type of screen
 */
public class UniversalController extends UIController {


    public UniversalController(ZBScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void setView(UIView view) {
        super.setView(view);
    }

    /**
     * @return number of entities in the current world
     */
    public int getEntities() {
        try {
            if (((GameScreen) screen).getWorld() != null) {
                return ((GameScreen) screen).getWorld().getEntities().getEntityCount();
            }
        } catch (ClassCastException ex) {
            ZeroBit.logger.logError("Linked screen is not an instance of GameScreen");
        }
        return 0;
    }

    /**
     * Resumes or Pauses the game depending on current pause state
     */
    public void togglePause() {
        if (!screen.isPaused()) screen.pause();
        else screen.resume();
    }

    /**
     * Dopes what it says on the tin
     */
    public void exitGame() {
        screen.getGame().exit();
    }
}
