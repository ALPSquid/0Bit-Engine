package com.squidtopusstudios.zerobitengine.refact.ui.controllers;

import com.squidtopusstudios.zerobitengine.refact.ui.View;

/**
 * Engine MVC Setup:
 *      view -> Controller -> model
 *      view - observers (push or pull) -> model
 *
 * Your controller should manage adding and removing the View observer with the target model(s) (in setView) to ensure
 * the view doesn't have any unnecessary model references
 */
public interface Controller {

    /** If your view is an observer, you should register the view with target Observables here. Otherwise ignore */
    void setView(View view);
}
