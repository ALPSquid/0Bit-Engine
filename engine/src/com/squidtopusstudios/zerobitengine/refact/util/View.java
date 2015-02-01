package com.squidtopusstudios.zerobitengine.refact.util;

import com.squidtopusstudios.zerobitengine.refact.util.controllers.Controller;
import com.squidtopusstudios.zerobitengine.refact.util.Observer.Observer;

/**
 * Base class for things that display to the screen, interactively or not, such as a GUI or a Renderer
 */
public abstract class View implements Observer {

    /** Your controller that will translate requests to the model */
    protected Controller controller;


    public View(Controller controller) {
        this.controller = controller;
    }

}
