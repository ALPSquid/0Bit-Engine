package com.squidtopusstudios.zerobitengine.refact.util.controllers;


import com.squidtopusstudios.zerobitengine.refact.screens.ZbeScreen;
import com.squidtopusstudios.zerobitengine.refact.util.View;


/**
 * Suggested base for User Interface Controllers, regardless of whether the view is a fancy GUI or a humble text based system
 */
public abstract class UIController implements Controller {

    protected View view;
    protected ZbeScreen screen;


    public UIController(ZbeScreen screen) {
        this.screen = screen;
    }

    /** Call super then, if your view is an observer, register it with an observable: observable.addObserver(view) */
    @Override
    public void setView(View view) {
        this.view = view;
    }
}
