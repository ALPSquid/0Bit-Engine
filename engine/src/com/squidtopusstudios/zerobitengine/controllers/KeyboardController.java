package com.squidtopusstudios.zerobitengine.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default Input Processor. Keeps a record of all currently pressed keys.
 */
public class KeyboardController implements InputProcessor {

    protected List<Integer> pressedKeys = new ArrayList();
    protected Map<String, Integer[]> keyBindings = new HashMap<String, Integer[]>();


    public void setKeyMap(String mapName, Integer... keys) {
        keyBindings.put(mapName, keys);
    }

    public boolean isKeyPressed(String mapName) {
        for (int keycode : keyBindings.get(mapName)) {
            if (pressedKeys.contains(keycode)) {
                return true;
            }
        }
        return false;
    }

    public boolean isKeyPressed(int keycode) {
        return pressedKeys.contains(keycode);
    }

    public boolean isKeyJustPressed(String mapName) {
        for (int keycode : keyBindings.get(mapName)) {
            if (Gdx.input.isKeyJustPressed(keycode)) {
                return true;
            }
        }
        return false;
    }

    public boolean isKeyJustPressed(int keycode) {
        return Gdx.input.isKeyJustPressed(keycode);
    }

    @Override
    public boolean keyDown(int keycode) {
        pressedKeys.add(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        pressedKeys.remove(Integer.valueOf(keycode));
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
