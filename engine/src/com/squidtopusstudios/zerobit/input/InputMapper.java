package com.squidtopusstudios.zerobit.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;

/**
 * Input Processor for Kbd & Mouse and Controllers that translates input to mapped events
 */
public class InputMapper extends ZBInput {
    //TODO

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
        /*if (WKGame.keyMaps.getKeyMap().get("btn-"+keycode) != null) {
            notifyObservers(WKGame.keyMaps.getKeyMap().get("btn-"+keycode).intValue());
        }

        // Debugging Keys
        if (keycode == WKGame.DEBUG_KEY) {
            WKGame.debugEnabled = !WKGame.debugEnabled;
        }
        else if (keycode == Input.Keys.RIGHT_BRACKET) {
            notifyObservers(GameActions.DAMAGE);
        } else if (keycode == Input.Keys.LEFT_BRACKET) {
            notifyObservers(GameActions.HEAL);
        }
        else if (keycode == Input.Keys.EQUALS) {
            notifyObservers(GameActions.XP_GAIN);
        }*/
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*if (WKGame.keyMaps.getKeyMap().get("btn-"+keycode) != null &&
                (WKGame.keyMaps.getKeyMap().get("btn-"+keycode).intValue() == GameActions.PLAYER_LEFT || WKGame.keyMaps.getKeyMap().get("btn-"+keycode).intValue() == GameActions.PLAYER_RIGHT)) {
            notifyObservers(GameActions.PLAYER_MOVE_NONE);
        }*/
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*WKGame.logger.logDebug(button + " pressed");
        if (WKGame.keyMaps.getKeyMap().get("btn-"+button) != null) {
            notifyObservers(WKGame.keyMaps.getKeyMap().get("btn-"+button).intValue());
        }*/
        return true;
    }

    /**
     * XBox 360 & One: Joysticks, Triggers
     */
    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        /*value = controller.getAxis(axisCode);
        //WKGame.logger.logDebug(controller.getName() + " axis " + axisCode + " at " + value);

        axisName = "axis-" + axisCode + ((value > 0)? "+" : "-");
        action = -2;
        if (WKGame.keyMaps.getControllerMap().get(axisName) != null) {
            action = WKGame.keyMaps.getControllerMap().get(axisName).intValue();
        }
        deadzone = WKGame.keyMaps.getControllerMap().get("deadzone");

        if ((value > deadzone || value < -deadzone) && action != -2) {
            notifyObservers(action);
        } else if (action == GameActions.PLAYER_LEFT || action == GameActions.PLAYER_RIGHT) {
            notifyObservers(GameActions.PLAYER_MOVE_NONE);
        }*/
        return true;
    }

    /**
     * XBox 360 & One: D-Pad
     */
    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        //WKGame.logger.logDebug(controller.getName() + ", moved pov: " + povCode + " to " + value);

       /* if (WKGame.keyMaps.getControllerMap().get("pov-"+value.ordinal()) != null) {
            notifyObservers(WKGame.keyMaps.getControllerMap().get("pov-" + value.ordinal()).intValue());
        }*/
        return true;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        //WKGame.logger.logDebug(controller.getName() + ", pressed: " + buttonCode);

        /*if (WKGame.keyMaps.getControllerMap().get("btn-"+buttonCode) != null) {
            notifyObservers(WKGame.keyMaps.getControllerMap().get("btn-"+buttonCode).intValue());
        }*/
        return true;
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
    }
}
