package com.squidtopusstudios.zerobit.ui.controllers;

import com.badlogic.gdx.InputProcessor;
import com.squidtopusstudios.zerobit.screens.ZBScreen;
import com.squidtopusstudios.zerobit.ui.views.UIView;

/**
 * Suggested base for User Interface Controllers, regardless of whether the view is a fancy GUI or a humble text based system
 */
public class UIController implements Controller {

    protected UIView view;
    protected ZBScreen screen;


    public UIController(ZBScreen screen) {
        this.screen = screen;
    }

    /** If your view is an observer, remember to register it with an observable: observable.addObserver(view) */
    public void setView(UIView view) {
        this.view = view;
    }

    /** Reset the view. Remove observers etc. */
    public void resetView() {}

    public void update(float delta) {
        if (view != null) view.render(delta);
    }

    public void resize(int width, int height) {
        if (view != null) view.resize(width, height);
    }

    public void dispose() {
        if (view != null) view.dispose();
    }

    /**
     * Changes to the specified screen
     * @param screen Screen to change to
     */
    public void setScreen(String screen) {
        this.screen.setScreen(screen);
    }

    /**
     * Adds an InputProcessor to the {@link ZBScreen}'s InputMultiplexer
     * @param processor InputProcessor to add
     */
    public void addInputProcessor(InputProcessor processor) {
        // Add the UI input processor at the top of the stack
        screen.getInputMultiplexer().addProcessor(0, processor);
    }
}