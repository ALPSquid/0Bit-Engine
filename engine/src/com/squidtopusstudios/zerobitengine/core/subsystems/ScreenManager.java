package com.squidtopusstudios.zerobitengine.core.subsystems;


import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.screens.ZbeScreen;
import com.squidtopusstudios.zerobitengine.utils.IManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Main resource manager for the engine. Handles registering, retrieving and disposing of physical resources.
 * Basically a wrapper/handler for the LibGDX built in AssetManager
 */
public class ScreenManager implements IManager {

    private static ScreenManager screenManagerInstance;
    private Map<String, ZbeScreen> registeredScreens = new HashMap<String, ZbeScreen>();
    private String prevScreen;
    private String currentScreen;

    private ScreenManager() {}

    public static ScreenManager getInstance() {
        if (screenManagerInstance == null) {
            screenManagerInstance = new ScreenManager();
        }
        return screenManagerInstance;
    }

    /**
     * Gets the requested screen and sets it as the current screen
     * @param screen Name of the registered screen
     */
    public void setScreen(String screen) {
        if (registeredScreens.containsKey(screen)) {
            prevScreen = currentScreen;
            currentScreen = screen;
            ZeroBit.getGame().setScreen(registeredScreens.get(screen));
        } else {
            ZeroBit.logger.logError("Screen does not exist! Use ScreenManager.addScreen()", new NullPointerException());
        }
    }

    /**
     * Register a screen with the manager for use later
     * @param ID name/ID for the screen (needed to retrieve it later)
     * @param screen the ZbeScreen instance
     */
    public void addScreen(String ID, ZbeScreen screen) {
        screen.create();
        registeredScreens.put(ID, screen);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        for (ZbeScreen screen : registeredScreens.values()) {
            try {
                screen.dispose();
            } catch (NullPointerException ex) {
                ZeroBit.logger.logError("NullPointer disposing screen: " + screen + " Trace: " + ex.getCause());
            }
        }
        registeredScreens.clear();
    }

    /**
     * Get currently set ZbeScreen instance
     * @return currently set ZbeScreen instance
     */
    public ZbeScreen getScreen() {
        return (ZbeScreen)ZeroBit.getGame().getScreen();
    }

    /**
     * Returns the ID of the previously set screen
     * @return ID/Name of the previous screen
     */
    public String getPrevScreenID() {
        return prevScreen;
    }

    /**
     * Returns the ID of the currently set screen
     * @return ID/Name of the current screen
     */
    public String getCurrentScreenID() {
        return currentScreen;
    }
}
