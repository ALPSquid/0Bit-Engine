package com.squidtopusstudios.zerobit.ui.views;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.squidtopusstudios.zerobit.input.keybindings.GameActions;
import com.squidtopusstudios.zerobit.ui.controllers.GameMenuController;
import com.squidtopusstudios.zerobit.util.observers.InputObserver;
import com.squidtopusstudios.zerobit.util.observers.Observable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Example/super class Game Menu View for in-game menus
 * TODO: Move to view examples
 */
public class GameMenuView extends UIView implements InputObserver {

    private GameMenuController controller;
    private ArrayList<Label> buttons = new ArrayList<Label>();


    public GameMenuView(GameMenuController controller) {
        super(controller);
        this.controller = controller;
    }

    public void loadUI() {
        setVisible(false);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void update(Observable obs, Map<String, Object> data) {
    }

    @Override
    public void inputEvent(int event) {
        switch (event) {
            case GameActions.GAME_MENU:
                controller.togglePause();
                setVisible(!isVisible());
                break;
        }
    }
}
