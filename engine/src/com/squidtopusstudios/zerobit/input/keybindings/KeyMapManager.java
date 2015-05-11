package com.squidtopusstudios.zerobit.input.keybindings;


import com.squidtopusstudios.zerobit.ZeroBit;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages saving, loading and binding of keys
 */
public class KeyMapManager {

    /** Map for Keyboard & Mouse */
    private Map<String, Float> keyMap;
    /** Map for a Controller */
    private Map<String, Float> controllerMap;



    /**
     * Loads a keyboard & mouse map from a file
     * @param localPath local path to the map file
     */
    @SuppressWarnings("unchecked")
    public void loadKeyMap(String localPath) {
        keyMap = ZeroBit.saveManager.load(HashMap.class, localPath);
    }

    /**
     * Loads a controller map from a file
     * @param localPath local path to the map file
     */
    @SuppressWarnings("unchecked")
    public void loadControllerMap(String localPath) {
        controllerMap = ZeroBit.saveManager.load(HashMap.class, localPath);
    }

    /**
     * @return the current keyboard & mouse map
     */
    public Map<String, Float> getKeyMap() {
        return keyMap;
    }

    /**
     * @return the current controller map
     */
    public Map<String, Float> getControllerMap() {
        return controllerMap;
    }

}
