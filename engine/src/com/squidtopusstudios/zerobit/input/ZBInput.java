package com.squidtopusstudios.zerobit.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.squidtopusstudios.zerobit.util.observers.InputObservable;
import com.squidtopusstudios.zerobit.util.observers.InputObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements InputProcessor and ControllerListener for de-cluttering subclasses and implements {@link InputObservable} for dispatching translated events
 */
public class ZBInput implements InputProcessor, ControllerListener, InputObservable {

    private List<InputObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(InputObserver o) {
        if (!observers.contains(o)) observers.add(o);
    }

    @Override
    public void removeObserver(InputObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(int event) {
        for (InputObserver observer : observers) {
            observer.inputEvent(event);
        }
    }

    // Controller Stuff
    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    // ---
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
