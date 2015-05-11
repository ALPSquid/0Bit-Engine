package com.squidtopusstudios.zerobit.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.input.keybindings.GameActions;

/**
 * Input Processor for Kbd & Mouse and Controllers that translates input to mapped events
 * TODO: Provide a way to customise the behaviour of this class
 */
public class InputMapper extends ZBInput {

    private float axisValue;
    private String axisName;
    private int action;
    private float deadzone;


    public InputMapper() {
        // Register with controllers
        Controllers.addListener(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (ZeroBit.keyMaps.getKeyMap().get("btn-"+keycode) != null) {
            notifyObservers(ZeroBit.keyMaps.getKeyMap().get("btn-"+keycode).intValue());
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (ZeroBit.keyMaps.getKeyMap().get("btn-"+button) != null) {
            notifyObservers(ZeroBit.keyMaps.getKeyMap().get("btn-"+button).intValue());
        }
        return true;
    }

    /**
     * XBox 360 & One: Joysticks, Triggers
     */
    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        value = controller.getAxis(axisCode);

        axisName = "axis-" + axisCode + ((value > 0)? "+" : "-");
        action = -2;
        if (ZeroBit.keyMaps.getControllerMap().get(axisName) != null) {
            action = ZeroBit.keyMaps.getControllerMap().get(axisName).intValue();
        }
        deadzone = ZeroBit.keyMaps.getControllerMap().get("deadzone");

        if ((value > deadzone || value < -deadzone) && action != -2) {
            notifyObservers(action);
        }
        return true;
    }

    /**
     * XBox 360 & One: D-Pad
     */
    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        if (ZeroBit.keyMaps.getControllerMap().get("pov-"+value.ordinal()) != null) {
            notifyObservers(ZeroBit.keyMaps.getControllerMap().get("pov-" + value.ordinal()).intValue());
        }
        return true;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (ZeroBit.keyMaps.getControllerMap().get("btn-"+buttonCode) != null) {
            notifyObservers(ZeroBit.keyMaps.getControllerMap().get("btn-"+buttonCode).intValue());
        }
        return true;
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
    }
}
