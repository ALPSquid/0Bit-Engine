package com.squidtopusstudios.zerobitengine.refact.screens;

import com.squidtopusstudios.zerobitengine.refact.Zbe;
import com.squidtopusstudios.zerobitengine.refact.ZbeGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Screen Manager/Pooler, for easily caching and loading screens with automatic loading screen support
 */
public class ScreenManager {

    private final ZbeGame game;
    private Map<String, ZbeScreen> registeredScreens = new HashMap<String, ZbeScreen>();
    private String prevScreen;
    private String currentScreen;


    public ScreenManager(ZbeGame game) {
        this.game = game;
        // Default loading screen, users can overwrite this with their own
        addScreen("loading", new LoadingScreen(game));
    }

    /**
     * Changes the game screen to the requested screen
     * @param screen Name of the registered screen
     */
    public void setScreen(String screen) {
        game.setScreen(registeredScreens.get("loading"));
        if (registeredScreens.containsKey(screen)) {
            Zbe.logger.logDebug("Setting screen: " + screen);
            prevScreen = currentScreen;
            currentScreen = screen;
            game.setScreen(registeredScreens.get(screen));
        } else {
            Zbe.logger.logError("Screen does not exist! Use addScreen()", new NullPointerException());
        }
    }

    /**
     * Register and cache a screen for setting later
     * @param ID name/ID for the screen (needed to retrieve it later)
     * @param screen the ZbeScreen instance
     */
    public void addScreen(String ID, ZbeScreen screen) {
        registeredScreens.put(ID, screen);
    }

    public void dispose() {
        Zbe.logger.logDebug("Disposing");
        for (ZbeScreen screen : registeredScreens.values()) {
            try {
                screen.dispose();
            } catch (NullPointerException ex) {
                Zbe.logger.logError("NullPointer disposing screen: " + screen + "\nTrace: " + ex.getCause());
            }
        }
        registeredScreens.clear();
    }

    /**
     * @return currently set ZbeScreen instance
     */
    public ZbeScreen getScreen() {
        return (ZbeScreen)game.getScreen();
    }

    /**
     * @return ID/Name of the previous screen
     */
    public String getPrevScreenID() {
        return prevScreen;
    }

    /**
     * @return ID/Name of the current screen
     */
    public String getCurrentScreenID() {
        return currentScreen;
    }
}
