package com.squidtopusstudios.zerobit.ui.controllers;

import com.squidtopusstudios.zerobit.screens.GameScreen;
import com.squidtopusstudios.zerobit.ui.views.GameMenuView;
import com.squidtopusstudios.zerobit.ui.views.UIView;

/**
 * Example/super class controller for the Game Menu
 * TODO: Move to controller examples
 */
public class GameMenuController extends UIController {


    public GameMenuController(GameScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void setView(UIView view) {
        super.setView(view);
        ((GameScreen)screen).getInputMapper().registerObserver((GameMenuView)view);
    }

    @Override
    public void resetView() {
        ((GameScreen)screen).getInputMapper().removeObserver((GameMenuView) view);
    }

    /**
     * Resumes or Pauses the game
     */
    public void togglePause() {
        if (!screen.isPaused()) screen.pause();
        else screen.resume();
    }
}
