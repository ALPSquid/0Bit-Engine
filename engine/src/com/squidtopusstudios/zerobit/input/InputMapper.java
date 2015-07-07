package com.squidtopusstudios.zerobit.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.input.keybindings.GameActions;

import java.util.Map;

/**
 * Input Processor for Kbd & Mouse and Controllers that translates input to mapped events.
 * Returns negative action values if the mapping was released (e.g If the player released A, negative PLAYER_LEFT would be returned
 * TODO: Provide a way to customise the behaviour of this class (simply extend and set in GameScreen?)
 */
public class InputMapper extends ZBInput {

    private float axisValue;
    private float lastAxisValue;
    private String axisName;
    private int action;
    private float deadzone;


    public InputMapper() {
        // Register with controllers
        Controllers.addListener(this);
    }

    /**
     * Notifies observers of the mapped value if the requested mapping exists in the keymap
     * @param mapping name of key mapping
     * @param controller whether to query the controller map
     * @param flip whether to flip the sign of the mapped value (used for key releases)
     */
    protected void notify(String mapping, boolean controller, boolean flip) {
        Map<String, Float> keyMap = (controller)? ZeroBit.keyMaps.getControllerMap() : ZeroBit.keyMaps.getKeyMap();
        if (keyMap.get(mapping) != null) {
            notifyObservers((flip)? -keyMap.get(mapping).intValue() : keyMap.get(mapping).intValue());
        }
    }
    private void notify(String mapping, boolean controller) {
        notify(mapping, controller, false);
    }

    @Override
    public boolean keyDown(int keycode) {
        notify("btn-"+keycode, false);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        notify("btn-"+keycode, false, true);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        notify("btn-"+button, false);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        notify("btn-"+button, false, true);
        return true;
    }

    /**
     * XBox 360 & One: Joysticks, Triggers
     */
    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        //axisValue = controller.getAxis(axisCode);
        axisValue = value;

        axisName = "axis-" + axisCode + ((axisValue > 0)? "+" : "-");
        deadzone = ZeroBit.keyMaps.getControllerMap().get("deadzone");
        if (axisValue > deadzone || axisValue < -deadzone) {
            notify(axisName, true);
        } else if (lastAxisValue < deadzone || lastAxisValue > -deadzone) {
            // Axis returned to center
            notify(axisName, true, true);
        }
        lastAxisValue = axisValue;
        return true;
    }

    /**
     * XBox 360 & One: D-Pad
     */
    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        notify("pov-"+value.ordinal(), true);
        return true;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        notify("btn-"+buttonCode, true);
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        notify("btn-"+buttonCode, true, true);
        return true;
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
    }

    @Override
    public void disconnected(Controller controller) {
        controller.removeListener(this);
    }
}
